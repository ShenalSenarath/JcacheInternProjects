package main.java.me.shenalsenarath.Models;

import java.io.Serializable;

/**
 * Created by shselk on 11/25/2014.
 */
public class Property implements Serializable {
    private String PropertyName;
    private String PropertyValue;

    public String getPropertyName() {
        return PropertyName;
    }

    public void setPropertyName(String propertyName) {
        PropertyName = propertyName;
    }

    public String getPropertyValue() {
        return PropertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        PropertyValue = propertyValue;
    }

    public Property(String propertyName, String propertyValue) {
        PropertyName = propertyName;
        PropertyValue = propertyValue;
    }

    public Property() {

    }

    @Override
    public String toString() {
        return "Property[" +
                "PropertyName='" + PropertyName + '\'' +
                ", PropertyValue='" + PropertyValue + '\'' +
                ']';
    }
}
