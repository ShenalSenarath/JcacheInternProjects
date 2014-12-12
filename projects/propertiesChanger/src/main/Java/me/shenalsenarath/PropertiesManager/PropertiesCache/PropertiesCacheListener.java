package me.shenalsenarath.PropertiesManager.PropertiesCache;

import me.shenalsenarath.PropertiesManager.PropertiesParser.PropertiesParser;

import javax.cache.event.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Properties;

/**
 * Created by shselk on 11/27/2014.
 */
public class PropertiesCacheListener<K,V> implements CacheEntryCreatedListener<K,V>, CacheEntryUpdatedListener<K,V>,CacheEntryRemovedListener<K, V>, Serializable {
    /**
     * Writes to the local properties file the just updated properties
     * @param cacheEntryEvents
     * @throws CacheEntryListenerException
     */
    @Override
    public void onCreated(Iterable<CacheEntryEvent<? extends K, ? extends V>> cacheEntryEvents) throws CacheEntryListenerException {
        System.out.println("*********************************************************Created");
        PropertiesParser propertiesParser =new PropertiesParser("config.properties");
        Properties currentPropertiesInFile;
        try {
            currentPropertiesInFile = propertiesParser.getPropertiesFromFile();
        } catch (IOException e) {
            currentPropertiesInFile = new Properties();
        }
        final Iterator<CacheEntryEvent<? extends K, ? extends V>> iterator = cacheEntryEvents.iterator();
        while (iterator.hasNext()){
            final CacheEntryEvent<? extends K, ? extends V> next = iterator.next();
            currentPropertiesInFile.setProperty(next.getKey().toString(),next.getValue().toString());
        }
        propertiesParser.setProperties(currentPropertiesInFile);
        try {
            propertiesParser.setPropertiesToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onUpdated(Iterable<CacheEntryEvent<? extends K, ? extends V>> cacheEntryEvents) throws CacheEntryListenerException {
        System.out.println("**********************************************************updated");
        PropertiesParser propertiesParser =new PropertiesParser("config.properties");
        Properties currentPropertiesInFile;
        try {
            currentPropertiesInFile = propertiesParser.getPropertiesFromFile();
        } catch (IOException e) {
            currentPropertiesInFile = new Properties();
        }
        final Iterator<CacheEntryEvent<? extends K, ? extends V>> iterator = cacheEntryEvents.iterator();
        while (iterator.hasNext()){
            final CacheEntryEvent<? extends K, ? extends V> next = iterator.next();
            currentPropertiesInFile.setProperty(next.getKey().toString(),next.getValue().toString());
        }
        propertiesParser.setProperties(currentPropertiesInFile);
        try {
            propertiesParser.setPropertiesToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRemoved(Iterable<CacheEntryEvent<? extends K, ? extends V>> cacheEntryEvents) throws CacheEntryListenerException {
        System.out.println("**********************************************************Removed");
        PropertiesParser propertiesParser =new PropertiesParser("config.properties");
        Properties currentPropertiesInFile;
        try {
            currentPropertiesInFile = propertiesParser.getPropertiesFromFile();
        } catch (IOException e) {
            currentPropertiesInFile = new Properties();
        }
        final Iterator<CacheEntryEvent<? extends K, ? extends V>> iterator = cacheEntryEvents.iterator();
        while (iterator.hasNext()){
            final CacheEntryEvent<? extends K, ? extends V> next = iterator.next();
            currentPropertiesInFile.remove(next.getKey().toString());
        }
        propertiesParser.setProperties(currentPropertiesInFile);
        try {
            propertiesParser.setPropertiesToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
