package me.shenalsenarath.PropertiesManager;

import me.shenalsenarath.PropertiesManager.RESTResources.CacheResource;
import me.shenalsenarath.PropertiesManager.RESTResources.PropertiesResource;

/**
 * Created by shselk on 12/4/2014.
 */

public class test {
    public static void main(String args[]){
        CacheResource cacheResource= new CacheResource();
        System.out.println(cacheResource.CacheStatus().toString());

        PropertiesResource propertiesResource =new PropertiesResource();
        System.out.println(propertiesResource.getAllProperties().toString());
    }
}
