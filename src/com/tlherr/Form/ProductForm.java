package com.tlherr.Form;

import com.tlherr.Input.SelectManufacturerInput;
import com.tlherr.Input.ValidatedTextFormInput;
import com.tlherr.Model.Product;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.InputService;

public class ProductForm extends AbstractForm {

    private Product product;

    private ValidatedTextFormInput name;
    private ValidatedTextFormInput modelNumber;
    private SelectManufacturerInput manufacturerSelect;

    public ProductForm(){};

    public ProductForm(Product product) {
        this.product = product;
    }


    @Override
    public void build() {
        name = new ValidatedTextFormInput(Strings.PRODUCT_FORM_LABEL_NAME, InputService.CHARACTERS_ONLY);
        modelNumber = new ValidatedTextFormInput(Strings.PRODUCT_FORM_LABEL_MODEL_NUMBER, InputService.ALPHANUMERIC_WORDS);
        manufacturerSelect = new SelectManufacturerInput(Strings.PRODUCT_FORM_LABEL_MANUFACTURER);

        if(this.product!=null) {
            name.setValue(this.product.getName());
            modelNumber.setValue(this.product.getModelNumber());
            manufacturerSelect.setValue(this.product.getManufacturer());
        }

        addInput(name);
        addInput(modelNumber);
        addInput(manufacturerSelect);
    }

    @Override
    public Product submit() {
        if (this.product == null) {
            this.product = new Product();
        }

        this.product.setName(name.getValue());
        this.product.setModelNumber(modelNumber.getValue());
        this.product.setManufacturer(manufacturerSelect.getValue());
        return this.product;

    }
}
