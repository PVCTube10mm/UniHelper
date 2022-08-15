package com.github.UniHelper.views.timetable;

import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.FeatureView;

import java.awt.image.BufferedImage;
import java.io.File;

public interface TimetableView extends FeatureView {
    void addOnTimetableImageUploadedCommand(Command command);

    void addOnTimetableImageDeletedCommand(Command command);

    File getRecentlyUploadedFile();

    void clearTimetableImage();

    void setTimetableImage(BufferedImage timetableImage);
}
