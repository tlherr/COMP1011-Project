package com.tlherr.Model.Employee;

import java.math.BigDecimal;

/**
 * This type of employee works on a base salary as well as commission rates
 */
public class BasePlusCommissionEmployee extends CommissionSalesEmployee {

    private BigDecimal baseSalary;

    public BasePlusCommissionEmployee() {
        super();
    }

    public BasePlusCommissionEmployee(String firstName, String lastName, String position, String department,
                                      BigDecimal commissionRates, BigDecimal sales, BigDecimal baseSalary) {
        super(firstName, lastName, position, department, commissionRates, sales);
        this.baseSalary = baseSalary;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Commission Rate: %1$f, Sales: %2$f, Base Salary: %3$f", this.getCommissionRate(),
                this.getSales(), this.getBaseSalary());
    }

    @Override
    public BigDecimal calculatePay() {
        return baseSalary.add(super.getSales().multiply(super.getCommissionRate()));
    }


    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }
}
