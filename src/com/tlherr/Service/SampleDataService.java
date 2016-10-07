package com.tlherr.Service;

import com.tlherr.Model.Employee.BasePlusCommissionEmployee;
import com.tlherr.Model.Employee.CommissionSalesEmployee;
import com.tlherr.Model.Employee.HourlyEmployee;
import com.tlherr.Model.Manufacturer;
import com.tlherr.Repository.EmployeeRepository;
import com.tlherr.Repository.ManufacturerRepository;

public class SampleDataService {

    public static void populate() {

        //Make some employees
        BasePlusCommissionEmployee emp1 = new BasePlusCommissionEmployee(
                "Tom",
                "Herr",
                "Developer",
                "Programming",
                20.00f,
                2000.00f,
                35000.00f
        );

        EmployeeRepository.getInstance().addEmployee(emp1);

        CommissionSalesEmployee emp2 = new CommissionSalesEmployee(
                "Dave",
                "Userguy",
                "Custodian",
                "Maintenance",
                10.00f,
                20000.00f
        );

        EmployeeRepository.getInstance().addEmployee(emp2);

        HourlyEmployee emp3 = new HourlyEmployee(
                "Bob",
                "Lastname",
                "CEO",
                "Management",
                2.00f,
                400.00f
        );

        EmployeeRepository.getInstance().addEmployee(emp3);

        //Make a manufacturer

        Manufacturer manufacturer = new Manufacturer("Manufacturer Name");

        ManufacturerRepository.getInstance().addManufacturer(manufacturer);
    }
}
