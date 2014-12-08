package me.shenalsenarath.PropertiesManager.RESTResources;

import me.shenalsenarath.PropertiesManager.PropertiesCache.PropertiesCache;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Properties;

/**
 * Created by shselk on 12/5/2014.
 */

@Path("/cache")
public class CacheResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Properties CacheStatus() {
        PropertiesCache propertiesCache = new PropertiesCache();
        if (propertiesCache.isInitialized()) {
            if (propertiesCache.isAllPropertiesSet()) {
                return propertiesCache.getAllProperties();
            } else {
                return propertiesCache.getAllProperties();
            }
        } else {
            return null;
        }
    }
}
