package com.tlherr.Repository;

import com.mysql.jdbc.ResultSetImpl;
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

public class EmployeeRepository extends AbstractRepository {

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

    @Override
    public ResultSet load(Class toLoad) throws SQLException {
        Connection conn = ConnectionService.getConnection();
        Statement statement = conn.createStatement();

        if(toLoad==BasePlusCommissionEmployee.class) {
            //Load BasePlusCommissionEmployees into result set and return it
            return statement.executeQuery("SELECT * FROM BasePlusCommissionEmployee");

        } else if(toLoad==CommissionSalesEmployee.class) {
            //Load CommissionSalesEmployees into result set and return it
            return statement.executeQuery("SELECT * FROM CommissionEmployee");

        } else if(toLoad==HourlyEmployee.class) {
            //Load HourlyEmployees into a result set and return it
            return statement.executeQuery("SELECT * FROM HourlyEmployee");

        } else if(toLoad==SalaryEmployee.class) {
            //Load SalaryEmployees into a result set and return it
            return statement.executeQuery("SELECT * FROM SalaryEmployee");
        } else {
            return null;
        }
    }
}
