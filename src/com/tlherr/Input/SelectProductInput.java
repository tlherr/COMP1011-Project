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
        productComboBox.setLightWeightPopupEnabled(false);
        addInputField(productComboBox);
    }

    @Override
    public Product getValue() {
        return (Product) productComboBox.getSelectedItem();
    }

    public void setValue(Product product) {
        model.setSelectedItem(product);
    }
}
