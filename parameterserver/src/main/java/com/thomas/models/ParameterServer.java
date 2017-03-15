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
        logger.info("create parameter table: " + tableId);
        boolean result = true;
        try {
            List<List<Double>> initials = intialVector.gradients;
            int dimems = initials.get(0).size();
            int nums = initials.size();

            AbstractPSTable table;
            if (tableType.startsWith(ParallelType.BSP)) {
                table = new BSPParameterTable(tableId, machines.size(), dimems, nums, (ArrayList<String>) machines);
                // init the initial vector or matrix.
                table.parameters = new double[nums][dimems];
                for (int i = 0; i < nums; i++) {
                    for (int j = 0; j < dimems; j++) {
                        table.parameters[i][j] = initials.get(i).get(j);
                    }
                }
            } else {
                String[] tmp = tableType.split("-");
                table = new BSPParameterTable(tableId, machines.size(), dimems, nums, (ArrayList<String>) machines);
            }

            tables.put(tableId, table);
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    public Carrier read(String hostId, String tableId, int t, int extra) {
        return tables.get(tableId).read(hostId, tableId, t, extra);
    }



    /*public ArrayList<Double> readRows(String tableId, int rowId, int range) {

        Double[] data = tables.get(tableId).getRow(rowId);
        ArrayList<Double> list = new ArrayList<Double>();
        for (double d: data) list.add(d);

        logger.info("read table row: " + tableId + ", row id : " + rowId+ " " + list);
        System.out.println("read table row: " + tableId + ", row id : " + rowId+ " " + list);

        return list;
    }

    public void updateRows(String tableId, int rowId, List<Double> valVector) {
        logger.info("update table row:" + tableId + ", rowid:" + rowId+ " " + valVector);

        metaData.get(tableId).updateNum.getAndIncrement();
        Double [] delta = new Double[valVector.size()];
        for (int i=0; i<valVector.size(); i++) delta[i] = valVector.get(i);
        tables.get(tableId).updateRow(rowId, delta);

        // if some iteration completed, initialize the next one parameter.
        if (metaData.get(tableId).isRoundOver()) {
            tables.get(tableId).initNextRow(rowId);
        }
    }

    public boolean round(String tableId) {
        // System.out.println("round received. num: "+metaData.get(tableId).recieverNum.get());
        // logger.info("round received. num: "+metaData.get(tableId).recieverNum.get());
        return metaData.get(tableId).isRoundOver();
    }*/

}
