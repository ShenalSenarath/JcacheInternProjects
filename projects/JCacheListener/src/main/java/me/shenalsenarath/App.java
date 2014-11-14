package me.shenalsenarath;


import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableCacheEntryListenerConfiguration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.spi.CachingProvider;
import java.util.concurrent.TimeUnit;
import javax.cache.expiry.Duration;

/**
 * Created by shselk on 11/14/2014.
 */
public class App 
{
    private static Duration TEN_SEC = new Duration(TimeUnit.SECONDS,10);

    CachingProvider cachingProvider;
    CacheManager cacheManager;
    Cache<String,Integer> cache;

    public static void main( String[] args ) throws InterruptedException {
        App app =new App();

        //initializing the cache manager

        app.cachingProvider = Caching.getCachingProvider();
        app.cacheManager=app.cachingProvider.getCacheManager();


        //initializing the cache======================================================

        //configure the cache
        MutableConfiguration<String, Integer> config = new MutableConfiguration<String, Integer>();
        config.setStoreByValue(true)
                .setTypes(String.class, Integer.class)
                .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(TEN_SEC))
                .setStatisticsEnabled(false);

        app.cacheManager.createCache("TestCache",config);

        app.cache=app.cacheManager.getCache("TestCache",String.class,Integer.class);

        //============================================================================



        //Initializing the Listener===================================================

        //create the EntryListener
        CacheListener<String, Integer> listener =  new CacheListener<String, Integer>();

        //using out listener, lets create a configuration
        CacheEntryListenerConfiguration<String, Integer> conf = new MutableCacheEntryListenerConfiguration<String, Integer>(FactoryBuilder.factoryOf(listener), null, true, false);

        //register it to the cache at run-time
        app.cache.registerCacheEntryListener(conf);

        //============================================================================


        //this will fire create event
        app.cache.put("theKey", 66);

        //but this one will fire an update event as we have it already
        app.cache.put("theKey", 111);

        //fire remove
        app.cache.remove("theKey");

        //lets put a value and then access it to start an expiry
        app.cache.put("theKey", 88);
        app.cache.get("theKey");

        //lets wait for 10 sec to expire the content
        Thread.sleep(10 * 1000);

        //will force to expire if we access it and fire expire event
        app.cache.get("theKey");


    }
}
