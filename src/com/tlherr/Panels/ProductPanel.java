package com.tlherr.Panels;

import com.tlherr.Form.ProductForm;
import com.tlherr.Listener.AuthenticationListener;
import com.tlherr.Model.Product;
import com.tlherr.Repository.ProductRepository;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.LoginService;
import com.tlherr.Table.GenericTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                productForm.getOkButton().addActionListener(new ActionListener() {
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
                });
                productForm.getCancelButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure?","Warning", JOptionPane.YES_NO_OPTION);
                        if(dialogResult == JOptionPane.YES_OPTION){
                            productForm.clear();
                        }
                    }
                });

                productForm.build();
                add(productForm, BorderLayout.SOUTH);
                repack();
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


}
