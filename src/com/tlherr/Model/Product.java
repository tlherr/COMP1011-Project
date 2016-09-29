package com.tlherr.Model;

import java.util.Date;

public class Product {

    private String productName, description, manufacturer, modelNumber;
    private Integer sku;
    private float msrp, weight, depth, price;
    private Date dateProduced;

    public Product() {}

    public Product(String productName, String description, String manufacturer, String modelNumber, Integer sku, float msrp, float weight, float depth, float price, Date dateProduced) {
        this.productName = productName;
        this.description = description;
        this.manufacturer = manufacturer;
        this.modelNumber = modelNumber;
        this.sku = sku;
        this.msrp = msrp;
        this.weight = weight;
        this.depth = depth;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
        this.sku = sku;
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
