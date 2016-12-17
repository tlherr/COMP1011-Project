package com.tlherr.Repository;

import com.tlherr.Model.Employee.BasePlusCommissionEmployee;
import com.tlherr.Model.Employee.CommissionSalesEmployee;
import com.tlherr.Model.Employee.HourlyEmployee;
import com.tlherr.Model.Employee.SalaryEmployee;
import com.tlherr.Model.Manufacturer;
import com.tlherr.Service.ConnectionService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Responsible for storing and accessing manufacturers as they as saved/edited/removed
 */
public class ManufacturerRepository extends AbstractRepository {

    private static ManufacturerRepository instance = new ManufacturerRepository();

    public static ManufacturerRepository getInstance() {
        return instance;
    }

    private ManufacturerRepository(){};

    @Override
    public ResultSet load(Class toLoad) throws SQLException {
        Connection conn = ConnectionService.getConnection();
        Statement statement = conn.createStatement();

        if(toLoad==Manufacturer.class) {
            //Load BasePlusCommissionEmployees into result set and return it
            return statement.executeQuery("SELECT * FROM Manufacturers");
        } else {
            return null;
        }
    }


}
