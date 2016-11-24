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
     * AbstractEmployeeForm Buttons
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

    /**
     * Products Panel
     */
    public static final String PRODUCTS_TITLE = "Products";
    public static final String ADD_PRODUCTS_BUTTON = "Add";
    public static final String DELETE_PRODUCTS_BUTTON = "Delete";
    public static final String EDIT_PRODUCTS_BUTTON = "Edit";

    /**
     * Search Page
     */
    public static final String SEARCH_TYPE_EMPLOYEE = "Employee";
    public static final String SEARCH_TYPE_MANUFACTURER = "Manufacturer";
    public static final String SEARCH_TYPE_PRODUCT = "Product";
    public static final String SEARCH_LABEL = "Enter Search Text";

    /**
     * Employee Selection Type
     */
    public static final String EMPLOYEE_TYPE_SELECTION_TITLE = "Select Employee Type";
    public static final String EMPLOYEE_TYPE_SELECTION_LABEL = "Please select the employee type to create";

    /**
     * Employee Form
     */
    public static final String EMPLOYEE_FORM_TITLE = " Employee";
    public static final String EMPLOYEE_FORM_LABEL_FIRSTNAME = "First Name: ";
    public static final String EMPLOYEE_FORM_LABEL_LASTNAME = "Last Name: ";


    private Strings() {
    }
}