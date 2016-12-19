package com.tlherr.Model;

import com.tlherr.Service.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Product Model
 * Contains various properties of products. Belong to manufacturers
 */
public class Product extends AbstractModel {

    private Manufacturer manufacturer;
    private String name, modelNumber;

    public Product(){}

    public Product(Vector v) {
        this.id = (int) v.get(0);
        this.name = v.get(1).toString();
        this.modelNumber = v.get(2).toString();

        Vector<Object> manufacturerVector = new Vector<>();
        manufacturerVector.add(v.get(3));
        manufacturerVector.add(v.get(4).toString());
        this.manufacturer = new Manufacturer(manufacturerVector);
    }

    public Product(String name, Manufacturer manufacturer, String modelNumber) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.modelNumber = modelNumber;
    }

    public Product(int id, String name, Manufacturer manufacturer, String modelNumber) {
        this(name, manufacturer, modelNumber);
        this.id=id;
    }

    @Override
    public void save() {
        //Get a connection
        try {
            Connection conn = ConnectionService.getConnection();
            PreparedStatement statement;
            //Check for an ID, if it has one this is an update
            if (this.id != 0) {
                statement = conn.prepareStatement("UPDATE Products SET name=?,modelNumber=?,manufacturer_ID=? WHERE id=?");

                statement.setString(1, this.name);
                statement.setString(2, this.modelNumber);
                statement.setInt(3, this.manufacturer.getId());
                statement.setInt(4, this.id);

            } else {
                statement = conn.prepareStatement("INSERT INTO Products " +
                        "(name,modelNumber,manufacturer_ID)" +
                        " VALUES (?,?,?)");

                statement.setString(1, this.name);
                statement.setString(2, this.modelNumber);
                statement.setInt(3, this.manufacturer.getId());
            }

            statement.execute();
            conn.close();

        } catch (SQLException e) {
            //@TODO: This should log to debug log as per requirements
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {

    }


    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
