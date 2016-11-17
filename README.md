## 16F Adv Object Oriented Prog - 01 (COMP1011-16F-11100) 

### Week 2

UML class diagram for an Employee, Product and driver class (does not have to be called driver - you choose the name)
The shell classes (meaning the basics - variables, methods and constructors) for the classes above. No logic at this point!

### Week 3

The Employee class inheritance model by adding two classes - CommissionSalesEmployee  (extends Employee) and BasePlusCommissionEmployee (extends CommissionSalesEmployee). You need to have a toString() method in the Employee class that prints out all the basic employee info. Both subclasses must also have their own overridden version of toString() adding the additional output for those classes (i.e. commission rates, sales and base salary - hint: super). Create UML class diagrams for the two new classes. 

**Additional challenge** We need a way to generate an employee id. This must happen outside the Employee class. Design a class that is called ServiceClass that accommodates for a method call to return an employee id (hint: static). Implement this into the Employee class. 

### Week 4

* Create appropriate packages for all classes;
* Create the following classes that will inherit from the Employee class:
..* Hourly Employee
..* Salary Employee
..* Commission Employee
..* Add functional methods for all employee types
..* Each sub-class will have some unique features to salary/pay
* Calculating methods that differentiate from the super class;
* Create product class with functional methods
* Create a manufacturer class and add a composition (HAS-A) reference to the
* Product class for each Product Object created. Add option to display
* Manufacturer info when reviewing a product.
* Complete the menu driven code in the main method in the Main class so it
will:
..* Read user input and create a new employee object. This needs to be
added to an ArrayList for storage;
..* Allow for a search of an employee and display the result;
..* Allow for a search for a product and display the result;
* Add validation to ALL user input (Note: NOT error handling/try-catch);
* Implement class abstraction where applicable.

### Week 8

* Build a graphical user interface (GUI) that replaces the menu-based system we worked
on in our assignment 1. The GUI will need to have a presentable user interface that
includes all the features included in assignment 1. Below is a list of some requirements
for this assignment.
..* An outer JFrame to house the all sub-components
..* A top panel that has a greeting label
..* A bottom panel that has a button panel that includes an exit button
..* A center panel that contains tabbed interface. The tabbed interface must have the
following tabs:
* HR tab that has the ability to collect all the information of all types of
Employees
* Inventory tab that has the ability to collect all the information about a
Product (including manufacturer information – HINT: Information to create
two separate objects might be required when you create a Product object)
* Search tab that allows for searching Employees and/or a Product
(separately) and have the potential to display the result in a JTextArea
component (no functionality is required for part 1 of the assignment).
* The GUI must have the proper default closing operations.