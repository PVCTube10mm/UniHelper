package com.github.UniHelper.views.calendar.calendar.month;

import com.github.UniHelper.model.calendar.Date;
import com.github.UniHelper.views.utils.Weekdays;

import javax.swing.*;
import java.awt.*;

public class MonthPanel extends JPanel {
    private final Date date;

    public MonthPanel() {
        super();
        date = new Date();
        setLayout(new GridLayout(5, 7, 0, 0));
        addDayAndSupplementaryButtons();
    }

    private void addDayAndSupplementaryButtons() {
        addSupplementaryButtonsBeforeFirstDay();
        addDayButtons();
        addSupplementaryButtonsAfterLastDay();
    }

    private void addSupplementaryButtonsBeforeFirstDay() {
        for (int i = Weekdays.MONDAY.getValue(); i < date.getFirstWeekdayOfMonth().getValue(); i++) {
            add(new JButton());
        }
    }

    private void addSupplementaryButtonsAfterLastDay() {
        for (int i = date.getLastWeekdayOfMonth().getValue(); i < date.getNumberOfWeekdays(); i++) {
            add(new JButton());
        }
    }

    private void addDayButtons() {
        for (int day = 1; day <= date.getNumberOfDaysInMonth(); day++) {
            add(new Button(String.valueOf(day)));
        }
    }
}
