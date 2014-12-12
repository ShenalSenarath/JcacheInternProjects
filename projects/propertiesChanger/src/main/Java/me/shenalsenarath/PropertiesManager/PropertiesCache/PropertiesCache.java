package me.shenalsenarath.PropertiesManager.PropertiesCache;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableCacheEntryListenerConfiguration;
import javax.cache.configuration.MutableConfiguration;

import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;
import java.util.Iterator;
import java.util.Properties;
import java.util.UUID;


/**
 * Created by shselk on 11/25/2014.
 */

public class PropertiesCache {
    public static final String ALLPROPERTIESSET = "AllPropertiesSet";
    public static final String PROPERTIESOWNERUUID = "PropertiesOwnerUUID";
    static final String CACHENAME = "PropertiesCache";
    CachingProvider cachingProvider;
    CacheManager cacheManager;
    UUID uuid;

    /**
     * At the initializing of the the class Caching provider will be started and the cache manager will be instantiated.
     * And the UUID is created. So it will identify the node of the cluster uniquely.
     */
    public PropertiesCache() {
        cachingProvider = Caching.getCachingProvider();
        cacheManager = cachingProvider.getCacheManager();
        uuid = UUID.randomUUID();
    }

    /**
     * This will create the Properties Cache in the cluster if not present and it will put a "false" value to the
     * entry with the key "AllPropertiesSet".
     *
     * @throws CacheException if already created in the cluster.
     */
    public void initCache() throws CacheException {
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

    /**
     * This will change the value of the entry with the key "AllPropertiesSet", "false" to "true"
     */
    public void setAllPropertiesSet() {
        Cache<String, String> cache = getCache();
        cache.replace(ALLPROPERTIESSET, "false", "true");
    }

    /**
     * @return boolean true if the cache is already initialized. false when the cache is not initialized.
     */
    public boolean isInitialized() {
        return cacheManager.getCache(CACHENAME, String.class, String.class) != null;
    }

    /**
     * UUID will be put to the cache and will again be checked to see if some node else has gained the lock.
     * This will check whether the current node is the owner of the properties in the cache.
     *
     * @return boolean true if the current node took the lock.
     */
    public boolean acquirePropertiesInitLock() {
        Cache<String, String> cache = getCache();
        cache.putIfAbsent(PROPERTIESOWNERUUID, uuid.toString());
        return cache.get(PROPERTIESOWNERUUID).equals(uuid.toString());

    }

    /**
     * This method will check the cache for the value of "AllPropertiesSet" key.
     *
     * @return boolean true if value is String true or else boolean false
     */
    public boolean isAllPropertiesSet() {
        Cache<String, String> cache = getCache();
        String status;
        try {
            status = cache.get(ALLPROPERTIESSET);
        } catch (NullPointerException e) {
            return false;
        }
        return status.equals("true");
    }

    /**
     * This will remove the property from the cache
     *
     * @param name of the property to be removed.
     */
    public void removeProperty(String name){
        Cache<String, String> cache = getCache();
        cache.remove(name);
    }

    /**
     * This method will be used to put properties to the Cache
     *
     * @param name  is the name of the property that needed to be put to Properties Cache
     * @param value is the value of the property that needed to be put to Properties Cache
     */
    public void putProperty(String name, String value) {
        Cache<String, String> cache = getCache();
        cache.put(name, value);
    }

    /**
     * Gets all the properties from the cache and creates a Properties Object
     *
     * @return Properties object containing all the entries in the properties cache
     */
    public Properties getAllProperties() {
        Cache<String, String> cache = getCache();
        Properties returnProperties = new Properties();
        Iterator<Cache.Entry<String, String>> allCacheEntries = cache.iterator();
        while (allCacheEntries.hasNext()) {
            Cache.Entry<String, String> currentEntry = allCacheEntries.next();
            returnProperties.setProperty(currentEntry.getKey(), currentEntry.getValue());
        }
        returnProperties.remove(ALLPROPERTIESSET);
        returnProperties.remove(PROPERTIESOWNERUUID);

        return returnProperties;
    }

    /**
     * Get the value of the property with the parameter from the Properties Cache.
     * @param propertyName String of the property name.
     * @return get the value of the given property name.
     */
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

    /**
     * Gets the Properties Cache
     *
     * @return Cache
     * @throws NullPointerException
     */
    protected Cache<String, String> getCache() throws NullPointerException {
        return cacheManager.getCache(CACHENAME, String.class, String.class);
    }

    /**
     * This will delete cache this will not notify any listeners.
     */
    public void destroyCache() {
        this.cacheManager.getCache(CACHENAME, String.class, String.class).close();
        this.cacheManager.destroyCache(CACHENAME);
    }

    /**
     * This will delete the cache and stop the caching provider
     */
    public void shutdownCache(){
        this.destroyCache();
        this.cacheManager.close();
        this.cachingProvider.close();
    }

    public void bindListener(){
        //create the EntryListener
        PropertiesCacheListener<String, String> listener =  new PropertiesCacheListener<String, String>();

        //using out listener, lets create a configuration
        CacheEntryListenerConfiguration<String, String> conf = new MutableCacheEntryListenerConfiguration<String, String>(FactoryBuilder.factoryOf(listener), null, true, false);

        //register it to the cache at run-time
        getCache().registerCacheEntryListener(conf);
    }
}
