package com.github.UniHelper.views.notes.notesOptionsPanel;

import com.github.UniHelper.model.categories.CategoriesModel;
import com.github.UniHelper.model.categories.Category;
import com.github.UniHelper.model.categories.DefaultCategoriesModel;
import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.utils.RadioButton;
import com.github.UniHelper.views.utils.RadioButtonBundle;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class NotesCategorySelectorPanel extends JPanel {

    @Getter
    private Category activeCategory;
    private final RadioButtonBundle radioButtonBundle;
    private final ArrayList<Command> onCategoryChangedCommands;
    private final ArrayList<RadioButton> selectorButtons;
    private final NotesEditCategoryButton editCategoryButton;
    private final NotesAllCategoriesButton allCategoriesButton;

    public NotesCategorySelectorPanel() {
        super();
        activeCategory = null;
        ArrayList<RadioButton> buttons = new ArrayList<>();
        onCategoryChangedCommands = new ArrayList<>();
        allCategoriesButton = new NotesAllCategoriesButton();

        CategoriesModel categoriesModel = DefaultCategoriesModel.getInstance();
        ArrayList<Category> categories = new ArrayList<>(categoriesModel.getAllCategories());

        selectorButtons = categories.stream()
                .map(NotesCategorySelectorButton::new)
                .collect(Collectors.toCollection(ArrayList::new));

        selectorButtons.add(0, allCategoriesButton);

        radioButtonBundle = new RadioButtonBundle(selectorButtons);
        allCategoriesButton.setActive(true);

        radioButtonBundle.addOnCategoryChangedCommand(this::executeOnCategoryChangedCommands);

        editCategoryButton = new NotesEditCategoryButton();
        editCategoryButton.addCommand(this::getColorFromUser);
        editCategoryButton.setEnabled(false);

        for(RadioButton rb : selectorButtons){
            add(rb);
        }
        add(editCategoryButton);

        setLook();
    }

    private void getColorFromUser() {
        Color newColor;
        if(activeCategory != null) {
            newColor = JColorChooser.showDialog(null, "JColorChooser Sample", activeCategory.getColor());
            if(newColor == null)
                return;
            CategoriesModel cm = DefaultCategoriesModel.getInstance();
            activeCategory.setColor(newColor);
            cm.addOrModifyCategory(activeCategory);
            NotesCategorySelectorButton sb = (NotesCategorySelectorButton) radioButtonBundle.getActiveButton();
            sb.updateColor();
        }
    }

    public void addOnCategoryChangedCommand(Command command) {
        onCategoryChangedCommands.add(command);
    }

    private void executeOnCategoryChangedCommands(){
        if(radioButtonBundle.getActiveButton() == allCategoriesButton) {
            editCategoryButton.setEnabled(false);
            activeCategory = null;
            for(Command c : onCategoryChangedCommands) {
                c.execute();
            }
            return;
        }
        editCategoryButton.setEnabled(true);
        NotesCategorySelectorButton activeButton = (NotesCategorySelectorButton) radioButtonBundle.getActiveButton();
        activeCategory = activeButton.getCategory();
        for(Command c : onCategoryChangedCommands) {
            c.execute();
        }
    }

    private void setLook() {
        FlowLayout fl = new FlowLayout();
        fl.setHgap(1);
        fl.setVgap(10);
        setLayout(fl);
    }
}
