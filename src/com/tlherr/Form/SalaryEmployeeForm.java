package com.tlherr.Form;

import com.tlherr.Input.ValidatedTextFormInput;
import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.Employee.SalaryEmployee;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.InputService;

/**
 * This class implements a form panel that collects/displays data for an Employee of type CommissionSalesEmployeeForm
 */
public class SalaryEmployeeForm extends AbstractEmployeeForm {

    private ValidatedTextFormInput salary;

    public SalaryEmployeeForm() {
        super();
    }

    @Override
    public AbstractEmployee submit() {
        SalaryEmployee empl;

        //Check if an employee already exists for the form
        if (this.employee == null) {
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

        firstName.setValue(empl.getFirstName());
        lastName.setValue(empl.getLastName());
        position.setValue(empl.getPosition());
        department.setValue(empl.getDepartment());
        salary.setValue(empl.getSalary().toString());
    }

    @Override
    public void addFormElements() {
        salary = new ValidatedTextFormInput(Strings.S_EMPLOYEE_FORM_LABEL_SALARY, InputService.DECIMAL);

        addInput(salary);
    }
}
