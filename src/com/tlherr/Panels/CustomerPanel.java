package com.tlherr.Panels;

import com.tlherr.Form.CustomerForm;
import com.tlherr.Listener.AuthenticationListener;
import com.tlherr.Model.Customer;
import com.tlherr.Repository.CustomerRepository;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.LoginService;
import com.tlherr.Table.GenericTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class CustomerPanel extends AbstractPanel {

    //class-based
    private JTable customerTable;
    private CustomerForm customerForm;
    private JButton createCustomerButton;
    private JButton deleteCustomerButton;

    public CustomerPanel() {
        setLayout(new BorderLayout());

        customerTable = new JTable();
        add(new JScrollPane(customerTable), BorderLayout.NORTH);

        createCustomerButton = new JButton(Strings.ADD_CUSTOMER_BUTTON);
        deleteCustomerButton = new JButton(Strings.DELETE_CUSTOMER_BUTTON);

        JPanel customerOperationsButtons = new JPanel(new FlowLayout());
        customerOperationsButtons.add(createCustomerButton);
        customerOperationsButtons.add(deleteCustomerButton);

        add(customerOperationsButtons, BorderLayout.CENTER);

        LoginService.getInstance().addListener(new AuthenticationListener() {
            @Override
            public void loggedIn(ActionEvent e) {
                updateCustomerTable();
            }

            @Override
            public void loggedOut(ActionEvent e) {

            }
        });

        createCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerForm = new CustomerForm();
                customerForm.build();
                //Set ok button listener for form submission
                customerForm.getOkButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (customerForm.validateForm()) {
                            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION);
                            if (dialogResult == JOptionPane.YES_OPTION) {
                                customerForm.submit().save();
                                customerForm.clear();
                                updateCustomerTable();
                            }
                        }
                    }
                });
                customerForm.setVisible(true);
                add(customerForm, BorderLayout.SOUTH);
                repack();
            }
        });

        customerTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
               // if (LoginService.getInstance().getActiveUser().getPermissions().implies(AdminUser.employeePermission)) {
                    if (e.getValueIsAdjusting()) {
                        DefaultTableModel model = (DefaultTableModel) customerTable.getModel();

                        try {
                            Vector vectorResult = (Vector) model.getDataVector().elementAt(customerTable.getSelectedRow());
                            Customer customer = new Customer(vectorResult);

                            createCustomerButton.setEnabled(false);
                            deleteCustomerButton.setEnabled(false);

                            customerForm = new CustomerForm(customer);
                            customerForm.build();
                            customerForm.setOkButtonActionListener(new okCustomerButtonListener());
                            customerForm.setCancelButtonActionListener(new cancelCustomerButtonListener());
                            add(customerForm, BorderLayout.SOUTH);
                            repack();
                        } catch (ArrayIndexOutOfBoundsException exception) {
                            //@TODO: Logging method should handle this as stated in requirements
                        }
                    }
                }
            //}
        });
    }

    private void updateCustomerTable() {
        try {
            ResultSet rs = CustomerRepository.getInstance().load(Customer.class);

            if (rs != null) {
                GenericTableModel tableModel = new GenericTableModel(rs);
                customerTable.setModel(tableModel);
            }
        } catch (SQLException e) {
            //@TODO: Handle with loggin class as stated in requirements
            e.printStackTrace();
        }
    }

    private class okCustomerButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {

                if (customerForm.validateForm()) {
                    customerForm.submit().save();
                    updateCustomerTable();
                    customerForm.clear();
                } else {
                    repack();
                }
            }
        }
    }

    private class cancelCustomerButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                customerForm.clear();
            }
        }
    }
}
