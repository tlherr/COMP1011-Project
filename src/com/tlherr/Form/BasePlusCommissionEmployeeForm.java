package com.tlherr.Form;

import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Resources.Strings;

import javax.swing.*;

public class BasePlusCommissionEmployeeForm extends AbstractEmployeeForm {

    public BasePlusCommissionEmployeeForm() {
        super();
    }

    public BasePlusCommissionEmployeeForm(AbstractEmployee empl) {
        super(empl);
    }

    @Override
    public JPanel addFormElements() {

        //This adds any extra form elements beyond the base ones provided by abstract employee form


        return contentPanel;
    }

    @Override
    public void bindFormData() {

    }
}
