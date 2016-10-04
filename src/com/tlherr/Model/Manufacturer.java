package com.tlherr.Model;

import com.tlherr.Model.Employee.AbstractEmployee;

import java.util.ArrayList;

public class Manufacturer {

    private ArrayList<Product> products;

    public Manufacturer() {}

    public Manufacturer(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
