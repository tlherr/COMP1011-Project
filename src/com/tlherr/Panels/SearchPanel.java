package com.tlherr.Panels;

import com.tlherr.Resources.Strings;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Search tab that allows for searching Employees and/or a Product
 * (separately) and have the potential to display the result in a JTextArea
 * component (no functionality is required for part 1 of the assignment).
 */
public class SearchPanel extends AbstractPanel {

    private JPanel typeSelectorPanel;
    private ButtonGroup typeSelectorButtonGroup;
    private JRadioButton EmployeeRadioButton;
    private JRadioButton ProductRadioButton;
    private JRadioButton ManufacturerRadioButton;
    private JPanel textEntryPanel;
    private JLabel textEntryLabel;
    private JTextField textEntryField;
    private JPanel resultsPanel;
    private JTextArea resultsTextArea;


    public SearchPanel() {
        setLayout(new BorderLayout());

        typeSelectorPanel = new JPanel(new FlowLayout());
        textEntryPanel = new JPanel(new FlowLayout());
        resultsPanel = new JPanel(new FlowLayout());

        typeSelectorButtonGroup = new ButtonGroup();
        EmployeeRadioButton = new JRadioButton(Strings.SEARCH_TYPE_EMPLOYEE);
        ProductRadioButton = new JRadioButton(Strings.SEARCH_TYPE_PRODUCT);
        ManufacturerRadioButton = new JRadioButton(Strings.SEARCH_TYPE_MANUFACTURER);

        typeSelectorButtonGroup.add(EmployeeRadioButton);
        typeSelectorButtonGroup.add(ProductRadioButton);
        typeSelectorButtonGroup.add(ManufacturerRadioButton);

        typeSelectorPanel.add(EmployeeRadioButton);
        typeSelectorPanel.add(ProductRadioButton);
        typeSelectorPanel.add(ManufacturerRadioButton);


        textEntryLabel = new JLabel(Strings.SEARCH_LABEL);
        textEntryField = new JTextField();
        textEntryField.setColumns(20);

        textEntryPanel.add(textEntryLabel);
        textEntryPanel.add(textEntryField);

        resultsPanel = new JPanel();
        resultsTextArea = new JTextArea(5, 20);
        resultsTextArea.setBorder(new LineBorder(new Color(0,0,0)));

        resultsPanel.add(resultsTextArea);

        add(typeSelectorPanel, BorderLayout.NORTH);
        add(textEntryPanel, BorderLayout.CENTER);
        add(resultsPanel, BorderLayout.SOUTH);
    }

}
