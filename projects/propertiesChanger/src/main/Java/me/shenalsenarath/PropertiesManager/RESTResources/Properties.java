package me.shenalsenarath.PropertiesManager.RESTResources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Properties REST Resource
 *
 * Created by shselk on 12/5/2014.
 */


@Path("/properties")
public class Properties {

    @GET
    @Path("/{propertyName}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getProperty(@PathParam("propertyName") String propertyName){
        //TODO implement
        System.out.println("get property : " + propertyName);

        return propertyName;
    }


    /**
     * This will add new property to the cache. It will replace the value if the property already exist in the cache
     * @param name String Name(Key) of the property
     * @param val String Value of the property
     */
    @PUT
    @Path("/add/{propertyName}/{propertyValue}")
    public void addProperty(@PathParam("propertyName") String name, @PathParam("propertyValue") String val) {
        //TODO implement
        System.out.println("Add property : key"+name+" Val: "+val);
    }


    /**
     * Clears the full Properties Cache and sets the cache to non initialized status
     */
    @DELETE
    public void deleteAll(){
        System.out.println("delete all");
        //TODO implement
    }

    /**
     * Deletes the given property from the Cache
     * @param propertiesName Name of the property that needed to be deleted
     */
    @DELETE
    @Path("/{propertiesName}")
    public void delete(@PathParam("propertiesName") String propertiesName){
        //TODO implement
        System.out.println("delete : " + propertiesName );
    }









}
