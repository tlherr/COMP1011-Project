package com.tlherr.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class manages connections to the DB
 */
public class ConnectionService {
    private static final String DBUrl = "jdbc:mysql://sql.computerstudi.es:3306/gc200325519?zeroDateTimeBehavior=convertToNull";
    private static final String username = "gc200325519";
    private static final String password = "L^cW3GW*";

    //@TODO: Hold a list of all connections and when logged out close them before exiting?

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DBUrl, username, password);
    }
}
