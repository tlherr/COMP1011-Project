package com.tlherr.Repository;

import com.tlherr.Model.Employee.*;
import com.tlherr.Service.ConnectionService;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
}
