package com.tlherr.Form;

import com.tlherr.Model.Employee.CommissionSalesEmployee;
import com.tlherr.Resources.Strings;

import javax.swing.*;

public class CommissionSalesEmployeeForm extends AbstractEmployeeForm {

    public CommissionSalesEmployeeForm() {
        super();
    }

    public CommissionSalesEmployeeForm(CommissionSalesEmployee empl) {
        super(empl);
    }

    @Override
    public void addFormElements() {
        CommissionSalesEmployee empl = (CommissionSalesEmployee) this.employee;

        //This adds any extra form elements beyond the base ones provided by abstract employee form

        //Commission Rate
        JLabel commissionRateLabel = new JLabel(Strings.C_EMPLOYEE_FORM_LABEL_COMMISSION_RATE);
        addLabel(commissionRateLabel);

        JTextField commissionRateTextField = new JTextField();
        if(this.employee!=null) {
            commissionRateTextField.setText(String.valueOf(empl.getCommissionRate()));
        }
        addTextField(commissionRateTextField);

        //Sales
        JLabel salesLabel = new JLabel(Strings.C_EMPLOYEE_FORM_LABEL_SALES);
        addLabel(salesLabel);

        JTextField salesTextField = new JTextField();
        if(this.employee!=null) {
            salesTextField.setText(String.valueOf(empl.getSales()));
        }
        addTextField(salesTextField);

    }
}
