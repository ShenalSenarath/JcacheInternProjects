package me.shenalsenarath.PropertiesManager.PropertiesCache;

import javax.cache.event.*;
import java.io.Serializable;

/**
 * Created by shselk on 11/27/2014.
 */
public class PropertiesCacheListener<K,V> implements CacheEntryCreatedListener<K,V>, CacheEntryUpdatedListener<K,V>,CacheEntryRemovedListener<K, V>, Serializable {
    @Override
    public void onCreated(Iterable<CacheEntryEvent<? extends K, ? extends V>> cacheEntryEvents) throws CacheEntryListenerException {
        System.out.println("*********************************************************Created");
    }

    @Override
    public void onUpdated(Iterable<CacheEntryEvent<? extends K, ? extends V>> cacheEntryEvents) throws CacheEntryListenerException {
        System.out.println("**********************************************************updated");
    }

    @Override
    public void onRemoved(Iterable<CacheEntryEvent<? extends K, ? extends V>> events) throws CacheEntryListenerException {
        System.out.println("**********************************************************Removed");
    }
}
