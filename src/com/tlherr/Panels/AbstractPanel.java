package com.tlherr.Panels;

import javax.swing.*;
import java.awt.*;

/**
 * Base Panel Class
 */
public abstract class AbstractPanel extends Panel {

    public AbstractPanel() {
        super();
    }

    public AbstractPanel(LayoutManager layoutManager) {
        super(layoutManager);
    }

    public void enableComponents(Container container, boolean enable) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(enable);
            if (component instanceof Container) {
                enableComponents((Container)component, enable);
            }
        }
    }

    /**
     * @ref https://docs.oracle.com/javase/8/docs/api/java/awt/Window.html#pack--
     * "Packing"  Causes this Window to be sized to fit the preferred size and layouts of its subcomponents
     * This can be called when components are added or removed to ensure window is sized correctly
     * Warning: This may move elements around which is annoying for end users.
     */
    public void repack() {
        SwingUtilities.getWindowAncestor(this).pack();
    }
}
