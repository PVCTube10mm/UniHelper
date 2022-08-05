package com.github.UniHelper.views.notes;

import com.github.UniHelper.model.Note;
import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.mainWindow.MenuButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DefaultNotesView implements NotesView {
    private final NotesPanel notesPanel;
    private final MenuButton newNoteButton;

    public DefaultNotesView() {
        notesPanel = new NotesPanel();
        notesPanel.setBackground(Color.DARK_GRAY);
        notesPanel.setLayout(new GridLayout(5, 5));
        newNoteButton = new MenuButton("new note");
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
    public ArrayList<Note> getNotes() {
        return null;
    }

    @Override
    public void setNewNoteButtonCommand(Command command) {
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
}
