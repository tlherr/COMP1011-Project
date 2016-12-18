package com.tlherr.Form;

import com.tlherr.Input.ValidatedTextFormInput;
import com.tlherr.Model.Manufacturer;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.InputService;

public class ManufacturerForm extends AbstractForm {

    private Manufacturer manufacturer;
    private ValidatedTextFormInput name;

    public ManufacturerForm() {
        super();
    }

    public ManufacturerForm(Manufacturer manufacturer) {
        this();
        this.manufacturer = manufacturer;
    }

    @Override
    public void build() {
        name = new ValidatedTextFormInput(Strings.MANUFACTURER_FORM_NAME, InputService.ALPHANUMERIC_WORDS);
        if(this.manufacturer!=null) {
            name.setValue(this.manufacturer.getName());
        }

        addInput(name);
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
