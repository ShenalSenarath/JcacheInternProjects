package main.java.me.shenalsenarath.XMLParsers;

import main.java.me.shenalsenarath.Models.Property;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by shselk on 11/25/2014.
 */
public class PropertiseXMLReader {
    static final String PROPERTIES = "properties";
    static final String PROPERTY ="property";
    static final String NAME="name";
    static final String VALUE="value";

    public ArrayList<Property> readPropertiseXML(String fileAddress) throws XMLStreamException, FileNotFoundException {

        ArrayList<Property> properties = new ArrayList<Property>();

        //Create a new XMLInputFactory
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        // Setup a new eventReader
        InputStream in = new FileInputStream(fileAddress);
        XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
        // read the XML document
        Property property = null;

        while(eventReader.hasNext()){
            XMLEvent event = eventReader.nextEvent();

            if(event.isStartElement())//when a property tag is discovered
                if(event.asStartElement().getName().getLocalPart().equals(PROPERTY)){
                    property = new Property();
                }

            if (event.isStartElement())
                if(event.asStartElement().getName().getLocalPart().equals(NAME)){
                    event = eventReader.nextEvent();
                    property.setPropertyName(event.asCharacters().getData());

                }
            if (event.isStartElement())
                if(event.asStartElement().getName().getLocalPart().equals(VALUE)){
                    event = eventReader.nextEvent();
                    property.setPropertyValue(event.asCharacters().getData());

                }
            if (event.isEndElement())
                if(event.asEndElement().getName().getLocalPart().equals(PROPERTY)){
                    properties.add(property);
                }



        }

        


        return properties;
    }
}
