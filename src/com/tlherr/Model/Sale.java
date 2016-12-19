package com.tlherr.Model;

import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.Employee.CommissionSalesEmployee;

import java.math.BigDecimal;

public class Sale extends AbstractModel {
    private Product product;
    private GenericEmployee employee;
    private BigDecimal salePrice;

    public Sale(){}

    public Sale(Product product, GenericEmployee employee, BigDecimal salePrice) {
        this.product = product;
        this.employee = employee;
        this.salePrice = salePrice;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public GenericEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(GenericEmployee employee) {
        this.employee = employee;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    @Override
    public void save() {

    }

    @Override
    public void delete() {

    }
}
