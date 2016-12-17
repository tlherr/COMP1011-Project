package com.tlherr.Form;

import com.tlherr.Input.ValidatedFormInput;
import com.tlherr.Model.Manufacturer;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.InputService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManufacturerForm extends AbstractForm {

    private Manufacturer manufacturer;
    private JButton okButton;
    private JButton cancelButton;
    private ValidatedFormInput name;

    public ManufacturerForm() {
        super();

        setLayout(new BorderLayout());

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        controlsPanel = new JPanel(new BorderLayout());
    }

    public ManufacturerForm(Manufacturer manufacturer) {
        this();
        this.manufacturer = manufacturer;
    }

    @Override
    public void build() {
        name = new ValidatedFormInput(Strings.MANUFACTURER_FORM_NAME, InputService.ALPHANUMERIC_WORDS);
        contentPanel.add(name);

        okButton = new JButton(Strings.EMPLOYEE_CONTROL_BUTTON_OK);
        cancelButton = new JButton(Strings.EMPLOYEE_CONTROL_BUTTON_CANCEL);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validateForm()) {
                    submit().save();
                }
            }
        });

        controlsPanel.add(okButton, BorderLayout.EAST);
        controlsPanel.add(cancelButton, BorderLayout.WEST);

        add(contentPanel, BorderLayout.NORTH);
        add(controlsPanel, BorderLayout.SOUTH);
    }

    @Override
    public Manufacturer submit() {
        if(manufacturer!=null) {
            return manufacturer;
        } else {
            return new Manufacturer(name.getValue());
        }
    }
}
