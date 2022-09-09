package com.github.UniHelper.presenters.notes.note.previewNote;

import com.github.UniHelper.model.categories.Category;
import com.github.UniHelper.model.categories.DefaultCategoriesModel;
import com.github.UniHelper.model.notes.DefaultNotesModel;
import com.github.UniHelper.model.notes.Note;
import com.github.UniHelper.model.notes.NotesModel;
import com.github.UniHelper.views.notes.note.previewNote.PreviewNoteView;
import lombok.Getter;

import java.awt.*;

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
        view.setId(note.getId());
        view.setColor(getNoteColor());
        setViewCommands();
    }

    private Color getNoteColor() {
        return DefaultCategoriesModel.getInstance().getAllCategories()
                .stream()
                .filter(c -> c.getName().equals(note.getCategory()))
                .map(Category::getColor)
                .findFirst()
                .orElse(null);
    }

    private void setViewCommands() {
        view.addOnNoteDeletedCommand(this::deleteNote);
    }

    private void deleteNote() {
        model.deleteNote(note);
    }
}
