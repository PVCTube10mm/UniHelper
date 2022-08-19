package com.github.UniHelper.views.notes.notesOptionsPanel;

import com.github.UniHelper.model.categories.Category;
import com.github.UniHelper.views.utils.RadioButton;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;


public class NotesCategorySelectorButton extends RadioButton {

    @Getter
    private final Category category;
    private final JLabel textLabel;
    private final JPanel colorPanel;

    public NotesCategorySelectorButton(Category category) {
        super();
        this.category = category;
        textLabel = new JLabel(category.getName());
        colorPanel = new JPanel();
        setLook();
    }

    public void updateColor() {
        colorPanel.setBackground(category.getColor());
    }

    private void setLook() {
        FlowLayout fl = new FlowLayout();
        fl.setHgap(10);
        fl.setVgap(10);
        setLayout(fl);
        colorPanel.setBackground(category.getColor());
        colorPanel.setPreferredSize(new Dimension(40, 40));
        textLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        add(colorPanel);
        add(textLabel);
    }
}
