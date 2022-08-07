package com.github.UniHelper.views.notes;

import com.github.UniHelper.model.Note;
import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.utils.NamedButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DefaultNotesView implements NotesView {
    private final NotesPanel notesPanel;
    private final NamedButton newNoteButton;
    private final ArrayList<Command> onNoteModifiedCommands;
    private final ArrayList<Command> onNoteDeletedCommands;
    private final ArrayList<Command> onNewNoteCommands;

    public DefaultNotesView() {
        notesPanel = new NotesPanel();
        notesPanel.setLayout(new GridLayout(5, 5));
        newNoteButton = new NamedButton("new note");
        onNoteModifiedCommands = new ArrayList<>();
        onNewNoteCommands = new ArrayList<>();
        onNoteDeletedCommands = new ArrayList<>();
        notesPanel.setBackground(Color.DARK_GRAY);
        notesPanel.add(newNoteButton);
    }

    @Override
    public void updateNotes(ArrayList<Note> notes) {
        notesPanel.removeAll();
        for (Note note : notes)
            addNote(note);
        notesPanel.add(newNoteButton);
    }

    @Override
    public void addNote(Note note) {
        notesPanel.add(new NotePanel());
        notesPanel.validate();
    }

    @Override
    public Note getLastOperationSubjectNote() {
        return null;
    }

    @Override
    public void addOnNoteDeletedCommand(Command command) {
        onNoteDeletedCommands.add(command);
    }

    @Override
    public void addOnNewNoteCommand(Command command) {
        newNoteButton.setCommand(command);
    }

    @Override
    public void addOnNoteModifiedCommand(Command command) {
        newNoteButton.setCommand(command);
    }

    @Override
    public String getFeatureName() {
        return "Notes";
    }

    @Override
    public JPanel getPanel() {
        return notesPanel;
    }

    private void executeOnNewNoteCommands(){
        for(Command c : onNewNoteCommands)
            c.execute();
    }

    private void executeOnNoteDeletedCommands(){
        for(Command c : onNewNoteCommands)
            c.execute();
    }

    private void executeOnNoteModifiedCommands(){
        for(Command c : onNewNoteCommands)
            c.execute();
    }
}
