package me.shenalsenarath.JCache;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;

import java.util.concurrent.TimeUnit;

/**
 * Created by shselk on 11/21/2014.
 */
public class JCache {

    private static Duration TEN_SEC = new Duration(TimeUnit.SECONDS,60);

    CachingProvider cachingProvider;
    CacheManager cacheManager;

    public JCache() {
        this.cachingProvider = Caching.getCachingProvider();
        this.cacheManager = this.cachingProvider.getCacheManager();

    }

    public Cache getCache(String name){
        //configure the cache
        MutableConfiguration<Integer, String> config = new MutableConfiguration<Integer, String>();
        config.setStoreByValue(true)
                .setTypes(Integer.class, String.class)
                .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(TEN_SEC))
                .setStatisticsEnabled(false);

        cacheManager.createCache(name,config);

        return cacheManager.getCache(name,Integer.class, String.class);
    }


    public void shutdown (){
        this.cacheManager.close();

    }
    public void destroy(String name){
        this.cacheManager.getCache(name,Integer.class, String.class).close();
        this.cacheManager.close();
        this.cachingProvider.close();

    }
}
