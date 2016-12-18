package com.tlherr.Repository;

import com.tlherr.Model.Product;
import com.tlherr.Service.ConnectionService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Responsible for storing and accessing products as they as saved/edited/removed
 */
public class ProductRepository extends AbstractRepository {

    private static ProductRepository instance = new ProductRepository();

    public static ProductRepository getInstance() {
        return instance;
    }

    private ProductRepository() {
    }

    ;

    @Override
    public ResultSet load(Class toLoad) throws SQLException {
        Connection conn = ConnectionService.getConnection();
        Statement statement = conn.createStatement();

        if (toLoad == Product.class) {
            return statement.executeQuery("SELECT prod.id,prod.name as 'Product Name',prod.modelNumber as 'Model Number', manu.id as 'Manufacturer ID', manu.name as 'Manufacturer' \n" +
                    "FROM products prod INNER JOIN manufacturers manu on prod.manufacturer_ID=manu.id;");
        } else {
            return null;
        }
    }


}
