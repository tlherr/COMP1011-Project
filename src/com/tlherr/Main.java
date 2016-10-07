package com.tlherr;

import com.tlherr.Menu.ConsoleMenu;
import com.tlherr.Model.Product;
import com.tlherr.Repository.ManufacturerRepository;
import com.tlherr.Service.EmployeeService;
import com.tlherr.Service.ManufacturerService;
import com.tlherr.Service.ProductService;
import com.tlherr.Service.SampleDataService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        HashMap<Integer, String> mainMenuOptions = new HashMap<Integer, String>();
        mainMenuOptions.put(1, "Create a new Employee");
        mainMenuOptions.put(2, "Search for an Employee");
        mainMenuOptions.put(3, "Search for a Product");
        mainMenuOptions.put(4, "Create a Product");
        mainMenuOptions.put(5, "Create a Manufacturer");
        mainMenuOptions.put(6, "List All Manufacturers");
        mainMenuOptions.put(7, "List All Employees");
        ConsoleMenu mainMenu = new ConsoleMenu(mainMenuOptions);

        mainMenu.setListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switch(e.getID()) {
                    case 1:
                            EmployeeService.getInstance().createEmployee();
                        break;
                    case 2:
                            EmployeeService.getInstance().findEmployee();
                        break;
                    case 3:
                            ProductService.getInstance().findProduct();
                        break;
                    case 4:
                            ProductService.getInstance().createProduct();
                        break;
                    case 5:
                            ManufacturerService.getInstance().createManufacturer();
                        break;
                    case 6:
                            ManufacturerService.getInstance().listAll();
                        break;
                    case 7:
                            EmployeeService.getInstance().listAll();
                        break;
                }
            }
        });

        mainMenu.render();
    }
}
