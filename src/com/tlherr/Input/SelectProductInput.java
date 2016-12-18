package com.tlherr.Input;

import com.tlherr.Model.ComboBox.ProductComboBoxModel;
import com.tlherr.Model.Manufacturer;
import com.tlherr.Model.Product;

import javax.swing.*;

public class SelectProductInput extends AbstractFormInput {

    private JComboBox productComboBox;
    private ProductComboBoxModel model;

    public SelectProductInput(String inputLabelText) {
        super(inputLabelText);

        productComboBox = new JComboBox<>();
        model = new ProductComboBoxModel();
        productComboBox.setModel(model);

        addInputField(productComboBox);
    }

    @Override
    public Manufacturer getValue() {
        return (Manufacturer) productComboBox.getSelectedItem();
    }

    public void setValue(Product product) {
        model.setSelectedItem(product);
    }
}
