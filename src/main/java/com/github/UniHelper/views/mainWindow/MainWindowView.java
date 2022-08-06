package com.github.UniHelper.views.mainWindow;

import com.github.UniHelper.views.FeatureView;

public interface MainWindowView {
    void showFeature(String featureName);

    void show();

    void addFeatureView(FeatureView featureView);
}
