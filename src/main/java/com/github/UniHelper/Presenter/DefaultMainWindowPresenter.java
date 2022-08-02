package com.github.UniHelper.Presenter;

import com.github.UniHelper.View.MainWindow.MainWindowView;

public class DefaultMainWindowPresenter implements MainWindowPresenter {
    private MainWindowView view;

    public DefaultMainWindowPresenter(MainWindowView mainWindowView) {
        this.view = mainWindowView;
        view.show();
    }

    @Override
    public void onMenuButtonClicked(String featureName) {
        this.view.showFeature(featureName);
    }
}
