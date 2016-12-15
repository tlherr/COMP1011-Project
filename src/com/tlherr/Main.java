package com.tlherr;

import com.tlherr.Frames.ContainerFrame;
import com.tlherr.Model.Employee.HourlyEmployee;
import com.tlherr.Model.Manufacturer;
import com.tlherr.Model.Product;
import com.tlherr.Repository.EmployeeRepository;
import com.tlherr.Repository.ManufacturerRepository;
import com.tlherr.Repository.ProductRepository;

import javax.swing.*;
import java.util.ArrayList;

public class Main {

    /**
     * Email Tony with Group Member Roles
     *
     * Thomas Herr, thom1810@gmail.com, 7059843263
     * Spencer Jackson, spencerja8@gmail.com
     * Jesse Johnston, jesse_dj@live.com
     * rugvita yagnik , rugvita.yagnik10@gmail.com
     *
     * Create New Classes+IO
     * Build db/GUI
     * Event Handling
     * Tidy Up
     * Testing
     * 
     * UML Diagrams
     *
     * @param args
     */



    public static void main(String[] args) {

        //created frame
        ContainerFrame containerFrame = new ContainerFrame();
        containerFrame.setSize(800, 800);
        containerFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        containerFrame.setVisible(true);
    }
}
