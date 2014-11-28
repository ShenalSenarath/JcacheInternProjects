package me.shenalsenarath.PropertiesCache;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.cache.Caching;
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
    static final String CACHENAME = "PropertiesCache";
    static final String ALLPROPERTIESSET = "AllPropertiesSet";


    public PropertiesCache() {
        cachingProvider = Caching.getCachingProvider();
        cacheManager = cachingProvider.getCacheManager();
    }

    public void initCache() throws CacheException{
        //configure the cache
        MutableConfiguration<String, String> config = new MutableConfiguration<String, String>();
        config.setStoreByValue(true)
                .setTypes(String.class, String.class)
                .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(Duration.ETERNAL))
                .setStatisticsEnabled(false);

        //createCache
        cacheManager.createCache(CACHENAME, config);

        //Add properties not present key
        getCache().putIfAbsent(ALLPROPERTIESSET, "false");
    }


    public void setAllPropertiesSet(){
        Cache<String, String> cache = getCache();
        cache.replace(ALLPROPERTIESSET,"false","true");
    }

    public boolean isInitialized() {
        if (cacheManager.getCache(CACHENAME, String.class, String.class) != null)
            return true;
        return false;
    }

    public boolean isAllPropertiesSet() {
        Cache<String, String> cache = getCache();
        String status;
        try {
            status = cache.get(ALLPROPERTIESSET);
        } catch (NullPointerException e) {
            return false;
        }
        if (status.equals("true")) {
            return true;
        } else {
            return false;
        }
    }
    public void putProperty(String name, String value){
        Cache<String, String> cache = getCache();
        cache.put(name,value);
    }

    public String getPropertyVal(String propertyName) {
        Cache<String, String> cache = getCache();
        String returnString = null;

        try {
            returnString = cache.get(propertyName);
            return returnString;
        } catch (NullPointerException e) {
            return null;
        }
    }

    private Cache<String, String> getCache() throws NullPointerException {
        return cacheManager.getCache(CACHENAME, String.class, String.class);
    }

    public void distroyCache() {
        this.cacheManager.getCache(CACHENAME, String.class, String.class).close();
        this.cacheManager.destroyCache(CACHENAME);
        this.cacheManager.close();
        this.cachingProvider.close();
    }


}
