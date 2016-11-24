package com.tlherr.Service;

import com.tlherr.Main;
import com.tlherr.Menu.ConsoleMenu;
import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.Employee.BasePlusCommissionEmployee;
import com.tlherr.Model.Employee.CommissionSalesEmployee;
import com.tlherr.Model.Employee.HourlyEmployee;
import com.tlherr.Repository.EmployeeRepository;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        employeeMenuOptions.put(1, "Hourly AbstractEmployeeForm");
        employeeMenuOptions.put(2, "Base+Commission AbstractEmployeeForm");
        employeeMenuOptions.put(3, "Commission AbstractEmployeeForm");
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
     * This method is called to get employee information
     */
    private void getInformation() {
        if(employee!=null) {
            employee.setFirstName(InputService.getStringInput(
                    "Enter the firstname of the AbstractEmployeeForm", InputService.CHARACTERS_ONLY, "John"));
            employee.setLastName(InputService.getStringInput(
                    "Enter the lastname of the AbstractEmployeeForm", InputService.CHARACTERS_ONLY, "Smith"));
            employee.setPosition(InputService.getStringInput(
                    "Enter the position of the AbstractEmployeeForm", InputService.CHARACTERS_ONLY, "Position"));
            employee.setDepartment(InputService.getStringInput(
                    "Enter the department of the AbstractEmployeeForm", InputService.CHARACTERS_ONLY, "Department"));
            employee.setSocialInsuranceNumber(InputService.getIntegerInput(
                    "Enter the social insurance number of the AbstractEmployeeForm", "123456789 (No dashes)"));
            employee.setEmail(InputService.getStringInput(
                    "Enter the email of the AbstractEmployeeForm", InputService.EMAIL_BASIC, "user@address.tld"));
            employee.setPhoneNumber(InputService.getStringInput(
                    "Enter the phone number of the AbstractEmployeeForm", InputService.PHONE_NUMBER, "111-222-3333 or 111.222.3333"));
            employee.setAddress(InputService.getStringInput(
                    "Enter the address of the AbstractEmployeeForm", InputService.ALPHANUMERIC_WORDS, "123 Fake St (No special characters)"));
            employee.setGender(InputService.getStringInput(
                    "Enter the gender of the AbstractEmployeeForm", InputService.GENDER, "M m or F f"));
            employee.setVacationsDays(InputService.getIntegerInput(
                    "Enter the vacation days of the AbstractEmployeeForm", "23 (Non negative whole numbers)"));

            employee.setDateHired(InputService.getDateInput(
                    "Enter the date the AbstractEmployeeForm was hired", "2002-01-01"));

            employee.setDateOfBirth(InputService.getDateInput(
                    "Enter the date of birth of the AbstractEmployeeForm", "2002-01-01"));


            //Dat polymorphism
            if(employee instanceof HourlyEmployee) {
                ((HourlyEmployee) employee).setHourlyRate(InputService.getFloatInput(
                        "Enter the hourly rate of the AbstractEmployeeForm", "42343.00"));
                ((HourlyEmployee) employee).setHoursWorked(InputService.getFloatInput(
                        "Enter the hours worked by the AbstractEmployeeForm", "42343.00"));
            }

            if (employee instanceof BasePlusCommissionEmployee) {
                ((BasePlusCommissionEmployee) employee).setBaseSalary(InputService.getFloatInput(
                        "Enter the base salary of the AbstractEmployeeForm", "42343.00"));
            }


            if(employee instanceof CommissionSalesEmployee) {
                ((CommissionSalesEmployee) employee).setCommissionRate(InputService.getFloatInput(
                        "Enter the commision rate of the AbstractEmployeeForm", "42343.00"));
                ((CommissionSalesEmployee) employee).setSales(InputService.getFloatInput(
                        "Enter the sales percentage of the AbstractEmployeeForm", "10%"));
            }

            //At this point we should have a full employee, save it

            EmployeeRepository.getInstance().addEmployee(employee);
            System.out.println("AbstractEmployeeForm Saved (ID: "+employee.getIdNumber()+")");

            String[] args = {};
            Main.main(args);
        }
    }

    public void findEmployee() {
        AbstractEmployee foundEmployee = EmployeeRepository.getInstance().findByFirstName(InputService.getStringInput(
                "Enter the firstname of the AbstractEmployeeForm", InputService.CHARACTERS_ONLY, "John"));

        if(foundEmployee!=null) {
            System.out.println("Found a matching employee ID:"+foundEmployee.getIdNumber());
        } else {
            System.out.println("No Matching Record Found");
        }

        String[] args = {};
        Main.main(args);
    }

}
