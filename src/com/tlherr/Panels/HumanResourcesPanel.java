package com.tlherr.Panels;

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
    private JComboBox<String> employeeTypeSelector;
    private JButton deleteEmployeeButton;
    private JButton editEmployeeButton;


    public HumanResourcesPanel() {
        setLayout(new BorderLayout());

        //Handy if frame reference is needed from panel
        //frameReference = (JFrame) SwingUtilities.getWindowAncestor(this);

        employeeTable = new JTable(new EmployeeTableModel());
        employeeTable.setFillsViewportHeight(true);
        add(employeeTable, BorderLayout.NORTH);

        employeeOperationsButtons = new JPanel(new FlowLayout());
        addEmployeeButton = new JButton(Strings.ADD_EMPLOYEE);
        String[] options = new String[]{"Base + Commission", "Commission", "Hourly"};
        employeeTypeSelector = new JComboBox<>(options);

        addEmployeeButton.addActionListener(new AddEmployeeButtonListener());
        deleteEmployeeButton = new JButton(Strings.DELETE_EMPLOYEE);
        editEmployeeButton = new JButton(Strings.EDIT_EMPLOYEE);

        employeeOperationsButtons.add(addEmployeeButton);
        employeeOperationsButtons.add(employeeTypeSelector);
        employeeOperationsButtons.add(deleteEmployeeButton);
        employeeOperationsButtons.add(editEmployeeButton);

        add(employeeOperationsButtons, BorderLayout.SOUTH);
    }

    private class AddEmployeeButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //Spawn a new selection window asking the user what type of Employee they want to add
            switch(employeeTypeSelector.getSelectedIndex()) {
                case 0:
                    //Create a new Base+Commission Employee
                    break;

                case 1:
                    //Create a new Commission Employee
                    break;

                case 2:
                    //Create a new Hourly Employee
                    break;
            }
        }
    }

}
