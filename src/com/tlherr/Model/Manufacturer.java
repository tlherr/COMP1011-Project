package com.tlherr.Model;

import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Service.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Manufacturer model. Contains a name and a list of products they produce.
 */
public class Manufacturer extends AbstractModel {

    private int id;
    private String name;
    private ArrayList<Product> products;

    public Manufacturer(String name) {
        this.name = name;
    }

    public Manufacturer(String name, ArrayList<Product> products) {
        this(name);
        this.products = products;
    }

    public Manufacturer(Vector v) {
        this.id = (int) v.get(1);
        this.name = v.get(2).toString();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    @Override
    public void save() {
        //Get a connection
        try {
            Connection conn = ConnectionService.getConnection();
            PreparedStatement statement;
            //Check for an ID, if it has one this is an update
            if(this.id!=0) {
                statement = conn.prepareStatement("UPDATE Manufacturers SET name=? WHERE id=? ");

                statement.setString(1, this.getName());
                statement.setInt(2, this.id);

            } else {
                statement = conn.prepareStatement("INSERT INTO Manufacturers " +
                        "(name)" +
                        " VALUES (?)");

                statement.setString(1, this.getName());
            }

            statement.execute();
            conn.close();

        } catch (SQLException e) {
            //@TODO: This should log to debug log as per requirements
            e.printStackTrace();
        }
    }
}
