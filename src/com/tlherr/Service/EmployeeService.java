package com.tlherr.Service;

import com.tlherr.Menu.ConsoleMenu;
import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.Employee.BasePlusCommissionEmployee;
import com.tlherr.Model.Employee.CommissionSalesEmployee;
import com.tlherr.Model.Employee.HourlyEmployee;
import com.tlherr.Repository.EmployeeRepository;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;

/**
 * Exposes methods that can be used to perform CRUD operations on Employees.
 * Note this class does not actually store user data. This is done by the repository.
 */
public class EmployeeService {

    private AbstractEmployee employee;

    public EmployeeService() {}

    /**
     * Read user input and create a new employee object.
     *
     */
    public void createEmployee() {
        //Which type of employee are we planning to create

        HashMap<Integer, String> employeeMenuOptions = new HashMap<Integer, String>();
        employeeMenuOptions.put(1, "Hourly Employee");
        employeeMenuOptions.put(2, "Base+Commission Employee");
        employeeMenuOptions.put(3, "Commission Employee");
        ConsoleMenu employeeMenu = new ConsoleMenu(employeeMenuOptions);

        employeeMenu.setListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(e.getID()) {
                    case 1:
                        employee = new HourlyEmployee();
                        break;

                    case 2:
                        employee = new BasePlusCommissionEmployee();
                        break;

                    case 3:
                        employee = new CommissionSalesEmployee();
                        break;
                    default:
                        //If we have arrived here, invalid input was sent in, crash shit
                        //@TODO: Handle this
                        break;
                }
                getInformation();
            }
        });

        employeeMenu.render();
    }

    /**
     * This method is called to get base employee information
     */
    private void getInformation() {
        if(employee!=null) {
            employee.setFirstName(ConsoleService.getInput(
                    "Enter the firstname of the Employee", ConsoleService.CHARACTERS_ONLY, "John"));
            employee.setLastName(ConsoleService.getInput(
                    "Enter the lastname of the Employee", ConsoleService.CHARACTERS_ONLY, "Smith"));
            employee.setPosition(ConsoleService.getInput(
                    "Enter the position of the Employee", ConsoleService.CHARACTERS_ONLY, "Position"));
            employee.setDepartment(ConsoleService.getInput(
                    "Enter the department of the Employee", ConsoleService.CHARACTERS_ONLY, "Department"));
            employee.setSocialInsuranceNumber(ConsoleService.getInput(
                    "Enter the social insurance number of the Employee", ConsoleService.NUMBERS_ONLY, "123456789 (No dashes)"));
            employee.setEmail(ConsoleService.getInput(
                    "Enter the email of the Employee", ConsoleService.EMAIL_BASIC, "user@address.tld"));
            employee.setPhoneNumber(ConsoleService.getInput(
                    "Enter the phone number of the Employee", ConsoleService.PHONE_NUMBER, "111-222-3333 or 111.222.3333"));
            employee.setAddress(ConsoleService.getInput(
                    "Enter the address of the Employee", ConsoleService.ALPHANUMERIC_WORDS, "123 Fake St (No special characters)"));
            employee.setGender(ConsoleService.getInput(
                    "Enter the gender of the Employee", ConsoleService.GENDER, "M m or F f"));
            employee.setVacationsDays(Integer.parseInt(ConsoleService.getInput(
                    "Enter the vacation days of the Employee", ConsoleService.NUMBERS_ONLY, "23 (Non negative whole numbers)")));
            employee.setDateHired(new Date(ConsoleService.getInput(
                    "Enter the date the Employee was hired", ConsoleService.CHARACTERS_ONLY, "2002-01-01")));
            employee.setDateOfBirth(new Date(ConsoleService.getInput(
                    "Enter the date of birth of the Employee", ConsoleService.CHARACTERS_ONLY, "2002-01-01")));

            //Dat polymorphism
            if(employee instanceof HourlyEmployee) {
                ((HourlyEmployee) employee).setHourlyRate(Float.parseFloat(ConsoleService.getInput(
                        "Enter the hourly rate of the Employee", ConsoleService.CURRENCY, "42343.00")));
                ((HourlyEmployee) employee).setHoursWorked(Float.parseFloat(ConsoleService.getInput(
                        "Enter the hours worked by the Employee", ConsoleService.CURRENCY, "42343.00")));
            }

            if (employee instanceof BasePlusCommissionEmployee) {
                ((BasePlusCommissionEmployee) employee).setBaseSalary(Float.parseFloat(ConsoleService.getInput(
                        "Enter the base salary of the Employee", ConsoleService.CURRENCY, "42343.00")));
            }


            if(employee instanceof CommissionSalesEmployee) {
                ((CommissionSalesEmployee) employee).setCommissionRate(Float.parseFloat(ConsoleService.getInput(
                        "Enter the commision rate of the Employee", ConsoleService.CURRENCY, "42343.00")));
                ((CommissionSalesEmployee) employee).setSales(Float.parseFloat(ConsoleService.getInput(
                        "Enter the sales rate of the Employee", ConsoleService.CURRENCY, "42343.00")));
            }

            //At this point we should have a full employee, save it

            EmployeeRepository.getInstance().addEmployee(employee);
            System.out.println("Employee Saved (ID: "+employee.getIdNumber()+")");
        }
    }

    public void findEmployee() {
        AbstractEmployee foundEmployee = EmployeeRepository.getInstance().findByFirstName(ConsoleService.getInput(
                "Enter the firstname of the Employee", ConsoleService.CHARACTERS_ONLY, "John"));

        if(foundEmployee!=null) {
            System.out.println("Found a matching employee ID:"+foundEmployee.getIdNumber());
        } else {
            System.out.println("No Matching Record Found");
            //@TODO: Go back to main menu?
        }

    }

}
