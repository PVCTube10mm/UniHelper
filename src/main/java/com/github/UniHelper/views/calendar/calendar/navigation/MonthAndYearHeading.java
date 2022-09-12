package com.github.UniHelper.views.calendar.calendar.navigation;

import com.github.UniHelper.model.calendar.Date;

import javax.swing.*;
import java.awt.*;

public class MonthAndYearHeading extends JLabel {

    private final Date date;

    public MonthAndYearHeading() {
        super();
        date = new Date();
        setLabelProperties();
    }

    public MonthAndYearHeading(Date date) {
        super();
        this.date = new Date(date);
        setLabelProperties();
    }

    private void setLabelProperties() {
        setText(" " + date.getMonthAsString() + " " + date.getYearAsString() + " ");
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        setHorizontalAlignment(CENTER);
    }
}
