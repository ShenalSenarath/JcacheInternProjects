package me.shenalsenarath;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.CompleteConfiguration;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        // Configuring the server mode
        System.setProperty("hazelcast.jcache.provider.type", "server");

        // Create the fake database access
        CustomModelDAO customModelDAO = new CustomModelDAO();

        //Create the Cache
        Cache<Integer, CustomModel> cache = getCache(customModelDAO);

    }

    private static Cache<Integer, CustomModel> getCache(CustomModelDAO customModelDAO) {
        // Explicitly retrieve the Hazelcast backed javax.cache.spi.CachingProvider
        CachingProvider cachingProvider = Caching.getCachingProvider("com.hazelcast.cache.impl.HazelcastCachingProvider");

        CacheManager cacheManager = cachingProvider.getCacheManager();

        //configuration
        CompleteConfiguration<Integer, CustomModel> configuration = new MutableConfiguration<Integer, CustomModel>()
                .setTypes(Integer.class, CustomModel.class)
                .setExpiryPolicyFactory(FactoryBuilder.factoryOf(new AccessedExpiryPolicy(new Duration(TimeUnit.SECONDS, 30))))
                .setReadThrough(true)
                .setWriteThrough(true)
                .setCacheLoaderFactory(FactoryBuilder.factoryOf(new CustomModelCacheLoader(customModelDAO)))
                .setCacheWriterFactory(FactoryBuilder.factoryOf(new CustomModelCacheWriter(customModelDAO)));



        return cacheManager.createCache("cache",configuration);
    }
}
