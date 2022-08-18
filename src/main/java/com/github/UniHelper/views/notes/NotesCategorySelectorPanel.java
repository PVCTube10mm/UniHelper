package com.github.UniHelper.views.notes;

import com.formdev.flatlaf.ui.FlatRoundBorder;
import com.github.UniHelper.model.categories.Category;
import com.github.UniHelper.views.utils.RadioButton;
import com.github.UniHelper.views.utils.RadioButtonBundle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class NotesCategorySelectorPanel extends JPanel {

    public NotesCategorySelectorPanel() {
        super();
        ArrayList<RadioButton> buttons = new ArrayList<>();

        NotesCategorySelectorButton button1 = new NotesCategorySelectorButton(new Category("Personal", Color.RED));
        NotesCategorySelectorButton button2 = new NotesCategorySelectorButton(new Category("Education", Color.GREEN));
        NotesCategorySelectorButton button3 = new NotesCategorySelectorButton(new Category("Work", Color.BLUE));
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        RadioButtonBundle radioButtonBundle = new RadioButtonBundle(buttons);

        add(button1);
        add(button2);
        add(button3);

        setLook();
    }

    private void setLook() {
        FlowLayout fl = new FlowLayout();
        fl.setHgap(1);
        fl.setVgap(0);
        setLayout(fl);
    }
}
