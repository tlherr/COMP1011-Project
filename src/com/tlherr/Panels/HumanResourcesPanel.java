package com.tlherr.Panels;

import com.tlherr.Components.EmployeeTypeSelection;
import com.tlherr.Model.Employee.EmployeeTableModel;
import com.tlherr.Resources.Strings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private JFrame frameReference;

    public HumanResourcesPanel() {
        setLayout(new BorderLayout());

        //Handy if frame reference is needed from panel
        frameReference = (JFrame) SwingUtilities.getWindowAncestor(this);

        employeeTable = new JTable(new EmployeeTableModel());
        employeeTable.setFillsViewportHeight(true);
        add(employeeTable, BorderLayout.NORTH);

        employeeOperationsButtons = new JPanel(new FlowLayout());
        addEmployeeButton = new JButton(Strings.ADD_EMPLOYEE);
        addEmployeeButton.addActionListener(new AddEmployeeButtonListener());
        deleteEmployeeButton = new JButton(Strings.DELETE_EMPLOYEE);
        editEmployeeButton = new JButton(Strings.EDIT_EMPLOYEE);

        employeeOperationsButtons.add(addEmployeeButton);
        employeeOperationsButtons.add(deleteEmployeeButton);
        employeeOperationsButtons.add(editEmployeeButton);

        add(employeeOperationsButtons, BorderLayout.SOUTH);
    }

    private class AddEmployeeButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //Spawn a new selection window asking the user what type of Employee they want to add

            EmployeeTypeSelection typeSelection = new EmployeeTypeSelection();
            int result = JOptionPane.showConfirmDialog(frameReference, typeSelection.build(), Strings.EMPLOYEE_TYPE_SELECTION_TITLE, JOptionPane.PLAIN_MESSAGE);

            if(result==JOptionPane.OK_OPTION) {
                System.out.println(typeSelection.getSelection());
                //Create a new info screen based on the selection given
            }
        }
    }

}
