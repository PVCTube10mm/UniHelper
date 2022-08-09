package com.github.UniHelper.views.notes;

import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.notes.Note.NoteView;
import com.github.UniHelper.views.utils.NamedButton;

import java.awt.*;
import java.util.ArrayList;

public class DefaultNotesView implements NotesView {
    private final NotesPanel notesPanel;
    private final NotesScrollPane notesScrollPane;
    private final NamedButton newNoteButton;
    private final ArrayList<Command> onNewNoteCommands;

    public DefaultNotesView() {
        notesPanel = new NotesPanel();
        notesScrollPane = new NotesScrollPane(notesPanel);
        notesPanel.setMaximumSize(new Dimension(0, 1000));
        onNewNoteCommands = new ArrayList<>();
        newNoteButton = new NewNoteButton();
        newNoteButton.addCommand(this::executeOnNewNoteCommands);
        notesPanel.add(newNoteButton);
    }

    @Override
    public void addNoteView(NoteView noteView) {
        notesPanel.add(noteView.getContainer(), 1);
        notesPanel.setPreferredSize(new Dimension(notesPanel.getWidth(), notesPanel.getHeight() + 101));
        notesPanel.revalidate();
    }

    @Override
    public void addOnNewNoteCommand(Command command) {
        newNoteButton.setCommand(command);
    }

    @Override
    public void removeNoteView(NoteView noteView) {
        notesPanel.remove(noteView.getContainer());
        notesPanel.revalidate();
        notesPanel.repaint();
    }

    @Override
    public String getFeatureName() {
        return "Notes";
    }

    @Override
    public Container getContainer() {
        return notesScrollPane;
    }

    private void executeOnNewNoteCommands() {
        for (Command c : onNewNoteCommands)
            c.execute();
    }
}
