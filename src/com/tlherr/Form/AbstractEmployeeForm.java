package com.tlherr.Form;

import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Resources.Strings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class AbstractEmployeeForm extends JFrame {

    protected AbstractEmployee employee;
    //Take a panel argument that contains text info from classes?
    protected JPanel contentPanel;
    protected JPanel controlsPanel;
    protected GridBagConstraints labelConstraints;
    protected GridBagConstraints textFieldConstrains;

    protected JButton okButton;
    protected JButton cancelButton;

    public AbstractEmployeeForm() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 400));
        setTitle("Employee Form");
        //No Employee was given here, make the container panel
        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        controlsPanel = new JPanel(new BorderLayout());

        //Set Grid Bag Constraints
        labelConstraints = new GridBagConstraints();
        //Fill Space Horizontally
        labelConstraints.fill = GridBagConstraints.HORIZONTAL;
        //Labels should always start top left of row
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

    public AbstractEmployeeForm(AbstractEmployee empl) {
        this();
        this.employee = empl;
    }

    protected void addLabel(JLabel c) {
        GridBagLayout gbl = (GridBagLayout) contentPanel.getLayout();
        gbl.setConstraints(c, labelConstraints);
        contentPanel.add(c);
    }

    protected void addTextField(JTextField c) {
        GridBagLayout gbl = (GridBagLayout) contentPanel.getLayout();
        gbl.setConstraints(c, textFieldConstrains);
        contentPanel.add(c);
    }

    /**
     * This method adds base employee attributes to panel
     * If an employee is set it will set values
     */
    private void buildBaseFormElements() {
        //First Name
        JLabel firstNameLabel = new JLabel(Strings.EMPLOYEE_FORM_LABEL_FIRSTNAME);
        addLabel(firstNameLabel);

        JTextField firstNameTextField = new JTextField();
        if(this.employee!=null) {
            firstNameTextField.setText(this.employee.getFirstName());
        }
        addTextField(firstNameTextField);

        //Last Name
        JLabel lastNameLabel = new JLabel(Strings.EMPLOYEE_FORM_LABEL_LASTNAME);
        addLabel(lastNameLabel);

        JTextField lastNameTextField = new JTextField();
        if(this.employee!=null) {
            lastNameTextField.setText(this.employee.getLastName());
        }
        addTextField(lastNameTextField);

        //Position
        JLabel positionLabel = new JLabel(Strings.EMPLOYEE_FORM_LABEL_POSITION);
        addLabel(positionLabel);

        JTextField positionTextField = new JTextField();
        if(this.employee!=null) {
            positionTextField.setText(this.employee.getPosition());
        }
        addTextField(positionTextField);

        //Department
        JLabel departmentLabel = new JLabel(Strings.EMPLOYEE_FORM_LABEL_DEPARTMENT);
        addLabel(departmentLabel);

        JTextField departmentTextField = new JTextField();
        if(this.employee!=null) {
            departmentTextField.setText(this.employee.getDepartment());
        }
        addTextField(departmentTextField);

        //Id Number
        JLabel idLabel = new JLabel(Strings.EMPLOYEE_FORM_LABEL_ID);
        addLabel(idLabel);

        JTextField idTextField = new JTextField();
        if(this.employee!=null) {
            departmentTextField.setText(String.valueOf(this.employee.getIdNumber()));
        }
        addTextField(idTextField);
    }

    /**
     * Adds buttons to the form
     */
    private void addFormControls() {
        //Add buttons here
        okButton = new JButton(Strings.EMPLOYEE_CONTROL_BUTTON_OK);
        cancelButton = new JButton(Strings.EMPLOYEE_CONTROL_BUTTON_CANCEL);

        controlsPanel.add(okButton, BorderLayout.EAST);
        controlsPanel.add(cancelButton, BorderLayout.WEST);

    }


    public void build() {
        buildBaseFormElements();
        addFormElements();
        addFormControls();
        add(contentPanel, BorderLayout.NORTH);
        add(controlsPanel, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    /**
     * Important to note that gridBagConstraits will already have elements in it
     * so start y after 6 when adding new elements
     */
    public abstract void addFormElements();

}
