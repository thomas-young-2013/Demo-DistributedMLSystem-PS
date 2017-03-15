package com.thomas.models;

import com.thomas.thrift.server.Carrier;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hadoop on 3/15/17.
 */
public class BSPParameterTable extends AbstractPSTable {
    public AtomicInteger updateNum;
    public AtomicInteger readNum;

    private Logger logger = Logger.getLogger(this.getClass());

    public BSPParameterTable() {
        super();
    }

    public BSPParameterTable(String tableId, int workerNum, int dimems, int rows, ArrayList<String> workers,
                             List<List<Double>>initials) {
        super(tableId, workerNum, dimems, rows, workers);
        updateNum = new AtomicInteger(workerNum);
        readNum = new AtomicInteger(0);
        // we init the parameters in the create table phase.
        parameters = new double[rows][dimems];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < dimems; j++) {
                parameters[i][j] = initials.get(i).get(j);
            }
        }
    }

    @Override
    public Carrier read(int t, int statle) {
        Carrier carrier = new Carrier();
        // waiting until all updates completed.
        if (updateNum.get() < workerNum) {
            carrier.iterationNum = -1;
            return carrier;
        }
        try {
            carrier.iterationNum = curIteration.get();
            assert carrier.iterationNum == t: "t doesn't equal to iteration num";
            carrier.gradients = new ArrayList<List<Double>>();
            for (int i = 0; i < rows; i++) {
                ArrayList<Double> list = new ArrayList<Double>();
                for (int j = 0; j < dimems; j++) list.add(parameters[i][j]);
                carrier.gradients.add(list);
                logger.info(t + " read: " + list);
            }

            if (readNum.get() == workerNum - 1) {
                updateNum.set(0);
            }
            readNum.getAndIncrement();
        } catch (Exception e) {
            carrier.iterationNum = -1;
            e.printStackTrace();
        }
        return carrier;
    }

    @Override
    public boolean update(Carrier carrier) {
        boolean result = true;
        try {

            int iterationId = carrier.iterationNum;
            assert iterationId == curIteration.get(): "updated iteration doesn't equal to cur iteration";
            List<List<Double>> gradients = carrier.gradients;
            if (readNum.get() < workerNum) {
                return false;
            }
            logger.info(iterationId + " update: " + gradients.get(0));
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < dimems; j++) {
                    this.parameters[i][j] += gradients.get(i).get(j);
                }
            }

            if (updateNum.get() == workerNum - 1) {
                readNum.set(0);
                curIteration.getAndIncrement();
            }
            updateNum.getAndIncrement();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
