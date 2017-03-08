package com.thomas.models;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hadoop on 3/6/17.
 */
public class ParameterTableMetaData {
    public AtomicInteger recieverNum;
    public int participantNum;
    public String [] participantsHosts;

    ParameterTableMetaData(int num) {
        participantNum = num;
        recieverNum = new AtomicInteger(0);
    }

    boolean isRoundOver() {
        return recieverNum.get() == participantNum;
    }
}
