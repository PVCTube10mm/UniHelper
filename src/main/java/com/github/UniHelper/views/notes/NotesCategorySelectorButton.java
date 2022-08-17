package com.github.UniHelper.views.notes;

import com.github.UniHelper.model.categories.Category;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class NotesCategorySelectorButton extends JPanel {

    private boolean isActive;

    public NotesCategorySelectorButton(Category category) {
        super();
        FlowLayout fl = new FlowLayout();
        fl.setHgap(11);
        fl.setVgap(11);
        setLayout(fl);

        setBackground(Color.DARK_GRAY.darker().darker());
        setBorder(new LineBorder(Color.BLACK, 1));

        JPanel colorLabel = new JPanel();
        colorLabel.setBackground(category.getColor());
        colorLabel.setPreferredSize(new Dimension(50,50));

        JLabel textLabel = new JLabel(category.getName());
        textLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

        add(colorLabel);
        add(textLabel);
    }
}
