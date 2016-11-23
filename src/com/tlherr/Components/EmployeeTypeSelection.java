package com.tlherr.Components;

import com.tlherr.Resources.Strings;

import javax.swing.*;

public class EmployeeTypeSelection {

    private String[] options;
    private JComboBox<String> employeeTypeComboBox;

    public EmployeeTypeSelection() {
        options = new String[]{"Base + Commission", "Commission", "Hourly"};
        employeeTypeComboBox = new JComboBox<>(options);
    }

    public JComponent[] build() {
        return new JComponent[] {
                new JLabel(Strings.EMPLOYEE_TYPE_SELECTION_LABEL),
                employeeTypeComboBox
        };
    }

    public int getSelection() {
        return employeeTypeComboBox.getSelectedIndex();
    }
}
