package com.thomas.algomodels;

import com.thomas.algorithm.LRWorker;

/**
 * Created by hadoop on 3/11/17.
 */
public class WorkerFactory {
    public static MlAlgoWorker getWorker(MlAlgoType mlAlgoType, Properties properties) {
        switch (mlAlgoType) {
            case LINEAR_REGRESSION:
                return new LRWorker(mlAlgoType, properties);
            default:
                throw new UnsupportedOperationException("Unsupported ml algorithm type: " + mlAlgoType);
        }
    }
}
