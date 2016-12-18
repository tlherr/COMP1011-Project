package com.tlherr.Panels;

import com.tlherr.Form.ProductForm;
import com.tlherr.Listener.AuthenticationListener;
import com.tlherr.Model.Product;
import com.tlherr.Repository.ProductRepository;
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

/**
 * Product panel contains table, add/edit/delete buttons
 */
public class ProductPanel extends AbstractPanel {
    private JTable productsTable;
    private JPanel productsOperationButtons;
    private JButton addProductButton;
    private JButton deleteProductButton;
    private ProductForm productForm;

    public ProductPanel() {
        setLayout(new BorderLayout());

        productsTable = new JTable();
        productsTable.setFillsViewportHeight(true);
        add(new JScrollPane(productsTable), BorderLayout.NORTH);

        productsOperationButtons = new JPanel(new FlowLayout());
        addProductButton = new JButton(Strings.ADD_PRODUCTS_BUTTON);
        deleteProductButton = new JButton(Strings.DELETE_PRODUCTS_BUTTON);

        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productForm = new ProductForm();
                productForm.getOkButton().addActionListener(new okButtonListener());
                productForm.getCancelButton().addActionListener(new cancelButtonListener());

                productForm.build();
                add(productForm, BorderLayout.SOUTH);
                repack();
            }
        });

        productsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    DefaultTableModel model = (DefaultTableModel) productsTable.getModel();

                    try {
                        Vector vectorResult = (Vector) model.getDataVector().elementAt(productsTable.getSelectedRow());
                        Product product = new Product(vectorResult);

                        addProductButton.setEnabled(false);
                        deleteProductButton.setEnabled(false);

                        productForm = new ProductForm(product);
                        productForm.build();
                        productForm.setOkButtonActionListener(new okButtonListener());
                        productForm.setCancelButtonActionListener(new cancelButtonListener());
                        add(productForm, BorderLayout.SOUTH);
                        repack();
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        //@TODO: Logging method should handle this as stated in requirements
                    }
                }
            }
        });



        productsOperationButtons.add(addProductButton);
        productsOperationButtons.add(deleteProductButton);

        add(productsOperationButtons, BorderLayout.CENTER);

        LoginService.getInstance().addListener(new AuthenticationListener() {
            @Override
            public void loggedIn(ActionEvent e) {
                updateProductTable();
            }

            @Override
            public void loggedOut(ActionEvent e) {

            }
        });
    }

    private void updateProductTable() {
        try {
            ResultSet rs = ProductRepository.getInstance().load(Product.class);

            if (rs != null) {
                GenericTableModel tableModel = new GenericTableModel(rs);
                productsTable.setModel(tableModel);
            }
        } catch (SQLException e) {
            //@TODO: Handle with loggin class as stated in requirements
            e.printStackTrace();
        }
    }

    private class okButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure?","Warning", JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION){
                if(productForm.validateForm()) {
                    productForm.submit().save();
                    updateProductTable();
                    productForm.clear();
                }
            }
        }
    }

    private class cancelButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure?","Warning", JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION){
                productForm.clear();
            }
        }
    }


}
