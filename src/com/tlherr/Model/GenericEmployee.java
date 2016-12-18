package com.tlherr.Model;

public class GenericEmployee extends AbstractModel {

    private String firstName;
    private int type;

    public GenericEmployee(int id, String firstName, int type) {
        this.id = id;
        this.firstName = firstName;
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public void save() {

    }

    @Override
    public String toString() {
        return this.firstName+"("+this.id+")";
    }
}
