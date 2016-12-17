package com.tlherr.Panels;

import com.tlherr.Form.CustomerForm;
import com.tlherr.Resources.Strings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerPanel extends AbstractPanel {

    private JTable customerTable;
    private CustomerForm customerForm;
    private JPanel customerOperationsButtons;
    private JButton createCustomerButton;
    private JButton deleteCustomerButton;

    public CustomerPanel() {
        setLayout(new BorderLayout());

        customerTable = new JTable();
        add(new JScrollPane(customerTable), BorderLayout.NORTH);

        createCustomerButton = new JButton(Strings.ADD_CUSTOMER_BUTTON);
        deleteCustomerButton = new JButton(Strings.DELETE_CUSTOMER_BUTTON);

        customerOperationsButtons = new JPanel(new FlowLayout());
        customerOperationsButtons.add(createCustomerButton);
        customerOperationsButtons.add(deleteCustomerButton);

        add(customerOperationsButtons, BorderLayout.CENTER);

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
                            }
                        }
                    }
                });
                customerForm.setVisible(true);
                add(customerForm, BorderLayout.SOUTH);
                repack();
            }
        });
    }
}
