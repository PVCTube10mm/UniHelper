package com.github.UniHelper.views.todo;

import java.awt.*;

public class DefaultTodoView implements TodoView {

    private final MainPanel mainPanel;

    public DefaultTodoView() {
        mainPanel = new MainPanel();
    }

    @Override
    public String getFeatureName() {
        return "Todo";
    }

    @Override
    public Container getContainer() {
        return mainPanel;
    }
}
