package me.shenalsenarath;



import javax.cache.event.*;
import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by shselk on 11/14/2014.
 */
public class CacheListener<K,V> implements CacheEntryCreatedListener<K,V>,CacheEntryUpdatedListener<K, V>, CacheEntryExpiredListener<K, V>,
        CacheEntryRemovedListener<K, V>, Serializable {


    public CacheListener() {
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

    private String printEvent(Iterable<CacheEntryEvent<? extends K, ? extends V>> cacheEntryEvents){
        StringBuilder sb = new StringBuilder();
        final Iterator<CacheEntryEvent<? extends K, ? extends V>> iterator = cacheEntryEvents.iterator();
        while (iterator.hasNext()){
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
