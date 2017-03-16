package com.thomas.models;

import com.thomas.thrift.server.Carrier;
import com.thomas.utils.constant.ParallelType;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hadoop on 3/6/17.
 */
public class ParameterServer {
    private HashMap<String, AbstractPSTable> tables;

    private Logger logger = Logger.getLogger(this.getClass());

    public ParameterServer() {
        tables = new HashMap<String, AbstractPSTable>();
    }

    public boolean createParameterTable(String tableType, String tableId, List<String> machines, Carrier intialVector) {
        logger.info("create table: " + tableId + " " + tableType);
        boolean result = true;
        try {
            List<List<Double>> initials = intialVector.gradients;
            int dimems = initials.get(0).size();
            int nums = initials.size();

            AbstractPSTable table = null;
            if (tableType.startsWith(ParallelType.BSP)) {
                table = new BSPParameterTable(tableId, machines.size(), dimems, nums,
                        (ArrayList<String>) machines, initials);
            } else {
                String[] tmp = tableType.split("-");
                if (tmp.length >= 2 && tmp[0].startsWith(ParallelType.SSP)) {
                    table = new SSPParameterTable(tableId, Integer.parseInt(tmp[1]), machines.size(), dimems, nums,
                            (ArrayList<String>) machines, initials);
                } else {
                    logger.error("Parallel Type Not Supported!");
                }

            }
            tables.put(tableId, table);
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    public Carrier read(String hostId, String tableId, int t, int extra) {
        return tables.get(tableId).read(t, extra);
    }

    public boolean update(String hostId, String tableId, Carrier carrier) {
        return tables.get(tableId).update(carrier);
    }

}
