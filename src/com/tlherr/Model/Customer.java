package com.tlherr.Model;

public class Customer extends AbstractModel {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String company;

    public Customer(String firstName, String lastName, String emailAddress, String company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.company = company;
    }

    @Override
    public void save() {

    }
}
