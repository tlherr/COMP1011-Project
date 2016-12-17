package com.tlherr.Repository;

import com.tlherr.Model.Customer;
import com.tlherr.Service.ConnectionService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerRepository extends AbstractRepository {

    private static CustomerRepository instance = new CustomerRepository();

    public static CustomerRepository getInstance() {
        return instance;
    }

    private CustomerRepository() {}

    @Override
    public ResultSet load(Class toLoad) throws SQLException {
        Connection conn = ConnectionService.getConnection();
        Statement statement = conn.createStatement();
        return statement.executeQuery("SELECT * FROM Customer");
    }
}
