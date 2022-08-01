package com.github.UniHelper.GUI;

import com.github.UniHelper.GUI.Features.FeatureView;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    private final MainFrame mainFrame;
    private final MenuPanel menuPanel;
    private final ActiveFeaturePanel activeFeaturePanel;

    public MainWindow() {
        mainFrame = new MainFrame();
        menuPanel = new MenuPanel();
        activeFeaturePanel = new ActiveFeaturePanel();
        mainFrame.add(menuPanel, BorderLayout.WEST);
        mainFrame.add(activeFeaturePanel, BorderLayout.CENTER);
    }

    public void addFeatureView(FeatureView featureView) {
        activeFeaturePanel.addFeatureView(featureView);
        JButton button = new JButton(featureView.getFeatureName());
        button.addActionListener(e -> activeFeaturePanel.showFeature(featureView.getFeatureName()));
        menuPanel.addFeatureButton(button);
    }

    public void show() {
        mainFrame.setVisible(true);
    }
}
