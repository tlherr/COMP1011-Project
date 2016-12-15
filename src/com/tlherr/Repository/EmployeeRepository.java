package com.tlherr.Repository;

import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.Employee.BasePlusCommissionEmployee;
import com.tlherr.Model.Employee.CommissionSalesEmployee;
import com.tlherr.Model.Employee.EmployeeTableModel;
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

    public void save(AbstractEmployee employee) {
        if(employee.getClass()==BasePlusCommissionEmployee.class) {
            save((BasePlusCommissionEmployee) employee);
        } else if(employee.getClass()== CommissionSalesEmployee.class) {
            save((CommissionSalesEmployee) employee);
        }
    }

    private void save(BasePlusCommissionEmployee employee) {
        //Get a connection
        try {
            Connection conn = ConnectionService.getConnection();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO BasePlusCommissionEmployee " +
                    "(firstName, lastName, position, department, commissionRate, sales, salary)" +
                    " VALUES (?,?,?,?,?,?,?)");

            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getPosition());
            statement.setString(4, employee.getDepartment());
            statement.setBigDecimal(5, employee.getCommissionRate());
            statement.setBigDecimal(6, employee.getSales());
            statement.setBigDecimal(7, employee.getBaseSalary());

            statement.execute();
            conn.close();

        } catch (SQLException e) {
            //@TODO: This should log to debug log as per requirements
             e.printStackTrace();
        }
    }


    private void save(CommissionSalesEmployee employee) {
        //Get a connection
        try {
            Connection conn = ConnectionService.getConnection();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO CommissionEmployee " +
                    "(firstName, lastName, position, department, commissionRate, sales)" +
                    " VALUES (?,?,?,?,?,?)");

            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getPosition());
            statement.setString(4, employee.getDepartment());
            statement.setBigDecimal(5, employee.getCommissionRate());
            statement.setBigDecimal(6, employee.getSales());

            statement.execute();
            conn.close();

        } catch (SQLException e) {
            //@TODO: This should log to debug log as per requirements
            e.printStackTrace();
        }
    }
}
