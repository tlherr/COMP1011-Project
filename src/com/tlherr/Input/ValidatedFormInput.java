package com.tlherr.Input;

import com.tlherr.Resources.Strings;
import com.tlherr.Service.InputService;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

//This class represents a text input panel containing
//a label, and input and an error message

//Have to be able to set the label text
//Set the input text and get the input value
//Validate the input text
//Show an error message when not validated
public class ValidatedFormInput extends JPanel {

    private JLabel inputLabel;
    private JTextField input;
    private JLabel errorLabel;
    private JTextField error;

    private String validator;

    private GridBagConstraints labelConstraints;
    private GridBagConstraints textFieldConstrains;


    public ValidatedFormInput(String inputLabelText, String validatorString) {
        super(new GridBagLayout());

        //Set Grid Bag Constraints
        labelConstraints = new GridBagConstraints();
        //Fill Space Horizontally
        labelConstraints.fill = GridBagConstraints.HORIZONTAL;
        //Labels should always start left of row
        labelConstraints.anchor = GridBagConstraints.NORTHWEST;
        //Give low weight to prioritize the width of other components that need more space
        labelConstraints.weightx = 0;
        //This component takes up 1 grid space
        labelConstraints.gridwidth = 1;

        //Copy previous settings, only add weight and take up all remaining space in the row
        textFieldConstrains = (GridBagConstraints) labelConstraints.clone();
        textFieldConstrains.weightx = 1.0;
        textFieldConstrains.gridwidth = GridBagConstraints.REMAINDER;

        validator = validatorString;

        inputLabel = new JLabel(inputLabelText);
        addLabel(inputLabel);

        input = new JTextField(10);
        addTextField(input);

        errorLabel = new JLabel(Strings.EMPLOYEE_FORM_LABEL_ERROR);
        errorLabel.setVisible(false);
        addLabel(errorLabel);

        error = new JTextField();
        error.setVisible(false);
        error.setEditable(false);

        addTextField(error);
    }

    public boolean validateInput() {
        Boolean isValid = InputService.validate(input.getText(), validator);

        if(isValid) {
            clearError();
        } else {
            displayError("Invalid Input");
        }

        return isValid;
    }

    public void displayError(String errorString) {
        error.setVisible(true);
        errorLabel.setVisible(true);
        input.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        error.setText(errorString);
    }

    public void clearError() {
        error.setVisible(false);
        errorLabel.setVisible(false);
        input.setBorder(BorderFactory.createEmptyBorder());
        error.setText("");
    }

    public String getValue() {
        return input.getText();
    }

    public void setEditText(String text) {
        input.setText(text);
    }

    public BigDecimal getDecimalValue() {
        return new BigDecimal(input.getText());
    }

    /**
     * Apply GridBagConstants to a label component and add it to the content panel
     * @param c JLabel Label component to be styled/positioned
     */
    private void addLabel(JLabel c) {
        GridBagLayout gbl = (GridBagLayout) this.getLayout();
        gbl.setConstraints(c, labelConstraints);
        this.add(c);
    }

    /**
     * Apply GridBagConstants to a textfield component and add it to the content panel
     * @param c JTextField TextField component to be styled
     */
    private void addTextField(JTextField c) {
        GridBagLayout gbl = (GridBagLayout) this.getLayout();
        gbl.setConstraints(c, textFieldConstrains);
        this.add(c);
    }




}
