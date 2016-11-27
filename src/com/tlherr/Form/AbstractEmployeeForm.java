package com.tlherr.Form;

import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Resources.Strings;

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
     * GridBagConstants allow for reuse of layouts and styles between form elements
     * Defined in constructor and applied via addLabel() and addTextField()
     */
    protected GridBagConstraints labelConstraints;
    protected GridBagConstraints textFieldConstrains;

    /**
     * Component references so we can get/set form values
     */
    protected JTextField firstNameTextField;
    protected JTextField lastNameTextField;
    protected JTextField positionTextField;
    protected JTextField departmentTextField;

    /**
     * Form Controls
     */
    protected JButton okButton;
    protected JButton cancelButton;

    public AbstractEmployeeForm() {
        setLayout(new BorderLayout());

        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        controlsPanel = new JPanel(new BorderLayout());

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
     * Apply GridBagConstants to a label component and add it to the content panel
     * @param c JLabel Label component to be styled/positioned
     */
    protected void addLabel(JLabel c) {
        GridBagLayout gbl = (GridBagLayout) contentPanel.getLayout();
        gbl.setConstraints(c, labelConstraints);
        contentPanel.add(c);
    }

    /**
     * Apply GridBagConstants to a textfield component and add it to the content panel
     * @param c JTextField TextField component to be styled
     */
    protected void addTextField(JTextField c) {
        GridBagLayout gbl = (GridBagLayout) contentPanel.getLayout();
        gbl.setConstraints(c, textFieldConstrains);
        contentPanel.add(c);
    }

    /**
     * This method adds base employee attribute form components to content panel
     * If an employee is set it will set values based on employee data
     */
    private void buildBaseFormElements() {
        //First Name
        JLabel firstNameLabel = new JLabel(Strings.EMPLOYEE_FORM_LABEL_FIRSTNAME);
        addLabel(firstNameLabel);

        firstNameTextField = new JTextField();
        if(this.employee!=null) {
            firstNameTextField.setText(this.employee.getFirstName());
        }
        addTextField(firstNameTextField);

        //Last Name
        JLabel lastNameLabel = new JLabel(Strings.EMPLOYEE_FORM_LABEL_LASTNAME);
        addLabel(lastNameLabel);

        lastNameTextField = new JTextField();
        if(this.employee!=null) {
            lastNameTextField.setText(this.employee.getLastName());
        }
        addTextField(lastNameTextField);

        //Position
        JLabel positionLabel = new JLabel(Strings.EMPLOYEE_FORM_LABEL_POSITION);
        addLabel(positionLabel);

        positionTextField = new JTextField();
        if(this.employee!=null) {
            positionTextField.setText(this.employee.getPosition());
        }
        addTextField(positionTextField);

        //Department
        JLabel departmentLabel = new JLabel(Strings.EMPLOYEE_FORM_LABEL_DEPARTMENT);
        addLabel(departmentLabel);

        departmentTextField = new JTextField();
        if(this.employee!=null) {
            departmentTextField.setText(this.employee.getDepartment());
        }
        addTextField(departmentTextField);
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
    public abstract Boolean validateForm();

    /**
     * Method for adding custom form elements for subclass data collection
     * Will get called after base elements are built and added
     * So any form elements added here will display under the base elements
     */
    public abstract void addFormElements();

}
