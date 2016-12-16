package com.tlherr.Form;

import javax.swing.*;

public abstract class AbstractForm extends JPanel {

    protected JPanel contentPanel;
    protected JPanel controlsPanel;

    public abstract void build();
    public abstract Object submit();
    public abstract Boolean validateForm();
}
