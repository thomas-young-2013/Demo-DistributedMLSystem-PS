package com.thomas;

import com.thomas.algomodels.Props;
import com.thomas.thrift.server.Carrier;
import com.thomas.thrift.worker.JobConfig;
import com.thomas.thrift.worker.PSWorkerService;

import java.util.Properties;

/**
 * Created by hadoop on 3/11/17.
 */
public class PSWorkerImp implements PSWorkerService.Iface {
    private PSWorker psWorker;

    public PSWorkerImp(Properties props) {
        psWorker = new PSWorker(props);
    }

    public void runJob(JobConfig jobConfig) {
        psWorker.run(jobConfig);
    }

    public boolean isAlive() {
        return true;
    }

    public void clock(String tableId, Carrier carrier) {
        psWorker.clock(tableId, carrier);
    }
}
