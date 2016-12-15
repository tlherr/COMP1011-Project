package com.tlherr.Resources;

/**
 * Contains application strings to make editing easy and in one place.
 * Also localization is easier using this method
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
     * Employee Subtabs
     */

    public static final String BASE_PLUS_COMMISSION_EMPLOYEE_TAB = "Base Plus Commission";
    public static final String COMMISSION_SALES_EMPLOYEE_TAB = "Commission Sales";
    public static final String HOURLY_EMPLOYEE_TAB = "Hourly";
    public static final String SALARY_EMPLOYEE_TAB = "Salary";

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
     * Employee Form
     */
    public static final String EMPLOYEE_FORM_LABEL_FIRSTNAME = "First Name: ";
    public static final String EMPLOYEE_FORM_LABEL_LASTNAME = "Last Name: ";
    public static final String EMPLOYEE_FORM_LABEL_POSITION = "Position: ";
    public static final String EMPLOYEE_FORM_LABEL_DEPARTMENT = "Department: ";

    /**
     * Employee Form Errors
     */
    public static final String EMPLOYEE_FORM_LABEL_ERROR = "Error: ";

    /**
     * Employee Form Controls
     */
    public static final String EMPLOYEE_CONTROL_BUTTON_OK = "Ok";
    public static final String EMPLOYEE_CONTROL_BUTTON_CANCEL = "Cancel";

    /**
     * Base Plus Commission Employee Specific Form
     */
    public static final String BPC_EMPLOYEE_FORM_LABEL_SALARY = "Salary: ";

    /**
     * Commission Sales Employee
     */
    public static final String C_EMPLOYEE_FORM_LABEL_COMMISSION_RATE = "Commission Rate (%): ";
    public static final String C_EMPLOYEE_FORM_LABEL_SALES = "Sales: ";

    /**
     * Hourly Sales Employee
     */
    public static final String H_EMPLOYEE_FORM_LABEL_HOURLY_RATE = "Hourly Rate: ";
    public static final String H_EMPLOYEE_FORM_LABEL_HOURS_PER_WEEK = "Hours Per Week: ";

    /**
     * Login Form
     */

    public static final String LOGIN_FORM_LABEL_USERNAME = "Username";
    public static final String LOGIN_FORM_LABEL_PASSWORD = "Password";
    public static final String LOGIN_FORM_BUTTON_LOGIN = "Login";
    public static final String LOGIN_FORM_BUTTON_LOGOUT = "Logout";



    private Strings() {
    }
}
