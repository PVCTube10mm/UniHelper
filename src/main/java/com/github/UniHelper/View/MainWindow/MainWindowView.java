package com.github.UniHelper.View.MainWindow;

import com.github.UniHelper.Presenter.MainWindowPresenter;

public interface MainWindowView {
    void setPresenter(MainWindowPresenter mainWindowPresenter);

    void showFeature(String featureName);

    void show();
}
