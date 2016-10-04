package com.tlherr;

import com.tlherr.Menu.ConsoleMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        //Make some stuff and run it

        HashMap<Integer, String> mainMenuOptions = new HashMap<Integer, String>();
        mainMenuOptions.put(1, "Create a new Employee");
        mainMenuOptions.put(2, "Search for an Employee");
        mainMenuOptions.put(3, "Search for a Product");
        ConsoleMenu mainMenu = new ConsoleMenu(mainMenuOptions);

        mainMenu.setListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Got an event");
            }
        });

        mainMenu.render();
    }
}
