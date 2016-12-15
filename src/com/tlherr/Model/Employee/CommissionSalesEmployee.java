package com.tlherr.Model.Employee;

import java.math.BigDecimal;

/**
 * This type of employee works on commission (percentage of sales they receive as compensation)
 */
public class CommissionSalesEmployee extends AbstractEmployee {

    private BigDecimal commissionRate, sales;

    public CommissionSalesEmployee() {
        super();
    }

    public CommissionSalesEmployee(String firstName, String lastName, String position, String department, BigDecimal commissionRate, BigDecimal sales) {
        super(firstName, lastName, position, department);
        this.commissionRate = commissionRate;
        this.sales = sales;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Commission Rate: %1$f, Sales: %2$f", this.getCommissionRate(), this.getSales());
    }

    @Override
    public BigDecimal calculatePay() {
        return (sales.multiply(commissionRate));
    }

    public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

}
