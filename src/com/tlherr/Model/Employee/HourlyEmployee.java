package com.tlherr.Model.Employee;

public class HourlyEmployee extends AbstractEmployee {

    private Float hoursWorked;
    private Float hourlyRate;

    public HourlyEmployee() {
        super();
    }

    public HourlyEmployee(String firstName, String lastName, String position, String department, Float hoursPerWeek, Float hourlyRate) {
        super(firstName, lastName, position, department);
        this.hoursWorked = hoursPerWeek;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public float calculatePay() {
        if(hourlyRate!=null && hourlyRate!=null) {
            return (hoursWorked*hourlyRate);
        } else {
            return 0.00f;
        }
    }

    public Float getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Float hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public Float getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Float hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
