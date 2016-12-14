package com.tlherr.Form;

import com.tlherr.Input.ValidatedFormInput;
import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.InputService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This class is an abstract representation of what an Employee Form should contain
 * it contains all base employee fields so it can be extended to allow CRUD operations on different employee types
 *
 * This panel uses a GridBagLayout. This allows us to dynamically position elements.
 * Elements for the base employee fields are added along with labels.
 *
 * The class provides default GridBagConstants that allow components to be added by subclasses and still share the same
 * styling and layout.
 */
public abstract class AbstractEmployeeForm extends JPanel {

    /**
     * This employee can be loaded into the form to allow for "edit" or "read" functionality
     * If an employee  is added the fields will default to displaying that data and the existing user will be updated
     * Rather than creating a new user
     */
    protected AbstractEmployee employee;

    /**
     * Two panels. One for form elements and another for controls (ok/cancel buttons)
     */
    protected JPanel contentPanel;
    protected JPanel controlsPanel;

    /**
     * Component references so we can get/set form values
     */
    protected ValidatedFormInput firstName;
    protected ValidatedFormInput lastName;
    protected ValidatedFormInput position;
    protected ValidatedFormInput department;

    /**
     * Form Controls
     */
    protected JButton okButton;
    protected JButton cancelButton;

    public AbstractEmployeeForm() {
        setLayout(new BorderLayout());

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        controlsPanel = new JPanel(new BorderLayout());
    }

    /**
     * Secondary constructor that allows an employee of any type to be passed in
     * so data can be displayed/edited vs a blank form if no employee is given
     *
     * @param empl AbstractEmployee an employee to be viewed/edited
     */
    public AbstractEmployeeForm(AbstractEmployee empl) {
        this();
        this.employee = empl;
    }

    public void addValidatedInput(ValidatedFormInput validatedFormInput) {
        this.contentPanel.add(validatedFormInput);
    }

    /**
     * This method adds base employee attribute form components to content panel
     * If an employee is set it will set values based on employee data
     */
    private void buildBaseFormElements() {
        firstName = new ValidatedFormInput(Strings.EMPLOYEE_FORM_LABEL_FIRSTNAME, InputService.CHARACTERS_ONLY);
        lastName = new ValidatedFormInput(Strings.EMPLOYEE_FORM_LABEL_LASTNAME, InputService.CHARACTERS_ONLY);
        position = new ValidatedFormInput(Strings.EMPLOYEE_FORM_LABEL_POSITION, InputService.CHARACTERS_ONLY);
        department = new ValidatedFormInput(Strings.EMPLOYEE_FORM_LABEL_DEPARTMENT, InputService.CHARACTERS_ONLY);

        contentPanel.add(firstName);
        contentPanel.add(lastName);
        contentPanel.add(position);
        contentPanel.add(department);
    }

    /**
     * Adds controls (buttons) to the controls panel
     */
    private void addFormControls() {
        //Add buttons here
        okButton = new JButton(Strings.EMPLOYEE_CONTROL_BUTTON_OK);
        cancelButton = new JButton(Strings.EMPLOYEE_CONTROL_BUTTON_CANCEL);

        controlsPanel.add(okButton, BorderLayout.EAST);
        controlsPanel.add(cancelButton, BorderLayout.WEST);
    }

    /**
     * Set a listener for the ok button
     * @param listener ActionListener Listener to handle event
     */
    public void setOkButtonActionListener(ActionListener listener) {
        if(this.okButton!=null) {
            this.okButton.addActionListener(listener);
        }
    }

    /**
     * Set a listener for the cancel button
     * @param listener ActionListener Listener to handle event
     */
    public void setCancelButtonActionListener(ActionListener listener) {
        if(this.cancelButton!=null) {
            this.cancelButton.addActionListener(listener);
        }
    }

    /**
     * Call build methods that add base form elements, any subclass form elements and form controls
     * then add panels to the main panel and make everything visible.
     */
    public void build() {
        buildBaseFormElements();
        addFormElements();
        addFormControls();
        add(contentPanel, BorderLayout.NORTH);
        add(controlsPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    /**
     * Get values from form and return an AbstractEmployee subclass object
     * @return AbstractEmployee Employee Object
     */
    public abstract AbstractEmployee submit();

    /**
     * Check that inputs match expectations before allowing submission to proceed
     * @ref InputService for validation methods
     * @return Boolean If form validated successfully
     */
    public Boolean validateForm() {
        //Iterate over all components and check validated fields to see if values are ok

        return true;
    };

    /**
     * Method for adding custom form elements for subclass data collection
     * Will get called after base elements are built and added
     * So any form elements added here will display under the base elements
     */
    public abstract void addFormElements();

}
