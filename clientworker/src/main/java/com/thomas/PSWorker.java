package com.thomas;

import com.thomas.algomodels.MlAlgoType;
import com.thomas.algomodels.MlAlgoWorker;
import com.thomas.algomodels.Properties;
import com.thomas.algomodels.WorkerFactory;
import com.thomas.thrift.server.Carrier;
import com.thomas.thrift.worker.JobConfig;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by hadoop on 3/11/17.
 */
public class PSWorker {
    private static HashMap<String, MlAlgoWorker> workerMap;

    public PSWorker() {
        workerMap = new HashMap<String, MlAlgoWorker>();
    }

    public static void run(JobConfig jobConfig) {
        Properties properties = new Properties();
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
        MlAlgoWorker mlAlgoWorker = WorkerFactory.getWorker(mlAlgoType, properties);
        workerMap.put(jobConfig.tableId, mlAlgoWorker);
        mlAlgoWorker.run();
    }

    public static void clock(String tableId, Carrier carrier) {
        workerMap.get(tableId).clock(carrier);
    }
}
