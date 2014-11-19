package me.shenalsenarath;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.CompleteConfiguration;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableCacheEntryListenerConfiguration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Created by shselk on 11/19/2014.
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        // Configuring the server mode
        System.setProperty("hazelcast.jcache.provider.type", "server");

        // Create the fake database access
        CustomModelDAO customModelDAO = new CustomModelDAO();
        System.out.println("DAO Created");

        //Create the Cache
        Cache<Integer, CustomModel> cache = getCache(customModelDAO);
        System.out.println("Cache Created");



        //
        for (int i = 0; i < 10 ; i++) {
            addDAO(customModelDAO,i);
        }

        getAllDAO(customModelDAO);
        listAllCache(cache,customModelDAO);

        Thread.sleep(15*1000);

        listAllCache(cache,customModelDAO);
    }

//======================================Cache Operations================================================================
    private static void addCache(Cache<Integer, CustomModel> cache, int id) {
        CustomModel newModel = new CustomModel(id, "F1 :" + id, "F2 :" + id);
        cache.put(id, newModel);
        System.out.println("Cache Added - " + newModel.toString());
    }

    private static CustomModel getCache(Cache<Integer, CustomModel> cache, int id){
        CustomModel result;
        result=cache.get(id);
        System.out.println("Cache Retrieved - " + result.toString());
        return result;
    }
    private static void removeCache(Cache<Integer, CustomModel> cache, int id){
        cache.remove(id);
        System.out.println("Cache Removed - ID: " + id);
    }
    private static void listAllCache(Cache<Integer, CustomModel> cache, CustomModelDAO customModelDAO){
        Collection<Integer> keys = customModelDAO.allUserIds();
        System.out.println("Cache Full List Request: ");
        for (Integer id : keys) {
            CustomModel tempModel = cache.get(id);
            System.out.println(tempModel.toString());
        }

    }
    private static void listCache(Cache<Integer, CustomModel> cache){

        System.out.println("Cache Content List: Not implemented");
    }

//==================================-End- Cache Operations==============================================================


//=================================Direct DAO Operations================================================================

    private static void getAllDAO(CustomModelDAO customModelDAO) {
        Collection<Integer> allUserIds = customModelDAO.allUserIds();
        System.out.println("DAO Full List : ");
        for (Integer id : allUserIds) {
            System.out.println(customModelDAO.findById(id).toString());
        }
    }

    private static void addDAO(CustomModelDAO customModelDAO, int id) {
        CustomModel newModel = new CustomModel(id, "F1 :" + id, "F2 :" + id);
        customModelDAO.storeCustomModel(id, newModel);
        System.out.println("DAO Added - " + newModel.toString());
    }

    private static CustomModel getDAO(CustomModelDAO customModelDAO, int id) {
        CustomModel result = customModelDAO.findById(id);
        System.out.println("DAO Retrieved - " + result.toString());
        return result;
    }

    private static void removeDAO(CustomModelDAO customModelDAO, int id) {
        customModelDAO.removeUser(id);
        System.out.println("DAO Removed - ID: " + id);
    }
//===============================-End- Direct DAO Operations============================================================


    /**
     * @param customModelDAO
     * @return Cache
     * <p/>
     * This method configures the cache with the DAO and event listener.
     */
    private static Cache<Integer, CustomModel> getCache(CustomModelDAO customModelDAO) {
        // Explicitly retrieve the Hazelcast backed javax.cache.spi.CachingProvider
        CachingProvider cachingProvider = Caching.getCachingProvider("com.hazelcast.cache.impl.HazelcastCachingProvider");

        CacheManager cacheManager = cachingProvider.getCacheManager();

        //configuration
        CompleteConfiguration<Integer, CustomModel> configuration = new MutableConfiguration<Integer, CustomModel>()
                .setTypes(Integer.class, CustomModel.class)
                .setExpiryPolicyFactory(FactoryBuilder.factoryOf(new AccessedExpiryPolicy(new Duration(TimeUnit.SECONDS, 10))))
                .setReadThrough(true)
                .setWriteThrough(true)
                .setCacheLoaderFactory(FactoryBuilder.factoryOf(new CustomModelCacheLoader(customModelDAO)))
                .setCacheWriterFactory(FactoryBuilder.factoryOf(new CustomModelCacheWriter(customModelDAO)))
                .addCacheEntryListenerConfiguration(new MutableCacheEntryListenerConfiguration<Integer, CustomModel>(new CacheListener(), null, true, true));


        return cacheManager.createCache("cache", configuration);
    }
}
