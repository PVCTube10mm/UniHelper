package com.github.UniHelper.views.calendar.calendar;


import com.github.UniHelper.views.calendar.CalendarView;
import com.github.UniHelper.views.calendar.calendar.month.MonthPanel;
import com.github.UniHelper.views.calendar.calendar.navigation.MonthNavigationPanel;
import com.github.UniHelper.views.calendar.calendar.weekdaysHeading.WeekdaysHeadingPanel;

import java.awt.*;

public class DefaultCalendarPanelView implements CalendarPanelView {

    private final CalendarPanel calendarPanel;
    private final MonthNavigationPanel monthNavigationPanel;
    private final WeekdaysHeadingPanel weekdaysHeadingPanel;
    private final MonthPanel monthPanel;
    private final GridBagConstraints gridBagConstraints;

    public DefaultCalendarPanelView(CalendarView view) {
        this.calendarPanel = view.getCalendarPanel();
        weekdaysHeadingPanel = new WeekdaysHeadingPanel();
        monthNavigationPanel = new MonthNavigationPanel();
        monthPanel = new MonthPanel();
        gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1;
        assembleView();
    }

    private void assembleView() {
        assembleCalendarPanel();
        assembleNavigationPanel();
        assembleWeekdaysHeadingPanel();
    }

    private void assembleNavigationPanel() {
        gridBagConstraints.weighty = 0.1;
        addGB(monthNavigationPanel, 0, 0);
    }

    private void assembleWeekdaysHeadingPanel() {
        gridBagConstraints.weighty = 0.1;
        addGB(weekdaysHeadingPanel, 0, 1);
    }

    private void assembleCalendarPanel() {
        gridBagConstraints.weighty = 0.8;
        addGB(monthPanel, 0, 2);
    }

    private void addGB(Component component, int x, int y) {
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        calendarPanel.add(component, gridBagConstraints);
    }
}
