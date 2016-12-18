package com.tlherr.Model.ComboBox;

import com.tlherr.Model.Manufacturer;
import com.tlherr.Repository.ManufacturerRepository;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManufacturerComboBoxModel extends AbstractListModel implements ComboBoxModel {

    private ArrayList<Manufacturer> manufacturerArrayList;
    private Manufacturer selection;


    public ManufacturerComboBoxModel() {
        manufacturerArrayList = new ArrayList<>();
        try {
            ResultSet rs = ManufacturerRepository.getInstance().load(Manufacturer.class);

            while(rs.next()) {
                manufacturerArrayList.add(new Manufacturer(rs.getInt("id"), rs.getString("name")));
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (Manufacturer) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }

    @Override
    public int getSize() {
        return manufacturerArrayList.size();
    }

    @Override
    public Object getElementAt(int index) {
        return manufacturerArrayList.get(index);
    }
}
