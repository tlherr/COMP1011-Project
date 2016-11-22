package com.tlherr.Panels;

import com.tlherr.Model.Employee.EmployeeTableModel;
import com.tlherr.Resources.Strings;

import javax.swing.*;
import java.awt.*;

/**
 * HR tab that has the ability to collect all the information of all types of
 * Employees
 */
public class HumanResourcesPanel extends Panel {

    private JTable employeeTable;
    private JPanel employeeOperationsButtons;
    private JButton addEmployeeButton;
    private JButton deleteEmployeeButton;
    private JButton editEmployeeButton;

    public HumanResourcesPanel() {
        setLayout(new BorderLayout());
        employeeTable = new JTable(new EmployeeTableModel());
        employeeTable.setFillsViewportHeight(true);
        add(employeeTable, BorderLayout.NORTH);

        employeeOperationsButtons = new JPanel(new FlowLayout());
        addEmployeeButton = new JButton(Strings.ADD_EMPLOYEE);
        deleteEmployeeButton = new JButton(Strings.DELETE_EMPLOYEE);
        editEmployeeButton = new JButton(Strings.EDIT_EMPLOYEE);

        employeeOperationsButtons.add(addEmployeeButton);
        employeeOperationsButtons.add(deleteEmployeeButton);
        employeeOperationsButtons.add(editEmployeeButton);

        add(employeeOperationsButtons, BorderLayout.SOUTH);
    }

}
