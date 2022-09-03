package com.github.UniHelper.views.notes.note;

import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.utils.ColorPalette;
import com.github.UniHelper.views.utils.NamedButton;

import javax.swing.*;
import java.awt.*;

public class OptionsPanel extends JPanel {

    private final NamedButton deleteButton;
    private final NamedButton editButton;

    public OptionsPanel() {
        super();
        deleteButton = new NamedButton("delete");
        editButton = new NamedButton("edit");
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
}
