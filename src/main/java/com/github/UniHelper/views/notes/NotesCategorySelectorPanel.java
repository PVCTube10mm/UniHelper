package com.github.UniHelper.views.notes;

import com.github.UniHelper.model.categories.CategoriesModel;
import com.github.UniHelper.model.categories.Category;
import com.github.UniHelper.model.categories.DefaultCategoriesModel;
import com.github.UniHelper.views.utils.RadioButton;
import com.github.UniHelper.views.utils.RadioButtonBundle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class NotesCategorySelectorPanel extends JPanel {

    public NotesCategorySelectorPanel() {
        super();
        ArrayList<RadioButton> buttons = new ArrayList<>();

        CategoriesModel categoriesModel = DefaultCategoriesModel.getInstance();
        ArrayList<Category> categories = new ArrayList<>(categoriesModel.getAllCategories());


        NotesCategorySelectorButton button1 = new NotesCategorySelectorButton(Category.NONE);
        NotesCategorySelectorButton button2 = new NotesCategorySelectorButton(categories.get(0));
        NotesCategorySelectorButton button3 = new NotesCategorySelectorButton(categories.get(1));
        NotesCategorySelectorButton button4 = new NotesCategorySelectorButton(categories.get(2));
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        RadioButtonBundle radioButtonBundle = new RadioButtonBundle(buttons);

        add(button1);
        add(button2);
        add(button3);
        add(button4);

        setLook();
    }

    private void setLook() {
        FlowLayout fl = new FlowLayout();
        fl.setHgap(1);
        fl.setVgap(0);
        setLayout(fl);
    }
}
