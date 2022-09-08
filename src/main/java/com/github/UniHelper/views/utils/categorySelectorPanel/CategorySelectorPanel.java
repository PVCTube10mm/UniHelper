package com.github.UniHelper.views.utils.categorySelectorPanel;

import com.github.UniHelper.model.categories.Category;
import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.utils.RadioButton;
import com.github.UniHelper.views.utils.RadioButtonBundle;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CategorySelectorPanel extends JPanel {

    private final ArrayList<Command> onCategoryChangedCommands;
    private final ArrayList<Command> onCategoryModifiedCommands;
    private final RadioButtonBundle radioButtonBundle;
    private final EditCategoryButton editCategoryButton;
    private final AllCategoriesButton allCategoriesButton;

    @Getter
    private Category activeCategory;
    @Getter
    private Color modifiedCategoryColor;
    private ArrayList<Category> categories;
    private ArrayList<RadioButton> selectorButtons;

    public CategorySelectorPanel() {
        super();
        onCategoryChangedCommands = new ArrayList<>();
        onCategoryModifiedCommands = new ArrayList<>();
        radioButtonBundle = new RadioButtonBundle();
        editCategoryButton = new EditCategoryButton();
        allCategoriesButton = new AllCategoriesButton();
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
        radioButtonBundle.addOnActiveButtonChangedCommand(this::changeCategory);
        editCategoryButton.addCommand(this::editCategory);
        editCategoryButton.setEnabled(false);
    }

    private void setLook() {
        FlowLayout fl = new FlowLayout();
        fl.setHgap(1);
        fl.setVgap(10);
        setLayout(fl);
    }

    private void changeCategory() {
        if (radioButtonBundle.getActiveButton() == allCategoriesButton) {
            activeCategory = null;
            editCategoryButton.setEnabled(false);
        } else {
            editCategoryButton.setEnabled(true);
            CategorySelectorButton activeButton = (CategorySelectorButton) radioButtonBundle.getActiveButton();
            activeCategory = activeButton.getCategory();
        }
        executeOnCategoryChangedCommands();
    }

    private void executeOnCategoryChangedCommands() {
        for (Command c : onCategoryChangedCommands) {
            c.execute();
        }
    }

    private void editCategory() {
        Color newColor = getColorFromUser();
        if(newColor != null) {
            modifiedCategoryColor = newColor;
            activeCategory.setColor(newColor);
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
        createNewButtons();
        reactivateButtonWithActiveCategory();
        add(editCategoryButton);
        revalidate();
        repaint();
    }

    private void reactivateButtonWithActiveCategory() {
        selectorButtons.stream()
                .filter(rb -> rb != allCategoriesButton)
                .map(rb -> (CategorySelectorButton) rb)
                .filter(rb -> rb.getCategory().equals(activeCategory))
                .forEach(radioButtonBundle::setActiveButton);
    }

    private void createNewButtons() {
        selectorButtons = categories.stream()
                .map(CategorySelectorButton::new)
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
