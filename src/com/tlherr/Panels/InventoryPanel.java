package com.tlherr.Panels;

import javax.swing.*;
import java.awt.*;

/**
 * Inventory tab that has the ability to collect all the information about a
 * Product (including manufacturer information – HINT: Information to create
 * two separate objects might be required when you create a Product object)
 */
public class InventoryPanel extends JPanel {

    private JPanel manufacturerPanel;
    private JPanel productPanel;

    public InventoryPanel() {
        setLayout(new BorderLayout());
        manufacturerPanel = new ManufacturerPanel();
        add(manufacturerPanel, BorderLayout.NORTH);

        productPanel = new ProductPanel();
        add(productPanel, BorderLayout.SOUTH);
    }
}
