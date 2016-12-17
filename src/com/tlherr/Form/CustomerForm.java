package com.tlherr.Form;

import com.tlherr.Input.ValidatedFormInput;
import com.tlherr.Model.Customer;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.InputService;

public class CustomerForm extends AbstractForm {

    private Customer customer;

    private ValidatedFormInput firstName;
    private ValidatedFormInput lastName;
    private ValidatedFormInput emailAddress;
    private ValidatedFormInput company;

    public CustomerForm() {
        super();
    }

    public CustomerForm(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void build() {
        firstName = new ValidatedFormInput(Strings.CUSTOMER_FORM_LABEL_FIRSTNAME, InputService.CHARACTERS_ONLY);
        lastName = new ValidatedFormInput(Strings.CUSTOMER_FORM_LABEL_LASTNAME, InputService.CHARACTERS_ONLY);
        emailAddress = new ValidatedFormInput(Strings.CUSTOMER_FORM_LABEL_EMAIL, InputService.EMAIL_BASIC);
        company = new ValidatedFormInput(Strings.CUSTOMER_FORM_LABEL_COMPANY, InputService.ALPHANUMERIC_WORDS);

        addValidatedInput(firstName);
        addValidatedInput(lastName);
        addValidatedInput(emailAddress);
        addValidatedInput(company);
    }

    @Override
    public Object submit() {
        if(this.customer==null) {
            this.customer = new Customer(
                    firstName.getValue(),
                    lastName.getValue(),
                    emailAddress.getValue(),
                    company.getValue()
            );

        }

        return this.customer;
    }
}
