package com.github.UniHelper.views.notes;

import com.github.UniHelper.model.categories.Category;
import com.github.UniHelper.views.utils.RadioButton;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;


public class NotesCategorySelectorButton extends RadioButton {

    @Getter
    private final Category category;

    public NotesCategorySelectorButton(Category category) {
        super();
        this.category = category;
        setLook();
    }

    private void setLook() {
        FlowLayout fl = new FlowLayout();
        fl.setHgap(10);
        fl.setVgap(10);
        setLayout(fl);

        JPanel colorLabel = new JPanel();
        colorLabel.setBackground(category.getColor());
        colorLabel.setPreferredSize(new Dimension(50, 50));

        JLabel textLabel = new JLabel(category.getName());
        textLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

        add(colorLabel);
        add(textLabel);
    }
}
