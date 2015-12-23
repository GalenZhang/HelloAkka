package com.achievo.akka.actors;

import akka.actor.UntypedActor;
import com.achievo.akka.message.ReduceData;
import com.achievo.akka.message.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by galen.zhang on 2015/12/17.
 */
public class AggregateActor extends UntypedActor {
    private Map<String, Integer> finalReducedMap = new HashMap<String, Integer>();

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof ReduceData) {
            ReduceData reduceData = (ReduceData) message;
            aggregateInMemoryReduce(reduceData.getReduceDataList());
        } else if (message instanceof Result) {
            System.out.println(finalReducedMap.toString());
        } else {
            unhandled(message);
        }
    }

    private void aggregateInMemoryReduce(HashMap<String, Integer> reduceDataList) {
        Integer count = null;
        for (String key : reduceDataList.keySet()) {
            if (finalReducedMap.containsKey(key)) {
                count = reduceDataList.get(key) + finalReducedMap.get(key);
                finalReducedMap.put(key, count);
            } else {
                finalReducedMap.put(key, reduceDataList.get(key));
            }
        }
    }
}
