package me.shenalsenarath.PropertiesManager.RESTResources;


import me.shenalsenarath.PropertiesManager.PropertiesCache.PropertiesCache;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * Created by shselk on 12/5/2014.
 */
@Path("/cache")
public class Cache {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String CacheStatus(){
        PropertiesCache propertiesCache= new PropertiesCache();
        if (propertiesCache.isInitialized()){
            if (propertiesCache.isAllPropertiesSet()){
                return "Cache initialized and All Properties Set";
            }else{
                return "Cache initialized and All Properties Not Set";
            }

        }else {
            return "Cache is not initialized";
        }

    }



}
