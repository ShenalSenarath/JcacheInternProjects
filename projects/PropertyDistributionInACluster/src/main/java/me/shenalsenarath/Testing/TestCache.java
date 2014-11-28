package me.shenalsenarath.Testing;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;

import javax.cache.spi.CachingProvider;

/**
 * Created by shselk on 11/27/2014.
 */
public class TestCache {

    private static final String CACHENAME = "PropertiesCache";
    private static final String ALLPROPERTIESSET = "AllPropertiesSet";

    public static void main(String args []) {


    }
    public void printContent(){
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
        Cache cache = cacheManager.getCache(CACHENAME,String.class,String.class);
        System.out.println("name : "+ cache.get("name"));
        System.out.println("age : "+ cache.get("age"));
        System.out.println("AllPropertiesSet : "+ cache.get(ALLPROPERTIESSET));
    }


}
