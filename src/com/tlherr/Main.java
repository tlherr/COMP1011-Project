package com.tlherr;

import com.tlherr.Frames.ContainerFrame;
import com.tlherr.Model.Employee.HourlyEmployee;
import com.tlherr.Repository.EmployeeRepository;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        //Add dummy data
        EmployeeRepository.getInstance().addEmployee(new HourlyEmployee("Thomas", "Herr", "Developer", "Information Technology", 40.0f, 20.0f));

        ContainerFrame containerFrame = new ContainerFrame();
        containerFrame.setSize(800, 800);
        containerFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        containerFrame.setVisible(true);
    }
}
