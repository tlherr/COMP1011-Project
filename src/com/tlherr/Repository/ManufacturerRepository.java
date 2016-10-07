package com.tlherr.Repository;

import com.tlherr.Model.Manufacturer;
import com.tlherr.Model.Product;

import java.util.ArrayList;

/**
 * Singleton Repository class for Manufacturers
 * Manages saving/searching manufacturers
 */
public class ManufacturerRepository {

    private static ManufacturerRepository instance = new ManufacturerRepository();

    public static ManufacturerRepository getInstance() {
        return instance;
    }

    private ManufacturerRepository(){};

    private ArrayList<Manufacturer> manufacturers = new ArrayList<Manufacturer>();

    public void addManufacturer(Manufacturer manufacturer) {
        manufacturers.add(manufacturer);
    }

    public ArrayList<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    public Manufacturer findByProductName(String manufacturerName) {
        for (Manufacturer manufacturer : manufacturers) {
            if(manufacturer.getName().equals(manufacturerName)) {
                return manufacturer;
            }
        }
        return null;
    }
}
