package com.tlherr.Resources;

/**
 * Created by tom on 2016-11-22.
 */
public class Strings {
    private static Strings manager = new Strings();
    public static Strings getManager() {
        return manager;
    }

    /**
     * Application Level
     */
    public static final String APPLICATION_TITLE = "COMP1011 Employee Management";

    /**
     * Welcome Greeting
     */
    public static final String WELCOME_MESSAGE = "Welcome";


    /**
     * Tabs
     */
    public static final String HUMAN_RESOURCES_TAB = "Human Resources";
    public static final String INVENTORY_TAB = "Inventory";
    public static final String SEARCH_TAB = "Search";

    /**
     * Employee Buttons
     */
    public static final String ADD_EMPLOYEE = "Add";
    public static final String DELETE_EMPLOYEE = "Delete";
    public static final String EDIT_EMPLOYEE = "Edit";

    /**
     * Manufacturers Panel
     */
    public static final String MANUFACTURERS_TITLE = "Manufacturers";
    public static final String ADD_MANUFACTURERS_BUTTON = "Add";
    public static final String DELETE_MANUFACTURERS_BUTTON = "Delete";
    public static final String EDIT_MANUFACTURERS_BUTTON = "Edit";


    private Strings() {
    }
}
