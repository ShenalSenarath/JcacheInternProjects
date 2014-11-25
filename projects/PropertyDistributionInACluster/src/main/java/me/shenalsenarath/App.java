package main.java.me.shenalsenarath;

import main.java.me.shenalsenarath.Models.Property;
import main.java.me.shenalsenarath.XMLParsers.PropertiesXMLWriter;
import main.java.me.shenalsenarath.XMLParsers.PropertiseXMLReader;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by shselk on 11/25/2014.
 */
public class App {
    public static void main (String args []) throws FileNotFoundException, XMLStreamException {

        Property p1 = new Property("name","shenal");
        Property p2 = new Property("age","15");



        PropertiesXMLWriter propertiesXmlWriter = new PropertiesXMLWriter();

        propertiesXmlWriter.addProperty(p1);
        propertiesXmlWriter.addProperty(p2);

        propertiesXmlWriter.writeXML("testShenal.xml");


        PropertiseXMLReader propertiseXMLReader = new PropertiseXMLReader();

        ArrayList<Property> properties=propertiseXMLReader.readPropertiseXML("testShenal.xml");
        for (Property p : properties){
            System.out.println(p);
        }

    }
}
