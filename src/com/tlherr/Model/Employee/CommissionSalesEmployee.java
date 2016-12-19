package com.tlherr.Model.Employee;

import com.tlherr.Service.ConnectionService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

/**
 * This type of employee works on commission (percentage of sales they receive as compensation)
 */
public class CommissionSalesEmployee extends AbstractEmployee {
    //declare variables used by this class to calculate the pay for this type of Employee
    private BigDecimal commissionRate, sales;

    public CommissionSalesEmployee() {
        super();
    }

    //converts a database object into a CommissionSalesEmployee
    public CommissionSalesEmployee(Vector v) {
        super();
        this.id = (int) v.get(0);
        this.setFirstName(v.get(1).toString());
        this.setLastName(v.get(2).toString());
        this.setPosition(v.get(3).toString());
        this.setDepartment(v.get(4).toString());
        this.setCommissionRate(new BigDecimal(v.get(5).toString()));
        this.setSales(new BigDecimal(v.get(6).toString()));
    }

    public CommissionSalesEmployee(String firstName, String lastName, String position, String department, BigDecimal commissionRate, BigDecimal sales) {
        super(firstName, lastName, position, department);
        this.commissionRate = commissionRate;
        this.sales = sales;
    }

    @Override
    public BigDecimal calculatePay() {
        return (sales.multiply(commissionRate));
    }
    //save/update CommissionEmployee table based on changes made, check for connection first
    @Override
    public void save() {
        //Get a connection
        try {
            Connection conn = ConnectionService.getConnection();
            PreparedStatement statement;

            if (this.id != 0) {
                statement = conn.prepareStatement("UPDATE CommissionEmployee SET firstName=?, lastName=?," +
                        "position=?,department=?,commissionRate=?,sales=? WHERE id=? ");

                statement.setString(1, this.getFirstName());
                statement.setString(2, this.getLastName());
                statement.setString(3, this.getPosition());
                statement.setString(4, this.getDepartment());
                statement.setBigDecimal(5, this.getCommissionRate());
                statement.setBigDecimal(6, this.getSales());
                statement.setInt(7, this.getId());
            } else {
                statement = conn.prepareStatement("INSERT INTO CommissionEmployee " +
                        "(firstName, lastName, position, department, commissionRate, sales)" +
                        " VALUES (?,?,?,?,?,?)");

                statement.setString(1, this.getFirstName());
                statement.setString(2, this.getLastName());
                statement.setString(3, this.getPosition());
                statement.setString(4, this.getDepartment());
                statement.setBigDecimal(5, this.getCommissionRate());
                statement.setBigDecimal(6, this.getSales());
            }

            statement.execute();
            conn.close();

        } catch (SQLException e) {
            //@TODO: This should log to debug log as per requirements
            e.printStackTrace();
        }
    }
    //delete selected item from CommissionEmployee
    @Override
    public void delete() {
        try {
            Connection conn = ConnectionService.getConnection();
            PreparedStatement statement;
            //Check for an ID, if it has one this is an update
            statement = conn.prepareStatement("DELETE FROM CommissionEmployee WHERE id=? ");
            statement.setInt(1, this.getId());

            statement.execute();
            conn.close();

        } catch (SQLException e) {
            //TODO: Handle this
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
