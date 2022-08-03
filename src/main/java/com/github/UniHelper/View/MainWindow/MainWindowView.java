package com.github.UniHelper.View.MainWindow;

import com.github.UniHelper.Presenter.Commands.Command;
import com.github.UniHelper.View.FeatureView;

public interface MainWindowView {
    void showFeature(String featureName);

    void show();

    void addFeatureView(FeatureView featureView);

    void setButtonCommand(String buttonName, Command command);
}
