package com.tlherr.Model;

import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.Employee.CommissionSalesEmployee;

import java.math.BigDecimal;

public class Sale extends AbstractModel {
    private Product product;
    private AbstractEmployee employee;
    private BigDecimal salePrice;

    public Sale(){}

    public Sale(Product product, AbstractEmployee employee, BigDecimal salePrice) {
        this.product = product;
        this.employee = employee;
        this.salePrice = salePrice;
    }


    @Override
    public void save() {

    }
}
