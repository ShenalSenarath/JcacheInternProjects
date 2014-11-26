package me.shenalsenarath;


import javax.cache.configuration.Factory;
import javax.cache.event.*;
import java.util.Iterator;

/**
 * Created by shselk on 11/19/2014.
 */
public class CacheListener implements CacheEntryCreatedListener<Integer, CustomModel>,
        CacheEntryUpdatedListener<Integer, CustomModel>,
        CacheEntryExpiredListener<Integer, CustomModel>,
        CacheEntryRemovedListener<Integer, CustomModel>,
        Factory<CacheEntryListener<Integer, CustomModel>>

{
    @Override
    public void onCreated(Iterable<CacheEntryEvent<? extends Integer, ? extends CustomModel>> cacheEntryEvents) throws CacheEntryListenerException {
        printEvents(cacheEntryEvents);
    }

    @Override
    public void onExpired(Iterable<CacheEntryEvent<? extends Integer, ? extends CustomModel>> cacheEntryEvents) throws CacheEntryListenerException {
        printEvents(cacheEntryEvents);
    }

    @Override
    public void onRemoved(Iterable<CacheEntryEvent<? extends Integer, ? extends CustomModel>> cacheEntryEvents) throws CacheEntryListenerException {
        printEvents(cacheEntryEvents);
    }

    @Override
    public void onUpdated(Iterable<CacheEntryEvent<? extends Integer, ? extends CustomModel>> cacheEntryEvents) throws CacheEntryListenerException {
        printEvents(cacheEntryEvents);
    }

    private void printEvents(Iterable<CacheEntryEvent<? extends Integer, ? extends CustomModel>> cacheEntryEvents) {
        Iterator<CacheEntryEvent<? extends Integer, ? extends CustomModel>> iterator;
        iterator = cacheEntryEvents.iterator();
        while (iterator.hasNext()) {
            CacheEntryEvent<? extends Integer, ? extends CustomModel> event = iterator.next();
            System.out.println(event.getEventType() + " Key: " + event.getKey() + " Value: " + event.getValue());
        }
    }

    @Override
    public CacheEntryListener<Integer, CustomModel> create() {
        return new CacheListener();
    }
}

