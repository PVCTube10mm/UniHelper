package com.github.UniHelper.views.mainWindow;

import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.FeatureView;

import java.awt.*;

public interface MainWindowView {

    void show();

    void showFeature(String featureName);

    void addFeatureView(FeatureView featureView);

    Dimension getFeaturePanelSize();

    void addOnCloseCommand(Command command);

    void addOnFeatureChangeCommand(Command command);
}
