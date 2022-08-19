package com.github.UniHelper.views.notes.note;

import com.github.UniHelper.presenters.commands.Command;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;

public class DefaultNoteView implements NoteView, DocumentListener {

    private final NoteMainPanel noteMainPanel;
    private final NoteTitlePanel titlePanel;
    private final NoteTextPanel textPanel;
    private final NoteOptionsPanel optionsPanel;
    private final ArrayList<Command> onNoteModifiedCommands;
    private final ArrayList<Command> onNoteDeletedCommands;

    public DefaultNoteView() {
        noteMainPanel = new NoteMainPanel();
        titlePanel = new NoteTitlePanel();
        textPanel = new NoteTextPanel();
        optionsPanel = new NoteOptionsPanel();
        onNoteModifiedCommands = new ArrayList<>();
        onNoteDeletedCommands = new ArrayList<>();
        assembleView();
    }

    @Override
    public Container getContainer() {
        return noteMainPanel;
    }

    @Override
    public void addOnNoteModifiedCommand(Command command) {
        onNoteModifiedCommands.add(command);
    }

    @Override
    public void addOnNoteDeletedCommand(Command command) {
        onNoteDeletedCommands.add(command);
    }

    @Override
    public String getNoteText() {
        return textPanel.getText();
    }

    @Override
    public String getNoteTitle() {
        return titlePanel.getTitle();
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

    @Override
    public void insertUpdate(DocumentEvent e) {
        changedUpdate(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        changedUpdate(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        executeOnNoteModifiedCommands();
    }

    private void assembleView() {
        noteMainPanel.add(titlePanel, BorderLayout.NORTH);
        noteMainPanel.add(textPanel, BorderLayout.CENTER);
        noteMainPanel.add(optionsPanel, BorderLayout.SOUTH);
        optionsPanel.addDeleteButtonCommand(this::executeOnNoteDeletedCommands);
        titlePanel.addTitleDocumentListener(this);
        textPanel.addTextDocumentListener(this);
    }

    private void executeOnNoteModifiedCommands() {
        for (Command c : onNoteModifiedCommands) {
            c.execute();
        }
    }

    private void executeOnNoteDeletedCommands() {
        for (Command c : onNoteDeletedCommands) {
            c.execute();
        }
    }
}
