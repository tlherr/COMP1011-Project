package com.tlherr.Model.Employee;

/**
 * Employee that has a no base salary. This employee is paid only on commission based on sales
 */
public class CommissionSalesEmployee extends AbstractEmployee {

    private float commissionRate, sales;

    public CommissionSalesEmployee() {
        super();
    }

    public CommissionSalesEmployee(String firstName, String lastName, String position, String department, float commissionRate, float sales) {
        super(firstName, lastName, position, department);
        this.commissionRate = commissionRate;
        this.sales = sales;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Commission Rate: %1$f, Sales: %2$f", this.getCommissionRate(), this.getSales());
    }

    /**
     * Pay is calculated as amount sold * commission rate
     * @return float amount employee is to be paid
     */
    @Override
    public float calculatePay() {
        return (sales*commissionRate);
    }

    public float getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(float commissionRate) {
        this.commissionRate = commissionRate;
    }

    public float getSales() {
        return sales;
    }

    public void setSales(float sales) {
        this.sales = sales;
    }
}
