package com.tlherr.Form;

import com.tlherr.Input.ValidatedFormInput;
import com.tlherr.Resources.Strings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class AbstractForm extends JPanel {

    protected JPanel contentPanel;
    protected JPanel controlsPanel;

    protected JButton okButton;
    protected JButton cancelButton;

    public AbstractForm() {
        super(new BorderLayout());

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        controlsPanel = new JPanel(new BorderLayout());

        okButton = new JButton(Strings.FORM_CONTROL_BUTTON_OK);
        cancelButton = new JButton(Strings.FORM_CONTROL_BUTTON_CANCEL);

        this.controlsPanel.add(okButton, BorderLayout.EAST);
        this.controlsPanel.add(cancelButton, BorderLayout.WEST);

        add(contentPanel, BorderLayout.NORTH);
        add(controlsPanel, BorderLayout.SOUTH);
    }

    public void addValidatedInput(ValidatedFormInput validatedFormInput) {
        this.contentPanel.add(validatedFormInput);
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public abstract void build();

    public abstract Object submit();

    public void clear() {
        if (contentPanel != null) {
            remove(contentPanel);
        }

        if (controlsPanel != null) {
            remove(controlsPanel);
        }
        setVisible(false);
    }

    /**
     * Check that inputs match expectations before allowing submission to proceed
     *
     * @return Boolean If form validated successfully
     * @ref InputService for validation methods
     */
    public Boolean validateForm() {
        Boolean isValid = true;
        //Iterate over all components and check validated fields to see if values are ok

        for (Component component : this.contentPanel.getComponents()) {
            if (component.getClass() == ValidatedFormInput.class) {
                if (!((ValidatedFormInput) component).validateInput()) {
                    isValid = false;
                }
            }
        }

        return isValid;
    }
}
