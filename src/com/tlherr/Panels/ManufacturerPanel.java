package com.tlherr.Panels;

import com.tlherr.Resources.Strings;

import javax.swing.*;
import java.awt.*;


public class ManufacturerPanel extends JPanel {
    private JLabel headerLabel;
    private JTable manufacturersTable;
    private JPanel manufacturersOperationButtons;
    private JButton addManufacturerButton;
    private JButton deleteManufacturerButton;
    private JButton editManufacturerButton;

    public ManufacturerPanel() {
        setLayout(new BorderLayout());

        headerLabel = new JLabel(Strings.MANUFACTURERS_TITLE);
        add(headerLabel, BorderLayout.NORTH);

        manufacturersTable = new JTable();
        manufacturersTable.setFillsViewportHeight(true);
        add(manufacturersTable, BorderLayout.CENTER);

        manufacturersOperationButtons = new JPanel(new FlowLayout());
        addManufacturerButton = new JButton(Strings.ADD_MANUFACTURERS_BUTTON);
        deleteManufacturerButton = new JButton(Strings.DELETE_MANUFACTURERS_BUTTON);
        editManufacturerButton = new JButton(Strings.EDIT_MANUFACTURERS_BUTTON);

        manufacturersOperationButtons.add(addManufacturerButton);
        manufacturersOperationButtons.add(deleteManufacturerButton);
        manufacturersOperationButtons.add(editManufacturerButton);

        add(manufacturersOperationButtons, BorderLayout.SOUTH);
    }


}
