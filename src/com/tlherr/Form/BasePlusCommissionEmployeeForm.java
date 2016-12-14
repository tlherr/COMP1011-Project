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
        empl.setCommissionRate(Float.parseFloat(commissionRate.getValue()));
        return empl;
    }

    @Override
    public Boolean validateForm() {
       //Iterate over child elements and

        return true;
    }

    public BasePlusCommissionEmployeeForm(BasePlusCommissionEmployee empl) {
        super(empl);
    }

    @Override
    public void addFormElements() {
        //This adds any extra form elements beyond the base ones provided by abstract employee form
        BasePlusCommissionEmployee empl = (BasePlusCommissionEmployee) this.employee;

        commissionRate = new ValidatedFormInput(Strings.C_EMPLOYEE_FORM_LABEL_COMMISSION_RATE, InputService.DECIMAL);
        sales = new ValidatedFormInput(Strings.C_EMPLOYEE_FORM_LABEL_SALES, InputService.DECIMAL);
        salary = new ValidatedFormInput(Strings.BPC_EMPLOYEE_FORM_LABEL_SALARY, InputService.DECIMAL);

        addValidatedInput(commissionRate);
        addValidatedInput(sales);
        addValidatedInput(salary);
    }
}
