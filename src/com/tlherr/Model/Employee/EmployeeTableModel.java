package com.tlherr.Model.Employee;

import com.tlherr.Repository.EmployeeRepository;

import javax.swing.table.AbstractTableModel;

/**
 * Custom model to convert/load employee object types into a table display. Makes use of the getProp() method
 * found in AbstractEmployee to get values
 */
public class EmployeeTableModel extends AbstractTableModel {

    private String[] columnNames = { "Employee Type", "ID", "First Name", "Last Name", "Position",
            "Department" };

    public int getRowCount() {
        return EmployeeRepository.getInstance().getCount();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(int row, int col) {
        //return data[row][col];
        return EmployeeRepository.getInstance().getPropAt(row,col);
    }

    public void update() {
        this.fireTableDataChanged();
    }
}
