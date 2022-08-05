package com.github.UniHelper.presenters;

import com.github.UniHelper.model.Note;
import com.github.UniHelper.model.NotesModel;
import com.github.UniHelper.views.notes.NotesView;

public class DefaultNotesPresenter implements NotesPresenter {
    NotesView view;
    NotesModel model;

    public DefaultNotesPresenter(NotesView notesView, NotesModel notesModel) {
        view = notesView;
        model = notesModel;
        view.setNewNoteButtonCommand(() -> {
            model.addNote(new Note("title", "data"));
            saveNotes();
            loadNotes();
        });
        loadNotes();
    }

    @Override
    public void addNote(Note note) {
        view.addNote(note);
    }

    @Override
    public void loadNotes() {
        view.updateNotes(model.getAllNotes());
    }

    @Override
    public void saveNotes() {
        model.updateNotes(model.getAllNotes());
    }

    @Override
    public void onClose() {
        saveNotes();
    }
}
