package com.github.UniHelper.views.notes;

import com.formdev.flatlaf.ui.FlatRoundBorder;
import com.github.UniHelper.model.categories.Category;

import javax.swing.*;
import java.awt.*;

public class NotesCategorySelectorPanel extends JPanel {

    public NotesCategorySelectorPanel() {
        super();
        setBackground(new Color(50,52,54));
        FlowLayout fl = new FlowLayout();
        fl.setHgap(1);
        fl.setVgap(0);
        setLayout(fl);
        setBorder(new FlatRoundBorder());
        add(new NotesCategorySelectorButton(new Category("Personal", Color.RED)));
        add(new NotesCategorySelectorButton(new Category("Education", Color.BLUE)));
        add(new NotesCategorySelectorButton(new Category("Work", Color.GREEN)));
    }
}
