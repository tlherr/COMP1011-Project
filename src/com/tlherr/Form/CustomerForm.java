package com.tlherr.Form;

import com.tlherr.Input.ValidatedTextFormInput;
import com.tlherr.Model.Customer;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.InputService;

public class CustomerForm extends AbstractForm {

    private Customer customer;

    private ValidatedTextFormInput firstName;
    private ValidatedTextFormInput lastName;
    private ValidatedTextFormInput emailAddress;
    private ValidatedTextFormInput company;

    public CustomerForm() {
        super();
    }

    public CustomerForm(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void build() {
        firstName = new ValidatedTextFormInput(Strings.CUSTOMER_FORM_LABEL_FIRSTNAME, InputService.CHARACTERS_ONLY);
        lastName = new ValidatedTextFormInput(Strings.CUSTOMER_FORM_LABEL_LASTNAME, InputService.CHARACTERS_ONLY);
        emailAddress = new ValidatedTextFormInput(Strings.CUSTOMER_FORM_LABEL_EMAIL, InputService.EMAIL_BASIC);
        company = new ValidatedTextFormInput(Strings.CUSTOMER_FORM_LABEL_COMPANY, InputService.ALPHANUMERIC_WORDS);

        if(this.customer!=null) {
            firstName.setValue(this.customer.getFirstName());
            lastName.setValue(this.customer.getLastName());
            emailAddress.setValue(this.customer.getEmailAddress());
            company.setValue(this.customer.getCompany());
        }

        addInput(firstName);
        addInput(lastName);
        addInput(emailAddress);
        addInput(company);
    }

    @Override
    public Customer submit() {
        if (this.customer == null) {
            this.customer = new Customer();
        }

        this.customer.setFirstName(firstName.getValue());
        this.customer.setLastName(lastName.getValue());
        this.customer.setEmailAddress(emailAddress.getValue());
        this.customer.setCompany(company.getValue());

        return this.customer;
    }
}
