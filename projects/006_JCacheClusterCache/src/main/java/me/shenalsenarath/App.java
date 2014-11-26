package me.shenalsenarath;


import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;
import java.io.File;
import java.net.URI;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by shselk on 11/17/2014.
 */
public class App {

    private CachingProvider cachingProvider;
    private CacheManager cacheManager;
    private Cache<String, Integer> cache;

    public static void main(String[] args) {
        //Initializing the cluster
        Cluster server = new Cluster();
        server.init();

        App app = new App();
        app.initCache();
        app.cacheProducer();
        app.cacheConsumer();


    }

    public void initCache() {
        URI configUri = new File("/src/main/resources/hazelcast-client.xml").toURI();

        this.cachingProvider = Caching.getCachingProvider();
        this.cacheManager = cachingProvider.getCacheManager(configUri, null);

        //Cache Configuration

        MutableConfiguration<String, Integer> config = new MutableConfiguration<String, Integer>();
        config.setStoreByValue(true)
                .setTypes(String.class, Integer.class)
                .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, 10)))
                .setStatisticsEnabled(false);
        if (cacheManager.getCache("OnlyCache", String.class, Integer.class) != null) {
            //cache should not exist destroy it
            cacheManager.destroyCache("OnlyCache");
        }
        //create the cache

        cache = cacheManager.createCache("OnlyCache", config);

    }

    public void cacheProducer() {

        final Random random = new Random();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {


                for (int i = 0; i < 100; i++) {


                    String currentKey = String.valueOf(i);
                    Integer currentVal = i * 10;
                    cache.put(currentKey, currentVal);
                    System.out.println("Added:  Key : " + currentKey + " Value : " + currentVal);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }

            }
        });
        t.start();

    }

    public void cacheConsumer() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    final Iterator<Cache.Entry<String, Integer>> iterator = cache.iterator();
                    while (iterator.hasNext()) {
                        final Cache.Entry<String, Integer> entry = iterator.next();
                        //value maybe null which means it is expired
                        if (entry.getValue() != null) {
                            System.out.println("Removed:  Key : " + entry.getKey() + " Value : " + entry.getValue());
                            //poor entry just remove
                            iterator.remove();
                            //do something with entry, save to db etc.
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }
        });
        t.start();


    }
}
