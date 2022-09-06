package com.github.UniHelper.presenters.notes;

import com.github.UniHelper.views.notes.NotesView;

public class DefaultNotesPresenter implements NotesPresenter {

    private final NotesView view;

    public DefaultNotesPresenter(NotesView notesView) {
        view = notesView;
    }
}
