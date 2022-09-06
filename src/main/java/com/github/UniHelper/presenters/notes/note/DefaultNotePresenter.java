package com.github.UniHelper.presenters.notes.note;

import com.github.UniHelper.model.notes.DefaultNotesModel;
import com.github.UniHelper.model.notes.Note;
import com.github.UniHelper.model.notes.NotesModel;
import com.github.UniHelper.views.notes.note.NoteView;

public class DefaultNotePresenter implements NotePresenter {

    private final NoteView view;
    private final NotesModel model;
    private final Note note;

    public DefaultNotePresenter(NoteView view, Note note) {
        this.view = view;
        this.model = DefaultNotesModel.getInstance();
        this.note = note;
        view.setNoteText(note.getText());
        view.setNoteTitle(note.getTitle());
        setViewCommands();
    }

    private void setViewCommands() {
        view.addOnNoteDeletedCommand(this::deleteNote);
        view.addOnNoteModifiedCommand(this::updateNote);
    }

    private void deleteNote() {
        model.deleteNote(note);
    }

    private void updateNote() {
        note.setTitle(view.getNoteTitle());
        note.setText(view.getNoteText());
        model.updateNoteWithSameID(note);
    }
}
