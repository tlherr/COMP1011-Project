package com.tlherr.Manager;

import com.tlherr.Model.Employee.BasePlusCommissionEmployee;
import com.tlherr.Model.Employee.CommissionSalesEmployee;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tom on 2016-10-04.
 */
public class EmployeeManagerTest {

    private BasePlusCommissionEmployee emp1;
    private CommissionSalesEmployee emp2;

    @Before
    public void setUp() throws Exception {

        emp1 = new BasePlusCommissionEmployee("Tom", "Herr", "CEO", "Management", 0.10f, 4000.00f, 40000.00f);
        EmployeeManager.addEmployee(emp1);

        emp2 = new CommissionSalesEmployee("Some", "Guy", "Janitor", "Maintenance", 0.10f, 4000.00f);
        EmployeeManager.addEmployee(emp2);
    }

    @Test
    public void testSearchEmployees() throws Exception {
        assertEquals(EmployeeManager.searchEmployees("Tom") instanceof BasePlusCommissionEmployee, true);
    }
}