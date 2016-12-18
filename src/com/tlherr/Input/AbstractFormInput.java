package com.tlherr.Input;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractFormInput extends JPanel {

    protected GridBagConstraints labelConstraints;
    protected GridBagConstraints inputFieldConstraints;

    protected JLabel inputLabel;

    public AbstractFormInput(String inputLabelText) {
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
        inputFieldConstraints = (GridBagConstraints) labelConstraints.clone();
        inputFieldConstraints.weightx = 1.0;
        inputFieldConstraints.gridwidth = GridBagConstraints.REMAINDER;

        inputLabel = new JLabel(inputLabelText);
        addLabel(inputLabel);
    }

    public abstract Object getValue();

    /**
     * Apply GridBagConstants to a label component and add it to the content panel
     *
     * @param c JLabel Label component to be styled/positioned
     */
    protected void addLabel(JLabel c) {
        GridBagLayout gbl = (GridBagLayout) this.getLayout();
        gbl.setConstraints(c, this.labelConstraints);
        this.add(c);
    }

    /**
     * Apply GridBagConstants to a textfield component and add it to the content panel
     *
     * @param c JTextField TextField component to be styled
     */
    protected void addInputField(JComponent c) {
        GridBagLayout gbl = (GridBagLayout) this.getLayout();
        gbl.setConstraints(c, this.inputFieldConstraints);
        this.add(c);
    }
}
