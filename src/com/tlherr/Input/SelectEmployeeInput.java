package com.tlherr.Input;

import com.tlherr.Model.ComboBox.EmployeeComboBoxModel;
import com.tlherr.Model.GenericEmployee;

import javax.swing.*;

public class SelectEmployeeInput extends AbstractFormInput {

    private JComboBox employeeComboBox;
    private EmployeeComboBoxModel model;

    public SelectEmployeeInput(String inputLabelText) {
        super(inputLabelText);

        employeeComboBox = new JComboBox<>();
        model = new EmployeeComboBoxModel();
        employeeComboBox.setModel(model);

        addInputField(employeeComboBox);
    }

    @Override
    public GenericEmployee getValue() {
        return (GenericEmployee) employeeComboBox.getSelectedItem();
    }

    public void setValue(GenericEmployee employee) {
        model.setSelectedItem(employee);
    }
}
