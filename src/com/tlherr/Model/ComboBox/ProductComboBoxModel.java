package com.tlherr.Model.ComboBox;

import com.tlherr.Model.Manufacturer;
import com.tlherr.Model.Product;
import com.tlherr.Repository.ProductRepository;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductComboBoxModel extends AbstractListModel implements ComboBoxModel {

    private ArrayList<Product> productArrayList;
    private Product selection;


    public ProductComboBoxModel() {
        productArrayList = new ArrayList<>();
        try {
            ResultSet rs = ProductRepository.getInstance().load(Product.class);

            while(rs.next()) {
                Manufacturer manufacturer = new Manufacturer(rs.getInt("Manufacturer ID"), rs.getString("Manufacturer Name"));
                Product product = new Product(rs.getInt("Product ID"), rs.getString("Product Name"), manufacturer, rs.getString("Model Number"));
                System.out.println(product.toString());
                productArrayList.add(product);
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (Product) anItem;
    }

    @Override
    public Product getSelectedItem() {
        return selection;
    }

    @Override
    public int getSize() {
        return productArrayList.size();
    }

    @Override
    public Object getElementAt(int index) {
        return productArrayList.get(index);
    }
}
