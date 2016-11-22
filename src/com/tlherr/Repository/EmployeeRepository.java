package com.tlherr.Repository;

import com.tlherr.Model.Employee.AbstractEmployee;

import java.lang.reflect.Field;
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

    public String getAt(int index, int col) {
        //Check if index is within bounds
        if(!(index>employees.size()) && !(index<0)) {
            return employees.get(index).getProp(col);
        }
        return null;
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
