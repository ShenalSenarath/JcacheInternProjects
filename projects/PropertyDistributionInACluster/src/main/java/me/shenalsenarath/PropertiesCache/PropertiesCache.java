package me.shenalsenarath.PropertiesCache;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;

/**
 * Created by shselk on 11/25/2014.
 */
public class PropertiesCache {
    CachingProvider cachingProvider;
    CacheManager cacheManager;
    static final String CACHENAME="PropertiesCache";

    public Cache initCache(){
        //configure the cache
        MutableConfiguration<String, String> config = new MutableConfiguration<String, String>();
        config.setStoreByValue(true)
                .setTypes(String.class, String.class)
                .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(Duration.ETERNAL))
                .setStatisticsEnabled(false);

        cacheManager.createCache(CACHENAME,config);

        return cacheManager.getCache(CACHENAME,String.class, String.class);
    }




    public void distroyCache(){
        this.cacheManager.getCache(CACHENAME,String.class, String.class).close();
        this.cacheManager.close();
        this.cachingProvider.close();
    }
}
