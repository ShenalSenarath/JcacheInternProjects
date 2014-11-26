package me.shenalsenarath.PropertiesParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by shselk on 11/26/2014.
 */
public class PropertiesParser {
    private Properties propertise;
    private String fileAddress;

    public PropertiesParser(String fileAddress) {
        this.fileAddress = fileAddress;

    }

    public Properties getPropertise() throws IOException {
        FileInputStream in = new FileInputStream(fileAddress);
        propertise= new Properties();
        propertise.load(in);
        return propertise;
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();

        Enumeration<?> e = propertise.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = propertise.getProperty(key);
            returnString.append("Key : " + key + ", Value : " + value +"\n");

        }
        return returnString.toString();
    }
}
