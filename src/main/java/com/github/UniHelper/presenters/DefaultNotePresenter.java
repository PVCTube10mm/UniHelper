package com.github.UniHelper.presenters;

import com.github.UniHelper.model.NotesModel;
import com.github.UniHelper.views.notes.Note.NoteView;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DefaultNotePresenter implements NotePresenter {
    private final NoteView view;
    private final NotesModel model;
}
