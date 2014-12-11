package me.shenalsenarath.PropertiesManager.ApplicationListerners;

import me.shenalsenarath.PropertiesManager.PropertiesCache.PropertiesCache;
import me.shenalsenarath.PropertiesManager.PropertiesParser.PropertiesParser;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by shselk on 12/11/2014.
 */
@WebListener
public class CacheBindingListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("************************************servlet context init");
        PropertiesCache propertiesCache= new PropertiesCache();
        propertiesCache.initCache();
        propertiesCache.bindListener();

        Properties appProperties=null;
        //Checks whether all properties are set in the cache(1.1.1)
        if (propertiesCache.isAllPropertiesSet()) { //Properties are all set(1.1.1)
            appProperties = new Properties();
            System.out.println("Reading properties from cache...");
            appProperties=propertiesCache.getAllProperties();

            PropertiesParser propertiesParser =new PropertiesParser("config.properties");
            propertiesParser.setProperties(appProperties);
            try {
                propertiesParser.setPropertiesToFile();
            } catch (IOException e) {
                System.out.println("File cannot be accessed for writing.");
            }

        } else {
            try {
                appProperties=getPropertiesFromLocalFile();
                System.out.println("Reading properties from local file...");
                Enumeration keys= appProperties.keys();

                if(propertiesCache.acquirePropertiesInitLock()) {
                    while (keys.hasMoreElements()) {
                        String key = (String) keys.nextElement();
                        propertiesCache.putProperty(key, appProperties.getProperty(key));
                    }
                    propertiesCache.setAllPropertiesSet();
                }
            } catch (IOException e) {
                System.out.println("Local file does not exist");
                waitTillPropertiesAvailable();
                try {
                    throw new Exception("No place to find properties: Neither cache nor local file exists");
                } catch (Exception e1) {
                    System.out.println(e1.toString());
                }
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private Properties getPropertiesFromLocalFile() throws IOException {
        PropertiesParser propertiesParser = new PropertiesParser("config.properties");
        return propertiesParser.getPropertiesFromFile();
    }
    private void waitTillPropertiesAvailable(){
        System.out.println("**************Waiting is not yet implemented***********************");
    }

}
