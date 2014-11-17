package me.shenalsenarath;

import java.io.Serializable;

/**
 * Created by shselk on 11/17/2014.
 */
public class CustomModel implements Serializable {
    private int Id;
    private String field1;
    private String field2;

    public CustomModel() {
    }

    public CustomModel(int id, String field1, String field2) {
        Id = id;
        this.field1 = field1;
        this.field2 = field2;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (this.getClass() != o.getClass())
            return false;
        CustomModel tempModel = (CustomModel) o;
        if (tempModel.getId() != this.getId())
            return false;
        if (tempModel.getField1() != this.getField1())
            return false;
        if (tempModel.getField2() != this.getField2())
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = Id;
        result = 31 * result + field1.hashCode();
        result = 31 * result + field2.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CustomModel{" +
                "Id=" + Id +
                ", field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                '}';
    }
}
