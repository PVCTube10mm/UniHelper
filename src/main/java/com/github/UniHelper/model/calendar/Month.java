package com.github.UniHelper.model.calendar;

import java.util.Calendar;
import java.util.Date;

public class Month {

    private final Calendar calendar;

    public Month(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
    }

    private void incrementMonth() {
        calendar.add(Calendar.MONTH, 1);
    }

    private void decrementMonth() {
        calendar.add(Calendar.MONTH, -1);
    }
}
