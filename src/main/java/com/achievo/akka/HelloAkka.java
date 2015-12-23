package com.achievo.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.achievo.akka.actors.MasterActor;
import com.achievo.akka.message.Result;

/**
 * Created by galen.zhang on 2015/12/17.
 */
public class HelloAkka {
    public static void main(String[] args) throws Exception {
        ActorSystem _system = ActorSystem.create("HelloAkka");
        ActorRef master = _system.actorOf(Props.create(MasterActor.class), "master");

        master.tell("Hi! My name is Rocky. I'm so so so so happy to be here.", null);
        master.tell("Today, I'm going to read a news article for you.", null);
        master.tell("I Hope I hope you'll like it.", null);

        Thread.sleep(500);

        master.tell(new Result(), null);

        Thread.sleep(500);
        _system.shutdown();
    }
}
