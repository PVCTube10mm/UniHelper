package com.github.UniHelper.views.timetable;

import com.github.UniHelper.views.utils.NamedButton;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class TimetableMainPanel extends JPanel {
    public TimetableMainPanel(){
        super();
        setLayout(new BorderLayout());
        JPanel imagePanel = new JPanel();
        JLabel imageContainerLabel = new JLabel();
        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(imageContainerLabel, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        this.add(scrollPane, BorderLayout.CENTER);
        NamedButton addTimetableButton = new NamedButton("add timetable");
        this.add(addTimetableButton, BorderLayout.NORTH);
        addTimetableButton.addCommand(() -> {
            JFileChooser fileChooser = new JFileChooser();
            File file = null;
            int returnValue = fileChooser.showOpenDialog(this);
            if(returnValue == JFileChooser.APPROVE_OPTION) {
                try{
                    file = fileChooser.getSelectedFile();
                    Files.copy(file.toPath(), new File(file.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            if(file != null){
                ImageIcon icon = new ImageIcon(file.getName());
                imageContainerLabel.setIcon(icon);
                revalidate();
                repaint();
            }
        });
    }
}
