package com.tlherr.Menu;

import com.tlherr.Service.ConsoleService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * This class provides the basic functionality of a console menu
 * Using a hashmap of integer - string pairs to represent menu data structure
 * Uses an ActionListener to inform other classes that an option has been selected
 */
public class ConsoleMenu {

    private HashMap<Integer, String> options = new HashMap<Integer, String>();
    private ActionListener listener;

    public ConsoleMenu(HashMap<Integer, String> options) {
        this.options = options;
    }

    /**
     * Print options from structure
     * We use the console service to get input from user and parse the input
     * Validation is using regex that matches on numbers from keys, example checking that input was 1,2 or 3 etc
     * depending on options
     * Fire listener action performed event, the ID given is the key of the menu item selected
     */
    public void render() {
        String instructions = "==========================\n";
        for (Map.Entry<Integer, String> menuItem  : options.entrySet()) {
            instructions+=menuItem.getKey().toString() + ") "+menuItem.getValue()+"\n";
        }
        instructions+="==========================\n";
        instructions+="Please type the number of the menu option you wish to select";

        try {
            Integer index = Integer.parseInt(ConsoleService.getStringInput(
                    instructions, ConsoleService.generateRegex(options), " a valid integer matching a menu item"));
            if(options.get(index)!=null && listener!=null) {
                listener.actionPerformed(new ActionEvent(this, index, options.get(index)));
            }
        } catch(NumberFormatException ex) {
            //We were unable to parse the integer from the console service. Redo menu capture
            System.out.println("Capture Error. Please try menu input again");
            render();
        }
    }

    /**
     * Set an actionlistener to be notified when user has selected a valid option
     *
     * @see <a href="https://docs.oracle.com/javase/tutorial/uiswing/events/actionlistener.html">Oracle Docs</a>
     * @param ActionListener listener
     */
    public void setListener(ActionListener listener) {
        this.listener = listener;
    }
}
