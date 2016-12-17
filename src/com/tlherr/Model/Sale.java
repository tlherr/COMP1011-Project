package com.tlherr.Model;

import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.Employee.CommissionSalesEmployee;

import java.math.BigDecimal;

public class Sale extends AbstractModel {
    private Product product;
    private AbstractEmployee employee;
    private BigDecimal salePrice;
    private BigDecimal commissionEarned;

    public Sale(Product product, AbstractEmployee employee, BigDecimal salePrice) {
        this.product = product;
        this.employee = employee;
        this.salePrice = salePrice;

        if (employee instanceof CommissionSalesEmployee) {
            //Commission can be earned by this type of employee
            commissionEarned = ((CommissionSalesEmployee) employee).getCommissionRate().multiply(this.salePrice);
        }

    }


    @Override
    public void save() {

    }
}
