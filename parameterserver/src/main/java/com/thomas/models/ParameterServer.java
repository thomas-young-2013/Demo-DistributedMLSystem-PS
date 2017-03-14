package com.thomas.models;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hadoop on 3/6/17.
 */
public class ParameterServer {
    private HashMap<String, ParameterTable> tables;
    private HashMap<String, ParameterTableMetaData> metaData;

    private Logger logger = Logger.getLogger(this.getClass());

    public ParameterServer() {
        tables = new HashMap<String, ParameterTable>();
        metaData = new HashMap<String, ParameterTableMetaData>();
    }

    public void createParameterTable(List<String> machines, String tableId, Double[] intialVector) {
        logger.info("create parameter table: " + tableId);

        ParameterTable table = new ParameterTable(intialVector.length);
        table.init(intialVector);
        tables.put(tableId, table);
        metaData.put(tableId, new ParameterTableMetaData(machines.size()));
    }

    public ArrayList<Double> readRows(String tableId, int rowId, int range) {

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
    }

}
