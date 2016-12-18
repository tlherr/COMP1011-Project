package com.tlherr.Form;

import com.tlherr.Input.ValidatedTextFormInput;
import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.Employee.HourlyEmployee;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.InputService;

/**
 * This class implements a form panel that collects/displays data for an Employee of type CommissionSalesEmployeeForm
 */
public class HourlyEmployeeForm extends AbstractEmployeeForm {


    private ValidatedTextFormInput hourlyRate;
    private ValidatedTextFormInput hoursPerWeek;

    public HourlyEmployeeForm() {
        super();
    }

    @Override
    public AbstractEmployee submit() {
        HourlyEmployee empl;

        //Check if an employee already exists for the form
        if (this.employee == null) {
            //Employee does not exist, create a new one
            empl = new HourlyEmployee();
        } else {
            //If an employee does exist overwrite the existing values
            empl = (HourlyEmployee) this.employee;
        }

        //Add form values to Employee object
        empl.setFirstName(firstName.getValue());
        empl.setLastName(lastName.getValue());
        empl.setPosition(position.getValue());
        empl.setDepartment(department.getValue());
        empl.setHourlyRate(hourlyRate.getDecimalValue());
        empl.setHoursWorked(hoursPerWeek.getDecimalValue());
        return empl;
    }

    public HourlyEmployeeForm(HourlyEmployee empl) {
        super(empl);
        build();

        firstName.setValue(empl.getFirstName());
        lastName.setValue(empl.getLastName());
        position.setValue(empl.getPosition());
        department.setValue(empl.getDepartment());
        hoursPerWeek.setValue(String.valueOf(empl.getHourlyRate()));
        hourlyRate.setValue(String.valueOf(empl.getHoursWorked()));
    }

    @Override
    public void addFormElements() {
        hourlyRate = new ValidatedTextFormInput(Strings.H_EMPLOYEE_FORM_LABEL_HOURLY_RATE, InputService.DECIMAL);
        hoursPerWeek = new ValidatedTextFormInput(Strings.H_EMPLOYEE_FORM_LABEL_HOURS_PER_WEEK, InputService.DECIMAL);

        addInput(hourlyRate);
        addInput(hoursPerWeek);
    }
}
