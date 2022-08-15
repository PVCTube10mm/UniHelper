package com.github.UniHelper.views.timetable;

import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.utils.NamedButton;

import javax.swing.*;
import java.awt.*;

public class TimetableOptionsPanel extends JPanel {
    private final NamedButton uploadTimetableButton;
    private final NamedButton deleteTimetableButton;

    public TimetableOptionsPanel() {
        super();
        uploadTimetableButton = new NamedButton("upload timetable image");
        deleteTimetableButton = new NamedButton("delete timetable");
        uploadTimetableButton.setPreferredSize(new Dimension((int) uploadTimetableButton.getPreferredSize().getWidth(), 65));
        deleteTimetableButton.setPreferredSize(new Dimension((int) deleteTimetableButton.getPreferredSize().getWidth(), 65));
        setPreferredSize(new Dimension(0, 75));
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(uploadTimetableButton);
        add(deleteTimetableButton);
    }

    public void setUploadTimetableButtonCommand(Command command) {
        uploadTimetableButton.setCommand(command);
    }

    public void setDeleteTimetableButtonCommand(Command command) {
        deleteTimetableButton.setCommand(command);
    }
}
