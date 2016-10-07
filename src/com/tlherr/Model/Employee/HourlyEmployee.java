package com.tlherr.Model.Employee;

/**
 * Hourly employees are paid based on a fixed hourly rate and only for the hours they worked.
 */
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

    /**
     * Multiply hours worked by the hourly rate
     * If the user has no rate or hours they get no money
     * @return float amount employee should be paid
     */
    @Override
    public float calculatePay() {
        if(hourlyRate!=null && hoursWorked!=null) {
            return (hoursWorked*hourlyRate);
        } else {
            return 0.00f;
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Hourly Rate: %1$f, Hours Worked: %2$f", this.getHourlyRate(), this.getHoursWorked());
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
