package com.thomas.models;

import com.thomas.thrift.master.ExecInfo;
import com.thomas.thrift.master.JobInfo;
import com.thomas.thrift.master.JobResult;
import com.thomas.thrift.worker.JobConfig;
import com.thomas.utils.constant.ParallelType;
import com.thomas.utils.thrift.PSUtils;
import com.thomas.utils.thrift.WorkerUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * Created by hadoop on 3/12/17.
 */
public class Master {
    private Cluster cluster;
    private HashMap<Long, Job> jobs;

    public void init(Properties props) {
        // init cluster info.
        cluster = new Cluster();
        // initialize the cluster from config
        cluster.init(props);
        jobs = new HashMap<Long, Job>();
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
        psTable.parallelType = ParallelType.SSP;

        job.tables.add(psTable);

        // assign data to this job node and assign the job config.
        for (int i = 0; i < job.workers.size(); i++) {
            JobConfig jobConfig = createJobConfig(job, i);
            job.jobConfigs.add(jobConfig);
        }

        try {
            createParameterTable(psTable, job.workers);

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

    public void createParameterTable(PSTable psTable, List<Node> workers) throws Exception {
        ArrayList<String> machines = new ArrayList<String>();
        for (Node node: workers) machines.add(node.hostId+":"+node.port);

        // to do list: support customized initialization such as rand(), or zero() and so on..

        ArrayList<Double> initials = new ArrayList<Double>();
        initials.add(0.0);
        initials.add(0.0);
        initials.add(0.0);

        PSUtils.createTable(psTable, machines, initials);
    }

    public void createWorkers(Job job) {
        for (int i=0; i<job.workers.size(); i++) {
            // send config info to each worker.
            WorkerUtils.assignJob(job.workers.get(i), job.jobConfigs.get(i));
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
