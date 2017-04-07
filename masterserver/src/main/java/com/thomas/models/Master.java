package com.thomas.models;

import com.thomas.thrift.master.ExecInfo;
import com.thomas.thrift.master.JobInfo;
import com.thomas.thrift.master.JobResult;
import com.thomas.thrift.worker.JobConfig;
import com.thomas.utils.thrift.PSUtils;
import com.thomas.utils.thrift.WorkerUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by hadoop on 3/12/17.
 */
public class Master {
    private Cluster cluster;
    private HashMap<Long, Job> jobs;
    private static Logger logger = Logger.getLogger("Master");

    public void init(Properties props) {
        // init cluster info.
        cluster = new Cluster();
        // initialize the cluster from config
        cluster.init(props);
        jobs = new HashMap<Long, Job>();
        logger.info("after init: " + cluster);
    }

    public long createJob(JobInfo jobInfo) {
        long jobId = System.currentTimeMillis();
        Job job = new Job(jobId, jobInfo);

        // assign workers and servers to this job.
        // to do list: assignment algorithm.
        for (Node node: cluster.workers) job.workers.add(node);
        for (Node node: cluster.servers) job.parameterServers.add(node);

        // assign parameter table.
        Node pserver = job.parameterServers.get(0);
        PSTable psTable = new PSTable();
        psTable.node = pserver;
        psTable.tableId = job.jobType + job.jobId;
        psTable.parallelType = jobInfo.parallelType;
        job.tables.add(psTable);

        try {
            // assign data to this job node and assign the job config.
            initJobConfigs(job);

            createParameterTable(psTable, job.workers, jobInfo.extraParams);

            // at last, assign the job to the workers.
            createWorkers(job);

        } catch (Exception e) {
            jobId = -1;
            e.printStackTrace();
        }

        if (jobId > 0) jobs.put(jobId, job);
        return jobId;
    }

    public JobResult getJobResult(long jobId) {
        Job job = jobs.get(jobId);
        if (job.execInfo.size() == cluster.workers.size()) {
            JobResult jobResult = new JobResult();
            // get final params from server.
            jobResult.setParams(job.getParamsResult());
            jobResult.setExecInfos(job.execInfo);
            return jobResult;
        }
        return null;
    }

    public boolean getJobDone(String hostId, long jobId, ExecInfo execInfo) {
        try {
            jobs.get(jobId).execInfo.add(execInfo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isAlive() {
        return true;
    }

    public void createParameterTable(PSTable psTable, List<Node> workers, String extraParams) throws Exception {
        ArrayList<String> machines = new ArrayList<String>();
        for (Node node: workers) machines.add(node.hostId+":"+node.port);
        PSUtils.createTable(psTable, machines, extraParams);
    }

    public void createWorkers(Job job) {
        for (int i=0; i<job.workers.size(); i++) {
            final Node worker= job.workers.get(i);
            final JobConfig jobConfig = job.jobConfigs.get(i);
            Runnable runnable = new Runnable() {
                public void run() {
                    WorkerUtils.assignJob(worker, jobConfig);
                }
            };
            // send config info to each worker.
            new Thread(runnable).start();
            logger.info(String.format("worker is: %s, job config is: %s", worker.toString(), jobConfig.toString()));
        }
    }

    public void initJobConfigs(Job job) throws Exception {
        for (int i=0; i<job.workers.size(); i++) {
            JobConfig jobConfig = new JobConfig();

            jobConfig.jobKey = job.jobId;
            jobConfig.jobType = job.jobType;
            jobConfig.learningRate = job.learningRate;

            jobConfig.iteNum = job.iteNum;
            jobConfig.serverId = job.parameterServers.get(0).hostId;
            jobConfig.serverPort = job.parameterServers.get(0).port;
            jobConfig.tableId = job.tables.get(0).tableId;
            String[] tmp = job.tables.get(0).parallelType.split(":");
            jobConfig.parallelType = tmp[0];
            if (tmp.length > 1) jobConfig.stale = Integer.parseInt(tmp[1]);

            String[] params = job.extraParams.split(":");
            if (params.length > 2) {
                jobConfig.rowNum = Integer.parseInt(params[0]);
                jobConfig.dimems = Integer.parseInt(params[1]);
            } else {
                throw new Exception("init failed!");
            }
            jobConfig.dataPath = job.dataPaths.get(i % job.dataPaths.size());
            job.jobConfigs.add(jobConfig);
        }
    }
}
