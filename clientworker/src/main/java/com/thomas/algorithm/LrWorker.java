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
 * Created by hadoop on 3/11/17.
 */
public class LrWorker extends MlAlgoWorker {

    private ParameterServerService.Client client;
    private SSPClientBuffer localStorage;

    private double lr;
    private int iteNum, m, n;
    private Matrix X, A, y;
    private String tableId;

    private Logger logger = Logger.getLogger(this.getClass());

    public LrWorker() {
        super();
    }

    public LrWorker(MlAlgoType mlAlgoType, Properties properties) {
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
    /*
    * function READPARAMFROMSERVER(t)
        if t >= cur_iteration + stale_value then
          sleep until cur_iteration in server increased and get the newest parameters.
        end if
        if cur_iteration_parameter not in local then
          get it from the remote
        end if
        return cur_iteraion_parameter add sum of local_gradient_updates

      function PUSHTOSERVER(t)
        if t < cur_iteration + stale_value then
          update the gradients to server in ASYNC
        end if
    *
    * */

    public void read() throws Exception {
        if (localStorage.exceed()) {
            Thread.sleep(1);
        }
        if (localStorage.localIter == localStorage.globalIter) {
            Carrier carrier = client.read(hostId, tableId, localStorage.localIter, localStorage.stale);
            localStorage.replace(carrier);
        }
    }

    public void train() {
        // train it and store it in buffer.
        localStorage.set(getDeltaPrams(localStorage.getParamWithLocalUpdate()));
    }

    public void update() throws TException {
        ArrayList<Double> params = localStorage.getParamWithLocalUpdate();
        List<List<Double>> data = new ArrayList<List<Double>>();
        data.add(params);
        Carrier carrier = new Carrier(localStorage.localIter, data);
        while (client.update(hostId, tableId, carrier) == false) {}
    }

    public void lrSSP() throws TException {
        try {
            for (int i=0; i<iteNum; i++) {
                read();

                if (i != localStorage.localIter) throw new Exception("Wrong arised!");

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
        double deltaArray[][] = X.transpose().times(tmp).times(lr/m*(-1.0)).getArray();
        ArrayList<Double> deltaList = new ArrayList<Double>(n);
        for (int i=0; i<n; i++) deltaList.add(deltaArray[i][0]);
        return deltaList;
    }

    @Override
    public void clock(Carrier carrier) {

    }

}
