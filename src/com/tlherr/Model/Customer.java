package com.tlherr.Model;

import com.tlherr.Service.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

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

    public Customer(Vector v) {
        this.id = (int) v.get(1);
        this.firstName = v.get(2).toString();
    }

    @Override
    public void save() {
        //Get a connection
        try {
            Connection conn = ConnectionService.getConnection();
            PreparedStatement statement;
            //Check for an ID, if it has one this is an update
            if(this.id!=0) {
                statement = conn.prepareStatement("UPDATE Customer SET firstName=?,lastName=?,emailAddress=?,company=? WHERE id=? ");

                statement.setString(1, this.firstName);
                statement.setString(2, this.lastName);
                statement.setString(3, this.emailAddress);
                statement.setString(4, this.company);
                statement.setInt(5, this.id);

            } else {
                statement = conn.prepareStatement("INSERT INTO Customer " +
                        "(firstName,lastName,emailAddress,company)" +
                        " VALUES (?,?,?,?)");

                statement.setString(1, this.firstName);
                statement.setString(2, this.lastName);
                statement.setString(3, this.emailAddress);
                statement.setString(4, this.company);            }

            statement.execute();
            conn.close();

        } catch (SQLException e) {
            //@TODO: This should log to debug log as per requirements
            e.printStackTrace();
        }
    }
}
