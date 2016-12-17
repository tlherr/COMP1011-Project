package com.tlherr.Model.Employee;

import com.tlherr.Service.ConnectionService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

/**
 * This type of employee is simply paid a flat rate per hour worked
 */
public class HourlyEmployee extends AbstractEmployee {

    private BigDecimal hoursWorked;
    private BigDecimal hourlyRate;

    public HourlyEmployee() {
        super();
    }

    public HourlyEmployee(Vector v) {
        this.idNumber = (int) v.get(0);
        this.setFirstName(v.get(1).toString());
        this.setLastName(v.get(2).toString());
        this.setPosition(v.get(3).toString());
        this.setDepartment(v.get(4).toString());
        this.setHoursWorked(new BigDecimal(v.get(5).toString()));
        this.setHourlyRate(new BigDecimal(v.get(6).toString()));
    }

    public HourlyEmployee(String firstName, String lastName, String position, String department, BigDecimal hoursPerWeek, BigDecimal hourlyRate) {
        super(firstName, lastName, position, department);
        this.hoursWorked = hoursPerWeek;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public BigDecimal calculatePay() {
        if (hourlyRate != null && hoursWorked != null) {
            return (hoursWorked.multiply(hourlyRate));
        } else {
            return new BigDecimal("0.00");
        }
    }

    @Override
    public void save() {
        //Get a connection
        try {
            Connection conn = ConnectionService.getConnection();
            PreparedStatement statement;
            //Check for an ID, if it has one this is an update
            if (this.idNumber != 0) {
                statement = conn.prepareStatement("UPDATE HourlyEmployee SET firstName=?, lastName=?," +
                        "position=?,department=?,hoursPerWeek=?,hourlyRate=? WHERE id=? ");

                statement.setString(1, this.getFirstName());
                statement.setString(2, this.getLastName());
                statement.setString(3, this.getPosition());
                statement.setString(4, this.getDepartment());
                statement.setBigDecimal(5, this.getHoursWorked());
                statement.setBigDecimal(6, this.getHourlyRate());
                statement.setInt(7, this.idNumber);

            } else {
                statement = conn.prepareStatement("INSERT INTO HourlyEmployee " +
                        "(firstName, lastName, position, department, hoursPerWeek, hourlyRate)" +
                        " VALUES (?,?,?,?,?,?)");

                statement.setString(1, this.getFirstName());
                statement.setString(2, this.getLastName());
                statement.setString(3, this.getPosition());
                statement.setString(4, this.getDepartment());
                statement.setBigDecimal(5, this.getHoursWorked());
                statement.setBigDecimal(6, this.getHourlyRate());
            }

            statement.execute();
            conn.close();

        } catch (SQLException e) {
            //@TODO: This should log to debug log as per requirements
            e.printStackTrace();
        }

    }

    public BigDecimal getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(BigDecimal hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
