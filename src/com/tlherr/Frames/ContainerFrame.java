package com.tlherr.Frames;


import com.tlherr.Panels.HumanResourcesPanel;
import com.tlherr.Panels.InventoryPanel;
import com.tlherr.Panels.LoginPanel;
import com.tlherr.Panels.SearchPanel;
import com.tlherr.Resources.Strings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * This is the main frame of the application. It contains all sub panels
 */
public class ContainerFrame extends JFrame {

    private JMenuBar menuBar;

    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel bottomPanel;

    private JLabel welcomeLabel;

    private JTabbedPane tabbedPane;


    private JButton exitButton;

    public ContainerFrame() {
        super(Strings.APPLICATION_TITLE);
        this.setLayout(new BorderLayout());

        //buildMenuBar();
        buildTopPanel(Strings.WELCOME_MESSAGE);
        buildMiddlePanel();
        buildBottomPanel();

        //add(menuBar, BorderLayout.PAGE_START);
        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        pack();
    }

    private void buildMenuBar() {
        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        fileMenu.add(new JMenuItem("Populate Test Data", 't'));

        menuBar.add(fileMenu);
    }

    /**
     * Builds top panel which contains a welcome greeting message as stated in requirements
     *
     * @param labelMessage String message to be displayed as a welcome
     */
    private void buildTopPanel(String labelMessage) {
        topPanel = new JPanel();
        welcomeLabel = new JLabel(labelMessage);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(welcomeLabel);
        topPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        topPanel.add(welcomeLabel);

        topPanel.add(new LoginPanel());
    }

    /**
     * Builds middle panel which contains tabs
     */
    private void buildMiddlePanel() {
        middlePanel = new JPanel(new BorderLayout());
        middlePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        tabbedPane = new JTabbedPane();
        tabbedPane.add(Strings.HUMAN_RESOURCES_TAB, new HumanResourcesPanel());
        tabbedPane.add(Strings.INVENTORY_TAB, new InventoryPanel());
        tabbedPane.add(Strings.SEARCH_TAB, new SearchPanel());
        middlePanel.add(tabbedPane, BorderLayout.NORTH);

    }

    /**
     * Adds button panel which defines an exit button as stated by requirements.
     * This button is part of a button group as stated by requirements
     */
    private void buildBottomPanel() {
        bottomPanel = new JPanel(new FlowLayout());
        ButtonGroup mainControlsGroup = new ButtonGroup();
        exitButton = new JButton("Exit");
        mainControlsGroup.add(exitButton);
        exitButton.setPreferredSize(new Dimension(60, 20));
        exitButton.addActionListener(new ExitButtonHandler());
        bottomPanel.add(exitButton);
    }

    /**
     * Action listener for exit button.
     * Ask for user confirmation before actually exiting to avoid accidental shutdown
     */
    private class ExitButtonHandler implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e ActionEvent event Object
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
