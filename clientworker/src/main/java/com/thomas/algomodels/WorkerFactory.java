package com.thomas.algomodels;

import com.thomas.algorithm.LRWorker;

import java.util.Properties;

/**
 * Created by hadoop on 3/11/17.
 */
public class WorkerFactory {
    public static MlAlgoWorker getWorker(MlAlgoType mlAlgoType, Props properties, Properties props) {
        switch (mlAlgoType) {
            case LINEAR_REGRESSION:
                return new LRWorker(mlAlgoType, properties, props);
            default:
                throw new UnsupportedOperationException("Unsupported ml algorithm type: " + mlAlgoType);
        }
    }
}
