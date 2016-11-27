package com.tlherr.Model;

import com.tlherr.Model.Employee.AbstractEmployee;

import java.util.ArrayList;

/**
 * Manufacturer model. Contains a name and a list of products they produce.
 */
public class Manufacturer implements IsTabular {

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

    @Override
    public Integer getRowCount() {
        return getClass().getDeclaredFields().length;
    }

    @Override
    public String getProp(int index) {
        switch(index) {
            case 0:
                return this.name;

            case 1:
                return this.getProducts().toString();
        }

        return null;
    }
}
