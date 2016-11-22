package com.tlherr.Frames;


import com.tlherr.Panels.HumanResourcesPanel;
import com.tlherr.Panels.InventoryPanel;
import com.tlherr.Panels.SearchPanel;
import com.tlherr.Resources.Strings;

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
        super(Strings.APPLICATION_TITLE);
        this.setLayout(new BorderLayout());

        buildTopPanel(Strings.WELCOME_MESSAGE);
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
        middlePanel = new JPanel(new BorderLayout());
        tabbedPane = new JTabbedPane();
        tabbedPane.add(Strings.HUMAN_RESOURCES_TAB, new HumanResourcesPanel());
        tabbedPane.add(Strings.INVENTORY_TAB, new InventoryPanel());
        tabbedPane.add(Strings.SEARCH_TAB, new SearchPanel());
        middlePanel.add(tabbedPane, BorderLayout.NORTH);

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
