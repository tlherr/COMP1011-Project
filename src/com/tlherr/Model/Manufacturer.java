package com.tlherr.Model;

import java.util.ArrayList;

/**
 * Manufacturers are the producers of products
 */
public class Manufacturer {

    private String name;
    private ArrayList<Product> products;

    public Manufacturer() {}

    public Manufacturer(String name) {
        this.name = name;
    }

    public Manufacturer(String name, ArrayList<Product> products) {
        this.name = name;
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        if(this.products==null) {
            this.products = new ArrayList<Product>();
        }

        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {

        if(this.products==null) {
            this.products = new ArrayList<Product>();
        }
        this.products.add(product);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return String.format("Name: %1$s, Number of Products: %2$s",
                this.getName(), this.getProducts().size());
    }
}
