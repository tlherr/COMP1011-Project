package com.tlherr.Model.Employee;

import com.tlherr.Service.ConnectionService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This type of employee works on a base salary as well as commission rates
 */
public class BasePlusCommissionEmployee extends CommissionSalesEmployee {

    private BigDecimal baseSalary;

    public BasePlusCommissionEmployee() {
        super();
    }

    public BasePlusCommissionEmployee(String firstName, String lastName, String position, String department,
                                      BigDecimal commissionRates, BigDecimal sales, BigDecimal baseSalary) {
        super(firstName, lastName, position, department, commissionRates, sales);
        this.baseSalary = baseSalary;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Commission Rate: %1$f, Sales: %2$f, Base Salary: %3$f", this.getCommissionRate(),
                this.getSales(), this.getBaseSalary());
    }

    @Override
    public BigDecimal calculatePay() {
        return baseSalary.add(super.getSales().multiply(super.getCommissionRate()));
    }

    @Override
    public void save() {
        //Get a connection
        try {
            Connection conn = ConnectionService.getConnection();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO BasePlusCommissionEmployee " +
                    "(firstName, lastName, position, department, commissionRate, sales, salary)" +
                    " VALUES (?,?,?,?,?,?,?)");

            statement.setString(1, this.getFirstName());
            statement.setString(2, this.getLastName());
            statement.setString(3, this.getPosition());
            statement.setString(4, this.getDepartment());
            statement.setBigDecimal(5, this.getCommissionRate());
            statement.setBigDecimal(6, this.getSales());
            statement.setBigDecimal(7, this.getBaseSalary());

            statement.execute();
            conn.close();

        } catch (SQLException e) {
            //@TODO: This should log to debug log as per requirements
            e.printStackTrace();
        }
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }
}
