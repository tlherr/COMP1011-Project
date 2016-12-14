package com.tlherr.Repository;

import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.Employee.EmployeeTableModel;

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
        Boolean found = false;
        //Check if the employee exists already
        for(int i=0; i<employees.size(); i++) {
            if(employees.get(i).getIdNumber() == employee.getIdNumber()) {
                //The employee already exists, update the info
                employees.set(i, employee);
                found = true;
            }
        }

        if(!found) {
            //This is a new employee, save it
            addEmployee(employee);
        }

    }
}
