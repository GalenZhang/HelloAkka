package com.achievo.akka.message;

import java.util.HashMap;

/**
 * Created by galen.zhang on 2015/12/17.
 */
public class ReduceData {
    private HashMap<String, Integer> reduceDataList;

    public ReduceData(HashMap<String, Integer> reduceDataList) {
        this.reduceDataList = reduceDataList;
    }

    public HashMap<String, Integer> getReduceDataList() {
        return reduceDataList;
    }
}
