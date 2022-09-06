package com.github.UniHelper.presenters.notes.note.previewNote;

import com.github.UniHelper.model.notes.DefaultNotesModel;
import com.github.UniHelper.model.notes.Note;
import com.github.UniHelper.model.notes.NotesModel;
import com.github.UniHelper.views.notes.note.NoteView;
import com.github.UniHelper.views.notes.note.previewNote.PreviewNoteView;
import lombok.Getter;

public class DefaultPreviewNotePresenter implements PreviewNotePresenter {

    private final PreviewNoteView view;
    private final NotesModel model;
    @Getter
    private final Note note;

    public DefaultPreviewNotePresenter(PreviewNoteView view, Note note) {
        this.view = view;
        this.model = DefaultNotesModel.getInstance();
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
