package com.tlherr.Panels;

import com.tlherr.Form.ManufacturerForm;
import com.tlherr.Listener.AuthenticationListener;
import com.tlherr.Model.Manufacturer;
import com.tlherr.Repository.ManufacturerRepository;
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
        manufacturersTable.setFillsViewportHeight(true);
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
                        if(manufacturerForm.validateForm()) {
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
    }

    public void updateManufacturerTable() {
        //Get manufacturers from DB and output to new table model
        try {
            ResultSet rs = ManufacturerRepository.getInstance().load(Manufacturer.class);

            if(rs!=null) {
                GenericTableModel tableModel = new GenericTableModel(rs);
                manufacturersTable.setModel(tableModel);
            }
        } catch (SQLException e) {
            //@TODO: Handle with loggin class as stated in requirements
            e.printStackTrace();
        }
    }


}
