package com.github.UniHelper.presenters;

import com.github.UniHelper.model.Note;
import com.github.UniHelper.model.NotesModel;
import com.github.UniHelper.views.notes.NotesView;

import java.util.ArrayList;

public class DefaultNotesPresenter implements NotesPresenter {
    NotesView view;
    NotesModel model;

    public DefaultNotesPresenter(NotesView notesView, NotesModel notesModel) {
        view = notesView;
        model = notesModel;
        addViewCommands();
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
        ArrayList<Note> updatedNotes = view.getNotes();
        model.updateNotes(model.getAllNotes());
    }

    @Override
    public void onClose() {
        saveNotes();
    }

    private void addViewCommands(){
        view.addOnCloseCommand(() -> model.updateNotes(view.getNotes()));
        view.addOnNewNoteCommand(() -> {
            model.addNote(new Note("title", "data"));
            saveNotes();
            loadNotes();
        });
    }
}
