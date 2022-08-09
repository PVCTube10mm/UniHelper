package com.github.UniHelper.views.notes.Note;

import com.github.UniHelper.model.Note;
import com.github.UniHelper.presenters.commands.Command;

import java.awt.*;
import java.util.ArrayList;

public class DefaultNoteView implements NoteView {
    private final NotePanel notePanel;
    private final NoteTitlePanel titlePanel;
    private final NoteTextPanel textPanel;
    private final NoteOptionsPanel optionsPanel;
    private final ArrayList<Command> onNoteModifiedCommands;
    private final ArrayList<Command> onNoteDeletedCommands;

    public DefaultNoteView(){
        notePanel = new NotePanel();
        titlePanel = new NoteTitlePanel();
        textPanel = new NoteTextPanel();
        optionsPanel = new NoteOptionsPanel();
        onNoteModifiedCommands = new ArrayList<>();
        onNoteDeletedCommands = new ArrayList<>();
        notePanel.add(titlePanel, BorderLayout.NORTH);
        notePanel.add(textPanel, BorderLayout.CENTER);
        notePanel.add(optionsPanel, BorderLayout.SOUTH);
        optionsPanel.addDeleteButtonCommand(this::executeOnNoteDeletedCommands);
    }

    @Override
    public Container getContainer() {
        return notePanel;
    }

    @Override
    public Note getDisplayedNote() {
        return null;
    }

    @Override
    public void addOnNoteModifiedCommand(Command command) {
        onNoteModifiedCommands.add(command);
    }

    @Override
    public void addOnNoteDeletedCommand(Command command) {
        onNoteDeletedCommands.add(command);
    }

    private void executeOnNoteModifiedCommands(){
        for(Command c : onNoteModifiedCommands)
            c.execute();
    }

    private void executeOnNoteDeletedCommands(){
        for(Command c : onNoteDeletedCommands)
            c.execute();
    }
}
