package com.thomas;

import com.thomas.algomodels.MlAlgoType;
import com.thomas.algomodels.MlAlgoWorker;
import com.thomas.algomodels.Props;
import com.thomas.algomodels.WorkerFactory;
import com.thomas.thrift.server.Carrier;
import com.thomas.thrift.worker.JobConfig;

import java.util.HashMap;
import java.util.Properties;

/**
 * Created by hadoop on 3/11/17.
 */
public class PSWorker {
    private static HashMap<String, MlAlgoWorker> workerMap;
    private static Properties props;

    public PSWorker(Properties property) {
        workerMap = new HashMap<String, MlAlgoWorker>();
        props = property;
    }

    public static void run(JobConfig jobConfig) {
        Props properties = new Props();
        properties.jobId = jobConfig.jobKey;
        properties.learningRate = jobConfig.getLearningRate();
        properties.iterationNum = jobConfig.getIteNum();
        properties.PSServerId = jobConfig.serverId;
        properties.PSPort = jobConfig.serverPort;
        properties.PSTableId = jobConfig.tableId;
        properties.dataPath = jobConfig.dataPath;
        properties.parallelType = jobConfig.parallelType;
        properties.stale = jobConfig.stale;
        properties.rowNum = jobConfig.rowNum;
        properties.dimems = jobConfig.dimems;

        MlAlgoType mlAlgoType = MlAlgoType.fromString(jobConfig.getJobType());
        MlAlgoWorker mlAlgoWorker = WorkerFactory.getWorker(mlAlgoType, properties, props);
        workerMap.put(jobConfig.tableId, mlAlgoWorker);
        mlAlgoWorker.run();
    }

    public static void clock(String tableId, Carrier carrier) {
        workerMap.get(tableId).clock(carrier);
    }
}
