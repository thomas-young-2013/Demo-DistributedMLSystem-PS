package com.thomas.models;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hadoop on 3/6/17.
 */
public class ParameterTableMetaData {
    public AtomicInteger updateNum;
    public AtomicInteger readNum;
    public int participantNum;
    public String [] participantsHosts;

    ParameterTableMetaData(int num) {
        participantNum = num;
        updateNum = new AtomicInteger(0);
        readNum = new AtomicInteger(0);
    }

    boolean isRoundOver() {
        return readNum.get() == participantNum;
    }
}
