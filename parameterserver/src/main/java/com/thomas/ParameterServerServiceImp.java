package com.thomas;

import com.thomas.models.ParameterServer;
import com.thomas.thrift.server.Carrier;
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


    public boolean update(String hostId, String tableId, Carrier carrier) {
        return false;
    }

    public Carrier read(String hostId, String tableId, int t, int extra) {
        return parameterServer.read(hostId, tableId, t, extra);
    }

    public boolean create(String tableType, String tableId, List<String> machines, Carrier params) {
        return parameterServer.createParameterTable(tableType, tableId, machines, params);
    }

}
