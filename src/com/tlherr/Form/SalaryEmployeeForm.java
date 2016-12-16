package com.tlherr.Form;

import com.tlherr.Input.ValidatedFormInput;
import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.Employee.SalaryEmployee;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.InputService;

import javax.swing.*;

/**
 * This class implements a form panel that collects/displays data for an Employee of type CommissionSalesEmployeeForm
 */
public class SalaryEmployeeForm extends AbstractEmployeeForm {

    private ValidatedFormInput salary;

    public SalaryEmployeeForm() {
        super();
    }

    @Override
    public AbstractEmployee submit() {
        SalaryEmployee empl;

        //Check if an employee already exists for the form
        if(this.employee==null) {
            //Employee does not exist, create a new one
            empl = new SalaryEmployee();
        } else {
            //If an employee does exist overwrite the existing values
            empl = (SalaryEmployee) this.employee;
        }

        //Add form values to Employee object
        empl.setFirstName(firstName.getValue());
        empl.setLastName(lastName.getValue());
        empl.setPosition(position.getValue());
        empl.setDepartment(department.getValue());
        empl.setSalary(salary.getDecimalValue());
        return empl;
    }

    public SalaryEmployeeForm(SalaryEmployee empl) {
        super(empl);
        build();

        firstName.setEditText(empl.getFirstName());
        lastName.setEditText(empl.getLastName());
        position.setEditText(empl.getPosition());
        department.setEditText(empl.getDepartment());
        salary.setEditText(empl.getSalary().toString());
    }

    @Override
    public void addFormElements() {
        salary = new ValidatedFormInput(Strings.S_EMPLOYEE_FORM_LABEL_SALARY, InputService.DECIMAL);

        addValidatedInput(salary);
    }
}
