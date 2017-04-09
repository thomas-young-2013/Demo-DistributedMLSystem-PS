package com.thomas.metric;

import com.thomas.thrift.master.ExecInfo;
import com.thomas.thrift.master.JobInfo;
import com.thomas.thrift.master.JobResult;
import com.thomas.thrift.master.PMasterService;
import com.thomas.thrift.worker.JobConfig;
import com.thomas.utils.math.ListUtils;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hadoop on 4/7/17.
 */
public class PMasterMetric {

    public static void main(String []args) {
        // create parameter table and init it. localhost 9000
        final PMasterMetric workerTest = new PMasterMetric();
        if (args.length > 0) {
            // start a worker and run a job.
            int flag = 0;
            if (args[0].equalsIgnoreCase("run")) flag = 1;
            if (args[0].equalsIgnoreCase("metric")) flag = 2;

            long jobId;
            if (flag == 1) {
                jobId = workerTest.startWorker("localhost", 8999, 30000);
                System.out.println("the job id is: " + jobId);
            }
            if (flag == 2 && args.length > 1) {
                jobId = Long.parseLong(args[1]);
                workerTest.getJobResult(jobId, "localhost", 8999, 30000);
            }
        }
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
            jobInfo.learningRate = 0.002;
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

            System.out.println("Raw Input is: ");
            System.out.println(client.getJobResult(jobId));
            JobResult jobResult = client.getJobResult(jobId);

            double ratio = 0.0;
            long conv_time = 0L;
            int num = jobResult.execInfos.size();
            int cost_num = jobResult.execInfos.get(0).loss.size();
            List<Double> costs = ListUtils.init(cost_num, 0.0);
            for (ExecInfo execInfo: jobResult.execInfos) {
                ratio += (execInfo.computeTime/(execInfo.execTime*1.0));
                conv_time += execInfo.execTime;
                ListUtils.add(costs, execInfo.loss);
            }
            for (int i=0; i<costs.size(); i++) {
                costs.set(i, costs.get(i)/(num*1.0));
            }
            // compute the ratio of computation
            System.out.println("\n\nMetric Information is: ");
            System.out.println("ave computation ratio is: " + ratio/num);
            System.out.println("ave convergence time is: " + conv_time/(num*1.0));
            System.out.println("ave loss trend is: " + costs);

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
