package com.tlherr.Service;


import com.tlherr.Main;
import com.tlherr.Model.Manufacturer;
import com.tlherr.Model.Product;
import com.tlherr.Repository.ManufacturerRepository;

import java.util.ArrayList;

public class ProductService {

    public static void findProduct() {

        ArrayList<Manufacturer> manufacturers = ManufacturerRepository.getInstance().getManufacturers();

        if(manufacturers!=null) {
            String productName = ConsoleService.getStringInput(
                    "Enter the name of the Product", ConsoleService.CHARACTERS_ONLY, "Productname");

            for(Manufacturer manufacturer:manufacturers) {
                for(Product product:manufacturer.getProducts()) {
                    if(product.getProductName().equals(productName)) {
                        System.out.println("Found matching product. Name: "+product.getProductName()+" Model Number:"+
                                product.getModelNumber()+" Manufacturer: "+manufacturer.getName());
                    }
                }
            }
        } else {
            System.out.println("No Manufacturers exist, please create one before searching for products");
        }

        String[] args = {};
        Main.main(args);
    }

}
