package me.shenalsenarath.PropertiesParser;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by shselk on 11/26/2014.
 */

public class PropertiesParser {
    private Properties properties;
    private String fileAddress;

    public PropertiesParser(String fileAddress) {
        this.fileAddress = fileAddress;

    }

    public Properties getProperties() throws IOException {
        FileInputStream inputStream = new FileInputStream(fileAddress);
        properties = new Properties();
        properties.load(inputStream);
        return properties;
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();

        Enumeration<?> e = properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = properties.getProperty(key);
            returnString.append("Key : " + key + ", Value : " + value +"\n");

        }
        return returnString.toString();
    }
}
