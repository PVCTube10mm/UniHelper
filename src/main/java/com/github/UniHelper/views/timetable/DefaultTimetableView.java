package com.github.UniHelper.views.timetable;

import java.awt.*;

public class DefaultTimetableView implements TimetableView {
    private final TimetableMainPanel mainPanel;

    public DefaultTimetableView() {
        mainPanel = new TimetableMainPanel();
    }

    @Override
    public String getFeatureName() {
        return "Timetable";
    }

    @Override
    public Container getContainer() {
        return mainPanel;
    }
}
