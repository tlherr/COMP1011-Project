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

        if(this.customer!=null) {
            firstName.setEditText(this.customer.getFirstName());
            lastName.setEditText(this.customer.getLastName());
            emailAddress.setEditText(this.customer.getEmailAddress());
            company.setEditText(this.customer.getCompany());
        }

        addValidatedInput(firstName);
        addValidatedInput(lastName);
        addValidatedInput(emailAddress);
        addValidatedInput(company);
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
