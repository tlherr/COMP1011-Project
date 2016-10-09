package com.tlherr.Service;


import com.tlherr.Listener.ProductAwareActionListener;
import com.tlherr.Main;
import com.tlherr.Menu.ConsoleMenu;
import com.tlherr.Model.Manufacturer;
import com.tlherr.Model.Product;
import com.tlherr.Repository.ManufacturerRepository;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductService {

    private static ProductService instance = new ProductService();

    public static ProductService getInstance() {
        return instance;
    }

    private ProductService() {}

    public void findProduct() {

        ArrayList<Manufacturer> manufacturers = ManufacturerRepository.getInstance().getManufacturers();

        if(manufacturers.size()!=0) {
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

    public void createProduct() {
        ArrayList<Manufacturer> manufacturers = ManufacturerRepository.getInstance().getManufacturers();

        //If no manufacturers exist the relationship cannot exist between manufacturers and products
        //If this is empty exit out back to main menu
        if(manufacturers.size()==0) {
            System.out.println("No Manufacturers currently exist, unable to add a product");
            String[] args = {};
            Main.main(args);
            return;
        }

        final Product product = new Product();

        product.setProductName(ConsoleService.getStringInput(
                "Enter the name of the Product", ConsoleService.ALPHANUMERIC_WORDS, "Nexus 5"));

        product.setPrice(ConsoleService.getFloatInput(
                "Enter the price of the Product", "20.00"
        ));

        product.setDateProduced(ConsoleService.getDateInput(
                "Enter the date the product was produced on", ConsoleService.DEFAULT_HINT_DATE
        ));

        product.setDepth(ConsoleService.getFloatInput(
                "Enter the depth of the product in cm", ConsoleService.DEFAULT_HINT_FLOAT
        ));

        product.setHeight(ConsoleService.getFloatInput(
                "Enter the height of the product in cm", ConsoleService.DEFAULT_HINT_FLOAT
        ));

        product.setWidth(ConsoleService.getFloatInput(
                "Enter the width of the product in cm", ConsoleService.DEFAULT_HINT_FLOAT
        ));

        product.setModelNumber(ConsoleService.getStringInput(
                "Enter the Model Number", ConsoleService.ALPHANUMERIC_WORDS, "DW713"
        ));

        product.setMsrp(ConsoleService.getFloatInput(
                "Enter the Manufacturers Suggested Retial Price for this item", ConsoleService.DEFAULT_HINT_FLOAT
        ));

        product.setWeight(ConsoleService.getFloatInput(
                "Enter the weight of the product in kilograms", "5.6"
        ));

        HashMap<Integer, String> manufacturerMenuOptions = new HashMap<Integer, String>();

        Integer keyCounter = 1;
        for(Manufacturer manufacturer:manufacturers) {
            manufacturerMenuOptions.put(keyCounter, manufacturer.getName());
            keyCounter++;
        }

        ConsoleMenu manufacturerMenu = new ConsoleMenu(manufacturerMenuOptions);

        manufacturerMenu.setListener(new ProductAwareActionListener(product, manufacturers) {
            @Override
            public void actionPerformed(ActionEvent e) {
                this.getManufacturers().get(e.getID()-1).addProduct(this.getProduct());
            }
        });
        System.out.println("Who manufactured this product?");
        manufacturerMenu.render();


        String[] args = {};
        Main.main(args);

    }
}
