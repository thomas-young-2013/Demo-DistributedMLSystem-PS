package com.thomas;

import com.thomas.models.Master;
import com.thomas.thrift.master.ExecInfo;
import com.thomas.thrift.master.JobInfo;
import com.thomas.thrift.master.JobResult;
import com.thomas.thrift.master.PMasterService;

/**
 * Created by hadoop on 3/12/17.
 */
public class PMasterServiceImp implements PMasterService.Iface {

    private Master master;

    public PMasterServiceImp(Master master) {
        this.master = master;
    }

    public long createJob(JobInfo jobInfo) {
        return master.createJob(jobInfo);
    }

    public JobResult getJobResult(long jobId) {
        return master.getJobResult(jobId);
    }

    public boolean getJobDone(String hostId, long jobId, ExecInfo execInfo) {
        return master.getJobDone(hostId, jobId, execInfo);
    }

    public boolean isAlive() {
        return master.isAlive();
    }
}
