package com.tlherr.Form;

import com.tlherr.Model.Employee.CommissionSalesEmployee;
import com.tlherr.Model.Employee.HourlyEmployee;
import com.tlherr.Resources.Strings;

import javax.swing.*;

public class HourlyEmployeeForm extends AbstractEmployeeForm {

    public HourlyEmployeeForm() {
        super();
    }

    public HourlyEmployeeForm(HourlyEmployee empl) {
        super(empl);
    }

    @Override
    public void addFormElements() {
        HourlyEmployee empl = (HourlyEmployee) this.employee;

        //This adds any extra form elements beyond the base ones provided by abstract employee form

        //Commission Rate
        JLabel hourlyRateLabel = new JLabel(Strings.H_EMPLOYEE_FORM_LABEL_HOURLY_RATE);
        addLabel(hourlyRateLabel);

        JTextField hourlyRateTextField = new JTextField();
        if(this.employee!=null) {
            hourlyRateTextField.setText(String.valueOf(empl.getHourlyRate()));
        }
        addTextField(hourlyRateTextField);


    }
}
