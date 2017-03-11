package com.thomas.algomodels;

import com.thomas.thrift.server.ParameterServerService;

/**
 * Created by hadoop on 3/11/17.
 */
public abstract class MlAlgoWorker {
    public MlAlgoType algoType;
    public Properties properties;

    public MlAlgoWorker() {}

    public MlAlgoWorker(MlAlgoType mlAlgoType, Properties properties) {
        this.algoType = mlAlgoType;
        this.properties = properties;
    }
    /*
    * to do list:
    *    read properties from props file.
    * */

    public void init() {}
    public void run() {
    }
}
