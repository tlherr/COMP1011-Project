package com.tlherr.Panels;

import com.tlherr.Form.BasePlusCommissionEmployeeForm;
import com.tlherr.Form.CommissionSalesEmployeeForm;
import com.tlherr.Form.HourlyEmployeeForm;
import com.tlherr.Form.SalaryEmployeeForm;
import com.tlherr.Listener.AuthenticationListener;
import com.tlherr.Model.Employee.BasePlusCommissionEmployee;
import com.tlherr.Model.Employee.CommissionSalesEmployee;
import com.tlherr.Model.Employee.HourlyEmployee;
import com.tlherr.Model.Employee.SalaryEmployee;
import com.tlherr.Permissions.EmployeePermission;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.LoginService;
import com.tlherr.Users.AdminUser;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * HR tab that has the ability to collect/display all the information of all types of
 * Employees
 */
public class HumanResourcesPanel extends AbstractPanel {

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

        deleteEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(LoginService.getInstance().getActiveUser().getPermissions().implies(AdminUser.employeePermission)) {
                    //Check which tab is active
                    switch(employeeTabbedPanel.getActiveTab()) {
                        case 0:
                            //BasePlusCommission selected

                            if(employeeTabbedPanel.getBasePlusCommissionTable().getSelectedRow()!=-1) {
                                DefaultTableModel model = (DefaultTableModel) employeeTabbedPanel.getBasePlusCommissionTable().getModel();
                                Vector vectorResult = (Vector) model.getDataVector().elementAt(employeeTabbedPanel.getBasePlusCommissionTable().getSelectedRow());
                                BasePlusCommissionEmployee empl = new BasePlusCommissionEmployee(vectorResult);
                                empl.delete();
                                clearForm();
                                employeeTabbedPanel.updateBasePlusCommissionTable();
                            } else {
                                JOptionPane.showMessageDialog(null, "Please select a base plus commission employee to delete");
                            }
                            break;


                        case 1:
                            if(employeeTabbedPanel.getCommissionSalesTable().getSelectedRow()!=-1) {
                                DefaultTableModel model = (DefaultTableModel) employeeTabbedPanel.getCommissionSalesTable().getModel();
                                Vector vectorResult = (Vector) model.getDataVector().elementAt(employeeTabbedPanel.getCommissionSalesTable().getSelectedRow());
                                CommissionSalesEmployee empl = new CommissionSalesEmployee(vectorResult);
                                empl.delete();
                                clearForm();
                                employeeTabbedPanel.updateCommissionSalesTable();
                            } else {
                                //Select something first Show a message if nothing is selected
                                JOptionPane.showMessageDialog(null, "Please select a commission employee to delete");
                            }
                            break;

                        case 2:
                            if(employeeTabbedPanel.getHourlyTable().getSelectedRow()!=-1) {
                                DefaultTableModel model = (DefaultTableModel) employeeTabbedPanel.getHourlyTable().getModel();
                                Vector vectorResult = (Vector) model.getDataVector().elementAt(employeeTabbedPanel.getHourlyTable().getSelectedRow());
                                HourlyEmployee empl = new HourlyEmployee(vectorResult);
                                empl.delete();
                                clearForm();
                                employeeTabbedPanel.updateHourlyTable();
                            } else {
                                //Select something first Show a message if nothing is selected
                                JOptionPane.showMessageDialog(null, "Please select an hourly employee to delete");
                            }
                            break;

                        case 3:
                            if(employeeTabbedPanel.getSalaryTable().getSelectedRow()!=-1) {
                                DefaultTableModel model = (DefaultTableModel) employeeTabbedPanel.getSalaryTable().getModel();
                                Vector vectorResult = (Vector) model.getDataVector().elementAt(employeeTabbedPanel.getSalaryTable().getSelectedRow());
                                SalaryEmployee empl = new SalaryEmployee(vectorResult);
                                empl.delete();
                                clearForm();
                                employeeTabbedPanel.updateSalaryTable();
                            } else {
                                //Select something first Show a message if nothing is selected
                                JOptionPane.showMessageDialog(null, "Please select a salaried employee to delete");
                            }
                            break;
                    }

                }


                //Check which employee has been selected in the JTable


            }
        });

        //Disable components until we have a logged in user
        enableComponents(employeeOperationsButtons, false);
        enableComponents(employeeTabbedPanel, false);

        //Handle login events
        LoginService.getInstance().addListener(new AuthenticationListener() {
            @Override
            public void loggedIn(ActionEvent e) {
                enableComponents(employeeOperationsButtons, true);
                enableComponents(employeeTabbedPanel, true);
                repack();
            }

            @Override
            public void loggedOut(ActionEvent e) {
                enableComponents(employeeOperationsButtons, false);
                enableComponents(employeeTabbedPanel, false);
                repack();
            }
        });
        //Handle user selecting table items
        employeeTabbedPanel.getBasePlusCommissionTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (LoginService.getInstance().getActiveUser().getPermissions().implies(AdminUser.employeePermission)) {
                    if (e.getValueIsAdjusting()) {
                        DefaultTableModel model = (DefaultTableModel) employeeTabbedPanel.getBasePlusCommissionTable().getModel();

                        try {
                            Vector vectorResult = (Vector) model.getDataVector().elementAt(employeeTabbedPanel.getBasePlusCommissionTable().getSelectedRow());
                            BasePlusCommissionEmployee empl = new BasePlusCommissionEmployee(vectorResult);

                            addEmployeeButton.setEnabled(false);
                            employeeTypeSelector.setEnabled(false);

                            clearForm();
                            bpcComissionEmployeeForm = new BasePlusCommissionEmployeeForm(empl);
                            bpcComissionEmployeeForm.setOkButtonActionListener(new OkEmployeeTabbedPanelButtonListener());
                            bpcComissionEmployeeForm.setCancelButtonActionListener(new CancelEmployeeButtonListener());
                            employeeFormPanel.add(bpcComissionEmployeeForm, BorderLayout.CENTER);
                            repack();
                        } catch (ArrayIndexOutOfBoundsException exception) {
                            //@TODO: Logging method should handle this as stated in requirements
                        }
                    }
                }
            }
        });

        employeeTabbedPanel.getCommissionSalesTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (LoginService.getInstance().getActiveUser().getPermissions().implies(AdminUser.employeePermission)) {
                    if (e.getValueIsAdjusting()) {
                        DefaultTableModel model = (DefaultTableModel) employeeTabbedPanel.getCommissionSalesTable().getModel();

                        try {
                            Vector vectorResult = (Vector) model.getDataVector().elementAt(employeeTabbedPanel.getCommissionSalesTable().getSelectedRow());
                            CommissionSalesEmployee empl = new CommissionSalesEmployee(vectorResult);

                            addEmployeeButton.setEnabled(false);
                            employeeTypeSelector.setEnabled(false);

                            clearForm();
                            commissionSalesEmployeeForm = new CommissionSalesEmployeeForm(empl);
                            commissionSalesEmployeeForm.setOkButtonActionListener(new OkEmployeeTabbedPanelButtonListener());
                            commissionSalesEmployeeForm.setCancelButtonActionListener(new CancelEmployeeButtonListener());
                            employeeFormPanel.add(commissionSalesEmployeeForm, BorderLayout.CENTER);
                            repack();
                        } catch (ArrayIndexOutOfBoundsException exception) {
                            //@TODO: Logging method should handle this as stated in requirements
                        }
                    }
                }
            }
        });

        employeeTabbedPanel.getHourlyTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (LoginService.getInstance().getActiveUser().getPermissions().implies(AdminUser.employeePermission)) {
                    if (e.getValueIsAdjusting()) {
                        DefaultTableModel model = (DefaultTableModel) employeeTabbedPanel.getHourlyTable().getModel();

                        try {
                            Vector vectorResult = (Vector) model.getDataVector().elementAt(employeeTabbedPanel.getHourlyTable().getSelectedRow());
                            HourlyEmployee empl = new HourlyEmployee(vectorResult);

                            addEmployeeButton.setEnabled(false);
                            employeeTypeSelector.setEnabled(false);

                            clearForm();
                            hourlyEmployeeForm = new HourlyEmployeeForm(empl);
                            hourlyEmployeeForm.setOkButtonActionListener(new OkEmployeeTabbedPanelButtonListener());
                            hourlyEmployeeForm.setCancelButtonActionListener(new CancelEmployeeButtonListener());
                            employeeFormPanel.add(hourlyEmployeeForm, BorderLayout.CENTER);
                            repack();
                        } catch (ArrayIndexOutOfBoundsException exception) {
                            //@TODO: Logging method should handle this as stated in requirements
                        }
                    }
                }
            }
        });

        employeeTabbedPanel.getSalaryTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (LoginService.getInstance().getActiveUser().getPermissions().implies(AdminUser.employeePermission)) {
                    if (e.getValueIsAdjusting()) {
                        DefaultTableModel model = (DefaultTableModel) employeeTabbedPanel.getSalaryTable().getModel();

                        try {
                            Vector vectorResult = (Vector) model.getDataVector().elementAt(employeeTabbedPanel.getSalaryTable().getSelectedRow());
                            SalaryEmployee empl = new SalaryEmployee(vectorResult);

                            addEmployeeButton.setEnabled(false);
                            employeeTypeSelector.setEnabled(false);

                            clearForm();
                            salaryEmployeeForm = new SalaryEmployeeForm(empl);
                            salaryEmployeeForm.setOkButtonActionListener(new OkEmployeeTabbedPanelButtonListener());
                            salaryEmployeeForm.setCancelButtonActionListener(new CancelEmployeeButtonListener());
                            employeeFormPanel.add(salaryEmployeeForm, BorderLayout.CENTER);
                            repack();
                        } catch (ArrayIndexOutOfBoundsException exception) {
                            //@TODO: Logging method should handle this as stated in requirements
                        }
                    }
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
            switch (employeeTypeSelector.getSelectedIndex()) {
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


    //This is the ok button listener for forms added via the dropdown
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

            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                switch (employeeTypeSelector.getSelectedIndex()) {
                    case 0:
                        if (bpcComissionEmployeeForm.validateForm()) {
                            bpcComissionEmployeeForm.submit().save();
                            employeeTabbedPanel.updateBasePlusCommissionTable();
                        }
                        break;

                    case 1:
                        if (commissionSalesEmployeeForm.validateForm()) {
                            commissionSalesEmployeeForm.submit().save();
                            employeeTabbedPanel.updateCommissionSalesTable();
                            clearForm();
                        }
                        break;

                    case 2:
                        if (hourlyEmployeeForm.validateForm()) {
                            hourlyEmployeeForm.submit().save();
                            employeeTabbedPanel.updateHourlyTable();
                            clearForm();
                        }
                        break;

                    case 3:
                        if (salaryEmployeeForm.validateForm()) {
                            salaryEmployeeForm.submit().save();
                            employeeTabbedPanel.updateSalaryTable();
                            clearForm();
                        }
                        break;
                }
            }
        }
    }

    private class OkEmployeeTabbedPanelButtonListener implements ActionListener {
        //Needs one
        @Override
        public void actionPerformed(ActionEvent e) {

            switch (employeeTabbedPanel.getActiveTab()) {
                case 0:
                    if (bpcComissionEmployeeForm.validateForm()) {
                        bpcComissionEmployeeForm.submit().save();
                        employeeTabbedPanel.updateBasePlusCommissionTable();
                        clearForm();
                    }
                    break;

                case 1:
                    if (commissionSalesEmployeeForm.validateForm()) {
                        commissionSalesEmployeeForm.submit().save();
                        employeeTabbedPanel.updateCommissionSalesTable();
                        clearForm();
                    }
                    break;

                case 2:
                    if (hourlyEmployeeForm.validateForm()) {
                        hourlyEmployeeForm.submit().save();
                        employeeTabbedPanel.updateHourlyTable();
                        clearForm();
                    }
                    break;

                case 3:
                    if (salaryEmployeeForm.validateForm()) {
                        salaryEmployeeForm.submit().save();
                        employeeTabbedPanel.updateSalaryTable();
                        clearForm();
                    }
                    break;
            }

        }
    }

    private void clearForm() {
        employeeFormPanel.removeAll();
        addEmployeeButton.setEnabled(true);
        employeeTypeSelector.setEnabled(true);

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
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
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
    }
}
