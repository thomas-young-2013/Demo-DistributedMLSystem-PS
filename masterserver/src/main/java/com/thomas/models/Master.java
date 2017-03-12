package com.thomas.models;

import com.thomas.thrift.master.ExecInfo;
import com.thomas.thrift.master.JobInfo;
import com.thomas.thrift.master.JobResult;
import com.thomas.thrift.worker.JobConfig;

import java.util.HashMap;

/**
 * Created by hadoop on 3/12/17.
 */
public class Master {
    private Cluster cluster;

    private HashMap<Long, Job> jobs;

    /*
    * initialize the cluster from config
    * */
    public void init() {
        // init cluster info.
        cluster = new Cluster();

        jobs = new HashMap<Long, Job>();
    }

    public long createJob(JobInfo jobInfo) {
        long jobId = System.currentTimeMillis();
        Job job = new Job(jobId, jobInfo);

        // assign workers and servers to this job.
        for (Node node: cluster.workers) job.workers.add(node);
        job.parameterServers.add(cluster.servers.get(0));

        // assign parameter table.
        Node pserver = job.parameterServers.get(0);
        PSTable psTable = new PSTable();
        psTable.node = pserver;
        psTable.tableId = job.jobType + job.jobId;
        job.tables.add(psTable);

        // assign data to this job node and assign the job config.
        for (int i = 0; i < job.workers.size(); i++) {
            JobConfig jobConfig = createJobConfig(job, i);
            job.jobConfigs.add(jobConfig);
        }

        try {
            createParameterTable(psTable);

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
        JobResult jobResult = new JobResult();
        jobResult.setParams(job.getParamsResult());
        jobResult.setExecInfos(job.execInfo);
        return jobResult;
    }

    public boolean getJobDone(String hostId, long jobId, ExecInfo execInfo) {
        jobs.get(jobId).execInfo.add(execInfo);
        return true;
    }

    public boolean isAlive() {
        return true;
    }

    public void createParameterTable(PSTable psTable) throws Exception {

    }

    public void createWorkers(Job job) {
        for (int i=0; i<job.workers.size(); i++) {
            // send config info to each worker.
        }
    }

    public JobConfig createJobConfig(Job job, int index) {
        JobConfig jobConfig = new JobConfig();
        jobConfig.setIteNum(job.iteNum);
        jobConfig.setJobKey(job.jobId);
        jobConfig.setJobType(job.jobType);
        jobConfig.setLearningRate(job.learningRate);
        jobConfig.setDataPath(job.dataPaths.get(index % job.dataPaths.size()));

        PSTable table = job.tables.get(0);
        jobConfig.setServerId(table.node.hostId);
        jobConfig.setServerPort(table.node.port);
        jobConfig.setTableId(table.tableId);
        return jobConfig;
    }
}
