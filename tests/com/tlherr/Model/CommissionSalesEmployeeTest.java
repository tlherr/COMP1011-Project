package com.tlherr.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CommissionSalesEmployeeTest {

    private CommissionSalesEmployee employee;

    @Before
    public void setUp() throws Exception {
        employee = new CommissionSalesEmployee("Tom", "Herr", "Position", "Department", 0.10f, 4000.00f);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals(employee.toString().contains("Commission Rate:"), true);
        assertEquals(employee.toString().contains("Sales:"), true);
    }

    @Test
    public void testCalculatePay() throws Exception {
        assertEquals(employee.calculatePay(), 400.00f, 0.0f);
    }

    @Test
    public void testGetSales() throws Exception {
        assertEquals(employee.getSales(), 4000.00f, 0.0f);
    }
}