package com.tlherr;

import java.sql.*;
import java.util.Scanner;

/**
 * This class exists to populate a targetDB with required tables for application
 */
public class DBGenerator {

    public static void main(String[] args) {

        //Ask for connection string
        System.out.println("Enter your connection string (Leave blank for default): ");

        Scanner scanner = new Scanner(System.in);
        String connection = scanner.nextLine();

        System.out.println("Enter your database username(Leave blank for default): ");

        String username = scanner.nextLine();

        System.out.println("Enter your database password (Leave blank for default): ");

        String password = scanner.nextLine();

        //If nothing was given connect to Toms database
        if(connection.length()==0&&username.length()==0&&password.length()==0) {
            connection = "jdbc:mysql://localhost:3306/gc200325519?zeroDateTimeBehavior=convertToNull&useSSL=false";
            username = "gc200325519";
            password = "L^cW3GW*";
        }

        //Attempt to make a connection based on that
        try {
            Connection conn = DriverManager.getConnection(connection, username, password);

            if(conn!=null) {
                String createUserTable = "CREATE TABLE IF NOT EXISTS `gc200325519`.`User` ("+
                "`id` INT NOT NULL AUTO_INCREMENT,"+
                "`username` VARCHAR(45) NOT NULL,"+
                "`password` VARCHAR(200) NOT NULL,"+
                "`type` TINYINT(1) NOT NULL,"+
                "`name` VARCHAR(45) NOT NULL,"+
                "PRIMARY KEY (`id`),"+
                "UNIQUE INDEX `username_UNIQUE` (`username` ASC))";

                Statement statement = conn.createStatement();
                int rows = statement.executeUpdate(createUserTable);

                if(rows>0) {
                    System.out.println("User Table Created");
                }

                System.out.println("Exiting");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
