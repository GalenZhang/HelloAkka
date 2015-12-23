package com.achievo.akka.actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import com.achievo.akka.message.MapData;
import com.achievo.akka.message.ReduceData;
import com.achievo.akka.message.WordCount;

import java.util.HashMap;
import java.util.List;

/**
 * Created by galen.zhang on 2015/12/17.
 */
public class ReduceActor extends UntypedActor {
    private ActorRef aggregateActor;

    public ReduceActor(ActorRef aggregateActor) {
        this.aggregateActor = aggregateActor;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof MapData) {
            System.out.println("ReduceActor msg: " + message);
            MapData mapData = (MapData) message;
            // reduce the incoming data
            ReduceData reduceData = reduce(mapData.getDataList());
            // forward the result to aggregate actor
            aggregateActor.tell(reduceData, null);
        } else {
            unhandled(message);
        }
    }

    private ReduceData reduce(List<WordCount> dataList) {
        HashMap<String, Integer> reduceMap = new HashMap<String, Integer>();
        for (WordCount wordCount : dataList) {
            if (reduceMap.containsKey(wordCount.getWord())) {
                Integer value = reduceMap.get(wordCount.getWord());
                value++;
                reduceMap.put(wordCount.getWord(), value);
            }
            else {
                reduceMap.put(wordCount.getWord(), Integer.valueOf(1));
            }
        }
        return new ReduceData(reduceMap);
    }
}
