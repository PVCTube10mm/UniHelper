package com.github.UniHelper;

import com.formdev.flatlaf.FlatDarkLaf;
import com.github.UniHelper.Presenter.DefaultMainWindowPresenter;
import com.github.UniHelper.Presenter.MainWindowPresenter;
import com.github.UniHelper.View.MainWindow.DefaultMainWindowView;
import com.github.UniHelper.View.MainWindow.MainWindowView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        UIManager.put("Button.arc", 0);
        MainWindowView mainWindowView = new DefaultMainWindowView();
        MainWindowPresenter mainWindowPresenter = new DefaultMainWindowPresenter(mainWindowView);
        mainWindowView.setPresenter(mainWindowPresenter);
    }
}
