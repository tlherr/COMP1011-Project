package com.tlherr.Model.Employee;

import java.math.BigDecimal;

/**
 * This type of employee is simply paid a flat rate per hour worked
 */
public class HourlyEmployee extends AbstractEmployee {

    private BigDecimal hoursWorked;
    private BigDecimal hourlyRate;

    public HourlyEmployee() {
        super();
    }

    public HourlyEmployee(String firstName, String lastName, String position, String department, BigDecimal hoursPerWeek, BigDecimal hourlyRate) {
        super(firstName, lastName, position, department);
        this.hoursWorked = hoursPerWeek;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public BigDecimal calculatePay() {
        if(hourlyRate!=null && hoursWorked!=null) {
            return (hoursWorked.multiply(hourlyRate));
        } else {
            return new BigDecimal("0.00");
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
