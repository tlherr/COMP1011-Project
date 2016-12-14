package com.tlherr.Panels;


import com.tlherr.Listener.AuthenticationListener;
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

        LoginService.getInstance().addListener(new AuthenticationListener() {
            @Override
            public void loggedIn(ActionEvent e) {
                //User has logged in. Modify the UI to reflect that
                nameLabel.setText(((BaseUser) e.getSource()).getName());
                loginPanel.setVisible(false);
                loggedInPanel.setVisible(true);
            }

            @Override
            public void loggedOut(ActionEvent e) {
                //User has logged out. Modify the UI to reflect that
                nameLabel.setText("");
                loginPanel.setVisible(true);
                loggedInPanel.setVisible(false);
            }
        });


        add(loginPanel);
        add(loggedInPanel);
    }

    private class LogoutButtonListener implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e ActionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            //Process a logout
            LoginService.getInstance().processLogout();
        }
    }


    private class LoginButtonListener implements ActionListener {

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
