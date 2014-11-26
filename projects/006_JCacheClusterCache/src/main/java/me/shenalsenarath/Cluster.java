package me.shenalsenarath;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

/**
 * Created by shselk on 11/14/2014.
 */
public class Cluster {

    /**
     * This will setup two  hz nodes instance for testing purposes
     */
    public void init() {
        Config config = new Config();

        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(true);
        config.getNetworkConfig().setPort(5701);
        config.getGroupConfig().setName("cluster1");
        config.getGroupConfig().setPassword("cluster1pass");

        final HazelcastInstance hz1 = Hazelcast.newHazelcastInstance(config);

        Config config2 = new Config();

        config2.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(true);
        config2.getNetworkConfig().setPort(5702);
        config2.getGroupConfig().setName("cluster1");
        config2.getGroupConfig().setPassword("cluster1pass");

        final HazelcastInstance hz2 = Hazelcast.newHazelcastInstance(config2);
    }



    public void runApp()
            throws InterruptedException {
        init();
    }

}
