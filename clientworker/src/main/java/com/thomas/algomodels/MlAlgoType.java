package com.thomas.algomodels;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hadoop on 3/11/17.
 */
public enum MlAlgoType {
    LINEAR_REGRESSION,
    LOGISTIC_REGRESSION;

    // Implementing a fromString method on an enum type
    private static final Map<String, MlAlgoType> stringToEnum = new HashMap<String, MlAlgoType>();
    static {
        // Initialize map from constant name to enum constant
        for(MlAlgoType MlAlgoType : values()) {
            stringToEnum.put(MlAlgoType.toString(), MlAlgoType);
        }
    }

    // Returns MlAlgoType for string, or null if string is invalid
    public static MlAlgoType fromString(String symbol) {
        return stringToEnum.get(symbol);
    }
}
