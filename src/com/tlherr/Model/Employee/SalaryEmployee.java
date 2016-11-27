package com.tlherr.Model.Employee;

/**
 * This type of employee is simply paid a salary regardless of time worked
 */
public class SalaryEmployee extends AbstractEmployee {

    private float salary;

    public SalaryEmployee() {
        super();
    }

    public SalaryEmployee(String firstName, String lastName, String position, String department, float baseSalary) {
        super(firstName, lastName, position, department);
        this.salary = baseSalary;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Salary: %1$f", this.getSalary());
    }

    @Override
    public float calculatePay() {
        return salary;
    }


    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public Integer getRowCount() {
        return getClass().getDeclaredFields().length;
    }
}
