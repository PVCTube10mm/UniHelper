package com.github.UniHelper.model.timetable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DefaultTimetableModel implements TimetableModel {
    private File saveFile;
    private BufferedImage timetable;

    public DefaultTimetableModel() {
        try {
            saveFile = new File("timetable.png");
            if(!saveFile.isFile())
                saveFile.createNewFile();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void setTimetable(BufferedImage timetable) {
        if(timetable == null) {
            deleteTimetable();
            return;
        }
        if(!saveFile.exists()){

        }
        saveAsPNG(timetable);
        try{
            this.timetable = ImageIO.read(saveFile);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTimetable() {
        if(saveFile.exists())
            saveFile.delete();
        timetable = null;
    }

    @Override
    public BufferedImage getTimetable() {
        return timetable;
    }

    private void saveAsPNG(BufferedImage image) {
        try (FileOutputStream outputStream
                     = new FileOutputStream(saveFile.getName())) {
            boolean result = ImageIO.write(
                    image, "png", outputStream);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
