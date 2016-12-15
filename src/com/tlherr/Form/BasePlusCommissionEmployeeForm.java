package com.tlherr.Form;

import com.sun.corba.se.spi.orbutil.fsm.Input;
import com.tlherr.Input.ValidatedFormInput;
import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.Employee.BasePlusCommissionEmployee;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.InputService;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.math.BigDecimal;

/**
 * This class implements a form panel that collects/displays data for an Employee of type BasePlusCommissionEmployee
 */
public class BasePlusCommissionEmployeeForm extends AbstractEmployeeForm {

    private ValidatedFormInput commissionRate;
    private ValidatedFormInput sales;
    private ValidatedFormInput salary;

    public BasePlusCommissionEmployeeForm() {
        super();
    }

    public BasePlusCommissionEmployeeForm(BasePlusCommissionEmployee empl) {
        super(empl);
        //If we were given an employee this is an edit
        build();

        firstName.setEditText(empl.getFirstName());
        lastName.setEditText(empl.getLastName());
        position.setEditText(empl.getPosition());
        department.setEditText(empl.getDepartment());
        sales.setEditText(empl.getSales().toString());
        salary.setEditText(empl.getBaseSalary().toString());
        commissionRate.setEditText(empl.getCommissionRate().toString());
    }

    @Override
    public AbstractEmployee submit() {
        BasePlusCommissionEmployee empl;

        //Check if an employee already exists for the form
        if(this.employee==null) {
            //Employee does not exist, create a new one
            empl = new BasePlusCommissionEmployee();
        } else {
            //If an employee does exist overwrite the existing values
            empl = (BasePlusCommissionEmployee) this.employee;
        }

        //Add form values to Employee object
        empl.setFirstName(firstName.getValue());
        empl.setLastName(lastName.getValue());
        empl.setPosition(position.getValue());
        empl.setDepartment(department.getValue());
        empl.setSales(sales.getDecimalValue());
        empl.setBaseSalary(salary.getDecimalValue());
        empl.setCommissionRate(new BigDecimal(commissionRate.getValue()));
        return empl;
    }

    @Override
    public void addFormElements() {
        //This adds any extra form elements beyond the base ones provided by abstract employee form

        commissionRate = new ValidatedFormInput(Strings.C_EMPLOYEE_FORM_LABEL_COMMISSION_RATE, InputService.NUMERIC_ONLY);
        sales = new ValidatedFormInput(Strings.C_EMPLOYEE_FORM_LABEL_SALES, InputService.DECIMAL);
        salary = new ValidatedFormInput(Strings.BPC_EMPLOYEE_FORM_LABEL_SALARY, InputService.DECIMAL);

        addValidatedInput(commissionRate);
        addValidatedInput(sales);
        addValidatedInput(salary);
    }
}
