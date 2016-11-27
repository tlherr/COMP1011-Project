package com.tlherr.Repository;

import com.tlherr.Model.IsTabular;
import com.tlherr.Model.Manufacturer;

import java.util.ArrayList;

/**
 * Responsible for storing and accessing manufacturers as they as saved/edited/removed
 */
public class ManufacturerRepository {

    private static ManufacturerRepository instance = new ManufacturerRepository();

    public static ManufacturerRepository getInstance() {
        return instance;
    }

    private ManufacturerRepository(){};

    private static ArrayList<Manufacturer> manufacturers = new ArrayList<Manufacturer>();

    public void addManufacturer(Manufacturer manufacturer) {
        manufacturers.add(manufacturer);
    }

    public String getPropAt(int index, int col) {
        //Check if index is within bounds
        if(!(index>manufacturers.size()) && !(index<0)) {
            return manufacturers.get(index).getProp(col);
        }
        return null;
    }

    public Integer getCount() {
        return manufacturers.size();
    }

    public Manufacturer findByName(String name) {
        for (Manufacturer manufacturer : manufacturers) {
            if(manufacturer.getName().equals(name)) {
                return manufacturer;
            }
        }
        return null;
    }

    public void save(Manufacturer manufacturer) {
        Boolean found = false;
        //Check if the employee exists already
        for(int i=0; i<manufacturers.size(); i++) {
            if(manufacturers.get(i).getName() == manufacturer.getName()) {
                //The employee already exists, update the info
                manufacturers.set(i, manufacturer);
                found = true;
            }
        }

        if(!found) {
            //This is a new manufacturer, save it
            addManufacturer(manufacturer);
        }

    }
}
