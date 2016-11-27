package com.tlherr.Repository;

import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.Product;

import java.util.ArrayList;

/**
 * Responsible for storing and accessing products as they as saved/edited/removed
 */
public class ProductRepository {

    private static ProductRepository instance = new ProductRepository();

    public static ProductRepository getInstance() {
        return instance;
    }

    private ProductRepository(){};

    private static ArrayList<Product> products = new ArrayList<Product>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public Product findByProductName(String productName) {
        for (Product product : products) {
            if(product.getProductName().equals(productName)) {
                return product;
            }
        }
        return null;
    }
}
