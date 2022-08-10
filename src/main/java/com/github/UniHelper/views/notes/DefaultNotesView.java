package com.github.UniHelper.views.notes;

import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.notes.Note.NoteView;
import com.github.UniHelper.views.utils.NamedButton;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;

public class DefaultNotesView implements NotesView, DocumentListener {
    private final NotesMainPanel notesMainPanel;
    private final NotesOptionsPanel notesOptionsPanel;
    private final NotesNotesPanel notesNotesPanel;
    private final NotesNotesScrollPane notesNotesScrollPane;
    private final NamedButton newNoteButton;
    private final ArrayList<Command> onNewNoteCommands;
    private final ArrayList<Command> onSearchBarUpdateCommands;

    public DefaultNotesView() {
        notesMainPanel = new NotesMainPanel();
        notesNotesPanel = new NotesNotesPanel();
        notesOptionsPanel = new NotesOptionsPanel();
        notesNotesScrollPane = new NotesNotesScrollPane(notesNotesPanel);
        notesMainPanel.setMaximumSize(new Dimension(0, 1000));
        onNewNoteCommands = new ArrayList<>();
        onSearchBarUpdateCommands = new ArrayList<>();
        newNoteButton = new NewNoteButton();
        newNoteButton.addCommand(this::executeOnNewNoteCommands);
        notesNotesPanel.add(newNoteButton);
        notesMainPanel.add(notesOptionsPanel, BorderLayout.NORTH);
        notesMainPanel.add(notesNotesScrollPane, BorderLayout.CENTER);
        notesOptionsPanel.addSearchBarDocumentListener(this);
    }

    @Override
    public void addNoteView(NoteView noteView) {
        notesNotesPanel.add(noteView.getContainer(), 1);
        notesNotesPanel.setPreferredSize(new Dimension(notesNotesPanel.getWidth(), notesNotesPanel.getHeight() + 101));
        notesNotesPanel.revalidate();
    }

    @Override
    public void addOnNewNoteCommand(Command command) {
        onNewNoteCommands.add(command);
    }

    @Override
    public void addOnSearchBarUpdateCommand(Command command) {
        onSearchBarUpdateCommands.add(command);
    }

    @Override
    public void removeNoteView(NoteView noteView) {
        notesNotesPanel.remove(noteView.getContainer());
        notesNotesPanel.revalidate();
        notesNotesPanel.repaint();
    }

    @Override
    public String getSearchBarText() {
        return notesOptionsPanel.getSearchBarText();
    }

    @Override
    public String getFeatureName() {
        return "Notes";
    }

    @Override
    public Container getContainer() {
        return notesMainPanel;
    }

    private void executeOnNewNoteCommands() {
        for (Command c : onNewNoteCommands)
            c.execute();
    }

    private void executeOnSearchBarUpdateCommands() {
        for (Command c : onSearchBarUpdateCommands)
            c.execute();
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
        executeOnSearchBarUpdateCommands();
    }

    @Override
    public void clearNotes(){
        notesNotesPanel.removeAll();
        notesNotesPanel.add(newNoteButton);
        notesNotesPanel.revalidate();
        notesNotesPanel.repaint();
    }
}
