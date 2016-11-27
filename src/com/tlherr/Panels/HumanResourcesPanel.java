package com.tlherr.Panels;

import com.tlherr.Form.*;
import com.tlherr.Model.Employee.EmployeeTableModel;
import com.tlherr.Repository.EmployeeRepository;
import com.tlherr.Resources.Strings;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
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
    private JPanel employeeFormPanel;

    /**
     * Forms
     */
    private BasePlusCommissionEmployeeForm bpcComissionEmployeeForm;
    private CommissionSalesEmployeeForm commissionSalesEmployeeForm;
    private HourlyEmployeeForm hourlyEmployeeForm;
    private SalaryEmployeeForm salaryEmployeeForm;


    public HumanResourcesPanel() {
        setLayout(new BorderLayout());

        employeeTable = new JTable(new EmployeeTableModel());
        employeeTable.setPreferredScrollableViewportSize(employeeTable.getPreferredSize());
        employeeTable.setFillsViewportHeight(true);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i=0; i<employeeTable.getColumnCount(); i++) {
            employeeTable.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }

        add(new JScrollPane(employeeTable), BorderLayout.NORTH);

        employeeOperationsButtons = new JPanel(new FlowLayout());
        addEmployeeButton = new JButton(Strings.ADD_EMPLOYEE);
        String[] options = new String[]{"Base + Commission", "Commission", "Hourly", "Salary"};
        employeeTypeSelector = new JComboBox<>(options);

        addEmployeeButton.addActionListener(new AddEmployeeButtonListener());
        deleteEmployeeButton = new JButton(Strings.DELETE_EMPLOYEE);
        editEmployeeButton = new JButton(Strings.EDIT_EMPLOYEE);

        employeeOperationsButtons.add(addEmployeeButton);
        employeeOperationsButtons.add(employeeTypeSelector);
        employeeOperationsButtons.add(deleteEmployeeButton);
        employeeOperationsButtons.add(editEmployeeButton);

        add(employeeOperationsButtons, BorderLayout.CENTER);

        employeeFormPanel = new JPanel(new BorderLayout());
        add(employeeFormPanel, BorderLayout.SOUTH);
    }

    private class AddEmployeeButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            //Spawn a new selection window asking the user what type of AbstractEmployeeForm they want to add
            switch(employeeTypeSelector.getSelectedIndex()) {
                case 0:
                    //Create a new Base+Commission EmployeeForm
                    bpcComissionEmployeeForm = new BasePlusCommissionEmployeeForm();
                    bpcComissionEmployeeForm.build();
                    bpcComissionEmployeeForm.setOkButtonActionListener(new OkEmployeeButtonListener());
                    bpcComissionEmployeeForm.setCancelButtonActionListener(new CancelEmployeeButtonListener());
                    employeeFormPanel.add(bpcComissionEmployeeForm, BorderLayout.CENTER);
                    break;

                case 1:
                    //Create a new Commission EmployeeForm
                    commissionSalesEmployeeForm = new CommissionSalesEmployeeForm();
                    commissionSalesEmployeeForm.build();
                    commissionSalesEmployeeForm.setOkButtonActionListener(new OkEmployeeButtonListener());
                    commissionSalesEmployeeForm.setCancelButtonActionListener(new CancelEmployeeButtonListener());
                    employeeFormPanel.add(commissionSalesEmployeeForm, BorderLayout.CENTER);
                    break;

                case 2:
                    //Create a new Hourly EmployeeForm
                    hourlyEmployeeForm = new HourlyEmployeeForm();
                    hourlyEmployeeForm.build();
                    hourlyEmployeeForm.setOkButtonActionListener(new OkEmployeeButtonListener());
                    hourlyEmployeeForm.setCancelButtonActionListener(new CancelEmployeeButtonListener());
                    employeeFormPanel.add(hourlyEmployeeForm, BorderLayout.CENTER);
                    break;

                case 3:
                    //Create a new Salary EmployeeForm
                    salaryEmployeeForm = new SalaryEmployeeForm();
                    salaryEmployeeForm.build();
                    salaryEmployeeForm.setOkButtonActionListener(new OkEmployeeButtonListener());
                    salaryEmployeeForm.setCancelButtonActionListener(new CancelEmployeeButtonListener());
                    employeeFormPanel.add(salaryEmployeeForm, BorderLayout.CENTER);
                    break;


            }
            addEmployeeButton.setEnabled(false);
            employeeTypeSelector.setEnabled(false);
            repack();
        }
    }

    private class OkEmployeeButtonListener implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            //Handle the OK button being clicked
            switch(employeeTypeSelector.getSelectedIndex()) {
                case 0:
                    if(bpcComissionEmployeeForm.validateForm()) {
                        EmployeeRepository.getInstance().save(bpcComissionEmployeeForm.submit());
                    }
                    break;
            }

            employeeFormPanel.removeAll();
            addEmployeeButton.setEnabled(true);
            employeeTypeSelector.setEnabled(true);

            hourlyEmployeeForm = null;
            bpcComissionEmployeeForm = null;
            commissionSalesEmployeeForm = null;
            salaryEmployeeForm = null;
            repack();
        }
    }

    private class CancelEmployeeButtonListener implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            //Clear out the frame
            employeeFormPanel.removeAll();
            addEmployeeButton.setEnabled(true);
            employeeTypeSelector.setEnabled(true);

            hourlyEmployeeForm = null;
            bpcComissionEmployeeForm = null;
            commissionSalesEmployeeForm = null;
            salaryEmployeeForm = null;
            repack();
        }
    }



    public void repack() {
        SwingUtilities.getWindowAncestor(this).pack();
    }

}
