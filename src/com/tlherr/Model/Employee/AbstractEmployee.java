package com.tlherr.Model.Employee;

import com.tlherr.Model.AbstractModel;
import com.tlherr.Service.ServiceClass;

import java.math.BigDecimal;
import java.util.Date;

/**
 * This class contains base elements and functionality for an employee. All employee types inherit from this class
 *
 * This class implements IsTablular interface so all child classes will have methods needed to be loaded into JTablel
 * model
 */
public abstract class AbstractEmployee extends AbstractModel {

    private String firstName, lastName, position, department;
    protected int idNumber;

    protected AbstractEmployee() {}

    protected AbstractEmployee(String firstName, String lastName, String position, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.department = department;
    }

    @Override
    public String toString() {
        return String.format("Name: %1$s,%2$s Id: %3$d Department: %4$s Position: %5$s",
                this.getFirstName(), this.getLastName(), this.getIdNumber(), this.getDepartment(), this.getPosition());
    }

    /**
     * Calculate the amount the employee should be paid. Changes based on employee type
     * @return Float amount of money the employee should be paid
     */
    public abstract BigDecimal calculatePay();

    /**
     * Getters/Setters
     */

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getIdNumber() {
        return idNumber;
    }
}
