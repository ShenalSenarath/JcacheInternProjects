package me.shenalsenarath.Testing;


import me.shenalsenarath.PropertiesCache.PropertiesCache;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableCacheEntryListenerConfiguration;
import javax.cache.event.*;
import javax.cache.spi.CachingProvider;
import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by shselk on 11/28/2014.
 */
public class Listener<K, V> implements CacheEntryCreatedListener<K, V>, CacheEntryUpdatedListener<K, V>, CacheEntryExpiredListener<K, V>,
        CacheEntryRemovedListener<K, V>, Serializable {
    private static final String CACHENAME = "PropertiesCache";
    private static final String ALLPROPERTIESSET = "AllPropertiesSet";

    public static void main(String args[]) {

        PropertiesCache  propertiesCache = new PropertiesCache();
        propertiesCache.initCache();
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
        Cache cache = cacheManager.getCache(CACHENAME, String.class, String.class);


        //create the EntryListener
        Listener<String, Integer> listener =  new Listener<String, Integer>();

        //using out listener, lets create a configuration
        CacheEntryListenerConfiguration<String, Integer> conf = new MutableCacheEntryListenerConfiguration<String, Integer>(FactoryBuilder.factoryOf(listener), null, true, false);

        //register it to the cache at run-time
        cache.registerCacheEntryListener(conf);


    }


    @Override
    public void onCreated(Iterable<CacheEntryEvent<? extends K, ? extends V>> cacheEntryEvents) throws CacheEntryListenerException {
        System.out.print("Created : ");
        System.out.println(printEvent(cacheEntryEvents));
    }

    @Override
    public void onExpired(Iterable<CacheEntryEvent<? extends K, ? extends V>> cacheEntryEvents) throws CacheEntryListenerException {
        System.out.print("Expired : ");
        System.out.println(printEvent(cacheEntryEvents));
    }

    @Override
    public void onRemoved(Iterable<CacheEntryEvent<? extends K, ? extends V>> cacheEntryEvents) throws CacheEntryListenerException {
        System.out.print("Removed : ");
        System.out.println(printEvent(cacheEntryEvents));
    }

    @Override
    public void onUpdated(Iterable<CacheEntryEvent<? extends K, ? extends V>> cacheEntryEvents) throws CacheEntryListenerException {
        System.out.print("Updated : ");
        System.out.println(printEvent(cacheEntryEvents));
    }

    private String printEvent(Iterable<CacheEntryEvent<? extends K, ? extends V>> cacheEntryEvents) {
        StringBuilder sb = new StringBuilder();
        final Iterator<CacheEntryEvent<? extends K, ? extends V>> iterator = cacheEntryEvents.iterator();
        while (iterator.hasNext()) {
            final CacheEntryEvent<? extends K, ? extends V> next = iterator.next();
            sb.append("Key: ");
            sb.append(next.getKey());
            sb.append(", Value:");
            sb.append(next.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }


}

