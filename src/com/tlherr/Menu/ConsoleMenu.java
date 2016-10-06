package com.tlherr.Menu;

import com.tlherr.Service.ConsoleService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class ConsoleMenu {

    private HashMap<Integer, String> options = new HashMap<Integer, String>();
    private Scanner scanner = new Scanner(System.in);
    private ActionListener listener;

    public ConsoleMenu(HashMap<Integer, String> options) {
        this.options = options;
    }

    public ConsoleMenu(HashMap<Integer, String> options, Scanner scanner, ActionListener listener) {
        this(options);
        this.listener = listener;
    }

    public void render() {
        String instructions = "";
        for (Map.Entry<Integer, String> menuItem  : options.entrySet()) {
            instructions+=menuItem.getKey().toString() + ") "+menuItem.getValue()+"\n";
        }
        instructions+="Please type the number of the menu option you wish to select\n";

        try {
            Integer index = Integer.parseInt(ConsoleService.getInput(instructions, ConsoleService.generateRegex(options)));
            if(options.get(index)!=null) {
                listener.actionPerformed(new ActionEvent(this, index, options.get(index)));
            }
        } catch(NumberFormatException ex) {
            //We were unable to parse the integer from the console service. Redo menu capture
            System.out.println("Capture Error. Please try menu input again");
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
