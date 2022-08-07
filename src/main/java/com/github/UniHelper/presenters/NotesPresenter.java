package com.github.UniHelper.presenters;

import com.github.UniHelper.model.Note;

public interface NotesPresenter {
    void addNote(Note note);

    void loadNotes();
}
