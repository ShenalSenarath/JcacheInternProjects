package me.shenalsenarath;

import javax.cache.Cache;
import javax.cache.integration.CacheWriter;
import javax.cache.integration.CacheWriterException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by shselk on 11/18/2014.
 */
public class CustomModelCacheWriter implements CacheWriter<Integer,CustomModel>, Serializable {

    private CustomModelDAO customModelDAO;

    public CustomModelCacheWriter(CustomModelDAO customModelDAO) {
        this.customModelDAO = customModelDAO;
    }

    @Override
    public void write(Cache.Entry<? extends Integer, ? extends CustomModel> entry) throws CacheWriterException {
        this.customModelDAO.storeCustomModel(entry.getKey(),entry.getValue());
    }

    @Override
    public void writeAll(Collection<Cache.Entry<? extends Integer, ? extends CustomModel>> entries) throws CacheWriterException {
        Iterator<Cache.Entry<? extends Integer, ?extends CustomModel>> iterator = entries.iterator();

        while (iterator.hasNext()){
            write(iterator.next());
            iterator.remove();
        }
    }

    @Override
    public void delete(Object key) throws CacheWriterException {
        if (!(key instanceof Integer)){
            throw new CacheWriterException("Illegal key type");
        }

        customModelDAO.removeUser((Integer)key);

    }

    @Override
    public void deleteAll(Collection<?> keys) throws CacheWriterException {
        Iterator<?> iterator = keys.iterator();

        while (iterator.hasNext()){
            delete((Integer)iterator.next());
            iterator.remove();
        }
    }
}
