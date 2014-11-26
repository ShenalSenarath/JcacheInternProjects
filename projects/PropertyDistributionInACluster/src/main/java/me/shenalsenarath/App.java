package me.shenalsenarath;


import me.shenalsenarath.PropertiesParser.PropertiesParser;


import java.io.IOException;
import java.util.Properties;

/**
 * Created by shselk on 11/25/2014.
 */
public class App {
    public static void main (String args[]){
        PropertiesParser parser = new PropertiesParser("config.properties");

        try {
            Properties configProperties = parser.getPropertise();
        } catch (IOException e) {
            System.out.println("Properties file not find!");
            System.exit(10);
        }

        System.out.println(parser);


    }





}

