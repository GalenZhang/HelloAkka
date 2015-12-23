package com.achievo.akka.actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import com.achievo.akka.message.MapData;
import com.achievo.akka.message.WordCount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by galen.zhang on 2015/12/17.
 */
public class MapActor extends UntypedActor {
    private ActorRef reduceActor;

    public MapActor(ActorRef reduceActor) {
        this.reduceActor = reduceActor;
    }

    private String[] STOP_WORDS = {"a", "is"};

    private List<String> STOP_WORDS_LIST = Arrays.asList(STOP_WORDS);

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            System.out.println("MapActor msg: " + message);
            String work = (String) message;
            // map the words in the sentence
            MapData data = evaluateExpression(work);
            // send the result to ReduceActor
            reduceActor.tell(data, null);
        } else {
            unhandled(message);
        }
    }

    private MapData evaluateExpression(String line) {
        List<WordCount> dataList = new ArrayList<WordCount>();
        StringTokenizer parser = new StringTokenizer(line);
        while (parser.hasMoreTokens()) {
            String word = parser.nextToken().toLowerCase();
            if (!STOP_WORDS_LIST.contains(word)) {
                dataList.add(new WordCount(word, Integer.valueOf(1)));
            }
        }
        return new MapData(dataList);
    }
}
