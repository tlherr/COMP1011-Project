package com.tlherr.Model.ComboBox;

import com.tlherr.Model.Employee.AbstractEmployee;
import com.tlherr.Model.GenericEmployee;
import com.tlherr.Model.Manufacturer;
import com.tlherr.Repository.EmployeeRepository;
import com.tlherr.Repository.ManufacturerRepository;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeComboBoxModel extends AbstractListModel implements ComboBoxModel {

    private ArrayList<GenericEmployee> employeeArrayList;
    private GenericEmployee selection;


    public EmployeeComboBoxModel() {
        employeeArrayList = new ArrayList<>();
        try {
            ResultSet rs = EmployeeRepository.getInstance().loadAll();

            while(rs.next()) {
                employeeArrayList.add(new GenericEmployee(rs.getInt("id"), rs.getString("firstName"), rs.getInt("emp_type")));
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (GenericEmployee) anItem;
    }

    @Override
    public GenericEmployee getSelectedItem() {
        return selection;
    }

    @Override
    public int getSize() {
        return employeeArrayList.size();
    }

    @Override
    public Object getElementAt(int index) {
        return employeeArrayList.get(index);
    }
}
