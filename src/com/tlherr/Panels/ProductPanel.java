package com.tlherr.Panels;

import com.tlherr.Resources.Strings;

import javax.swing.*;
import java.awt.*;

/**
 * Product panel contains table, add/edit/delete buttons
 */
public class ProductPanel extends AbstractPanel {
    private JTable productsTable;
    private JPanel productsOperationButtons;
    private JButton addManufacturerButton;
    private JButton deleteManufacturerButton;
    private JButton editManufacturerButton;
    private JPanel formPanel;

    public ProductPanel() {
        setLayout(new BorderLayout());

        productsTable = new JTable();
        productsTable.setFillsViewportHeight(true);
        add(new JScrollPane(productsTable), BorderLayout.NORTH);

        productsOperationButtons = new JPanel(new FlowLayout());
        addManufacturerButton = new JButton(Strings.ADD_PRODUCTS_BUTTON);
        deleteManufacturerButton = new JButton(Strings.DELETE_PRODUCTS_BUTTON);
        editManufacturerButton = new JButton(Strings.EDIT_PRODUCTS_BUTTON);

        productsOperationButtons.add(addManufacturerButton);
        productsOperationButtons.add(deleteManufacturerButton);
        productsOperationButtons.add(editManufacturerButton);

        add(productsOperationButtons, BorderLayout.CENTER);

        formPanel = new JPanel();
        add(formPanel, BorderLayout.SOUTH);
    }


}
