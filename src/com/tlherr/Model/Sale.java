package com.tlherr.Model;

import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.Employee.CommissionSalesEmployee;

public class Sale {
    private Product product;
    private AbstractEmployee employee;
    private double salePrice;
    private double commissionEarned;

    public Sale(Product product, AbstractEmployee employee, double salePrice) {
        this.product = product;
        this.employee = employee;
        this.salePrice = salePrice;

        if(employee instanceof CommissionSalesEmployee) {
            //Commission can be earned by this type of employee
            commissionEarned =  ((CommissionSalesEmployee) employee).getCommissionRate() * this.salePrice;
        }

    }


}
