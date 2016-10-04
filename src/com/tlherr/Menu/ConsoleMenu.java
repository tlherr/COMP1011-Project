package com.tlherr.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class ConsoleMenu {

    private HashMap<Integer, String> options = new HashMap<Integer, String>();
    private Scanner scanner = new Scanner(System.in);
    private ActionListener listener;

    public ConsoleMenu(HashMap<Integer, String> options) {
        this.options = options;
    }

    public ConsoleMenu(HashMap<Integer, String> options, ActionListener listener) {
        this.options = options;
        this.listener = listener;
    }

    public void render() {
        for (Map.Entry<Integer, String> menuItem  : options.entrySet()) {
            System.out.println(menuItem.getKey().toString() + ") "+menuItem.getValue());
        }
        System.out.println("Please type the number of the menu option you wish to select");

        try {
            Integer selection = scanner.nextInt();
            scanner.nextLine();

            if(options.get(selection)!=null) {
                //Action was selected, inform anything that was listening
                listener.actionPerformed(new ActionEvent(this, selection, options.get(selection)));
            } else {
                throw new InputMismatchException();
            }
        } catch(InputMismatchException | IllegalStateException ex) {
            System.out.println("Invalid input was entered ("+ex.getMessage()+"). Please retry selection");
            render();
        }
    }

    public HashMap<Integer, String> getOptions() {
        return options;
    }

    public void setOptions(HashMap<Integer, String> options) {
        this.options = options;
    }

    public ActionListener getListener() {
        return listener;
    }

    public void setListener(ActionListener listener) {
        this.listener = listener;
    }
}
