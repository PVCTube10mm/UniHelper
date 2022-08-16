package com.github.UniHelper.views.timetable;

import com.github.UniHelper.presenters.commands.Command;
import lombok.Getter;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class DefaultTimetableView implements TimetableView {

    private final TimetableMainPanel mainPanel;
    private final TimetableOptionsPanel optionsPanel;
    private final TimetableImagePanel imagePanel;
    private final TimetableImagePanelScrollPane imagePanelScrollPane;
    private final ArrayList<Command> onTimetableImageUploadedCommands;
    private final ArrayList<Command> onTimetableImageDeletedCommands;
    private final JFileChooser fileChooser;
    @Getter
    private File recentlyUploadedFile;

    public DefaultTimetableView() {
        mainPanel = new TimetableMainPanel();
        optionsPanel = new TimetableOptionsPanel();
        imagePanel = new TimetableImagePanel();
        imagePanelScrollPane = new TimetableImagePanelScrollPane(imagePanel);
        onTimetableImageUploadedCommands = new ArrayList<>();
        onTimetableImageDeletedCommands = new ArrayList<>();
        fileChooser = new JFileChooser();
        assembleView();
    }

    @Override
    public String getFeatureName() {
        return "Timetable";
    }

    @Override
    public Container getContainer() {
        return mainPanel;
    }

    @Override
    public void addOnTimetableImageUploadedCommand(Command command) {
        onTimetableImageUploadedCommands.add(command);
    }

    @Override
    public void addOnTimetableImageDeletedCommand(Command command) {
        onTimetableImageDeletedCommands.add(command);
    }

    @Override
    public void clearTimetableImage() {
        imagePanel.clearImage();
    }

    @Override
    public void setTimetableImage(BufferedImage timetableImage) {
        imagePanel.setImage(timetableImage);
    }

    private void assembleView() {
        mainPanel.add(optionsPanel, BorderLayout.NORTH);
        mainPanel.add(imagePanelScrollPane, BorderLayout.CENTER);
        setButtonCommands();
        setFileChooserFilter();
    }

    private void setButtonCommands() {
        optionsPanel.setUploadTimetableButtonCommand(this::getFileFromUser);
        optionsPanel.setDeleteTimetableButtonCommand(this::executeOnTimetableImageDeletedCommands);
    }

    private void setFileChooserFilter() {
        FileFilter fileFilter = new FileNameExtensionFilter("Image files", "jpg", "png", "bmp");
        fileChooser.setFileFilter(fileFilter);
    }

    private void executeOnTimetableImageUploadedCommands() {
        for (Command c : onTimetableImageUploadedCommands) {
            c.execute();
        }
    }

    private void executeOnTimetableImageDeletedCommands() {
        for (Command c : onTimetableImageDeletedCommands) {
            c.execute();
        }
    }

    private void getFileFromUser() {
        File file = fileChooser.getSelectedFile();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            recentlyUploadedFile = fileChooser.getSelectedFile();
            executeOnTimetableImageUploadedCommands();
        }
    }
}
