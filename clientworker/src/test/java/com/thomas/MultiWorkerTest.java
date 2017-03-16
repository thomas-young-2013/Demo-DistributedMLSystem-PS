package com.thomas;

import com.thomas.models.Node;
import com.thomas.thrift.server.Carrier;
import com.thomas.thrift.server.ParameterServerService;
import com.thomas.thrift.worker.JobConfig;
import com.thomas.thrift.worker.PSWorkerService;
import com.thomas.utils.thrift.PSUtils;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hadoop on 3/11/17.
 */
public class MultiWorkerTest {
    public static void main(String []args) {
        // create parameter table and init it. localhost 9000
        final MultiWorkerTest workerTest = new MultiWorkerTest();
        workerTest.initParameterServer("localhost", 8000, 30000);

        // start a worker and run a job.
        Runnable s1 = new Runnable() {
            public void run() {
                workerTest.startWorker("localhost", 8080, 30000, "/home/hadoop/Desktop/train.txt");
            }
        };
        Runnable s2 = new Runnable() {
            public void run() {
                workerTest.startWorker("localhost", 8081, 30000, "/home/hadoop/Desktop/train2.txt");
            }
        };
        Runnable s3 = new Runnable() {
            public void run() {
                workerTest.startWorker("localhost", 8082, 30000, "/home/hadoop/Desktop/train3.txt");
            }
        };
        Thread t1 = new Thread(s1);
        Thread t2 = new Thread(s2);
        Thread t3 = new Thread(s3);
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Node node = new Node("localhost", 8000);
            System.out.println(PSUtils.getParams(node,1000,"lr"));
        }

    }

    public void initParameterServer(String host, int port, int timeout) {
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(0.0);
        list.add(0.0);
        list.add(0.0);
        List<String> machines = new ArrayList<String>();
        machines.add("worker1");
        machines.add("worker2");
        machines.add("worker3");
        List<List<Double>> list1 = new ArrayList<List<Double>>();
        list1.add(list);
        Carrier carrier = new Carrier(0, list1);
        String SERVER_IP = "worker1";

        TTransport transport = null;
        try {
            transport = new TSocket(host, port, timeout);
            TProtocol protocol = new TBinaryProtocol(transport);
            ParameterServerService.Client client = new ParameterServerService.Client(protocol);
            transport.open();

            client.create("BSP", "lr", machines, carrier);

            /*System.out.println(client.readRows("lr", 0, 2));
            System.out.println(client.round("lr"));
            client.updateRows("lr", 0, list);
            System.out.println(client.readRows("lr", 0, 2));*/

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

    public void startWorker(String host, int port, int timeout, String dataPath) {
        TTransport transport = null;
        try {
            transport = new TSocket(host, port, timeout);
            TProtocol protocol = new TCompactProtocol(transport);
            PSWorkerService.Client client = new PSWorkerService.Client(protocol);
            transport.open();

            JobConfig jobConfig = new JobConfig();
            jobConfig.jobKey = 1231231L;
            jobConfig.jobType = "LINEAR_REGRESSION";
            jobConfig.learningRate = 0.007;
            jobConfig.dataPath = dataPath;
            jobConfig.iteNum = 1000;
            jobConfig.serverId = "localhost";
            jobConfig.serverPort = 8000;
            jobConfig.tableId = "lr";

            client.runJob(jobConfig);

            System.out.println(client.isAlive());
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
