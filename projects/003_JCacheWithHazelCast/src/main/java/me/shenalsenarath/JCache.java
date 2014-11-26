package me.shenalsenarath;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;
import java.util.concurrent.TimeUnit;


/**
 * Created by shselk on 11/13/2014.
 */


public class JCache {

    private static Duration TEN_SEC = new Duration(TimeUnit.SECONDS,10);

    CachingProvider cachingProvider;
    CacheManager cacheManager;

    public JCache() {
        this.cachingProvider = Caching.getCachingProvider();
        this.cacheManager = this.cachingProvider.getCacheManager();

    }

    public Cache getCache(String name){
        //configure the cache
        MutableConfiguration<String, Integer> config = new MutableConfiguration<String, Integer>();
        config.setStoreByValue(true)
                .setTypes(String.class, Integer.class)
                .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(TEN_SEC))
                .setStatisticsEnabled(false);

        cacheManager.createCache(name,config);

        return cacheManager.getCache(name,String.class,Integer.class);
    }

    public void shutdown (){
        this.cacheManager.close();

    }


}
