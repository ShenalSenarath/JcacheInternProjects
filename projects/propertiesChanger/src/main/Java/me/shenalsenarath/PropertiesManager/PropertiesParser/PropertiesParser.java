package me.shenalsenarath.PropertiesManager.PropertiesParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by shselk on 12/11/2014.
 */
public class PropertiesParser {
    private Properties properties;
    private String fileAddress;

    public PropertiesParser(String fileAddress) {
        this.fileAddress = fileAddress;
        properties = new Properties();
    }

    public Properties getPropertiesFromFile() throws IOException {
        FileInputStream inputStream = new FileInputStream(fileAddress);
        properties.load(inputStream);
        return properties;
    }

    public void setPropertiesToFile() throws IOException {
        File file = new File(fileAddress);
        FileOutputStream fileOut = new FileOutputStream(file);
        this.properties.store(fileOut, "Application Configurations");
        fileOut.close();
    }

    public void addProperty(String property, String value) {
        properties.setProperty(property, value);

    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();

        Enumeration<?> e = properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = properties.getProperty(key);
            returnString.append("Key : " + key + ", Value : " + value + "\n");

        }
        return returnString.toString();
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}