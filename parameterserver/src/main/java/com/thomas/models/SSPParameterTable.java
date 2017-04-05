package com.thomas.models;

import com.thomas.thrift.server.Carrier;
import com.thomas.thrift.worker.PSWorkerService;
import com.thomas.utils.math.ListUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by hadoop on 3/18/17.
 */
public class SSPParameterTable extends AbstractPSTable {
    public int staleValue;

    public int globalClock;

    public AtomicIntegerArray updateRecorder;

    public Carrier clockCarrier;

    public List<List<Double>> globalParameter;
    public List<List<List<Double>>> caches;
    private Logger logger = Logger.getLogger(this.getClass());

    public SSPParameterTable() {
        super();
    }

    public SSPParameterTable(String tableId, int staleValue, int workerNum, int dimems, int rows, ArrayList<String> workers,
                             List<List<Double>>initials) {
        super(tableId, workerNum, dimems, rows, workers);
        this.staleValue = staleValue;
        this.globalClock = 0;
        this.clockCarrier = new Carrier(-1, null);
        // we init the parameters in the create table phase.
        this.globalParameter = new ArrayList<List<Double>>();
        for (int i=0; i<rows; i++) this.globalParameter.add(initials.get(i));

        this.caches = new ArrayList<List<List<Double>>>();
        for (int i=0; i<staleValue; i++) {
            ArrayList<List<Double>> list = new ArrayList<List<Double>>();
            for (int j=0; j<rows; j++) list.add(ListUtils.init(dimems, 0.0));
            this.caches.add(list);
        }

        this.updateRecorder = new AtomicIntegerArray(new int[staleValue]);

        // init the nodes.
        for (String hostId: workers) {
            String []tmp = hostId.split(":");
            if (tmp.length < 2) logger.error("SSP table initialization failed!");
            nodes.add(new Node(tmp[0], Integer.parseInt(tmp[1])));
        }
    }

    @Override
    public Carrier read(int t, int updateNum) {
        Carrier carrier = new Carrier();
        if (updateNum <= staleValue && t >= globalClock && t + updateNum <= globalClock + staleValue) {
            carrier.gradients = new ArrayList<List<Double>>();
            carrier.iterationNum = t;
            // add global parameter.
            carrier.gradients.add(globalParameter.get(0));
            // append the updates delta.
            for (int i=0; i<updateNum; i++) {
                carrier.gradients.add(caches.get((t+i)%staleValue).get(0));
            }
        } else {
            carrier.iterationNum = -1;
        }
        logger.info("read: " + carrier);
        return carrier;
    }

    @Override
    public boolean update(Carrier carrier) {
        boolean result = true;
        try {
            int iterationId = carrier.iterationNum;
            List<List<Double>> gradients = carrier.gradients;
            if (gradients == null) return false;
            int updateRowNums = gradients.size();

            if (iterationId < globalClock || iterationId >= staleValue + globalClock || updateRowNums > staleValue) return false;

            synchronized (caches) {
                for (int i=0; i<updateRowNums; i++) {
                    ListUtils.add(caches.get((iterationId+i)%staleValue).get(0), gradients.get(i));

                    updateRecorder.incrementAndGet((iterationId+i)%staleValue);

                    if (iterationId + i == globalClock && updateRecorder.get(globalClock%staleValue) == workerNum) {
                        // print();
                        updateRecorder.set(globalClock%staleValue, 0);

                        // update the gradients and prepare the carrier to be sent to all workers.
                        ListUtils.add(globalParameter.get(0), caches.get(globalClock%staleValue).get(0));
                        for (int j=0; j<rows; j++) {
                            caches.get(globalClock%staleValue).set(j, ListUtils.init(dimems, 0.0));
                        }
                        globalClock++;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public Carrier check(String hostId, int iter) {
        Carrier carrier = new Carrier(-1, null);
        // if it is consistent.
        if (iter == globalClock) return carrier;

        logger.info(hostId + " check local: " + iter + "remote: " + curIteration.get());

        return read(globalClock, 0);
    }

}
