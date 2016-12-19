package com.tlherr.Model.Employee;

import com.tlherr.Service.ConnectionService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

/**
 * This type of employee works on a base salary as well as commission rates
 */
public class BasePlusCommissionEmployee extends CommissionSalesEmployee {
    //declare var baseSalary
    private BigDecimal baseSalary;

    public BasePlusCommissionEmployee() {
        super();
    }
    //BasePlusCommisionEmployee vector declaration
    public BasePlusCommissionEmployee(Vector v) {
        super();
        this.id = (int) v.get(0);
        this.setFirstName(v.get(1).toString());
        this.setLastName(v.get(2).toString());
        this.setPosition(v.get(3).toString());
        this.setDepartment(v.get(4).toString());
        this.setCommissionRate(new BigDecimal(v.get(5).toString()));
        this.setSales(new BigDecimal(v.get(6).toString()));
        this.setBaseSalary(new BigDecimal(v.get(7).toString()));
    }
    //create BasePlusCommissionEmployee Object, Super in necessary attributes
    public BasePlusCommissionEmployee(String firstName, String lastName, String position, String department,
                                      BigDecimal commissionRates, BigDecimal sales, BigDecimal baseSalary) {
        super(firstName, lastName, position, department, commissionRates, sales);
        this.baseSalary = baseSalary;
    }
    //calculate pay using required info
    @Override
    public BigDecimal calculatePay() {
        return baseSalary.add(super.getSales().multiply(super.getCommissionRate()));
    }
    //save/update basePlusCOMmissionEmployee table based on changes made, check for connection first
    @Override
    public void save() {
        //Get a connection
        try {
            Connection conn = ConnectionService.getConnection();
            PreparedStatement statement;
            //Check for an ID, if it has one this is an update
            if (this.id != 0) {
                statement = conn.prepareStatement("UPDATE BasePlusCommissionEmployee SET firstName=?, lastName=?," +
                        "position=?,department=?,commissionRate=?,sales=?,salary=? WHERE id=? ");

                statement.setString(1, this.getFirstName());
                statement.setString(2, this.getLastName());
                statement.setString(3, this.getPosition());
                statement.setString(4, this.getDepartment());
                statement.setBigDecimal(5, this.getCommissionRate());
                statement.setBigDecimal(6, this.getSales());
                statement.setBigDecimal(7, this.getBaseSalary());
                statement.setInt(8, this.getId());

            } else {
                statement = conn.prepareStatement("INSERT INTO BasePlusCommissionEmployee " +
                        "(firstName, lastName, position, department, commissionRate, sales, salary)" +
                        " VALUES (?,?,?,?,?,?,?)");

                statement.setString(1, this.getFirstName());
                statement.setString(2, this.getLastName());
                statement.setString(3, this.getPosition());
                statement.setString(4, this.getDepartment());
                statement.setBigDecimal(5, this.getCommissionRate());
                statement.setBigDecimal(6, this.getSales());
                statement.setBigDecimal(7, this.getBaseSalary());
            }

            statement.execute();
            conn.close();

        } catch (SQLException e) {
            //@TODO: This should log to debug log as per requirements
            e.printStackTrace();
        }
    }
    //get Base Salary
    public BigDecimal getBaseSalary() {
        return baseSalary;
    }
    //set Base Salary
    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }
    //Delete from BasePlusCommissionEmployee table where id=current index
    @Override
    public void delete() {
        //Get a connection
        try {
            Connection conn = ConnectionService.getConnection();
            PreparedStatement statement;
            //Check for an ID, if it has one this is an update
            statement = conn.prepareStatement("DELETE FROM BasePlusCommissionEmployee WHERE id=? ");
            statement.setInt(1, this.getId());

            statement.execute();
            conn.close();

        } catch (SQLException e) {
            //TODO: Handle this
            e.printStackTrace();
        }
    }
}
