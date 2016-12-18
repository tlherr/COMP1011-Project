package com.tlherr.Form;

import com.tlherr.Input.ValidatedTextFormInput;
import com.tlherr.Resources.Strings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

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

    public void addInput(JComponent inputElement) {
        this.contentPanel.add(inputElement);
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
            if (component.getClass() == ValidatedTextFormInput.class) {
                if (!((ValidatedTextFormInput) component).validateInput()) {
                    isValid = false;
                }
            }
        }

        return isValid;
    }

    /**
     * Set a listener for the ok button
     *
     * @param listener ActionListener Listener to handle event
     */
    public void setOkButtonActionListener(ActionListener listener) {
        if (this.okButton != null) {
            this.okButton.addActionListener(listener);
        }
    }

    /**
     * Set a listener for the cancel button
     *
     * @param listener ActionListener Listener to handle event
     */
    public void setCancelButtonActionListener(ActionListener listener) {
        if (this.cancelButton != null) {
            this.cancelButton.addActionListener(listener);
        }
    }
}
