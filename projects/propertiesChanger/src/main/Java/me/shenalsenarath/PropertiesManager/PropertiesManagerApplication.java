package me.shenalsenarath.PropertiesManager;

import me.shenalsenarath.PropertiesManager.RESTResources.CacheResource;
import me.shenalsenarath.PropertiesManager.RESTResources.PropertiesResource;

import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by shselk on 12/5/2014.
 */

@ApplicationPath("/")
public class PropertiesManagerApplication extends Application{
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(CacheResource.class);
        s.add(PropertiesResource.class);
        return s;
    }
}
