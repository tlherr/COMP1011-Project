package com.tlherr.Panels;


import com.tlherr.Resources.Strings;
import com.tlherr.Service.LoginService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.PasswordAuthentication;
import java.sql.SQLException;

public class LoginPanel extends JPanel {
    private JPanel loginPanel;
    private JPanel loggedInPanel;

    private JLabel usernameLabel;
    private JTextField usernameTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;

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

        add(loginPanel);

        LoginService.getInstance().addListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Check what event was given
                if(e.getID()==LoginService.EVENT_LOGGED_IN) {
                    //User has logged in. Modify the UI to reflect that
                    remove(loginPanel);
                    add(loggedInPanel);
                } else if(e.getID()==LoginService.EVENT_LOGGED_OUT) {
                    //User has logged out. Modify the UI to reflect that
                }
            }
        });


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
