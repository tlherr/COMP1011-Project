package com.tlherr.Service;

import com.tlherr.Users.AdminUser;
import com.tlherr.Users.BaseUser;
import com.tlherr.Users.RegularUser;

import javax.swing.event.EventListenerList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.PasswordAuthentication;
import java.sql.*;

/**
 * Service to facilitate user authentication
 * Emits events when user is logged in/out so UI can respond appropriately
 */
public class LoginService {
    public static int EVENT_LOGGED_IN = 1;
    public static int EVENT_LOGGED_OUT = 2;
    public static String COMMAND_LOGGED_IN = "LoggedIn";
    public static String COMMAND_LOGGED_OUT = "LoggedOut";

    private BaseUser activeUser;
    private static LoginService instance = new LoginService();
    EventListenerList listenerList = new EventListenerList();

    private LoginService(){};

    public static LoginService getInstance() {
        return instance;
    }

    public void removeListener(ActionListener listener) {
        this.listenerList.remove(ActionListener.class, listener);
    }

    public void addListener(ActionListener listener) {
        this.listenerList.add(ActionListener.class, listener);
    }

    private void loginSuccessful() {
        Object[] listeners = listenerList.getListenerList();
        //Notify Listeners that a user has successfully logged in
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==ActionListener.class) {
                ((ActionListener)listeners[i]).actionPerformed(new ActionEvent(this.activeUser, LoginService.EVENT_LOGGED_IN, LoginService.COMMAND_LOGGED_IN));
            }
        }
    }

    private void logoutSuccessful() {
        Object[] listeners = listenerList.getListenerList();
        //Notify Listeners that a user has successfully logged in
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==ActionListener.class) {
                ((ActionListener)listeners[i]).actionPerformed(new ActionEvent(this.activeUser, LoginService.EVENT_LOGGED_OUT, LoginService.COMMAND_LOGGED_OUT));
            }
        }

        this.activeUser = null;
    }

    public void processLogin(PasswordAuthentication credentials) throws SQLException {

        Connection conn = ConnectionService.getConnection();

       if(conn!=null) {
           PreparedStatement statement = conn.prepareStatement("SELECT * FROM USER usr WHERE usr.username = ?, usr.password = ?");
           statement.setString(1, credentials.getUserName());
           statement.setString(2, String.valueOf(credentials.getPassword()));
           ResultSet rs =  statement.executeQuery();

           if(rs.next()) {
               switch(rs.getInt("type")) {
                   case RegularUser.USER_TYPE_BASIC:
                       activeUser = new RegularUser(rs.getString("name"));
                       loginSuccessful();
                       break;

                   case AdminUser.USER_TYPE_ADMIN:
                       activeUser = new AdminUser(rs.getString("name"));
                       loginSuccessful();
                       break;
               }

           } else {
               //Login failed
           }
       }

    }


}
