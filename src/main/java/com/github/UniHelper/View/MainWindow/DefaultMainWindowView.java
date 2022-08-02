package com.github.UniHelper.View.MainWindow;

import com.github.UniHelper.Presenter.MainWindowPresenter;
import com.github.UniHelper.View.FeatureView;

import javax.swing.*;
import java.awt.*;

public class DefaultMainWindowView implements MainWindowView {
    private final MainFrame mainFrame;
    private final SideMenuPanel sideMenuPanel;
    private final ActiveFeaturePanel activeFeaturePanel;
    private MainWindowPresenter presenter;

    public DefaultMainWindowView() {
        mainFrame = new MainFrame();
        sideMenuPanel = new SideMenuPanel();
        activeFeaturePanel = new ActiveFeaturePanel();
        mainFrame.add(sideMenuPanel, BorderLayout.WEST);
        mainFrame.add(activeFeaturePanel, BorderLayout.CENTER);
    }

    @Override
    public void setPresenter(MainWindowPresenter mainWindowPresenter) {
        this.presenter = mainWindowPresenter;
        for(Component c : sideMenuPanel.getComponents()){
            JButton jb = (JButton) c;
            jb.addActionListener(e -> presenter.onMenuButtonClicked(jb.getText()));
        }
    }

    @Override
    public void show() {
        mainFrame.setVisible(true);
    }

    @Override
    public void showFeature(String featureName){
        this.activeFeaturePanel.chooseView(featureName);
    }

    public void addFeatureView(FeatureView featureView) {
        activeFeaturePanel.addFeatureView(featureView);
        JButton button = new JButton(featureView.getFeatureName());
        sideMenuPanel.addFeatureButton(button);
    }
}
