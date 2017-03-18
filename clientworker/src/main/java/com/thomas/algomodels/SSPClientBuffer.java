package com.thomas.algomodels;

import com.thomas.thrift.server.Carrier;
import com.thomas.utils.math.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hadoop on 3/17/17.
 */
public class SSPClientBuffer {
    public int rowNum;
    public int dimems;
    public int globalIter;
    public int localIter;
    public int stale;

    public List<List<Double>> buffer;

    public List<Double> globalDelta;

    public SSPClientBuffer(int rowNum, int dimems, int stale) {
        this.rowNum = rowNum;
        this.dimems = dimems;
        this.stale = stale;
        this.globalIter = 0;
        this.localIter = 0;
        this.buffer = new ArrayList<List<Double>>();
        for (int i=0; i<stale+1; i++) this.buffer.add(ListUtils.init(dimems, 0.0));

        this.globalDelta = new ArrayList<Double>();
    }

    @Override
    public String toString() {
        return "SSPClientBuffer{" +
                "rowNum=" + rowNum +
                ", dimems=" + dimems +
                ", globalIter=" + globalIter +
                ", localIter=" + localIter +
                ", buffer=" + buffer +
                '}';
    }

    public ArrayList<Double> getParamWithLocalUpdate() {
        if (localIter == globalIter) {
            return (ArrayList<Double>) buffer.get(localIter%(stale+1));
        }

        ArrayList<Double> result = ListUtils.init(dimems, 0.0);
        for (int i=globalIter; i<localIter; i++) {
            ListUtils.add(result, buffer.get( i % (stale + 1) ));
        }
        return result;
    }

    public List<Double> getUpdate() {
        if (globalIter == localIter) return globalDelta;
        return buffer.get(localIter%(stale+1));
    }

    public boolean exceed() {
        return localIter > globalIter + stale;
    }

    public void replace(Carrier carrier) {
        buffer.set(globalIter%(stale+1), carrier.gradients.get(0));
    }

    public void set(ArrayList<Double> list) {
        buffer.set(localIter%(stale+1), list);
    }

    public void add(Carrier carrier) {
        ListUtils.add(buffer.get(globalIter%(stale+1)), carrier.gradients.get(0));
    }

    public void add(ArrayList<Double> list) {
        ListUtils.add(buffer.get(localIter%(stale+1)), list);
    }

    // reset the global iteration.
    public void reset() {
        buffer.set(globalIter%(stale+1), ListUtils.init(dimems, 0.0));
    }

    // reset the some fixed iteration.
    public void reset(int index) {
        buffer.set(index%(stale+1), ListUtils.init(dimems, 0.0));
    }

}
