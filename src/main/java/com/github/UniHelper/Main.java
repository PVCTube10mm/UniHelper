package com.github.UniHelper;

import com.formdev.flatlaf.FlatDarkLaf;
import com.github.UniHelper.GUI.Features.ExampleFeatureView;
import com.github.UniHelper.GUI.MainWindow;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        UIManager.put("Button.arc", 0);
        MainWindow mainWindow = new MainWindow();
        mainWindow.addFeatureView(new ExampleFeatureView());
        mainWindow.show();
    }
}
