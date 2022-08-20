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

    private final ArrayList<Command> onCategoryChangedCommands;
    private final ArrayList<Command> onCategoryModifiedCommands;
    private final RadioButtonBundle radioButtonBundle;
    private final NotesEditCategoryButton editCategoryButton;
    private final NotesAllCategoriesButton allCategoriesButton;

    @Getter
    private Category activeCategory;
    private ArrayList<Category> categories;
    private ArrayList<RadioButton> selectorButtons;

    public NotesCategorySelectorPanel() {
        super();
        onCategoryChangedCommands = new ArrayList<>();
        onCategoryModifiedCommands = new ArrayList<>();
        radioButtonBundle = new RadioButtonBundle();
        editCategoryButton = new NotesEditCategoryButton();
        allCategoriesButton = new NotesAllCategoriesButton();
        activeCategory = null;
        assembleView();
        setLook();
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
        updateButtons();
    }

    public void addOnCategoryModifiedCommand(Command command) {
        onCategoryModifiedCommands.add(command);
    }

    private void assembleView() {
        add(allCategoriesButton);
        add(editCategoryButton);
        allCategoriesButton.setActive(true);
        radioButtonBundle.addOnActiveButtonChangedCommand(this::executeOnCategoryChangedCommands);
        editCategoryButton.addCommand(this::editCategory);
        editCategoryButton.setEnabled(false);
    }

    private void setLook() {
        FlowLayout fl = new FlowLayout();
        fl.setHgap(1);
        fl.setVgap(10);
        setLayout(fl);
    }

    private void executeOnCategoryChangedCommands() {
        if (radioButtonBundle.getActiveButton() == allCategoriesButton) {
            activeCategory = null;
            editCategoryButton.setEnabled(false);
        } else {
            editCategoryButton.setEnabled(true);
            NotesCategorySelectorButton activeButton = (NotesCategorySelectorButton) radioButtonBundle.getActiveButton();
            activeCategory = activeButton.getCategory();
        }
        for (Command c : onCategoryChangedCommands) {
            c.execute();
        }
    }

    private void editCategory() {
        Color newColor = getColorFromUser();
        if(newColor != null) {
            CategoriesModel cm = DefaultCategoriesModel.getInstance();
            cm.addOrModifyCategory(activeCategory);
            activeCategory.setColor(newColor);
            NotesCategorySelectorButton sb = (NotesCategorySelectorButton) radioButtonBundle.getActiveButton();
            sb.updateColor();
            executeOnCategoryModifiedCommands();
        }
    }

    private Color getColorFromUser() {
        return JColorChooser.showDialog(null, "Choose new color", activeCategory.getColor());
    }

    public void addOnCategoryChangedCommand(Command command) {
        onCategoryChangedCommands.add(command);
    }

    private void updateButtons() {
        this.removeAll();
        add(allCategoriesButton);
        makeButtons();
        reactivateActiveCategoryButton();
        add(editCategoryButton);
        revalidate();
        repaint();
    }

    private void reactivateActiveCategoryButton() {
        for (RadioButton rb : selectorButtons) {
            if (rb instanceof NotesCategorySelectorButton) {
                if (((NotesCategorySelectorButton) rb).getCategory().equals(activeCategory))
                    radioButtonBundle.setActiveButton(rb);
            }
        }
    }

    private void makeButtons() {
        selectorButtons = categories.stream()
                .map(NotesCategorySelectorButton::new)
                .collect(Collectors.toCollection(ArrayList::new));
        selectorButtons.add(0, allCategoriesButton);
        radioButtonBundle.setButtons(selectorButtons);
        for (RadioButton rb : selectorButtons) {
            add(rb);
        }
    }

    private void executeOnCategoryModifiedCommands() {
        for (Command c : onCategoryModifiedCommands) {
            c.execute();
        }
    }
}
