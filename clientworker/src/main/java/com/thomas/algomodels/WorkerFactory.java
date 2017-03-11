package com.thomas.algomodels;

import com.thomas.algorithm.LrWorker;

/**
 * Created by hadoop on 3/11/17.
 */
public class WorkerFactory {
    public static MlAlgoWorker getWorker(MlAlgoType mlAlgoType, Properties properties) {
        switch (mlAlgoType) {
            case LINEAR_REGRESSION:
                return new LrWorker(mlAlgoType, properties);
            default:
                throw new UnsupportedOperationException("Unsupported ml algorithm type: " + mlAlgoType);
        }
    }
}
