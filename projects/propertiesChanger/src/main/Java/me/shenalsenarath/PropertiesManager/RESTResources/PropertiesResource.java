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


@Path("/properties")
public class PropertiesResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Properties getAllProperties() {
        //System.out.println("get property all properties");
        PropertiesCache cache = new PropertiesCache();
        Properties returnProperties = cache.getAllProperties();
        returnProperties.remove(PropertiesCache.ALLPROPERTIESSET);
        returnProperties.remove(PropertiesCache.PROPERTIESOWNERUUID);
        return returnProperties;
    }

    @GET
    @Path("/{propertyName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Properties getProperty(@PathParam("propertyName") String propertyName) {

        //System.out.println("get property : " + propertyName);
        PropertiesCache cache = new PropertiesCache();
        if (cache.isInitialized()) {
            Properties returnProperties = new Properties();
            returnProperties.setProperty(propertyName, cache.getPropertyVal(propertyName));
            return returnProperties;
        } else {
            return null;
        }
    }


    /**
     * This will add new properties to the cache. It will replace the value if the property already exist in the cache
     *
     * @param properties
     */
    @POST
    @Path("/add}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addProperty(Properties properties) {
        //TODO implement
        PropertiesCache propertiesCache = new PropertiesCache();
        Enumeration enumeration=properties.keys();
        if (propertiesCache.isInitialized()) {
            while (enumeration.hasMoreElements()) {
                String key = (String)enumeration.nextElement();
                String value = properties.getProperty(key);
                propertiesCache.putProperty(key,value);

            }
        }

    }


    /**
     * Clears the full Properties Cache and sets the cache to non initialized status
     */
    @DELETE
    public void deleteAll() {
        System.out.println("delete all");
        //TODO implement
    }

    /**
     * Deletes the given property from the Cache
     *
     * @param propertiesName Name of the property that needed to be deleted
     */
    @DELETE
    @Path("/{propertiesName}")
    public void delete(@PathParam("propertiesName") String propertiesName) {
        //TODO implement
        System.out.println("delete : " + propertiesName);
    }


}
