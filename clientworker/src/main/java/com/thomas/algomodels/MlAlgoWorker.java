package com.thomas.algomodels;

import com.thomas.thrift.server.Carrier;
import com.thomas.utils.constant.NodeStatus;

/**
 * Created by hadoop on 3/11/17.
 */
public abstract class MlAlgoWorker {
    public MlAlgoType algoType;
    public Properties properties;
    public String hostId;
    public int workerStatus;

    public MlAlgoWorker() {}

    public MlAlgoWorker(MlAlgoType mlAlgoType, Properties properties) {
        this.algoType = mlAlgoType;
        this.properties = properties;
        this.workerStatus = NodeStatus.RUNNING;
    }
    /*
    * to do list:
    *    read properties from props file.
    * */

    public abstract void init();
    public abstract void run();
    public abstract void clock(Carrier carrier);
}
