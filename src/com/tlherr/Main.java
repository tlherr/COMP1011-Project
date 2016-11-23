package com.tlherr;

import com.tlherr.Frames.ContainerFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        ContainerFrame containerFrame = new ContainerFrame();
        containerFrame.setSize(400, 400);
        containerFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        containerFrame.setVisible(true);
    }
}
