package com.github.UniHelper.model.timetable;

import java.awt.image.BufferedImage;

public interface TimetableModel {

    void setTimetableImage(BufferedImage timetableImage);

    void deleteTimetable();

    BufferedImage getTimetableImage();
}
