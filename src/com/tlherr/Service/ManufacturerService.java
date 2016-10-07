package com.tlherr.Service;

import com.tlherr.Main;
import com.tlherr.Model.Manufacturer;
import com.tlherr.Repository.ManufacturerRepository;

public class ManufacturerService {
    private static ManufacturerService instance = new ManufacturerService();

    public static ManufacturerService getInstance() {
        return instance;
    }

    private ManufacturerService() {}

    public void listAll() {
        for(Manufacturer manufacturer: ManufacturerRepository.getInstance().getManufacturers()) {
            System.out.println(manufacturer.toString());
        }

        String[] args = {};
        Main.main(args);
    }


    public void createManufacturer() {

        Manufacturer manufacturer = new Manufacturer();

        manufacturer.setName(ConsoleService.getStringInput(
                "Enter the name of the Manufacturer", ConsoleService.ALPHANUMERIC_WORDS, "Coca Cola"));

        ManufacturerRepository.getInstance().addManufacturer(manufacturer);

        System.out.println("Manufacturer Saved (Name: "+manufacturer.getName()+")");

        String[] args = {};
        Main.main(args);
    }
}
