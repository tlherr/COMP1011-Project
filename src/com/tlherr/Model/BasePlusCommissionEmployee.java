package com.tlherr.Model;

public class BasePlusCommissionEmployee extends CommissionSalesEmployee {

    private float baseSalary;

    public BasePlusCommissionEmployee() {
        super();
    }

    public BasePlusCommissionEmployee(String firstName, String lastName, String position, String department, float commissionRates, float sales, float baseSalary) {
        super(firstName, lastName, position, department, commissionRates, sales);
        this.commissionRate = commissionRates;
        this.sales = sales;
        this.baseSalary = baseSalary;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Commission Rate: %1$f, Sales: %2$f, Base Salary: %3$f", this.getCommissionRate(),
                this.getSales(), this.getBaseSalary());
    }

    @Override
    public float calculatePay() {
        return baseSalary + (sales * commissionRate);
    }


    public float getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(float baseSalary) {
        this.baseSalary = baseSalary;
    }
}
