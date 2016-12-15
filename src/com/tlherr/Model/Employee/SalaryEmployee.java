package com.tlherr.Model.Employee;

import java.math.BigDecimal;

/**
 * This type of employee is simply paid a salary regardless of time worked
 */
public class SalaryEmployee extends AbstractEmployee {

    private BigDecimal salary;

    public SalaryEmployee() {
        super();
    }

    public SalaryEmployee(String firstName, String lastName, String position, String department, BigDecimal baseSalary) {
        super(firstName, lastName, position, department);
        this.salary = baseSalary;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Salary: %1$f", this.getSalary());
    }

    @Override
    public BigDecimal calculatePay() {
        return salary;
    }


    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

}
