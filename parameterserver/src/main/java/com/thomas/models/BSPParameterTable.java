package com.thomas.models;

import com.thomas.thrift.server.Carrier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hadoop on 3/15/17.
 */
public class BSPParameterTable extends AbstractPSTable {
    public AtomicInteger updateNum;
    public AtomicInteger readNum;

    public BSPParameterTable() {
        super();
    }

    public BSPParameterTable(String tableId, int workerNum, int dimems, int rows, ArrayList<String> workers) {
        super(tableId, workerNum, dimems, rows, workers);
        updateNum = new AtomicInteger(workerNum);
        readNum = new AtomicInteger(0);
        // we init the parameters in the create table phase.
    }

    public Carrier read(String hostId, String tableId, int t, int statle) {
        Carrier carrier = new Carrier();
        // waiting until all updates completed.
        if (updateNum.get() < workerNum) {
            carrier.iterationNum = -1;
            return carrier;
        }
        carrier.gradients = new ArrayList<List<Double>>();
        for (int i=0; i<rows; i++) {
            ArrayList<Double> list = new ArrayList<Double>();
            for (int j=0; j<dimems; j++) list.add(parameters[i][j]);
            carrier.gradients.add(list);
        }
        readNum.getAndIncrement();
        return carrier;
    }

    public boolean update(String hostId, String tableId, Carrier carrier) {
        int iterationId = carrier.iterationNum;
        List<List<Double>> gradients = carrier.gradients;
        if (readNum.get() < workerNum) {
            return false;
        }
        for (int i=0; i<rows; i++) {
            for (int j=0; j<dimems; j++) {
                this.parameters[i][j] += gradients.get(i).get(j);
            }
        }
        updateNum.getAndIncrement();
        return true;
    }
}
