package com.github.UniHelper.View.Notes;

import com.github.UniHelper.Model.Note;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DefaultNotesView implements NotesView {
    private final NotesPanel notesPanel;

    public DefaultNotesView() {
        notesPanel = new NotesPanel();
        notesPanel.setBackground(Color.GRAY);
    }

    @Override
    public void updateNotes(ArrayList<Note> notes) {
        for (Note note : notes)
            notesPanel.add(new JButton(note.getTitle()));
    }

    @Override
    public void addNote(Note note) {
        notesPanel.add(new JButton(note.getTitle()));
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
