package com.tlherr.Model;

import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.Employee.CommissionSalesEmployee;
import com.tlherr.Service.ConnectionService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        try {
            Connection conn = ConnectionService.getConnection();
            PreparedStatement statement;
            switch(this.employee.getType()) {
                case 1:
                    statement = conn.prepareStatement("INSERT INTO BasePlusCommissionEmployeeSales (salePrice, product_ID, employee_ID) VALUES(?,?,?)");
                    statement.setBigDecimal(1,this.getSalePrice());
                    statement.setInt(2, this.product.getId());
                    statement.setInt(3, this.employee.getId());
                    statement.execute();
                    break;

                case 2:
                    statement = conn.prepareStatement("INSERT INTO CommissionEmployeeSales (salePrice, product_ID, employee_ID) VALUES(?,?,?)");
                    statement.setBigDecimal(1,this.getSalePrice());
                    statement.setInt(2, this.product.getId());
                    statement.setInt(3, this.employee.getId());
                    statement.execute();
                    break;

                case 3:
                    statement = conn.prepareStatement("INSERT INTO HourlyEmployeeSales (salePrice, product_ID, employee_ID) VALUES(?,?,?)");
                    statement.setBigDecimal(1,this.getSalePrice());
                    statement.setInt(2, this.product.getId());
                    statement.setInt(3, this.employee.getId());
                    statement.execute();
                    break;

                case 4:
                    statement = conn.prepareStatement("INSERT INTO SalaryEmployeeSales (salePrice, product_ID, employee_ID) VALUES(?,?,?)");
                    statement.setBigDecimal(1,this.getSalePrice());
                    statement.setInt(2, this.product.getId());
                    statement.setInt(3, this.employee.getId());
                    statement.execute();
                    break;
            }

            conn.close();

        } catch (SQLException e) {
            //@TODO: Handle this
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {

    }
}
