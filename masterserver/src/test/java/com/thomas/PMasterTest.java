package com.thomas;

import com.thomas.thrift.master.JobInfo;
import com.thomas.thrift.master.PMasterService;
import com.thomas.thrift.worker.JobConfig;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.ArrayList;

/**
 * Created by hadoop on 4/7/17.
 */
public class PMasterTest {

    public static void main(String []args) {
        // create parameter table and init it. localhost 9000
        final PMasterTest workerTest = new PMasterTest();

        // start a worker and run a job.
        long jobId;
        if (true) {
            jobId = workerTest.startWorker("localhost", 8999, 30000);
            System.out.println("the job id is: " + jobId);
        }
        // workerTest.getJobResult(jobId, "localhost", 8999, 30000);
    }

    public long startWorker(String host, int port, int timeout) {
        TTransport transport = null;
        try {
            transport = new TSocket(host, port, timeout);
            TProtocol protocol = new TBinaryProtocol(transport);
            PMasterService.Client client = new PMasterService.Client(protocol);
            transport.open();

            ArrayList<String> dataPath = new ArrayList<String>();
            dataPath.add("/home/hadoop/Desktop/train.txt");
            dataPath.add("/home/hadoop/Desktop/train2.txt");
            dataPath.add("/home/hadoop/Desktop/train3.txt");

            JobInfo jobInfo = new JobInfo();

            JobConfig jobConfig = new JobConfig();
            jobInfo.jobType = "LINEAR_REGRESSION";
            jobInfo.learningRate = 0.005;
            jobInfo.dataPaths = dataPath;
            jobInfo.iteNum = 1000;
            // jobInfo.parallelType="SSP:5";
            jobInfo.parallelType="BSP";
            jobInfo.extraParams="1:3:ZERO";

            return client.createJob(jobInfo);

        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
        return -1;
    }

    public void getJobResult(long jobId, String host, int port, int timeout) {
        TTransport transport = null;
        try {
            transport = new TSocket(host, port, timeout);
            TProtocol protocol = new TBinaryProtocol(transport);
            PMasterService.Client client = new PMasterService.Client(protocol);
            transport.open();

            System.out.println(client.getJobResult(jobId));

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
