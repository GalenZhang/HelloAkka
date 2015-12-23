package com.achievo.akka.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.achievo.akka.message.Result;

/**
 * Created by galen.zhang on 2015/12/17.
 */
public class MasterActor extends UntypedActor {
    private ActorRef aggregateActor = getContext().actorOf(Props.create(AggregateActor.class), "aggregate");

    private ActorRef reduceActor = getContext().actorOf(Props.create(ReduceActor.class, aggregateActor), "reduce");

    private ActorRef mapActor = getContext().actorOf(Props.create(MapActor.class, reduceActor), "map");

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            System.out.println("Master msg: " + message);
            mapActor.tell(message, null);
        } else if (message instanceof Result) {
            aggregateActor.tell(message, null);
        } else {
            unhandled(message);
        }
    }
}
