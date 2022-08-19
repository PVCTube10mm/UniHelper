package com.github.UniHelper.views.mainWindow;

import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.FeatureView;

import java.awt.*;

public interface MainWindowView {

    void showFeature(String featureName);

    void show();

    void addFeatureView(FeatureView featureView);

    void addOnCloseCommand(Command command);

    void addOnFeatureChangeCommand(Command command);

    Dimension getFeaturePanelSize();
}
