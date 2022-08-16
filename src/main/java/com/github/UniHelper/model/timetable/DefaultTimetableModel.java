package com.github.UniHelper.model.timetable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DefaultTimetableModel implements TimetableModel {

    private final File saveFile;
    private BufferedImage timetableImage;

    public DefaultTimetableModel() {
        saveFile = new File("timetable.png");
        if (!saveFile.exists()) {
            createNewSaveFile();
        } else {
            loadTimetable();
        }
    }

    @Override
    public void setTimetableImage(BufferedImage timetableImage) {
        this.timetableImage = timetableImage;
        if (timetableImage != null) {
            saveTimetable();
        } else {
            deleteTimetable();
        }
    }

    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void deleteTimetable() {
        if (saveFile.exists()) {
            saveFile.delete();
        }
        timetableImage = null;
    }

    @Override
    public BufferedImage getTimetableImage() {
        return timetableImage;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void createNewSaveFile() {
        try {
            saveFile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTimetable() {
        try {
            timetableImage = ImageIO.read(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveTimetable() {
        try (FileOutputStream outputStream = new FileOutputStream(saveFile.getName())) {
            boolean result = ImageIO.write(
                    timetableImage, "png", outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
