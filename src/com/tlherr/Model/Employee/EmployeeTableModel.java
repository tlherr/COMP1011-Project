package com.tlherr.Model.Employee;

import com.tlherr.Repository.EmployeeRepository;
import com.tlherr.Service.EmployeeService;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

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
        return EmployeeRepository.getInstance().getAt(row,col);
    }
}
