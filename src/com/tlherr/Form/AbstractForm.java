package com.tlherr.Form;

import com.tlherr.Input.ValidatedFormInput;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractForm extends JPanel {

    protected JPanel contentPanel;
    protected JPanel controlsPanel;

    public abstract void build();
    public abstract Object submit();

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
    };
}
