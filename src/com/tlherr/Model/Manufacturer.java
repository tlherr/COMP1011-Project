package com.tlherr.Model;

import com.tlherr.Model.Employee.AbstractEmployee;

import java.util.ArrayList;

/**
 * Manufacturer model. Contains a name and a list of products they produce.
 */
public class Manufacturer {

    private String name;
    private ArrayList<Product> products;

    public Manufacturer(String name) {
        this.name = name;
    }

    public Manufacturer(String name, ArrayList<Product> products) {
        this(name);
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
}
