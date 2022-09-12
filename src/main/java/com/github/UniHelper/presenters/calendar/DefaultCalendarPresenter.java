package com.github.UniHelper.presenters.calendar;

import com.github.UniHelper.presenters.calendar.calendar.CalendarPanelPresenter;
import com.github.UniHelper.presenters.calendar.calendar.DefaultCalendarPanelPresenter;
import com.github.UniHelper.views.calendar.CalendarView;
import com.github.UniHelper.views.calendar.calendar.CalendarPanelView;
import com.github.UniHelper.views.calendar.calendar.DefaultCalendarPanelView;

public class DefaultCalendarPresenter implements CalendarPresenter {

    private final CalendarView view;

    public DefaultCalendarPresenter(CalendarView view) {
        this.view = view;
        initializePanelsViews();
    }

    private void initializePanelsViews() {
        initializeCalendar();
    }

    private void initializeCalendar() {
        CalendarPanelView calendarPanelView = new DefaultCalendarPanelView(view);
        CalendarPanelPresenter calendarPanelPresenter = new DefaultCalendarPanelPresenter();
    }
}
