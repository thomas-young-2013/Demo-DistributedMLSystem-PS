package com.thomas.algorithm;

import Jama.Matrix;
import com.thomas.algomodels.MlAlgoType;
import com.thomas.algomodels.MlAlgoWorker;
import com.thomas.algomodels.Properties;
import com.thomas.thrift.server.ParameterServerService;
import com.thomas.utils.DataInfo;
import com.thomas.utils.math.MatrixUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.ArrayList;

import static com.thomas.utils.DataReader.getDataInfo;
import static com.thomas.utils.DataReader.getTrainData;

/**
 * Created by hadoop on 3/11/17.
 */
public class LrWorker extends MlAlgoWorker {

    private ParameterServerService.Client client;

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

            lr();

        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }

    public void lr() throws TException {
        try {
            for (int i = 0; i < iteNum; i++) {
                while (i != 0 && client.round(tableId) == false) {
                    Thread.sleep(50);
                }
                ArrayList<Double> params = (ArrayList<Double>) client.readRows(tableId, i, 0);
                System.out.println(properties.dataPath + "finish: " + i);
                while (i != 0 && client.round(tableId) == false) {
                    Thread.sleep(50);
                }
                client.updateRows(tableId, i, getDeltaPrams(params));
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

}
