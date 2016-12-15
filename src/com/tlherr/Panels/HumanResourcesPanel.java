package com.tlherr.Panels;

import com.tlherr.Form.*;
import com.tlherr.Listener.AuthenticationListener;
import com.tlherr.Model.Employee.EmployeeTableModel;
import com.tlherr.Repository.EmployeeRepository;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.LoginService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * HR tab that has the ability to collect/display all the information of all types of
 * Employees
 */
public class HumanResourcesPanel extends BasePanel {

    private EmployeeTabbedPanel employeeTabbedPanel;
    private JPanel employeeOperationsButtons;
    private JButton addEmployeeButton;
    private JComboBox<String> employeeTypeSelector;
    private JButton deleteEmployeeButton;
    private JButton editEmployeeButton;
    private JPanel employeeFormPanel;

    /**
     * Form References
     */
    private BasePlusCommissionEmployeeForm bpcComissionEmployeeForm;
    private CommissionSalesEmployeeForm commissionSalesEmployeeForm;
    private HourlyEmployeeForm hourlyEmployeeForm;
    private SalaryEmployeeForm salaryEmployeeForm;


    public HumanResourcesPanel() {
        setLayout(new BorderLayout());

        employeeTabbedPanel = new EmployeeTabbedPanel();
        JScrollPane tablePanel = new JScrollPane(employeeTabbedPanel);
        tablePanel.setPreferredSize(new Dimension(400, 200));
        add(tablePanel, BorderLayout.NORTH);

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

        //Disable components until we have a logged in user
        enableComponents(employeeOperationsButtons, false);

        LoginService.getInstance().addListener(new AuthenticationListener() {
            @Override
            public void loggedIn(ActionEvent e) {
                enableComponents(employeeOperationsButtons, true);
                repack();
            }

            @Override
            public void loggedOut(ActionEvent e) {
                enableComponents(employeeOperationsButtons, false);
                repack();
            }
        });

        //Listen for table click events
        employeeTabbedPanel.getBasePlusCommissionTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if(e.getValueIsAdjusting()) {
                    System.out.println(e.toString());
                    //Now have the index of what was clicked, have to load that record into a form and display it
                }

            }
        });


    }

    private class AddEmployeeButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //disable buttons to prevent a user from adding multiple forms
            addEmployeeButton.setEnabled(false);
            employeeTypeSelector.setEnabled(false);


            //Based on user selection create a new user form for the specified type and display it
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
            repack();
        }
    }

    private class OkEmployeeButtonListener implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e ActionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            //Handle the OK button being clicked
            //Active form will validate and if successful submit data to repository

            switch(employeeTypeSelector.getSelectedIndex()) {
                case 0:
                    if(bpcComissionEmployeeForm.validateForm()) {
                        bpcComissionEmployeeForm.submit().save();
                        clearForm();
                    }
                    break;

                case 1:
                    if(commissionSalesEmployeeForm.validateForm()) {
                        commissionSalesEmployeeForm.submit().save();
                        clearForm();
                    }
                    break;

                case 2:
                    if(hourlyEmployeeForm.validateForm()) {
                        hourlyEmployeeForm.submit().save();
                        clearForm();
                    }
                    break;

                case 3:
                    if(salaryEmployeeForm.validateForm()) {
                        salaryEmployeeForm.submit().save();
                        clearForm();
                    }
                    break;
            }
            repack();
        }
    }

    private void clearForm() {
        employeeFormPanel.removeAll();
        addEmployeeButton.setEnabled(true);
        employeeTypeSelector.setEnabled(true);

        hourlyEmployeeForm = null;
        bpcComissionEmployeeForm = null;
        commissionSalesEmployeeForm = null;
        salaryEmployeeForm = null;
        repack();
    }


    /**
     * Listener for cancel button, if user wants to discard new employee data entry
     */
    private class CancelEmployeeButtonListener implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e ActionEvent event
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


    /**
     * @ref https://docs.oracle.com/javase/8/docs/api/java/awt/Window.html#pack--
     * "Packing"  Causes this Window to be sized to fit the preferred size and layouts of its subcomponents
     * This can be called when components are added or removed to ensure window is sized correctly
     * Warning: This may move elements around which is annoying for end users.
     */
    public void repack() {
        SwingUtilities.getWindowAncestor(this).pack();
    }

}
