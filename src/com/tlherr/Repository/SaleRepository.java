package com.tlherr.Repository;

import com.tlherr.Model.Product;
import com.tlherr.Model.Sale;
import com.tlherr.Service.ConnectionService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SaleRepository extends AbstractRepository {

    private static SaleRepository instance = new SaleRepository();

    public static SaleRepository getInstance() {
        return instance;
    }

    private SaleRepository() {}


    @Override
    public ResultSet load(Class toLoad) throws SQLException {
        Connection conn = ConnectionService.getConnection();
        Statement statement = conn.createStatement();
        /**
         * Giant query grabs employees from all different tables to be displayed in JTable. Selecting all of these props because we need them to make a new sale object
         */
        if (toLoad == Sale.class) {
            return statement.executeQuery("select 1 as 'emptype',bpcs.id as 'Employee ID', bpc.firstName as 'First Name', bpcs.salePrice as 'Sale Price', prod.id as 'Product ID', prod.name as 'Product Name',\n" +
                    "prod.modelNumber as 'Product Model Number', manu.id as 'Manufacturer ID', manu.name as 'Manufacturer Name' from BasePlusCommissionEmployeeSales bpcs LEFT JOIN\n" +
                    "Products prod ON bpcs.product_ID = prod.id LEFT JOIN Manufacturers manu on prod.manufacturer_ID=manu.id\n" +
                    "LEFT JOIN BasePlusCommissionEmployee bpc ON bpcs.employee_ID=bpc.id\n" +
                    "UNION\n" +
                    "select 2 as 'emptype',css.id as 'Employee ID', c.firstName as 'First Name', css.salePrice as 'Sale Price', prod.id as 'Product ID', prod.name as 'Product Name',\n" +
                    "prod.modelNumber as 'Product Model Number', manu.id as 'Manufacturer ID', manu.name as 'Manufacturer Name' from CommissionEmployeeSales css LEFT JOIN\n" +
                    "Products prod ON css.product_ID = prod.id LEFT JOIN Manufacturers manu on prod.manufacturer_ID=manu.id\n" +
                    "LEFT JOIN CommissionEmployee c ON css.employee_ID=c.id\n" +
                    "UNION\n" +
                    "select 3 as 'emptype',hs.id as 'Employee ID', he.firstName as 'First Name', hs.salePrice as 'Sale Price', prod.id as 'Product ID', prod.name as 'Product Name',\n" +
                    "prod.modelNumber as 'Product Model Number', manu.id as 'Manufacturer ID', manu.name as 'Manufacturer Name' from HourlyEmployeeSales hs LEFT JOIN\n" +
                    "Products prod ON hs.product_ID = prod.id LEFT JOIN Manufacturers manu on prod.manufacturer_ID=manu.id\n" +
                    "LEFT JOIN HourlyEmployee he ON hs.employee_ID=he.id\n" +
                    "UNION\n" +
                    "select 4 as 'emptype',ss.id as 'Employee ID', se.firstName as 'First Name', ss.salePrice as 'Sale Price', prod.id as 'Product ID', prod.name as 'Product Name',\n" +
                    "prod.modelNumber as 'Product Model Number', manu.id as 'Manufacturer ID', manu.name as 'Manufacturer Name' from SalaryEmployeeSales ss LEFT JOIN\n" +
                    "Products prod ON ss.product_ID = prod.id LEFT JOIN Manufacturers manu on prod.manufacturer_ID=manu.id\n" +
                    "LEFT JOIN SalaryEmployee se ON ss.employee_ID=se.id");
        } else {
            return null;
        }
    }
}
