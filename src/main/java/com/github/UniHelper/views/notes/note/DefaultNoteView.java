package com.github.UniHelper.views.notes.note;

import com.github.UniHelper.presenters.commands.Command;

import java.awt.*;
import java.util.ArrayList;

public class DefaultNoteView implements NoteView {

    private final MainPanel mainPanel;
    private final TitlePanel titlePanel;
    private final TextPanel textPanel;
    private final OptionsPanel optionsPanel;
    private final ArrayList<Command> onNoteDeletedCommands;
    private final ArrayList<Command> onNoteModifiedCommands;
    private final ArrayList<Command> onEditModeActivatedCommands;

    public DefaultNoteView() {
        mainPanel = new MainPanel();
        titlePanel = new TitlePanel();
        textPanel = new TextPanel();
        optionsPanel = new OptionsPanel();
        onNoteDeletedCommands = new ArrayList<>();
        onNoteModifiedCommands = new ArrayList<>();
        onEditModeActivatedCommands = new ArrayList<>();
        assembleView();
    }

    @Override
    public Container getContainer() {
        return mainPanel;
    }

    @Override
    public void addOnNoteDeletedCommand(Command command) {
        onNoteDeletedCommands.add(command);
    }

    @Override
    public void addOnNoteModifiedCommand(Command command) {
        onNoteModifiedCommands.add(command);
    }

    @Override
    public void addOnEditModeActivatedCommand(Command command) {
        onEditModeActivatedCommands.add(command);
    }

    @Override
    public void setNoteTitle(String title) {
        titlePanel.setTitle(title);
    }

    @Override
    public String getNoteTitle() {
        return titlePanel.getTitle();
    }

    @Override
    public void setNoteText(String text) {
        textPanel.setText(text);
    }

    @Override
    public String getNoteText() {
        return textPanel.getText();
    }

    @Override
    public void setColor(Color color) {
        titlePanel.setTitleBackground(color.darker().darker());
        textPanel.setTextBackground(color);
    }

    @Override
    public void setEditable(boolean editable) {
        textPanel.setEditable(editable);
        titlePanel.setEditable(editable);

    }

    private void assembleView() {
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(textPanel, BorderLayout.CENTER);
        mainPanel.add(optionsPanel, BorderLayout.SOUTH);
        optionsPanel.addDeleteButtonCommand(this::executeOnNoteDeletedCommands);
        optionsPanel.addEditButtonCommand(this::onEditButtonClicked);
    }

    private void onEditButtonClicked() {
        boolean editModeActive = optionsPanel.isEditModeActive();
        if(editModeActive) {
            executeOnNoteModifiedCommands();
        }
        else {
            executeOnEditModeActivatedCommands();
        }
        titlePanel.setEditable(!editModeActive);
        textPanel.setEditable(!editModeActive);
        optionsPanel.setEditModeActive(!editModeActive);
    }

    private void executeOnNoteDeletedCommands() {
        for (Command c : onNoteDeletedCommands) {
            c.execute();
        }
    }

    private void executeOnNoteModifiedCommands() {
        for (Command c : onNoteModifiedCommands) {
            c.execute();
        }
    }

    private void executeOnEditModeActivatedCommands() {
        for (Command c : onEditModeActivatedCommands) {
            c.execute();
        }
    }
}
