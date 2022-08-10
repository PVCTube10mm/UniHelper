package com.github.UniHelper.presenters;

import com.github.UniHelper.model.Note;
import com.github.UniHelper.model.NotesModel;
import com.github.UniHelper.views.notes.Note.DefaultNoteView;
import com.github.UniHelper.views.notes.Note.NoteView;
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
    public void loadNotes() {
        ArrayList<Note> notes = (ArrayList<Note>) model.getAllNotes().clone();
        for (Note n : notes) {
            NoteView noteView = new DefaultNoteView();
            noteView.addOnNoteDeletedCommand(() -> view.removeNoteView(noteView));
            NotePresenter notePresenter = new DefaultNotePresenter(noteView, model, n);
            view.addNoteView(noteView);
        }
    }

    private void addViewCommands() {
        view.addOnNewNoteCommand(() -> {
            Note newNote = new Note("New note", "");
            model.addNote(newNote);
            NoteView noteView = new DefaultNoteView();
            noteView.addOnNoteDeletedCommand(() -> view.removeNoteView(noteView));
            NotePresenter notePresenter = new DefaultNotePresenter(noteView, model, newNote);
            view.addNoteView(noteView);
        });
    }
}
