package com.github.UniHelper.views.timetable;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TimetableImagePanel extends JPanel {
    private final JLabel imageContainerLabel;

    public TimetableImagePanel() {
        super();
        setLayout(new BorderLayout());
        imageContainerLabel = new JLabel();
        add(imageContainerLabel, BorderLayout.CENTER);
    }

    public void setImage(BufferedImage image){
        ImageIcon icon = new ImageIcon(image);
        imageContainerLabel.setIcon(icon);
        revalidate();
    }

    public void clearImage() {
        imageContainerLabel.setIcon(null);
    }
}
