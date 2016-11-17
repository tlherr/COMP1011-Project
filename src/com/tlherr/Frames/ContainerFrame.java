package com.tlherr.Frames;


import com.tlherr.Panels.HumanResourcesPanel;
import com.tlherr.Panels.InventoryPanel;
import com.tlherr.Panels.SearchPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContainerFrame extends JFrame {

    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel bottomPanel;

    private JLabel welcomeLabel;

    private JTabbedPane tabbedPane;

    private JButton exitButton;

    public ContainerFrame() {
        super("COMP1011 Employee System");
        this.setLayout(new BorderLayout());

        buildTopPanel("Welcome");
        buildMiddlePanel();
        buildBottomPanel();

        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        pack();
    }

    private void buildTopPanel(String labelMessage) {
        topPanel = new JPanel();
        welcomeLabel = new JLabel(labelMessage);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(welcomeLabel);
        topPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        topPanel.add(welcomeLabel);
    }

    private void buildMiddlePanel() {
        middlePanel = new JPanel(new GridLayout());
        tabbedPane = new JTabbedPane();
        tabbedPane.add("Human Resources", new HumanResourcesPanel());
        tabbedPane.add("Inventory", new InventoryPanel());
        tabbedPane.add("Search", new SearchPanel());
        middlePanel.add(tabbedPane);
    }

    private void buildBottomPanel() {
        bottomPanel = new JPanel(new BorderLayout());
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ExitButtonHandler());
        bottomPanel.add(exitButton, BorderLayout.CENTER);
    }

    private class ExitButtonHandler implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            int doExit = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?","Confirm Action", JOptionPane.YES_NO_OPTION);

            if(doExit == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

}
