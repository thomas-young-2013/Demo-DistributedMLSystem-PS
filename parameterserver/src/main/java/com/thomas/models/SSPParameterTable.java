package com.thomas.models;

import com.thomas.thrift.server.Carrier;
import com.thomas.thrift.worker.PSWorkerService;
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

    // the start of current parameter index
    public int curIndex;

    public AtomicIntegerArray updateRecorder;

    private Logger logger = Logger.getLogger(this.getClass());

    public SSPParameterTable() {
        super();
    }

    public SSPParameterTable(String tableId, int staleValue, int workerNum, int dimems, int rows, ArrayList<String> workers,
                             List<List<Double>>initials) {
        super(tableId, workerNum, dimems, rows, workers);
        this.staleValue = staleValue;
        this.curIndex = 0;

        // we init the parameters in the create table phase.
        parameters = new double[rows + staleValue*rows][dimems];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < dimems; j++) {
                parameters[i][j] = initials.get(i).get(j);
            }
        }
        int []arr = new int[staleValue+1];
        this.updateRecorder = new AtomicIntegerArray(arr);

        // init the nodes.
        for (String hostId: workers) {
            String []tmp = hostId.split(":");
            if (tmp.length < 2) logger.error("SSP table initialization failed!");
            nodes.add(new Node(tmp[0], Integer.parseInt(tmp[1])));
        }
    }

    @Override
    public Carrier read(int t, int stale) {
        Carrier carrier = new Carrier();
        int curIter = curIteration.get();
        if (stale <= staleValue && t >= curIter && t + stale <= curIter + staleValue) {
            carrier.gradients = new ArrayList<List<Double>>();
            carrier.iterationNum = t;

            int totalRows = (1+staleValue)*rows;

            for (int i=0; i<(1+stale)*rows; i++) {
                List<Double> list = new ArrayList<Double>();
                int row = ((t - curIter)*rows + curIndex + i) % totalRows;
                for (int j=0; j<dimems; j++) list.add(parameters[row][j]);
                carrier.gradients.add(list);
                logger.info(t + " iteration read: " + list);
            }
        } else {
            carrier.iterationNum = -1;
        }
        return carrier;
    }

    @Override
    public boolean update(Carrier carrier) {
        boolean result = true;
        try {
            int iterationId = carrier.iterationNum;
            int curIter = curIteration.get();
            List<List<Double>> gradients = carrier.gradients;
            if (iterationId < curIteration.get() || iterationId > staleValue+curIter || gradients== null) {
                return false;
            }
            int updateRowNums = gradients.size();
            int totalRows = (1 + staleValue)*rows;
            for (int i = 0; i < updateRowNums; i++) {
                for (int j = 0; j < dimems; j++) {
                    this.parameters[((iterationId - curIter)*rows + curIndex + i) % totalRows][j]
                            += gradients.get(i).get(j);
                }
                logger.info(iterationId + " update is: " + gradients.get(i));
            }

            // increase the count and decide whether the end.
            updateRecorder.incrementAndGet(iterationId%(1+staleValue));

            if (updateRecorder.get(curIter%(1+staleValue)) == workerNum) {

                updateRecorder.set(curIter%(1+staleValue), 0);

                // keep the parameter
                Carrier carrier2 = new Carrier();
                carrier2.iterationNum = curIter;
                carrier2.gradients = new ArrayList<List<Double>>();
                for (int i=0; i<rows; i++) {
                    List<Double> list = new ArrayList<Double>();
                    for (int j=0; j<dimems; j++) list.add(parameters[(curIndex + i) % totalRows][j]);
                    carrier2.gradients.add(list);
                }

                // push the parameter to the next iteration.
                Carrier carrier1 = new Carrier();
                carrier1.iterationNum = curIteration.incrementAndGet();
                carrier1.gradients = new ArrayList<List<Double>>();

                // update the gradients and prepare the carrier to be sent to all workers.
                for (int i=0; i<rows; i++ ) {
                    List<Double> list = new ArrayList<Double>();
                    for (int j=0; j<dimems; j++) {
                        double tmp = parameters[(i+curIndex + rows) % totalRows][j];
                        tmp += parameters[i+curIndex][j];
                        parameters[(i+curIndex + rows) % totalRows][j] = tmp;
                        list.add(tmp);
                    }
                    carrier1.gradients.add(list);
                }

                // reset to 0
                for (int i=0; i<rows; i++) {
                    for (int j=0; j<dimems; j++) {
                        parameters[i+curIndex][j] = 0.0;
                    }
                }

                curIndex = (curIndex + rows) % totalRows;

                // push the newest parameter to all workers.
                clock(nodes, carrier2);

            }

        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public Carrier check(int iter) {
        Carrier carrier = new Carrier(-1, null);
        // if it is consistent.
        if (iter == curIteration.get()) return carrier;

        // otherwise push the global parameter to the worker.
        carrier.iterationNum = curIteration.get();
        carrier.gradients = new ArrayList<List<Double>>();
        for (int i=0; i<rows; i++) {
            List<Double> list = new ArrayList<Double>();
            for (int j=0; j<dimems; j++) list.add(parameters[curIndex + i][j]);
            carrier.gradients.add(list);
        }
        return carrier;
    }

    private void clock(ArrayList<Node> nodes, Carrier carrier) {
        try {
            for (Node node: nodes) {
                logger.info("Start clock: " + carrier);
                TTransport transport = new TSocket(node.hostId, node.port);
                transport.open();
                TProtocol protocol = new TBinaryProtocol(transport);
                PSWorkerService.Client client = new PSWorkerService.Client(protocol);
                client.clock(tableId, carrier);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
