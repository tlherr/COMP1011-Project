package com.tlherr.Form;

import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.Employee.BasePlusCommissionEmployee;
import com.tlherr.Resources.Strings;

import javax.swing.*;

public class BasePlusCommissionEmployeeForm extends AbstractEmployeeForm {

    public BasePlusCommissionEmployeeForm() {
        super();
    }

    public BasePlusCommissionEmployeeForm(BasePlusCommissionEmployee empl) {
        super(empl);
    }

    @Override
    public void addFormElements() {
        BasePlusCommissionEmployee empl = (BasePlusCommissionEmployee) this.employee;

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

        //Base Salary
        JLabel salaryLabel = new JLabel(Strings.BPC_EMPLOYEE_FORM_LABEL_SALARY);
        addLabel(salaryLabel);

        JTextField salaryTextField = new JTextField();
        if(this.employee!=null) {
            salaryTextField.setText(String.valueOf(empl.getBaseSalary()));
        }
        addTextField(salaryTextField);



    }
}
