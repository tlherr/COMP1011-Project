package com.tlherr.Model;

import java.util.Date;

public class Product {

    private Manufacturer manufacturer;
    private String productName, description, modelNumber;
    private float msrp, weight, depth, height, price, width;
    private Date dateProduced;

    public Product() {}

    public Product(String productName, Manufacturer manufacturer, String modelNumber) {
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.modelNumber = modelNumber;
    }


    public Product(String productName, String description, Manufacturer manufacturer, String modelNumber, float msrp, float weight, float depth, float width, float height, float price, Date dateProduced) {
        this.productName = productName;
        this.description = description;
        this.manufacturer = manufacturer;
        this.modelNumber = modelNumber;
        this.msrp = msrp;
        this.weight = weight;
        this.width = width;
        this.depth = depth;
        this.height = height;
        this.price = price;
        this.dateProduced = dateProduced;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getMsrp() {
        return msrp;
    }

    public void setMsrp(float msrp) {
        this.msrp = msrp;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getDepth() {
        return depth;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDateProduced() {
        return dateProduced;
    }

    public void setDateProduced(Date dateProduced) {
        this.dateProduced = dateProduced;
    }
}
