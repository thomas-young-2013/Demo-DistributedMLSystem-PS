package com.thomas.algomodels;

import com.thomas.thrift.server.Carrier;

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

    public SSPClientBuffer(int rowNum, int dimems, int stale) {
        this.rowNum = rowNum;
        this.dimems = dimems;
        this.stale = stale;
        this.globalIter = 0;
        this.localIter = -1;
        this.buffer = new ArrayList<List<Double>>();
        for (int i=0; i<stale+1; i++) this.buffer.add(new ArrayList<Double>());
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

        int index = (globalIter%(stale+1))*rowNum;
        ArrayList<Double> result = new ArrayList<Double>();
        for (int i=0; i<rowNum*(localIter-globalIter + 1); i++) {
            for (int j=0; j<dimems; j++) {
                int tmp = (index + i) % ((stale+1)*rowNum);
                result.set(j, result.get(j) + buffer.get(tmp).get(j));
            }
        }
        return result;
    }

    public boolean exceed() {
        return localIter > globalIter + stale;
    }

    public void replace(Carrier carrier) {
        int parameterNum = carrier.gradients.size();
        int updateIndex = (carrier.iterationNum-localIter)*rowNum;
        for (int i=0; i<parameterNum; i++) {
            buffer.set((updateIndex + i) % (stale + 1), carrier.gradients.get(i));
        }
    }

    public void set(ArrayList<Double> list) {
        buffer.set(localIter%(stale+1), list);
    }
}
