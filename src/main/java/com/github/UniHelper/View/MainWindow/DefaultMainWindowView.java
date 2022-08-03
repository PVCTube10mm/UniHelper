package com.github.UniHelper.View.MainWindow;

import com.github.UniHelper.Presenter.Commands.Command;
import com.github.UniHelper.View.FeatureView;
import com.github.UniHelper.View.Utils.ActionButton;

import java.awt.*;

public class DefaultMainWindowView implements MainWindowView {
    private final MainFrame mainFrame;
    private final SideMenuPanel sideMenuPanel;
    private final ActiveFeaturePanel activeFeaturePanel;

    public DefaultMainWindowView() {
        mainFrame = new MainFrame();
        sideMenuPanel = new SideMenuPanel();
        activeFeaturePanel = new ActiveFeaturePanel();
        mainFrame.add(sideMenuPanel, BorderLayout.WEST);
        mainFrame.add(activeFeaturePanel, BorderLayout.CENTER);
    }

    @Override
    public void show() {
        mainFrame.setVisible(true);
    }

    @Override
    public void addFeatureView(FeatureView featureView) {
        String featureName = featureView.getFeatureName();
        activeFeaturePanel.addFeaturePanel(featureView);
        sideMenuPanel.addFeatureButton(featureName);
        setButtonCommand(featureName, () -> showFeature(featureName));
    }

    @Override
    public void setButtonCommand(String buttonName, Command command) {
        ActionButton ab = sideMenuPanel.getButtonByName(buttonName);
        ab.setCommand(command);
    }

    @Override
    public void showFeature(String featureName) {
        this.activeFeaturePanel.chooseView(featureName);
    }
}
