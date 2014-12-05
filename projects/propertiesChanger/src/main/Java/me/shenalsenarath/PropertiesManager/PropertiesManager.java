package me.shenalsenarath.PropertiesManager;

import me.shenalsenarath.PropertiesManager.RESTResources.Cache;
import me.shenalsenarath.PropertiesManager.RESTResources.Properties;

import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by shselk on 12/5/2014.
 */

@ApplicationPath("/")
public class PropertiesManager extends Application{
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(Cache.class);
        s.add(Properties.class);
        return s;
    }
}
