package me.shenalsenarath;

import org.omg.PortableServer.ServantActivator;

import javax.cache.integration.CacheLoader;
import javax.cache.integration.CacheLoaderException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shselk on 11/18/2014.
 */
public class CustomModelCacheLoader implements CacheLoader<Integer,CustomModel>,Serializable{
    private CustomModelDAO customModelDAO;

    public CustomModelCacheLoader(CustomModelDAO customModelDAO) {
        this.customModelDAO = customModelDAO;
    }

    @Override
    public CustomModel load(Integer key) throws CacheLoaderException {

        return customModelDAO.findById(key);
    }

    @Override
    public Map<Integer, CustomModel> loadAll(Iterable<? extends Integer> keys) throws CacheLoaderException {
        Map<Integer, CustomModel> loaded = new HashMap<Integer, CustomModel>();
        for (Integer key: keys){
            CustomModel customModel =customModelDAO.findById(key);

            if (customModel != null){
                loaded.put(key,customModel);
            }
        }

        return loaded;
    }
}
