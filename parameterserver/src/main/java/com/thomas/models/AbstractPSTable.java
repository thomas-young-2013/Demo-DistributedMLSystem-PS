package com.thomas.models;

import com.thomas.thrift.server.Carrier;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hadoop on 3/15/17.
 */
public abstract class AbstractPSTable {
    public ArrayList<String> workers;
    public String tableId;
    public int rows;
    public int dimems;
    public int workerNum;

    public AtomicInteger curIteration;

    public double [][] parameters;

    public AbstractPSTable() {}
    public AbstractPSTable(String tableId, int workerNum, int dimems, int rows, ArrayList<String> workers) {
        this.workerNum = workerNum;
        this.dimems = dimems;
        this.rows = rows;
        this.tableId = tableId;
        this.workers = new ArrayList<String>();
        this.workers.addAll(workers);
        this.curIteration = new AtomicInteger(0);
    }

    public abstract boolean update(Carrier carrier);

    public abstract Carrier read(int t, int statle);
}
