package com.tlherr.Form;

import com.tlherr.Input.SelectEmployeeInput;
import com.tlherr.Input.SelectProductInput;
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

    private SelectProductInput product;
    private ValidatedTextFormInput salePrice;
    private SelectEmployeeInput employee;

    public SaleForm() {
        super();
    }

    public SaleForm(Sale sale) {
        this.sale = sale;
    }

    @Override
    public void build() {
        salePrice = new ValidatedTextFormInput(Strings.SALE_FORM_LABEL_SALE_PRICE, InputService.DECIMAL);
        product = new SelectProductInput(Strings.SALE_FORM_LABEL_PRODUCT);
        employee = new SelectEmployeeInput(Strings.SALE_FORM_LABEL_EMPLOYEE);

        if(this.sale!=null) {
            //Set Values
            salePrice.setValue(sale.getSalePrice().toString());
            product.setValue(sale.getProduct());
            employee.setValue(sale.getEmployee());
        }

        addInput(salePrice);
        addInput(product);
        addInput(employee);

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
