package com.tlherr.Form;

import com.tlherr.Input.ValidatedFormInput;
import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.Employee.CommissionSalesEmployee;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.InputService;

import javax.swing.*;
import java.math.BigDecimal;

/**
 * This class implements a form panel that collects/displays data for an Employee of type CommissionSalesEmployeeForm
 */
public class CommissionSalesEmployeeForm extends AbstractEmployeeForm {

    private ValidatedFormInput commissionRate;
    private ValidatedFormInput sales;

    public CommissionSalesEmployeeForm() {
        super();
    }

    @Override
    public AbstractEmployee submit() {
        CommissionSalesEmployee empl;

        //Check if an employee already exists for the form
        if(this.employee==null) {
            //Employee does not exist, create a new one
            empl = new CommissionSalesEmployee();
        } else {
            //If an employee does exist overwrite the existing values
            empl = (CommissionSalesEmployee) this.employee;
        }

        //Add form values to Employee object
        empl.setFirstName(firstName.getValue());
        empl.setLastName(lastName.getValue());
        empl.setPosition(position.getValue());
        empl.setDepartment(department.getValue());
        empl.setSales(sales.getDecimalValue());
        empl.setCommissionRate(new BigDecimal(commissionRate.getValue()));
        return empl;
    }

    public CommissionSalesEmployeeForm(CommissionSalesEmployee empl) {
        super(empl);
    }

    @Override
    public void addFormElements() {

        //This adds any extra form elements beyond the base ones provided by abstract employee form
        commissionRate = new ValidatedFormInput(Strings.C_EMPLOYEE_FORM_LABEL_COMMISSION_RATE, InputService.NUMERIC_ONLY);
        sales = new ValidatedFormInput(Strings.C_EMPLOYEE_FORM_LABEL_SALES, InputService.DECIMAL);

        addValidatedInput(commissionRate);
        addValidatedInput(sales);
    }
}
