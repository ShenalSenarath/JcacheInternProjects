package me.shenalsenarath;


import me.shenalsenarath.AppStarter.AppStarter;
import me.shenalsenarath.PropertiesParser.PropertiesParser;


import java.io.IOException;
import java.util.Properties;

/**
 * Created by shselk on 11/25/2014.
 */


public class App {
    public static void main (String args[]) throws Exception {
        AppStarter appStarter = new AppStarter();
        String[] propertiesNames = {"name", "age"};

        Properties properties = appStarter.getProperties(propertiesNames);
        System.out.println(properties.toString());

    }

    public static void testPropetiesParser(){


        PropertiesParser parser = new PropertiesParser("config.properties");

        try {
            Properties configProperties = parser.getPropertiesFromFile();
        } catch (IOException e) {
            System.out.println("Properties file not find!");
            System.exit(10);
        }

        System.out.println(parser);
    }





}

