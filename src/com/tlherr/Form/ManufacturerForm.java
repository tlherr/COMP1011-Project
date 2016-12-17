package com.tlherr.Form;

import com.tlherr.Input.ValidatedFormInput;
import com.tlherr.Model.Manufacturer;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.InputService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManufacturerForm extends AbstractForm {

    private Manufacturer manufacturer;
    private ValidatedFormInput name;

    public ManufacturerForm() {
        super();
    }

    public ManufacturerForm(Manufacturer manufacturer) {
        this();
        this.manufacturer = manufacturer;
    }

    @Override
    public void build() {
        name = new ValidatedFormInput(Strings.MANUFACTURER_FORM_NAME, InputService.ALPHANUMERIC_WORDS);
        if(this.manufacturer!=null) {
            name.setEditText(this.manufacturer.getName());
        }

        addValidatedInput(name);
    }

    @Override
    public Manufacturer submit() {
        if (manufacturer == null) {
            this.manufacturer = new Manufacturer();
        }

        this.manufacturer.setName(name.getValue());
        return this.manufacturer;
    }
}
