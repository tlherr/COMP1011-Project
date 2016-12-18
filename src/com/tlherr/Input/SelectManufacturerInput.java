package com.tlherr.Input;

import com.tlherr.Model.Manufacturer;
import com.tlherr.Model.ManufacturerComboBoxModel;

import javax.swing.*;

public class SelectManufacturerInput extends AbstractFormInput {

    private JComboBox manufacturerComboBox;
    private ManufacturerComboBoxModel model;

    public SelectManufacturerInput(String inputLabelText) {
        super(inputLabelText);

        manufacturerComboBox = new JComboBox<>();
        model = new ManufacturerComboBoxModel();
        manufacturerComboBox.setModel(model);

        addInputField(manufacturerComboBox);
    }

    @Override
    public Manufacturer getValue() {
        return (Manufacturer) manufacturerComboBox.getSelectedItem();
    }

    public void setValue(Manufacturer manufacturer) {
        model.setSelectedItem(manufacturer);
    }
}
