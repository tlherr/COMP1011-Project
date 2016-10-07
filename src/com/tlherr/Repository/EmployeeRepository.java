package com.tlherr.Repository;

import com.tlherr.Model.Employee.AbstractEmployee;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Singleton Repository class for Employees
 * Manages saving/searching employees
 */
public class EmployeeRepository {

    private static EmployeeRepository instance = new EmployeeRepository();

    public static EmployeeRepository getInstance() {
        return instance;
    }

    private EmployeeRepository(){}

    private static ArrayList<AbstractEmployee> employees = new ArrayList<AbstractEmployee>();

    public void addEmployee(AbstractEmployee employee) {
        employees.add(employee);
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
