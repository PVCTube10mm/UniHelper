package com.github.UniHelper.views.timetable;

import com.github.UniHelper.model.timetable.DefaultTimetableModel;
import com.github.UniHelper.views.utils.NamedButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class TimetableMainPanel extends JPanel {
    public TimetableMainPanel(){
        super();

        DefaultTimetableModel model = new DefaultTimetableModel();

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
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            if(file != null){
                try{
                    BufferedImage image = ImageIO.read(file);
                    model.setTimetableImage(image);
                    image = model.getTimetableImage();
                    ImageIcon icon = new ImageIcon(image);
                    imageContainerLabel.setIcon(icon);
                    revalidate();
                    repaint();
                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }
}
