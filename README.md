## 16F Adv Object Oriented Prog - 01 (COMP1011-16F-11100) 

### Week 2

UML class diagram for an Employee, Product and driver class (does not have to be called driver - you choose the name)
The shell classes (meaning the basics - variables, methods and constructors) for the classes above. No logic at this point!

### Week 3

The Employee class inheritance model by adding two classes - CommissionSalesEmployee  (extends Employee) and BasePlusCommissionEmployee (extends CommissionSalesEmployee). You need to have a toString() method in the Employee class that prints out all the basic employee info. Both subclasses must also have their own overridden version of toString() adding the additional output for those classes (i.e. commission rates, sales and base salary - hint: super). Create UML class diagrams for the two new classes. 

**Additional challenge** We need a way to generate an employee id. This must happen outside the Employee class. Design a class that is called ServiceClass that accommodates for a method call to return an employee id (hint: static). Implement this into the Employee class. 

