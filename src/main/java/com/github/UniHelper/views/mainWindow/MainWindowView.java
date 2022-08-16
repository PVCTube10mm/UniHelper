package com.github.UniHelper.views.mainWindow;

import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.FeatureView;

public interface MainWindowView {

    void showFeature(String featureName);

    void show();

    void addFeatureView(FeatureView featureView);

    void addOnCloseCommand(Command command);

    void addOnFeatureChangeCommand(Command command);
}
