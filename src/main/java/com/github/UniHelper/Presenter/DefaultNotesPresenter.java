package com.github.UniHelper.Presenter;

import com.github.UniHelper.Model.Note;
import com.github.UniHelper.Model.NotesModel;
import com.github.UniHelper.View.Notes.NotesView;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DefaultNotesPresenter implements NotesPresenter{
    NotesView view;
    NotesModel model;

    @Override
    public void addNote(Note note) {
        view.addNote(note);
    }

    @Override
    public void loadNotes() {
        view.updateNotes(model.getAllNotes());
    }
}
