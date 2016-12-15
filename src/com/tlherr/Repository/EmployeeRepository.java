package com.tlherr.Repository;

import com.tlherr.Model.Employee.*;
import com.tlherr.Service.ConnectionService;

import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class EmployeeRepository {

    private static EmployeeRepository instance = new EmployeeRepository();

    public static EmployeeRepository getInstance() {
        return instance;
    }

    private EmployeeRepository(){};

    private static ArrayList<AbstractEmployee> employees = new ArrayList<AbstractEmployee>();

    public void addEmployee(AbstractEmployee employee) {
        employees.add(employee);
    }

    public Integer getCount() {
        return employees.size();
    }

    public AbstractEmployee findByFirstName(String firstName) {
        for (AbstractEmployee employee : employees) {
            if(employee.getFirstName().equals(firstName)) {
                return employee;
            }
        }
        return null;
    }

    public static ResultSet loadResultSetOrNull(Class c) throws SQLException {
        Connection conn = ConnectionService.getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs;

        if(c==BasePlusCommissionEmployee.class) {
            //Load BasePlusCommissionEmployees into result set and return it
            rs = statement.executeQuery("SELECT * FROM BasePlusCommissionEmployee");
            return rs;
        } else if(c==CommissionSalesEmployee.class) {
            //Load CommissionSalesEmployees into result set and return it
            rs =  statement.executeQuery("SELECT * FROM CommissionSalesEmployee");
            return rs;
        } else if(c==HourlyEmployee.class) {
            //Load HourlyEmployees into a result set and return it
            rs = statement.executeQuery("SELECT * FROM HourlyEmployee");
            conn.close();
            return rs;
        } else if(c==SalaryEmployee.class) {
            //Load SalaryEmployees into a result set and return it
            rs = statement.executeQuery("SELECT * FROM SalaryEmployee");
            rs.close();
            return rs;
        } else {
            return null;
        }
    }

}
