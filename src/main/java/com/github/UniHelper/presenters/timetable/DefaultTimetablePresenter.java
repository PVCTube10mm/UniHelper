package com.github.UniHelper.presenters.timetable;

import com.github.UniHelper.model.timetable.TimetableModel;
import com.github.UniHelper.views.timetable.TimetableView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class DefaultTimetablePresenter implements TimetablePresenter {

    private final TimetableView view;
    private final TimetableModel model;

    public DefaultTimetablePresenter(TimetableView view, TimetableModel model) {
        this.view = view;
        this.model = model;
        loadTimetable();
        addViewCommands();
    }

    private void loadTimetable() {
        BufferedImage timetable = model.getTimetableImage();
        if (timetable != null)
            view.setTimetableImage(timetable);
    }

    private void addViewCommands() {
        view.addOnTimetableImageUploadedCommand(this::onImageUploaded);
        view.addOnTimetableImageDeletedCommand(this::onImageDeleted);
    }

    private void onImageUploaded() {
        try {
            File uploadedFile = view.getRecentlyUploadedFile();
            BufferedImage timetableImage = ImageIO.read(uploadedFile);
            model.setTimetableImage(timetableImage);
            view.setTimetableImage(timetableImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onImageDeleted() {
        view.clearTimetableImage();
        model.deleteTimetable();
    }
}
