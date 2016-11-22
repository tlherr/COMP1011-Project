package com.tlherr.Panels;

import com.tlherr.Model.Employee.EmployeeTableModel;

import javax.swing.*;
import java.awt.*;

/**
 * HR tab that has the ability to collect all the information of all types of
 * Employees
 */
public class HumanResourcesPanel extends Panel {

    private JTable employeeTable;

    public HumanResourcesPanel() {
        setLayout(new FlowLayout());
        employeeTable = new JTable(new EmployeeTableModel());
        employeeTable.setFillsViewportHeight(true);
        add(employeeTable);
        //Create employee list panel that shows all currently added employees
    }

}
