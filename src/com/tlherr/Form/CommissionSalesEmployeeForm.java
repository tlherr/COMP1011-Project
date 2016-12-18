package com.tlherr.Form;

import com.tlherr.Input.ValidatedTextFormInput;
import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.Employee.CommissionSalesEmployee;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.InputService;

/**
 * This class implements a form panel that collects/displays data for an Employee of type CommissionSalesEmployeeForm
 */
public class CommissionSalesEmployeeForm extends AbstractEmployeeForm {

    private ValidatedTextFormInput commissionRate;
    private ValidatedTextFormInput sales;

    public CommissionSalesEmployeeForm() {
        super();
    }

    @Override
    public AbstractEmployee submit() {
        CommissionSalesEmployee empl;

        //Check if an employee already exists for the form
        if (this.employee == null) {
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
        empl.setCommissionRate(commissionRate.getDecimalValue());
        return empl;
    }

    public CommissionSalesEmployeeForm(CommissionSalesEmployee empl) {
        super(empl);

        build();

        firstName.setValue(empl.getFirstName());
        lastName.setValue(empl.getLastName());
        position.setValue(empl.getPosition());
        department.setValue(empl.getDepartment());
        sales.setValue(empl.getSales().toString());
        commissionRate.setValue(empl.getCommissionRate().toString());
    }

    @Override
    public void addFormElements() {

        //This adds any extra form elements beyond the base ones provided by abstract employee form
        commissionRate = new ValidatedTextFormInput(Strings.C_EMPLOYEE_FORM_LABEL_COMMISSION_RATE, InputService.NUMERIC_ONLY);
        sales = new ValidatedTextFormInput(Strings.C_EMPLOYEE_FORM_LABEL_SALES, InputService.DECIMAL);

        addInput(commissionRate);
        addInput(sales);
    }
}
