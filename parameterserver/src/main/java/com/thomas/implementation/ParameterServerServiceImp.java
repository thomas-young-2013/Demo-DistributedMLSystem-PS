package com.thomas.implementation;

import com.thomas.models.ParameterServer;
import com.thomas.thrift.ParameterServerService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hadoop on 3/8/17.
 */
public class ParameterServerServiceImp implements ParameterServerService.Iface {
    private ParameterServer parameterServer;

    public ParameterServerServiceImp() {
        this.parameterServer = new ParameterServer();
    }

    public ArrayList<Double> readRows(String tableId, int rowId, int range) {
        return parameterServer.readRows(tableId, rowId, range);
    }

    public void updateRows(String tableId, int rowId, List<Double> valVector) {
        parameterServer.updateRows(tableId, rowId, valVector);
    }

    public boolean round(String tableId) {
        return true;
    }

    public boolean createTable(String tableId, List<String> machines, List<Double> valVector) {
        parameterServer.createParameterTable(new String []{"C1", "C2"}, "lr", 3, new Double[]{0.0, 0.0, 0.0});
        return true;
    }
}
