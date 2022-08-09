package com.github.UniHelper.presenters;

import com.github.UniHelper.model.Note;
import com.github.UniHelper.model.NotesModel;
import com.github.UniHelper.views.notes.Note.DefaultNoteView;
import com.github.UniHelper.views.notes.Note.NoteView;
import com.github.UniHelper.views.notes.NotesView;

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
    public void loadNotes() {
        view.updateNotes(model.getAllNotes());
    }

    private void addViewCommands(){
        view.addOnNewNoteCommand(() -> {
            NoteView noteView = new DefaultNoteView();
            noteView.addOnNoteDeletedCommand(() -> view.removeNoteView(noteView));
            NotePresenter notePresenter = new DefaultNotePresenter(noteView, model);
            view.addNoteView(noteView);
        });
    }
}
