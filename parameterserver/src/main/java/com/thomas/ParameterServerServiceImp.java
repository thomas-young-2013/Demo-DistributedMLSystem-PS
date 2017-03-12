package com.thomas;

import com.thomas.models.ParameterServer;
import com.thomas.thrift.server.ParameterServerService;

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
        return parameterServer.round(tableId);
    }

    public boolean createTable(String tableId, List<String> machines, List<Double> valVector) {
        Double []initialArray = new Double[valVector.size()];
        for (int i=0; i<valVector.size(); i++) {
            initialArray[i] = valVector.get(i);
        }

        parameterServer.createParameterTable(machines, tableId, initialArray);
        return true;
    }
}
