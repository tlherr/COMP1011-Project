package com.tlherr.Model.Employee;

import com.tlherr.Service.ConnectionService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This type of employee works on commission (percentage of sales they receive as compensation)
 */
public class CommissionSalesEmployee extends AbstractEmployee {

    private BigDecimal commissionRate, sales;

    public CommissionSalesEmployee() {
        super();
    }

    public CommissionSalesEmployee(String firstName, String lastName, String position, String department, BigDecimal commissionRate, BigDecimal sales) {
        super(firstName, lastName, position, department);
        this.commissionRate = commissionRate;
        this.sales = sales;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Commission Rate: %1$f, Sales: %2$f", this.getCommissionRate(), this.getSales());
    }

    @Override
    public BigDecimal calculatePay() {
        return (sales.multiply(commissionRate));
    }

    @Override
    public void save() {
        //Get a connection
        try {
            Connection conn = ConnectionService.getConnection();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO CommissionEmployee " +
                    "(firstName, lastName, position, department, commissionRate, sales)" +
                    " VALUES (?,?,?,?,?,?)");

            statement.setString(1, this.getFirstName());
            statement.setString(2, this.getLastName());
            statement.setString(3, this.getPosition());
            statement.setString(4, this.getDepartment());
            statement.setBigDecimal(5, this.getCommissionRate());
            statement.setBigDecimal(6, this.getSales());

            statement.execute();
            conn.close();

        } catch (SQLException e) {
            //@TODO: This should log to debug log as per requirements
            e.printStackTrace();
        }
    }

    public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

}
