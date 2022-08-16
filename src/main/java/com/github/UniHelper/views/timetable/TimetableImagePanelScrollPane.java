package com.github.UniHelper.views.timetable;

import javax.swing.*;

public class TimetableImagePanelScrollPane extends JScrollPane {

    public TimetableImagePanelScrollPane(JPanel imagePanel) {
        super(imagePanel);
        getVerticalScrollBar().setUnitIncrement(16);
    }
}
