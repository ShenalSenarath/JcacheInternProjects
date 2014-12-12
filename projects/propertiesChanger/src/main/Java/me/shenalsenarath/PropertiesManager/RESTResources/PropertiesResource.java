package me.shenalsenarath.PropertiesManager.RESTResources;

import me.shenalsenarath.PropertiesManager.PropertiesCache.PropertiesCache;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Properties REST Resource
 * <p/>
 * Created by shselk on 12/5/2014.
 */

//TODO have to set concurrency

@Path("/properties")
public class PropertiesResource {

    /**
     * This will return all the properties from the properties cache.
     *
     * @return all properties from the cache
     */
    @GET
    public Properties getAllProperties() {
        //System.out.println("get property all properties");
        PropertiesCache cache = new PropertiesCache();
        Properties returnProperties = cache.getAllProperties();
        returnProperties.remove(PropertiesCache.ALLPROPERTIESSET);
        returnProperties.remove(PropertiesCache.PROPERTIESOWNERUUID);
        return returnProperties;
    }

    /**
     * This will add new properties to the cache. It will replace the value if the property already exist in the cache
     *
     * @param properties
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addProperty(Properties properties) {
        //TODO check consistency

        PropertiesCache propertiesCache = new PropertiesCache();
        Enumeration enumeration = properties.keys();
        if (!propertiesCache.isInitialized())
            propertiesCache.initCache();
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            String value = properties.getProperty(key);
            propertiesCache.putProperty(key, value);
        }
    }


    /**
     * Deletes the properties from the cache.
     *
     * @param properties the properties that should be deleted
     */
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteAll(Properties properties) {
        PropertiesCache propertiesCache = new PropertiesCache();
        Enumeration enumeration = properties.keys();
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            propertiesCache.removeProperty(key);
        }
    }

}
