package com.tlherr.Form;

import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.Employee.BasePlusCommissionEmployee;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.InputService;

import javax.swing.*;

public class BasePlusCommissionEmployeeForm extends AbstractEmployeeForm {

    private JTextField commissionRateTextField;
    private JTextField salesTextField;
    private JTextField salaryTextField;

    public BasePlusCommissionEmployeeForm() {
        super();
    }

    @Override
    public AbstractEmployee submit() {
        //This is form submission, get the values from the fields then return an employee

        BasePlusCommissionEmployee empl;

        if(this.employee==null) {
            empl = new BasePlusCommissionEmployee();
        } else {
            empl = (BasePlusCommissionEmployee) this.employee;
        }

        empl.setFirstName(firstNameTextField.getText());
        empl.setLastName(lastNameTextField.getText());
        empl.setPosition(positionTextField.getText());
        empl.setDepartment(departmentTextField.getText());
        empl.setCommissionRate(Float.parseFloat(commissionRateTextField.getText()));
        return empl;
    }

    @Override
    public Boolean validateForm() {
        //Check form values

        return InputService.validate(firstNameTextField.getText(), InputService.CHARACTERS_ONLY)
                && InputService.validate(lastNameTextField.getText(), InputService.CHARACTERS_ONLY)
                && InputService.validate(positionTextField.getText(), InputService.CHARACTERS_ONLY)
                && InputService.validate(departmentTextField.getText(), InputService.CHARACTERS_ONLY)
                && InputService.validate(commissionRateTextField.getText(), InputService.NUMERIC_ONLY)
                && InputService.validate(salesTextField.getText(), InputService.NUMERIC_ONLY)
                && InputService.validate(salaryTextField.getText(), InputService.NUMERIC_ONLY);
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

        commissionRateTextField = new JTextField();
        if(this.employee!=null) {
            commissionRateTextField.setText(String.valueOf(empl.getCommissionRate()));
        }
        addTextField(commissionRateTextField);

        //Sales
        JLabel salesLabel = new JLabel(Strings.C_EMPLOYEE_FORM_LABEL_SALES);
        addLabel(salesLabel);

        salesTextField = new JTextField();
        if(this.employee!=null) {
            salesTextField.setText(String.valueOf(empl.getSales()));
        }
        addTextField(salesTextField);

        //Base Salary
        JLabel salaryLabel = new JLabel(Strings.BPC_EMPLOYEE_FORM_LABEL_SALARY);
        addLabel(salaryLabel);

        salaryTextField = new JTextField();
        if(this.employee!=null) {
            salaryTextField.setText(String.valueOf(empl.getBaseSalary()));
        }
        addTextField(salaryTextField);



    }
}
