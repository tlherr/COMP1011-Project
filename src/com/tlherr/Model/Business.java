package com.tlherr.Model;

import java.util.ArrayList;

public class Business {

    private ArrayList<Employee> employees;
    private ArrayList<Product> products;

    public Business() {}

    public Business(ArrayList<Employee> employees, ArrayList<Product> products) {
        this.employees = employees;
        this.products = products;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
