package com.thomas.models;

import com.thomas.thrift.master.ExecInfo;
import com.thomas.thrift.master.JobInfo;
import com.thomas.thrift.worker.JobConfig;
import com.thomas.utils.constant.NodeStatus;
import com.thomas.utils.thrift.PSUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hadoop on 3/12/17.
 */
public class Job {
    public long jobId;
    public String jobType;
    public int iteNum;
    public double learningRate;
    public List<String> dataPaths;
    public int status;

    public List<Node> parameterServers;
    public List<Node> workers;
    public List<JobConfig> jobConfigs;

    public List<PSTable> tables;
    public List<ExecInfo> execInfo;

    public Job(long jobId, JobInfo jobInfo) {
        this.jobId = jobId;
        this.jobType = jobInfo.jobType;
        this.iteNum = jobInfo.iteNum;
        this.learningRate = jobInfo.learningRate;
        this.dataPaths = jobInfo.dataPaths;
        this.status = NodeStatus.RUNNING;

        this.parameterServers = new ArrayList<Node>();
        this.workers = new ArrayList<Node>();
        this.execInfo = new ArrayList<ExecInfo>();
        this.tables = new ArrayList<PSTable>();
        this.jobConfigs = new ArrayList<JobConfig>();
    }

    public ArrayList<Double> getParamsResult() {
        Node node = parameterServers.get(0);
        // return PSUtils.getParams(node, iteNum, tables.get(0).tableId, 2);
        return null;
    }
}
