package com.thomas;

import com.thomas.models.Node;
import com.thomas.thrift.server.Carrier;
import com.thomas.thrift.server.ParameterServerService;
import com.thomas.thrift.worker.JobConfig;
import com.thomas.thrift.worker.PSWorkerService;
import com.thomas.utils.thrift.PSUtils;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hadoop on 3/17/17.
 */
public class WorkerSSPTest {
    public static void main(String []args) {
        // create parameter table and init it. localhost 9000
        WorkerSSPTest workerTest = new WorkerSSPTest();
        workerTest.initParameterServer("localhost", 8000, 30000);

        // start a worker and run a job.
        workerTest.startWorker("localhost", 8080, 30000);

        Node node = new Node("localhost", 8000);
        System.out.println(PSUtils.getParams(node,20,"lr"));
    }

    public void initParameterServer(String host, int port, int timeout) {
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(0.0);
        list.add(0.0);
        list.add(0.0);
        List<String> machines = new ArrayList<String>();
        machines.add("localhost:8080");
        List<List<Double>> list1 = new ArrayList<List<Double>>();
        list1.add(list);
        Carrier carrier = new Carrier(0, list1);

        TTransport transport = null;
        try {
            transport = new TSocket(host, port, timeout);
            TProtocol protocol = new TBinaryProtocol(transport);
            ParameterServerService.Client client = new ParameterServerService.Client(protocol);
            transport.open();

            client.create("SSP:3", "lr", machines, carrier);

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

    public void startWorker(String host, int port, int timeout) {
        TTransport transport = null;
        try {
            transport = new TSocket(host, port, timeout);
            TProtocol protocol = new TBinaryProtocol(transport);
            PSWorkerService.Client client = new PSWorkerService.Client(protocol);
            transport.open();

            JobConfig jobConfig = new JobConfig();
            jobConfig.jobKey = 1231231L;
            jobConfig.jobType = "LINEAR_REGRESSION";
            jobConfig.learningRate = 0.02;
            jobConfig.dataPath = "/home/hadoop/Desktop/train2.txt";
            jobConfig.iteNum = 20;
            jobConfig.serverId = "localhost";
            jobConfig.serverPort = 8000;
            jobConfig.tableId = "lr";
            jobConfig.stale = 3;
            jobConfig.parallelType="SSP";
            jobConfig.rowNum=1;
            jobConfig.dimems=3;

            client.runJob(jobConfig);

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
}
