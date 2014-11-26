package me.shenalsenarath;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;

import java.io.Serializable;
import java.util.concurrent.Callable;

/**
 * Created by shselk on 11/12/2014.
 */
public class EchoService implements Runnable,Serializable, HazelcastInstanceAware, Callable<String> {
    private final String msg;
    transient HazelcastInstance localInstance;

    public EchoService(String msg)  {
        this.msg = msg;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        System.out.println(" echo :" + msg + " "+ localInstance.toString());
    }

    @Override
    public String call() throws Exception {
        return this.localInstance.toString();
    }

    @Override
    public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        this.localInstance = hazelcastInstance;
    }
}
