package com.tlherr.Panels;

import com.tlherr.Form.ManufacturerForm;
import com.tlherr.Listener.AuthenticationListener;
import com.tlherr.Model.Manufacturer;
import com.tlherr.Repository.ManufacturerRepository;
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
 * Manufacturer panel, contains table and add/edit/remove buttons
 */
public class ManufacturerPanel extends AbstractPanel {
    private JTable manufacturersTable;
    private JPanel manufacturersOperationButtons;
    private JButton addManufacturerButton;
    private JButton deleteManufacturerButton;
    private JButton editManufacturerButton;

    private ManufacturerForm manufacturerForm;


    public ManufacturerPanel() {
        setLayout(new BorderLayout());

        manufacturersTable = new JTable();
        add(new JScrollPane(manufacturersTable), BorderLayout.NORTH);

        manufacturersOperationButtons = new JPanel(new FlowLayout());
        addManufacturerButton = new JButton(Strings.ADD_MANUFACTURERS_BUTTON);
        deleteManufacturerButton = new JButton(Strings.DELETE_MANUFACTURERS_BUTTON);
        editManufacturerButton = new JButton(Strings.EDIT_MANUFACTURERS_BUTTON);

        manufacturersOperationButtons.add(addManufacturerButton);
        manufacturersOperationButtons.add(deleteManufacturerButton);
        manufacturersOperationButtons.add(editManufacturerButton);

        add(manufacturersOperationButtons, BorderLayout.CENTER);

        LoginService.getInstance().addListener(new AuthenticationListener() {
            @Override
            public void loggedIn(ActionEvent e) {
                updateManufacturerTable();
            }

            @Override
            public void loggedOut(ActionEvent e) {

            }
        });

        addManufacturerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manufacturerForm = new ManufacturerForm();
                manufacturerForm.build();
                manufacturerForm.getOkButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (manufacturerForm.validateForm()) {
                            manufacturerForm.submit().save();
                            manufacturerForm.clear();
                        }
                        updateManufacturerTable();
                    }
                });
                manufacturerForm.setVisible(true);
                add(manufacturerForm, BorderLayout.SOUTH);
                repack();
            }
        });

        manufacturersTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    DefaultTableModel model = (DefaultTableModel) manufacturersTable.getModel();

                    try {
                        Vector vectorResult = (Vector) model.getDataVector().elementAt(manufacturersTable.getSelectedRow());
                        Manufacturer manufacturer = new Manufacturer(vectorResult);

                        addManufacturerButton.setEnabled(false);
                        deleteManufacturerButton.setEnabled(false);

                        manufacturerForm = new ManufacturerForm(manufacturer);
                        manufacturerForm.build();
                        manufacturerForm.setOkButtonActionListener(new okButtonListener());
                        manufacturerForm.setCancelButtonActionListener(new cancelButtonListener());
                        add(manufacturerForm, BorderLayout.SOUTH);
                        repack();
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        //@TODO: Logging method should handle this as stated in requirements
                    }
                }
            }
        });
    }

    private void updateManufacturerTable() {
        //Get manufacturers from DB and output to new table model
        try {
            ResultSet rs = ManufacturerRepository.getInstance().load(Manufacturer.class);

            if (rs != null) {
                GenericTableModel tableModel = new GenericTableModel(rs);
                manufacturersTable.setModel(tableModel);
            }
        } catch (SQLException e) {
            //@TODO: Handle with loggin class as stated in requirements
            e.printStackTrace();
        }
    }

    private class okButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {

                if (manufacturerForm.validateForm()) {
                    manufacturerForm.submit().save();
                    updateManufacturerTable();
                    manufacturerForm.clear();
                } else {
                    repack();
                }
            }
        }
    }

    private class cancelButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                manufacturerForm.clear();
            }
        }
    }


}
