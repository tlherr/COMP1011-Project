package com.tlherr.Repository;

import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.Manufacturer;
import com.tlherr.Model.Product;
import com.tlherr.Service.ConnectionService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Responsible for storing and accessing products as they as saved/edited/removed
 */
public class ProductRepository extends AbstractRepository {

    private static ProductRepository instance = new ProductRepository();

    public static ProductRepository getInstance() {
        return instance;
    }

    private ProductRepository(){};

    @Override
    public ResultSet load(Class toLoad) throws SQLException {
        Connection conn = ConnectionService.getConnection();
        Statement statement = conn.createStatement();

        if(toLoad==Product.class) {
            //Load BasePlusCommissionEmployees into result set and return it
            return statement.executeQuery("SELECT * FROM Manufacturers");
        } else {
            return null;
        }
    }


}
