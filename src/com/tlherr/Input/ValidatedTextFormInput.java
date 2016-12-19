package com.tlherr.Input;

import com.tlherr.Resources.Strings;
import com.tlherr.Service.InputService;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

//This class represents a text input panel containing
//a label, and input and an error message

//Have to be able to set the label text
//Set the input text and get the input value
//Validate the input text
//Show an error message when not validated
public class ValidatedTextFormInput extends AbstractFormInput {

    private JTextField input;
    private JLabel errorLabel;
    private JTextField error;

    private String validator;

    public ValidatedTextFormInput(String inputLabelText, String validatorString) {
        super(inputLabelText);

        validator = validatorString;

        input = new JTextField(10);
        addInputField(input);

        errorLabel = new JLabel(Strings.EMPLOYEE_FORM_LABEL_ERROR);
        errorLabel.setVisible(false);
        addLabel(errorLabel);

        error = new JTextField();
        error.setVisible(false);
        error.setEditable(false);

        addInputField(error);
    }

    public boolean validateInput() {
        Boolean isValid = InputService.validate(input.getText(), validator);

        if (isValid) {
            clearError();
        } else {
            displayError("Invalid Input");
        }

        return isValid;
    }

    private void displayError(String errorString) {
        error.setVisible(true);
        errorLabel.setVisible(true);
        input.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        error.setText(errorString);
    }

    private void clearError() {
        error.setVisible(false);
        errorLabel.setVisible(false);
        input.setBorder(BorderFactory.createEmptyBorder());
        error.setText("");
    }

    public String getValue() {
        return input.getText();
    }

    public void setValue(String text) {
        input.setText(text);
    }

    public BigDecimal getDecimalValue() {
        BigDecimal toReturn;
        try {
            toReturn = new BigDecimal(input.getText()).setScale(2, RoundingMode.HALF_UP);
        } catch (NumberFormatException numException) {
            toReturn = new BigDecimal(0).setScale(2, RoundingMode.HALF_UP);
        }

        return toReturn;
    }


}
