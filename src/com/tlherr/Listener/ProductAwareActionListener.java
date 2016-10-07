package com.tlherr.Listener;

import com.tlherr.Model.Manufacturer;
import com.tlherr.Model.Product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProductAwareActionListener implements ActionListener {

    private Product product;
    private  ArrayList<Manufacturer> manufacturers;

    public ProductAwareActionListener(Product p, ArrayList<Manufacturer> m) {
        this.product = p;
        this.manufacturers = m;
    }

    public Product getProduct() {
        return product;
    }

    public ArrayList<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
