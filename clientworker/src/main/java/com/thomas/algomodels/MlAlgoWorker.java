package com.thomas.algomodels;

import com.thomas.thrift.server.Carrier;
import com.thomas.utils.constant.NodeStatus;

import java.util.Properties;

/**
 * Created by hadoop on 3/11/17.
 */
public abstract class MlAlgoWorker {
    public MlAlgoType algoType;
    public Props properties;
    public String hostId;
    public int workerStatus;
    public Properties props;

    public MlAlgoWorker() {}

    public MlAlgoWorker(MlAlgoType mlAlgoType, Props properties, Properties props) {
        this.algoType = mlAlgoType;
        this.properties = properties;
        this.workerStatus = NodeStatus.RUNNING;
        this.props = props;
    }
    /*
    * to do list:
    *    read properties from props file.
    * */

    public abstract void init();
    public abstract void run();
    public abstract void clock(Carrier carrier);
}
