package com.tlherr.Panels;

import com.tlherr.Form.ManufacturerForm;
import com.tlherr.Resources.Strings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Manufacturer panel, contains table and add/edit/remove buttons
 */
public class ManufacturerPanel extends BasePanel {
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

        addManufacturerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manufacturerForm = new ManufacturerForm();
                manufacturerForm.build();
                manufacturerForm.setVisible(true);
                add(manufacturerForm, BorderLayout.SOUTH);
                repack();
            }
        });
    }


}
