package com.github.UniHelper.presenters;

import com.github.UniHelper.model.Note;
import com.github.UniHelper.model.NotesModel;
import com.github.UniHelper.views.notes.Note.NoteView;

public class DefaultNotePresenter implements NotePresenter {
    private final NoteView view;
    private final NotesModel model;
    private final Note note;

    public DefaultNotePresenter(NoteView view, NotesModel model, Note note){
        this.view = view;
        this.model = model;
        this.note = note;
        view.addOnNoteDeletedCommand(this::deleteNote);
        view.setNoteText(note.getData());
        view.setNoteTitle(note.getTitle());
    }

    private void deleteNote(){
        model.deleteNote(note);
    }
}
