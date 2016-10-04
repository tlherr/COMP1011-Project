package com.tlherr.Manager;

import com.tlherr.Model.Employee.AbstractEmployee;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class EmployeeManager {

    private static ArrayList<AbstractEmployee> employees = new ArrayList<AbstractEmployee>();

    private EmployeeManager() {}

    public static void addEmployee(AbstractEmployee employee) {
        employees.add(employee);
    }

    public static AbstractEmployee searchEmployees(String firstName) {
        for (AbstractEmployee employee : employees) {
            if(employee.getFirstName().equals(firstName)) {
                return employee;
            }
        }
        return null;
    }


    public static AbstractEmployee searchEmployees(String field, Object value) throws NoSuchFieldException {
        for (AbstractEmployee employee : employees) {

            Class<?> c = employee.getClass();
            Field f = c.getDeclaredField(field);
            f.setAccessible(true);

            try {
                if (f.get(0) == value) {
                    return employee;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



}
