package com.tlherr.Form;

import com.tlherr.Input.ValidatedTextFormInput;
import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.Employee.BasePlusCommissionEmployee;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.InputService;

/**
 * This class implements a form panel that collects/displays data for an Employee of type BasePlusCommissionEmployee
 */
public class BasePlusCommissionEmployeeForm extends AbstractEmployeeForm {

    private ValidatedTextFormInput commissionRate;
    private ValidatedTextFormInput sales;
    private ValidatedTextFormInput salary;

    public BasePlusCommissionEmployeeForm() {
        super();
    }

    public BasePlusCommissionEmployeeForm(BasePlusCommissionEmployee empl) {
        super(empl);
        //If we were given an employee this is an edit
        build();

        firstName.setValue(empl.getFirstName());
        lastName.setValue(empl.getLastName());
        position.setValue(empl.getPosition());
        department.setValue(empl.getDepartment());
        sales.setValue(empl.getSales().toString());
        salary.setValue(empl.getBaseSalary().toString());
        commissionRate.setValue(empl.getCommissionRate().toString());
    }

    @Override
    public AbstractEmployee submit() {
        BasePlusCommissionEmployee empl;

        //Check if an employee already exists for the form
        if (this.employee == null) {
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
        empl.setCommissionRate(commissionRate.getDecimalValue());
        return empl;
    }

    @Override
    public void addFormElements() {
        //This adds any extra form elements beyond the base ones provided by abstract employee form

        commissionRate = new ValidatedTextFormInput(Strings.C_EMPLOYEE_FORM_LABEL_COMMISSION_RATE, InputService.DECIMAL);
        sales = new ValidatedTextFormInput(Strings.C_EMPLOYEE_FORM_LABEL_SALES, InputService.DECIMAL);
        salary = new ValidatedTextFormInput(Strings.BPC_EMPLOYEE_FORM_LABEL_SALARY, InputService.DECIMAL);

        addInput(commissionRate);
        addInput(sales);
        addInput(salary);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
