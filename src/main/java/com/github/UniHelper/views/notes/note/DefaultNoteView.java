package com.github.UniHelper.views.notes.note;

import com.github.UniHelper.presenters.commands.Command;

import java.awt.*;
import java.util.ArrayList;

public class DefaultNoteView implements NoteView {

    private final NoteMainPanel noteMainPanel;
    private final NoteTitlePanel titlePanel;
    private final NoteTextPanel textPanel;
    private final NoteOptionsPanel optionsPanel;
    private final ArrayList<Command> onNoteDeletedCommands;

    public DefaultNoteView() {
        noteMainPanel = new NoteMainPanel();
        titlePanel = new NoteTitlePanel();
        textPanel = new NoteTextPanel();
        optionsPanel = new NoteOptionsPanel();
        onNoteDeletedCommands = new ArrayList<>();
        assembleView();
    }

    @Override
    public Container getContainer() {
        return noteMainPanel;
    }

    @Override
    public void addOnNoteDeletedCommand(Command command) {
        onNoteDeletedCommands.add(command);
    }

    @Override
    public void setNoteTitle(String title) {
        titlePanel.setTitle(title);
    }

    @Override
    public void setNoteText(String text) {
        textPanel.setText(text);
    }

    @Override
    public void setColor(Color color) {
        titlePanel.setTitleBackground(color.darker().darker());
        textPanel.setTextBackground(color);
    }

    public NoteTitlePanel getTitlePanel() {
        return titlePanel;
    }

    public NoteTextPanel getTextPanel() {
        return textPanel;
    }

    private void assembleView() {
        noteMainPanel.add(titlePanel, BorderLayout.NORTH);
        noteMainPanel.add(textPanel, BorderLayout.CENTER);
        noteMainPanel.add(optionsPanel, BorderLayout.SOUTH);
        optionsPanel.addDeleteButtonCommand(this::executeOnNoteDeletedCommands);
    }

    private void executeOnNoteDeletedCommands() {
        for (Command c : onNoteDeletedCommands) {
            c.execute();
        }
    }
}
