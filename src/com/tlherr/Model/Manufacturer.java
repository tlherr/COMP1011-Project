package com.tlherr.Model;

import com.tlherr.Service.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Manufacturer model. Contains a name and a list of products they produce.
 */
public class Manufacturer extends AbstractModel {

    private String name;

    public Manufacturer() {
    }

    public Manufacturer(String name) {
        this.name = name;
    }

    public Manufacturer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Manufacturer(Vector v) {
        this.id = (int) v.get(0);
        this.name = v.get(1).toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void save() {
        //Get a connection
        try {
            Connection conn = ConnectionService.getConnection();
            PreparedStatement statement;
            //Check for an ID, if it has one this is an update
            if (this.id != 0) {
                statement = conn.prepareStatement("UPDATE Manufacturers SET name=? WHERE id=? ");

                statement.setString(1, this.name);
                statement.setInt(2, this.id);

            } else {
                statement = conn.prepareStatement("INSERT INTO Manufacturers " +
                        "(name)" +
                        " VALUES (?)");

                statement.setString(1, this.name);
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

    @Override
    public String toString() {
        return this.getName();
    }
}
