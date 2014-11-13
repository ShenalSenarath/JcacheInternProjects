package me.shenalsenarath;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;


public class App {
    public static void main(String[] args) throws InterruptedException {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        IExecutorService executor = hz.getExecutorService("exec");
        HazelcastInstance hz1 = Hazelcast.newHazelcastInstance();
        HazelcastInstance hz2 = Hazelcast.newHazelcastInstance();




        for (int k = 1; k <= 1000; k++) {
            Thread.sleep(1000);
            System.out.println(" Producing echo task : " + k);
            executor.execute(new EchoService("" + k));
        }
        System.out.println(" EchoTaskMain finished !");
    }
}
