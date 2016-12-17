package com.tlherr.Panels;

import com.tlherr.Resources.Strings;

import javax.swing.*;
import java.awt.*;

/**
 * Inventory tab that has the ability to collect all the information about a
 * Product (including manufacturer information – HINT: Information to create
 * two separate objects might be required when you create a Product object)
 */
public class InventoryPanel extends BasePanel {

    private JTabbedPane inventoryTabbedPane;
    private ManufacturerPanel manufacturerPanel;
    private ProductPanel productPanel;

    public InventoryPanel() {
        setLayout(new BorderLayout());
        inventoryTabbedPane = new JTabbedPane();

        manufacturerPanel = new ManufacturerPanel();
        productPanel = new ProductPanel();

        inventoryTabbedPane.add(Strings.MANUFACTURERS_TAB_TITLE, manufacturerPanel);
        inventoryTabbedPane.add(Strings.PRODUCTS_TAB_TITLE, productPanel);

        add(inventoryTabbedPane, BorderLayout.NORTH);
    }
}
