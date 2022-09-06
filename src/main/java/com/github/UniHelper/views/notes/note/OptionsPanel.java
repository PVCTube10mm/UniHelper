package com.github.UniHelper.views.notes.note;

import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.utils.ColorPalette;
import com.github.UniHelper.views.utils.NamedButton;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class OptionsPanel extends JPanel {

    private final NamedButton deleteButton;
    private final NamedButton editButton;
    @Getter
    private boolean editModeActive;

    public OptionsPanel() {
        super();
        deleteButton = new NamedButton("delete");
        editButton = new NamedButton("edit");
        editModeActive = false;
        setLayout(new GridLayout(1, 0));
        deleteButton.setBackground(ColorPalette.RED);
        editButton.setBackground(ColorPalette.BROWN);
        add(editButton);
        add(deleteButton);
    }

    public void addDeleteButtonCommand(Command command) {
        deleteButton.addCommand(command);
    }

    public void addEditButtonCommand(Command command) {
        editButton.addCommand(command);
    }

    public void setEditModeActive(boolean editModeActive) {
        this.editModeActive = editModeActive;
        if(editModeActive) {
            editButton.setText("save");
            editButton.setBackground(ColorPalette.BLUE);
        }
        else {
            editButton.setText("edit");
            editButton.setBackground(ColorPalette.BROWN);
        }
    }
}
