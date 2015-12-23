package com.achievo.akka.message;

import java.util.List;

/**
 * Created by galen.zhang on 2015/12/17.
 */
public class MapData {
    private List<WordCount> dataList;

    public List<WordCount> getDataList() {
        return dataList;
    }

    public MapData(List<WordCount> dataList) {
        this.dataList = dataList;
    }
}
