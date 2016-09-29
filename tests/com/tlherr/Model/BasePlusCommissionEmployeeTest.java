package com.tlherr.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasePlusCommissionEmployeeTest {

    private BasePlusCommissionEmployee employee;

    @Before
    public void setUp() throws Exception {
        employee = new BasePlusCommissionEmployee("Tom", "Herr", "Position", "Department", 0.10f, 4000.00f, 40000.00f);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testToString() throws Exception {
        assertEquals(employee.toString().contains("Commission Rate:"), true);
        assertEquals(employee.toString().contains("Sales:"), true);
        assertEquals(employee.toString().contains("Base Salary:"), true);
    }

    @Test
    public void testCalculatePay() throws Exception {
        assertEquals(employee.calculatePay(), 40400.00f, 0.0f);
    }

    @Test
    public void testGetBaseSalary() throws Exception {
        assertEquals(employee.getBaseSalary(), 40000.00f, 0.0f);
    }
}