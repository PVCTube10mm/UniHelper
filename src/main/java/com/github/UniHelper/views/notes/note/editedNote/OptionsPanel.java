package com.github.UniHelper.views.notes.note.editedNote;

import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.utils.ColorPalette;
import com.github.UniHelper.views.utils.NamedButton;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class OptionsPanel extends JPanel {

    private final NamedButton deleteButton;
    private final NamedButton saveButton;

    public OptionsPanel() {
        super();
        setPreferredSize(new Dimension(0,100));
        deleteButton = new NamedButton("delete");
        saveButton = new NamedButton("save");
        setLayout(new GridLayout(1, 0));
        deleteButton.setBackground(ColorPalette.RED);
        saveButton.setBackground(ColorPalette.BLUE);
        add(saveButton);
        add(deleteButton);
    }

    public void addDeleteButtonCommand(Command command) {
        deleteButton.addCommand(command);
    }

    public void addSaveButtonCommand(Command command) {
        saveButton.addCommand(command);
    }
}
