package com.tlherr.Listener;

import java.awt.event.ActionEvent;
import java.util.EventListener;

public interface AuthenticationListener extends EventListener {

    /**
     * Invoked when a user logs in
     *
     * @param e ActionEvent event
     */
    public void loggedIn(ActionEvent e);

    /**
     * Invoked when a user logs out
     *
     * @param e ActionEvent event
     */
    public void loggedOut(ActionEvent e);

}
