package com.tlherr.Panels;

import java.awt.*;

/**
 * Base Panel Class
 */
public class BasePanel extends Panel {

    public void enableComponents(Container container, boolean enable) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(enable);
            if (component instanceof Container) {
                enableComponents((Container)component, enable);
            }
        }
    }
}
