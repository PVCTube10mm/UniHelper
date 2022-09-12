package com.github.UniHelper.views.calendar.calendar.weekdaysHeading;

import com.github.UniHelper.views.utils.Weekdays;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WeekdaysHeadingPanel extends JPanel {

    private final ArrayList<JLabel> weekdaysLabels;

    public WeekdaysHeadingPanel() {
        super();
        weekdaysLabels = new ArrayList<>();
        setLayout(new GridLayout(1, 7));
        addWeekdaysToArrayList();
        add(weekdaysLabels);
    }

    private void add(ArrayList<JLabel> weekdaysLabels) {
        for (JLabel dayLabel : weekdaysLabels) {
            add(dayLabel);
        }
    }

    private void addWeekdaysToArrayList() {
        for (Weekdays day : Weekdays.values()) {
            JLabel weekday = new JLabel(day.getShortcut(), SwingConstants.CENTER);
            weekdaysLabels.add(weekday);
        }
    }
}