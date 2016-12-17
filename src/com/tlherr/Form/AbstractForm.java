package com.tlherr.Form;

import com.tlherr.Input.ValidatedFormInput;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class AbstractForm extends JPanel {

    protected JPanel contentPanel;
    protected JPanel controlsPanel;

    public AbstractForm() {
        super(new BorderLayout());

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        controlsPanel = new JPanel(new BorderLayout());

        add(contentPanel, BorderLayout.NORTH);
        add(controlsPanel, BorderLayout.SOUTH);
    }

    public abstract void build();
    public abstract Object submit();

    public void clear() {
        if(contentPanel!=null) {
            remove(contentPanel);
        }

        if(controlsPanel!=null) {
            remove(controlsPanel);
        }
        setVisible(false);
    };

    /**
     * Check that inputs match expectations before allowing submission to proceed
     * @ref InputService for validation methods
     * @return Boolean If form validated successfully
     */
    public Boolean validateForm() {
        Boolean isValid = true;
        //Iterate over all components and check validated fields to see if values are ok

        for(Component component: this.contentPanel.getComponents()) {
            if(component.getClass()==ValidatedFormInput.class) {
                if(!((ValidatedFormInput) component).validateInput()) {
                    isValid = false;
                }
            }
        }

        return isValid;
    }
}
