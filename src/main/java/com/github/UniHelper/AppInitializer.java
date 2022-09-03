package com.github.UniHelper;

import com.formdev.flatlaf.FlatDarkLaf;
import com.github.UniHelper.presenters.DefaultMainWindowPresenter;
import com.github.UniHelper.presenters.MainWindowPresenter;
import com.github.UniHelper.views.mainWindow.DefaultMainWindowView;
import com.github.UniHelper.views.mainWindow.MainWindowView;

import javax.swing.*;

public class AppInitializer {

    public AppInitializer() {
        initializeLAF();
        initializeMainWindow();
    }

    private void initializeLAF() {
        FlatDarkLaf.setup();
        UIManager.put("Button.arc", 0);
    }

    private void initializeMainWindow() {
        MainWindowView mainWindowView = new DefaultMainWindowView();
        MainWindowPresenter mainWindowPresenter = new DefaultMainWindowPresenter(mainWindowView);
        mainWindowPresenter.onLaunch();
    }
}
