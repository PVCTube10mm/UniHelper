package com.github.UniHelper.presenters.notes;

import com.github.UniHelper.model.notes.Note;
import com.github.UniHelper.model.notes.NotesModel;
import com.github.UniHelper.views.notes.note.NoteView;

public class DefaultNotePresenter implements NotePresenter {

    private final NoteView view;
    private final NotesModel model;
    private final Note note;

    public DefaultNotePresenter(NoteView view, NotesModel model, Note note) {
        this.view = view;
        this.model = model;
        this.note = note;
        view.setNoteText(note.getText());
        view.setNoteTitle(note.getTitle());
        setViewCommands();
    }

    private void setViewCommands() {
        view.addOnNoteDeletedCommand(this::deleteNote);
    }

    private void deleteNote() {
        model.deleteNote(note);
    }
}
