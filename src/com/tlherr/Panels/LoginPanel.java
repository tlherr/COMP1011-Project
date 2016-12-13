package com.tlherr.Panels;


import com.tlherr.Resources.Strings;
import com.tlherr.Service.LoginService;
import com.tlherr.Users.BaseUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.PasswordAuthentication;
import java.sql.SQLException;

public class LoginPanel extends JPanel {
    private JPanel loginPanel;
    private JPanel loggedInPanel;

    //Login panel elements
    private JLabel usernameLabel;
    private JTextField usernameTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;

    //Logout panel elements
    private JLabel nameLabel;
    private JButton logoutButton;

    public LoginPanel() {
        setLayout(new FlowLayout());

        loginPanel = new JPanel(new FlowLayout());
        loggedInPanel = new JPanel(new FlowLayout());

        //Login Panel Elements
        usernameLabel = new JLabel(Strings.LOGIN_FORM_LABEL_USERNAME);
        usernameTextField = new JTextField(15);
        passwordLabel = new JLabel(Strings.LOGIN_FORM_LABEL_PASSWORD);
        passwordField = new JPasswordField(15);
        loginButton = new JButton(Strings.LOGIN_FORM_BUTTON_LOGIN);

        loginButton.addActionListener(new LoginButtonListener());

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameTextField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        //Logout Panel elements
        nameLabel = new JLabel();
        logoutButton = new JButton(Strings.LOGIN_FORM_BUTTON_LOGOUT);

        loggedInPanel.add(nameLabel);
        loggedInPanel.add(logoutButton);
        loggedInPanel.setVisible(false);

        logoutButton.addActionListener(new LogoutButtonListener());

        LoginService.getInstance().addListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("LoginPanel catching Authentication Event");

                //Check what event was given
                if(e.getID()==LoginService.EVENT_LOGGED_IN) {
                    System.out.println("LoginPanel processing Login Event");

                    //User has logged in. Modify the UI to reflect that
                    nameLabel.setText(((BaseUser) e.getSource()).getName());
                    loginPanel.setVisible(false);
                    loggedInPanel.setVisible(true);
                } else if(e.getID()==LoginService.EVENT_LOGGED_OUT) {
                    System.out.println("LoginPanel processing Logout Event");

                    //User has logged out. Modify the UI to reflect that
                    nameLabel.setText("");
                    loginPanel.setVisible(true);
                    loggedInPanel.setVisible(false);
                }
            }
        });

        add(loginPanel);
        add(loggedInPanel);
    }

    public class LogoutButtonListener implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            //Process a logout
            LoginService.getInstance().processLogout();
        }
    }


    public class LoginButtonListener implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e ActionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                LoginService.getInstance().processLogin(new PasswordAuthentication(usernameTextField.getText(), passwordField.getPassword()));
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

}
