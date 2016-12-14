package com.tlherr.Form;

import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.Employee.CommissionSalesEmployee;
import com.tlherr.Model.Employee.HourlyEmployee;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.InputService;

import javax.swing.*;

/**
 * This class implements a form panel that collects/displays data for an Employee of type CommissionSalesEmployeeForm
 */
public class HourlyEmployeeForm extends AbstractEmployeeForm {

    private JTextField hourlyRateTextField;

    public HourlyEmployeeForm() {
        super();
    }

    @Override
    public AbstractEmployee submit() {
        HourlyEmployee empl;

        //Check if an employee already exists for the form
        if(this.employee==null) {
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
        empl.setHourlyRate(Float.parseFloat(hourlyRateTextField.getText()));
        return empl;
    }



    public HourlyEmployeeForm(HourlyEmployee empl) {
        super(empl);
    }

    @Override
    public void addFormElements() {
        HourlyEmployee empl = (HourlyEmployee) this.employee;

//        //This adds any extra form elements beyond the base ones provided by abstract employee form
//
//        //Commission Rate
//        JLabel hourlyRateLabel = new JLabel(Strings.H_EMPLOYEE_FORM_LABEL_HOURLY_RATE);
//        addLabel(hourlyRateLabel);
//
//        hourlyRateTextField = new JTextField();
//        if(this.employee!=null) {
//            hourlyRateTextField.setText(String.valueOf(empl.getHourlyRate()));
//        }
//        addTextField(hourlyRateTextField);
    }
}
