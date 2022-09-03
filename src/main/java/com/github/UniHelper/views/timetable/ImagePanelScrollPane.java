package com.github.UniHelper.views.timetable;

import javax.swing.*;

public class ImagePanelScrollPane extends JScrollPane {

    public ImagePanelScrollPane(JPanel imagePanel) {
        super(imagePanel);
        getVerticalScrollBar().setUnitIncrement(16);
    }
}
