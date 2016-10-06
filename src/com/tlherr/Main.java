package com.tlherr;

import com.tlherr.Menu.ConsoleMenu;
import com.tlherr.Service.EmployeeService;
import com.tlherr.Service.ProductService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        HashMap<Integer, String> mainMenuOptions = new HashMap<Integer, String>();
        mainMenuOptions.put(1, "Create a new Employee");
        mainMenuOptions.put(2, "Search for an Employee");
        mainMenuOptions.put(3, "Search for a Product");
        ConsoleMenu mainMenu = new ConsoleMenu(mainMenuOptions);

        mainMenu.setListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(e.getID()) {
                    case 1:
                        EmployeeService.createEmployee();
                        break;
                    case 2:
                        EmployeeService.findEmployee();
                        break;
                    case 3:
                        ProductService.findProduct();
                        break;
                }
            }
        });

        mainMenu.render();
    }
}
