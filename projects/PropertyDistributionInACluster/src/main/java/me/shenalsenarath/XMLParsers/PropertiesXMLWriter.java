package main.java.me.shenalsenarath.XMLParsers;

import main.java.me.shenalsenarath.Models.Property;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by shselk on 11/25/2014.
 */

public class PropertiesXMLWriter {
    private String fileLocation;
    private ArrayList<Property> properties;

    public void addProperty(Property property){
        this.properties.add(property);
    }

    public PropertiesXMLWriter() {
        this.properties = new ArrayList<Property>();
    }

    public void writeXML(String fileLocation) throws XMLStreamException, FileNotFoundException {
        this.fileLocation = fileLocation;
        // create an XMLOutputFactory
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

        // create XMLEventWriter
        XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(fileLocation));

        // create an EventFactory
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent newLine = eventFactory.createDTD("\n");

        //write Start Tag
        eventWriter.add(eventFactory.createStartDocument());
        eventWriter.add(newLine);
        // create config open tag
        StartElement configStartElement = eventFactory.createStartElement("",
                "", "properties");
        eventWriter.add(configStartElement);
        eventWriter.add(newLine);

        for (Property property : properties){
            // create config open tag
            XMLEvent tab = eventFactory.createDTD("\t");
            eventWriter.add(tab);
            eventWriter.add(eventFactory.createStartElement("","", "property"));
            eventWriter.add(newLine);
            createNode(eventWriter, "name",property.getPropertyName());
            createNode(eventWriter,"value",property.getPropertyValue());
            eventWriter.add(tab);
            eventWriter.add(eventFactory.createEndElement("", "", "property"));
            eventWriter.add(newLine);

        }

        eventWriter.add(eventFactory.createEndElement("", "", "properties"));
        eventWriter.add(newLine);
        eventWriter.add(eventFactory.createEndDocument());
        eventWriter.close();
    }
    private void createNode(XMLEventWriter eventWriter, String name,
                            String value) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t\t");
        // create Start node
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        // create Content
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        // create End node
        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);

    }



}
