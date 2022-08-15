package com.github.UniHelper.model.timetable;

import java.awt.image.BufferedImage;

public interface TimetableModel {
    void setTimetable(BufferedImage timetable);

    void deleteTimetable();

    BufferedImage getTimetable();
}
