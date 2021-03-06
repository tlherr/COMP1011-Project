package com.tlherr.Form;

import com.tlherr.Input.ValidatedTextFormInput;
import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.InputService;

/**
 * This class is an abstract representation of what an Employee Form should contain
 * it contains all base employee fields so it can be extended to allow CRUD operations on different employee types
 * <p>
 * This panel uses a GridBagLayout. This allows us to dynamically position elements.
 * Elements for the base employee fields are added along with labels.
 * <p>
 * The class provides default GridBagConstants that allow components to be added by subclasses and still share the same
 * styling and layout.
 */
public abstract class AbstractEmployeeForm extends AbstractForm {

    /**
     * This employee can be loaded into the form to allow for "edit" or "read" functionality
     * If an employee  is added the fields will default to displaying that data and the existing user will be updated
     * Rather than creating a new user
     */
    protected AbstractEmployee employee;

    /**
     * Component references so we can get/set form values
     */
    protected ValidatedTextFormInput firstName;
    protected ValidatedTextFormInput lastName;
    protected ValidatedTextFormInput position;
    protected ValidatedTextFormInput department;

    public AbstractEmployeeForm() {
        super();
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

    /**
     * This method adds base employee attribute form components to content panel
     * If an employee is set it will set values based on employee data
     */
    private void buildBaseFormElements() {
        firstName = new ValidatedTextFormInput(Strings.EMPLOYEE_FORM_LABEL_FIRSTNAME, InputService.CHARACTERS_ONLY);
        lastName = new ValidatedTextFormInput(Strings.EMPLOYEE_FORM_LABEL_LASTNAME, InputService.CHARACTERS_ONLY);
        position = new ValidatedTextFormInput(Strings.EMPLOYEE_FORM_LABEL_POSITION, InputService.CHARACTERS_ONLY);
        department = new ValidatedTextFormInput(Strings.EMPLOYEE_FORM_LABEL_DEPARTMENT, InputService.CHARACTERS_ONLY);

        this.contentPanel.add(firstName);
        this.contentPanel.add(lastName);
        this.contentPanel.add(position);
        this.contentPanel.add(department);
    }

    /**
     * Call build methods that add base form elements, any subclass form elements and form controls
     * then add panels to the main panel and make everything visible.
     */
    public void build() {
        buildBaseFormElements();
        addFormElements();
        setVisible(true);
    }

    /**
     * Get values from form and return an AbstractEmployee subclass object
     *
     * @return AbstractEmployee Employee Object
     */
    public abstract AbstractEmployee submit();

    /**
     * Method for adding custom form elements for subclass data collection
     * Will get called after base elements are built and added
     * So any form elements added here will display under the base elements
     */
    public abstract void addFormElements();

}
