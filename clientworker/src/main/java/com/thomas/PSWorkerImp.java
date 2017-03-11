package com.thomas;

import com.thomas.thrift.worker.JobConfig;
import com.thomas.thrift.worker.PSWorkerService;

/**
 * Created by hadoop on 3/11/17.
 */
public class PSWorkerImp implements PSWorkerService.Iface {
    private PSWorker psWorker;

    public PSWorkerImp() {
        psWorker = new PSWorker();
    }

    public void runJob(JobConfig jobConfig) {
        psWorker.run(jobConfig);
    }

    public boolean isAlive() {
        return true;
    }
}
