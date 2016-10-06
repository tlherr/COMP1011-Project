package com.tlherr.Service;


import com.tlherr.Model.Product;
import com.tlherr.Repository.ProductRepository;

public class ProductService {

    public static void findProduct() {
        Product foundProduct = ProductRepository.getInstance().findByProductName(ConsoleService.getInput("Enter the name of the Product", ConsoleService.CHARACTERS_ONLY));

        if(foundProduct!=null) {
            System.out.println("Found a matching product Model Number:"+foundProduct.getModelNumber());
        } else {
            System.out.println("No Matching Record Found");
            //@TODO: Go back to main menu?
        }
    }

}
