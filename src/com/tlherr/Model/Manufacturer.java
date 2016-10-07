package com.tlherr.Model;

import java.util.ArrayList;

/**
 * Manufacturers are the producers of products
 */
public class Manufacturer {

    private String name;
    private ArrayList<Product> products;

    public Manufacturer() {}

    public Manufacturer(String name, ArrayList<Product> products) {
        this.name = name;
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
