package com.thomas.models;

import java.util.ArrayList;

/**
 * Created by hadoop on 3/6/17.
 */
public class ParameterTable {
    public ArrayList<Double[]> params;
    public int dimenNum;
    public int clock;

    ParameterTable(int dimens) {
        dimenNum = dimens;
        params = new ArrayList<Double[]>();
    }

    public Double[] getRow(int rowId) {
        return params.get(rowId);
    }

    public void updateRow(int rowId, Double[] valVector) {
        for (int i=0; i < dimenNum; i++) {
            params.get(rowId)[i] += valVector[i];
        }
    }

    public void init(Double[] intials) {
        params.add(intials);
    }
}
