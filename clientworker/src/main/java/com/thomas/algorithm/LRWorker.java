package com.thomas.algorithm;

import Jama.Matrix;
import com.thomas.algomodels.MlAlgoType;
import com.thomas.algomodels.MlAlgoWorker;
import com.thomas.algomodels.Properties;
import com.thomas.algomodels.SSPClientBuffer;
import com.thomas.thrift.server.Carrier;
import com.thomas.thrift.server.ParameterServerService;
import com.thomas.utils.DataInfo;
import com.thomas.utils.constant.NodeStatus;
import com.thomas.utils.constant.ParallelType;
import com.thomas.utils.math.MatrixUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.ArrayList;
import java.util.List;

import static com.thomas.utils.DataReader.getDataInfo;
import static com.thomas.utils.DataReader.getTrainData;

/**
 * Created by hadoop on 3/18/17.
 */
public class LRWorker extends MlAlgoWorker {
    private ParameterServerService.Client client;
    private SSPClientBuffer localStorage;

    private double lr;
    private int iteNum, m, n;
    private Matrix X, A, y;
    private String tableId;

    private Logger logger = Logger.getLogger(this.getClass());

    public LRWorker() {
        super();
    }

    public LRWorker(MlAlgoType mlAlgoType, Properties properties) {
        super(mlAlgoType, properties);

        // init the worker props.
        init();
    }

    @Override
    public void init() {
        try {

            iteNum = properties.iterationNum;
            lr = properties.learningRate;
            tableId = properties.PSTableId;
            hostId = "worker1";

            localStorage = new SSPClientBuffer(properties.rowNum, properties.dimems, properties.stale);

            /*
            * about Linear Regression Computation
            * */

            DataInfo dataInfo = getDataInfo(properties.dataPath);
            m = dataInfo.rowNum;
            n = dataInfo.colNum;

            A = getTrainData(properties.dataPath, m, n);

            int matrixRowDim = A.getRowDimension();
            int matrixColDim = A.getColumnDimension();

            // compute X matrix and do feature normalization.
            Matrix featureMat = A.getMatrix(0, matrixRowDim-1, 0, matrixColDim-2);
            boolean isRegu = false;
            Matrix tmpMat = featureMat;
            if (isRegu) tmpMat = MatrixUtils.featureNormalize(featureMat);

            X = new Matrix(matrixRowDim, matrixColDim, 1.0);
            X.setMatrix(0, matrixRowDim-1, 1, matrixColDim-1, tmpMat);
            y = A.getMatrix(0, matrixRowDim-1, new int[]{matrixColDim-1});

        } catch (Exception e) {
            logger.error("init failed");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        TTransport transport = null;
        try {
            transport = new TSocket(properties.PSServerId, properties.PSPort, 20000);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);

            client = new ParameterServerService.Client(protocol);

            long startTime = System.currentTimeMillis();
            if (properties.parallelType.equals(ParallelType.BSP)) lr();
            if (properties.parallelType.equals(ParallelType.SSP)) lrSSP();
            long endTime = System.currentTimeMillis();
            System.out.println("it costs: " + (endTime-startTime)/1000.0 + "s");

        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
            workerStatus = NodeStatus.FINISHED;
        }
    }

    public void read() throws Exception {
        Carrier carrier = null;
        while (localStorage.exceed()) {
            // check the consistency of global iteration in local and remote.
            carrier = client.check(hostId, tableId, localStorage.globalIter);
            if (carrier.iterationNum != -1) break;
            Thread.sleep(1);
        }

        if (carrier == null) carrier = client.check(hostId, tableId, localStorage.globalIter);
        if (carrier.iterationNum != -1) {
            // inconsistent case occured.
            logger.info("INCON-->local: " + localStorage.globalIter + " remote: " + carrier);
            // first we need to clean the out-of-dated parameters: [global: carrier.iteration]
            for (int i=localStorage.globalIter; i<carrier.iterationNum; i++) {
                localStorage.reset(i);
            }
            // replace the parameter.
            localStorage.globalIter = carrier.iterationNum;
            localStorage.replace(carrier);
        }

        /*// if exceed, wait for the other workers.
        while (localStorage.exceed()) {
            // check the consistency of global iteration in local and remote.
            Carrier carrier1 = client.check(hostId, tableId, localStorage.globalIter);
            if (carrier1.iterationNum != -1) {
                // inconsistent case occured.
                logger.info("inconsistent case --> local: " + localStorage.globalIter + " remote: " + carrier1);

                // first we need to clean the out-of-dated parameters: [global: carrier1.iteration]
                for (int i=localStorage.globalIter; i<carrier1.iterationNum; i++) {
                    localStorage.reset(i);
                }
                // replace the parameter.
                localStorage.globalIter = carrier1.iterationNum;
                localStorage.add(carrier1);
            }
            Thread.sleep(1);
        }*/
    }

    public void train() {
        // train it and store it in buffer.
        ArrayList<Double> params = localStorage.getParamWithLocalUpdate();
        if (params.size() == 0) {
            logger.error("parameter size error!");
        }

        // if global iter == local one, remember the update and prepare to update in the future.
        localStorage.globalDelta = getDeltaPrams(params);

        localStorage.add((ArrayList<Double>) localStorage.globalDelta);
    }

    public void update() throws TException {
        // only update the delta.
        List<Double> params = localStorage.getUpdate();

        // prepare the carrier.
        List<List<Double>> data = new ArrayList<List<Double>>();
        data.add(params);
        Carrier carrier = new Carrier(localStorage.localIter, data);

        logger.info("start update " + localStorage.localIter + " update: " + carrier);
        // update the parameter at most 3 times.
        int count = 0;
        while (client.update(hostId, tableId, carrier) == false) {
            count++;
            if (count == 3) break;
        }

        /*
        * TO DO LIST
        * */

        // if update failed. in some way, it does not matter.
        if (count == 3) {
            // maybe this is the straggler.
            if (localStorage.localIter == localStorage.globalIter) {
                logger.error("local iter == global iter: update failed");
            } else {
                logger.error("local iter == global iter: update failed");
            }
        }

        // finish updating to the remote, step into next iteration.
        localStorage.localIter++;
    }

    @Override
    public void clock(Carrier carrier) {}

    public void lrSSP() throws TException {
        try {
            for(int i=0; i<iteNum; i++) {
                read();

                assert i==localStorage.globalIter : "Errors Here!";

                train();
                update();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lr() throws TException {
        try {
            for (int i = 0; i < iteNum; i++) {
                Carrier carrier = client.read(hostId, tableId, i, 2);
                while (carrier.iterationNum == -1) {
                    Thread.sleep(1);
                    carrier = client.read(hostId, tableId, i, 2);
                }

                ArrayList<Double> params = getDeltaPrams((ArrayList<Double>) carrier.gradients.get(0));

                carrier.gradients.clear();
                carrier.gradients.add(params);
                while(client.update(hostId, tableId, carrier) == false) {
                    Thread.sleep(1);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Double> getDeltaPrams(ArrayList<Double> params) {
        double [][] thetaArray = new double[params.size()][1];
        for (int i=0; i<params.size(); i++) thetaArray[i][0] = params.get(i);
        Matrix theta = new Matrix(thetaArray);

        Matrix tmp = X.times(theta).minus(y);
        int rowDim = tmp.getRowDimension();

        double cost = tmp.transpose().times(tmp).get(0, 0)/(2.0*rowDim);
        logger.info("Iter " + localStorage.localIter + " the cost is: <" + cost + ">");

        double deltaArray[][] = X.transpose().times(tmp).times(lr/m*(-1.0)).getArray();
        ArrayList<Double> deltaList = new ArrayList<Double>(n);
        for (int i=0; i<n; i++) deltaList.add(deltaArray[i][0]);
        return deltaList;
    }
}
