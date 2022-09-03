package com.github.UniHelper.model.timetable;

import java.awt.image.BufferedImage;

public interface TimetableModel {

    BufferedImage getTimetableImage();

    void setTimetableImage(BufferedImage timetableImage);

    void deleteTimetable();
}
