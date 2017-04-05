package com.thomas.algomodels;

import com.thomas.thrift.server.Carrier;
import com.thomas.utils.math.ListUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hadoop on 4/5/17.
 */
public class Buffer {
    private Logger logger = Logger.getLogger(this.getClass());
    public List<List<Double>> globalParameter;
    public List<List<List<Double>>> caches;

    public int rows;
    public int dimems;
    public int stale;
    public int globalClock;
    public int localClock;

    public Buffer(int rows, int dimems, int stale) {
        this.globalClock = 0;
        this.localClock = 0;
        this.rows = rows;
        this.dimems = dimems;
        this.stale = stale;

        this.globalParameter = new ArrayList<List<Double>>();
        for (int i=0; i<rows; i++) this.globalParameter.add(ListUtils.init(dimems, 0.0));

        this.caches = new ArrayList<List<List<Double>>>();
        for (int i=0; i<stale; i++) {
            ArrayList<List<Double>> list = new ArrayList<List<Double>>();
            for (int j=0; j<rows; j++) list.add(ListUtils.init(dimems, 0.0));
            this.caches.add(list);
        }
    }

    public boolean exceed() {
        return globalClock + stale <= localClock;
    }

    public void reset(int iterationNum) {
        for (int i=globalClock; i<iterationNum; i++) {
            for (int j = 0; j < rows; j++) {
                caches.get(i % stale).set(j, ListUtils.init(dimems, 0.0));
            }
        }
    }

    public void set(Carrier carrier) {
        globalClock = carrier.iterationNum;
        globalParameter = carrier.gradients;
    }

    public ArrayList<Double> get() {
        ArrayList<Double> result = ListUtils.init(dimems, 0.0);
        ListUtils.add(result, globalParameter.get(0));
        for (int i=globalClock; i<localClock; i++) {
            ListUtils.add(result, caches.get(i%stale).get(0));
        }
        return result;
    }

    public void update(ArrayList<Double> delta) {
        ListUtils.add(caches.get(localClock%stale).get(0), delta);
    }

    public List<Double> getUpdate() {
        return caches.get(localClock%stale).get(0);
    }

    public void increment() {
        localClock++;
    }
}
