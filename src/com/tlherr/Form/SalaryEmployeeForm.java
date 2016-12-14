package com.tlherr.Form;

import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.Employee.SalaryEmployee;
import com.tlherr.Resources.Strings;

import javax.swing.*;

/**
 * This class implements a form panel that collects/displays data for an Employee of type CommissionSalesEmployeeForm
 */
public class SalaryEmployeeForm extends AbstractEmployeeForm {

    public SalaryEmployeeForm() {
        super();
    }

    @Override
    public AbstractEmployee submit() {
        return null;
    }

    @Override
    public Boolean validateForm() {
        return null;
    }

    public SalaryEmployeeForm(SalaryEmployee empl) {
        super(empl);
    }

    @Override
    public void addFormElements() {
        SalaryEmployee empl = (SalaryEmployee) this.employee;

        //This adds any extra form elements beyond the base ones provided by abstract employee form

        //Salary
//        JLabel salaryLabel = new JLabel(Strings.BPC_EMPLOYEE_FORM_LABEL_SALARY);
//        addLabel(salaryLabel);
//
//        JTextField salaryTextField = new JTextField();
//        if(this.employee!=null) {
//            salaryTextField.setText(String.valueOf(empl.getSalary()));
//        }
//        addTextField(salaryTextField);
    }
}
