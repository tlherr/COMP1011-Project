package com.tlherr.Model.Employee;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tom on 2016-10-04.
 */
public class HourlyEmployeeTest {

    private HourlyEmployee employee;

    @Before
    public void setUp() throws Exception {
        employee = new HourlyEmployee("Tom", "Herr", "Position", "Department", 28.0f, 12.00f);
    }

    @Test
    public void testCalculatePay() throws Exception {
        assertEquals(employee.calculatePay(), (employee.getHourlyRate()*employee.getHoursWorked()), 0.0f);
        employee.setHourlyRate(0.0f);
        assertEquals(employee.calculatePay(), (employee.getHourlyRate()*employee.getHoursWorked()), 0.0f);
    }
}