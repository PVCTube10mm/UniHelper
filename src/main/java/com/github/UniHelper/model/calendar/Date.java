package com.github.UniHelper.model.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;

public class Date extends java.util.Date {

    private final Calendar calendar;

    public Date() {
        super();
        calendar = Calendar.getInstance();
    }
    public Date(java.util.Date date) {
        super();
        calendar = Calendar.getInstance();
        calendar.setTime(date);
    }

    public String getYearAsString() {
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    public String getMonthAsString() {
        return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
    }

    public DayOfWeek getFirstWeekdayOfMonth() {
        return LocalDate.now().withDayOfMonth(calendar.getActualMinimum(Calendar.DAY_OF_MONTH)).getDayOfWeek();
    }

    public DayOfWeek getLastWeekdayOfMonth() {
        return LocalDate.now().withDayOfMonth(calendar.getActualMaximum(Calendar.DAY_OF_MONTH)).getDayOfWeek();
    }

    public int getNumberOfWeekdays() {
        return 7;
    }

    public int getNumberOfDaysInMonth() {
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public void incrementByAMonth() {
        calendar.add(Calendar.MONTH, 1);
    }

    public void decrementByAMonth() {
        calendar.add(Calendar.MONTH, -1);
    }
}
