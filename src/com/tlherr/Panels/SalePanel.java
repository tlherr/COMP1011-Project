package com.tlherr.Panels;

import com.tlherr.Form.ProductForm;
import com.tlherr.Form.SaleForm;
import com.tlherr.Listener.AuthenticationListener;
import com.tlherr.Model.GenericEmployee;
import com.tlherr.Model.Manufacturer;
import com.tlherr.Model.Product;
import com.tlherr.Model.Sale;
import com.tlherr.Repository.SaleRepository;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.LoginService;
import com.tlherr.Table.GenericTableModel;
import com.tlherr.Users.AdminUser;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class SalePanel extends AbstractPanel {

    private JTable saleTable;
    private JPanel saleOperationButtons;
    private JButton addSaleButton;
    private JButton deleteSaleButton;
    private SaleForm saleForm;

    public SalePanel() {
        setLayout(new BorderLayout());

        saleTable = new JTable();
        add(new JScrollPane(saleTable), BorderLayout.NORTH);

        saleTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(LoginService.getInstance().getActiveUser().getPermissions().implies(AdminUser.employeePermission)) {

                    if (e.getValueIsAdjusting()) {
                        DefaultTableModel model = (DefaultTableModel) saleTable.getModel();

                        try {
                            Vector vectorResult = (Vector) model.getDataVector().elementAt(saleTable.getSelectedRow());


                            //Make an Generic Employee
                            GenericEmployee employee = new GenericEmployee((int)vectorResult.get(1),(String)vectorResult.get(2), ((Long)vectorResult.get(0)).intValue());
                            //Make a manufacturer
                            Manufacturer manufacturer = new Manufacturer((int)vectorResult.get(7),(String)vectorResult.get(8));
                            //Make a product
                            Product product = new Product((int)vectorResult.get(4), (String)vectorResult.get(5), manufacturer, (String)vectorResult.get(6));

                            Sale sale = new Sale(product, employee, (BigDecimal) vectorResult.get(3));

                            addSaleButton.setEnabled(false);
                            deleteSaleButton.setEnabled(false);

                            saleForm = new SaleForm(sale);
                            saleForm.build();
                            saleForm.setOkButtonActionListener(new okButtonListener());
                            saleForm.setCancelButtonActionListener(new cancelButtonListener());
                            add(saleForm, BorderLayout.SOUTH);
                            repack();
                        } catch (ArrayIndexOutOfBoundsException exception) {
                            //@TODO: Logging method should handle this as stated in requirements
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, Strings.INSUFFICIENT_PERMISSIONS);
                }
            }
        });

        saleOperationButtons = new JPanel(new FlowLayout());
        addSaleButton = new JButton(Strings.SALE_PANEL_ADD_SALE);

        addSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saleForm = new SaleForm();
                saleForm.build();
                saleForm.getOkButton().addActionListener(new okButtonListener());
                saleForm.getCancelButton().addActionListener(new cancelButtonListener());

                add(saleForm, BorderLayout.SOUTH);
                repack();
            }
        });

        LoginService.getInstance().addListener(new AuthenticationListener() {
            @Override
            public void loggedIn(ActionEvent e) {
                updateSalesTable();
            }

            @Override
            public void loggedOut(ActionEvent e) {

            }
        });

        deleteSaleButton = new JButton(Strings.SALE_PANEL_DELETE_SALE);

        saleOperationButtons.add(addSaleButton);
        saleOperationButtons.add(deleteSaleButton);

        add(saleOperationButtons, BorderLayout.CENTER);
    }

    private class okButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure?","Warning", JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION){
                if(saleForm.validateForm()) {
                    saleForm.submit().save();
                    updateSalesTable();
                    saleForm.clear();
                }
            }
        }
    }

    private class cancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure?","Warning", JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION){
                saleForm.clear();
            }
        }
    }

    private void updateSalesTable() {
        try {
            ResultSet rs = SaleRepository.getInstance().load(Sale.class);

            if (rs != null) {
                GenericTableModel tableModel = new GenericTableModel(rs);
                saleTable.setModel(tableModel);
            }
        } catch (SQLException e) {
            //@TODO: Handle with loggin class as stated in requirements
            e.printStackTrace();
        }
    }

}
