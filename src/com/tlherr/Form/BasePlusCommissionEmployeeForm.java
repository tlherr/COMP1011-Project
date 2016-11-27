package com.tlherr.Form;

import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.Employee.BasePlusCommissionEmployee;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.InputService;

import javax.swing.*;

/**
 * This class implements a form panel that collects/displays data for an Employee of type BasePlusCommissionEmployee
 */
public class BasePlusCommissionEmployeeForm extends AbstractEmployeeForm {

    private JTextField commissionRateTextField;
    private JTextField salesTextField;
    private JTextField salaryTextField;

    public BasePlusCommissionEmployeeForm() {
        super();
    }

    @Override
    public AbstractEmployee submit() {
        BasePlusCommissionEmployee empl;

        //Check if an employee already exists for the form
        if(this.employee==null) {
            //Employee does not exist, create a new one
            empl = new BasePlusCommissionEmployee();
        } else {
            //If an employee does exist overwrite the existing values
            empl = (BasePlusCommissionEmployee) this.employee;
        }

        //Add form values to Employee object
        empl.setFirstName(firstNameTextField.getText());
        empl.setLastName(lastNameTextField.getText());
        empl.setPosition(positionTextField.getText());
        empl.setDepartment(departmentTextField.getText());
        empl.setCommissionRate(Float.parseFloat(commissionRateTextField.getText()));
        return empl;
    }

    @Override
    public Boolean validateForm() {
        //Check form values to see if they match expectations
        //@TODO: Modify to return array of failed values so validation messages can be displayed
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
        //This adds any extra form elements beyond the base ones provided by abstract employee form
        BasePlusCommissionEmployee empl = (BasePlusCommissionEmployee) this.employee;

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
