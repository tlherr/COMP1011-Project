package com.tlherr.Form;

import com.tlherr.Input.ValidatedTextFormInput;
import com.tlherr.Model.Customer;
import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.Product;
import com.tlherr.Model.Sale;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.InputService;

import javax.swing.*;

public class SaleForm extends AbstractForm {

    private Sale sale;

    private JComboBox<Product> product;
    private JComboBox<AbstractEmployee> employee;
    private ValidatedTextFormInput salePrice;

    public SaleForm() {
        super();
    }

    public SaleForm(Sale sale) {
        this.sale = sale;
    }

    @Override
    public void build() {
        salePrice = new ValidatedTextFormInput(Strings.CUSTOMER_FORM_LABEL_FIRSTNAME, InputService.DECIMAL);


        if(this.sale!=null) {
            //Set Values
        }

        //addValidatedInput(firstName);

    }

    @Override
    public Sale submit() {
        if (this.sale == null) {
            this.sale = new Sale();
        }

        //this.sale.set(firstName.getValue());
        return this.sale;
    }
}
