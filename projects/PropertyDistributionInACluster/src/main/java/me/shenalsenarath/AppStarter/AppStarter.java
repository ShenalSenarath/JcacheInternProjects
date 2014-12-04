package me.shenalsenarath.AppStarter;

import me.shenalsenarath.PropertiesCache.PropertiesCache;
import me.shenalsenarath.PropertiesParser.PropertiesParser;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by shselk on 11/27/2014.
 */
public class AppStarter {

    PropertiesCache propertiesCache;


    /**
     * At the initialization of the AppStarter a properties cache will be created
     */
    public AppStarter() {
        this.propertiesCache = new PropertiesCache();
    }

    /**
     * When the run method it will run the following Sequence of operations
     * 1.) Checks whether the cache with the name of "PropertiesCache" is present.
     *      1.1) if Present:
     *          1.1.1) Checks whether all properties are already initialized in the Cache
     *              1.1.1.1) If initialized:
     *                  1.1.1.1.1) get properties
     *                  1.1.1.1.2) return properties
     *
     *              1.1.1.2) If not initialized:
     *                  1.1.1.2.1) Go to 1.3
     *
     *      1.2) if NOT Present:
     *          1.2.1) Initialize the Cache
     *          1.2.2) Go to 1.3
     *
     *      1.3)
     *          1.3.1) Add an entry to the cache with Key :"AllPropertiesSet" and Value: "false"
     *          1.3.2) Checks for the File in the local location
     *              1.3.2.1) If Present:
     *                  1.3.2.1.1) Get all the properties from the file
     *                  1.3.2.1.2) Put all to the cache
     *                  1.3.2.1.3) Change the value of the entry with key: "AllPropertiesSet" to "true"
     *                  1.3.2.1.4) return all properties
     *              1.3.2.2) If not present:
     *                  1.3.2.2.1) Go to 1.1
     *
     *          1.3.5) Return
     *
     */
    public Properties getProperties(String [] propertiesNames) throws Exception {

        Properties appProperties = null;

        try {
            propertiesCache.initCache();// Initializes the cache if it doesn't exists
        }
        finally {

            //Checks whether all properties are set in the cache(1.1.1)
            if (propertiesCache.isAllPropertiesSet()) { //Properties are all set(1.1.1)
                appProperties = new Properties();
                System.out.println("Reading properties from cache...");
                for (String name: propertiesNames){
                    if(propertiesCache.getPropertyVal(name)!=null)
                        appProperties.setProperty(name,propertiesCache.getPropertyVal(name));

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
                    throw new Exception("No place to find properties: Neither cache nor local file exists");
                }
            }
        }
        return appProperties;
    }

    private Properties getPropertiesFromLocalFile() throws IOException {
        PropertiesParser propertiesParser = new PropertiesParser("config.properties");
        return propertiesParser.getProperties();
    }
    private void waitTillPropertiesAvailable(){

    }


}
