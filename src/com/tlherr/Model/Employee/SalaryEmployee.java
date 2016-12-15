package com.tlherr.Model.Employee;

import com.tlherr.Service.ConnectionService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    @Override
    public void save() {
        //Get a connection
        try {
            Connection conn = ConnectionService.getConnection();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO SalaryEmployee " +
                    "(firstName, lastName, position, department, salary)" +
                    " VALUES (?,?,?,?,?)");

            statement.setString(1, this.getFirstName());
            statement.setString(2, this.getLastName());
            statement.setString(3, this.getPosition());
            statement.setString(4, this.getDepartment());
            statement.setBigDecimal(5, this.getSalary());

            statement.execute();
            conn.close();

        } catch (SQLException e) {
            //@TODO: This should log to debug log as per requirements
            e.printStackTrace();
        }
    }


    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

}
