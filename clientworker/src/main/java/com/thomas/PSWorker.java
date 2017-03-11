package com.thomas;

import com.thomas.algomodels.MlAlgoType;
import com.thomas.algomodels.MlAlgoWorker;
import com.thomas.algomodels.Properties;
import com.thomas.algomodels.WorkerFactory;
import com.thomas.thrift.worker.JobConfig;

/**
 * Created by hadoop on 3/11/17.
 */
public class PSWorker {

    public static void run(JobConfig jobConfig) {
        Properties properties = new Properties();
        properties.learningRate = jobConfig.getLearningRate();
        properties.iterationNum = jobConfig.getIteNum();
        properties.PSServerId = jobConfig.serverId;
        properties.PSPort = jobConfig.serverPort;
        properties.PSTableId = jobConfig.tableId;
        properties.dataPath = jobConfig.dataPath;

        MlAlgoType mlAlgoType = MlAlgoType.fromString(jobConfig.getJobType());
        MlAlgoWorker mlAlgoWorker = WorkerFactory.getWorker(mlAlgoType, properties);
        mlAlgoWorker.run();
    }

}
