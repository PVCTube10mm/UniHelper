package com.github.UniHelper.presenters.notes;

import com.github.UniHelper.model.categories.Category;
import com.github.UniHelper.model.categories.DefaultCategoriesModel;
import com.github.UniHelper.model.notes.DefaultNotesModel;
import com.github.UniHelper.model.notes.Note;
import com.github.UniHelper.presenters.notes.note.editedNote.DefaultEditedNotePresenter;
import com.github.UniHelper.presenters.notes.note.editedNote.EditedNotePresenter;
import com.github.UniHelper.presenters.notes.showNotes.DefaultShowNotesPresenter;
import com.github.UniHelper.presenters.notes.showNotes.ShowNotesPresenter;
import com.github.UniHelper.views.notes.DefaultNotesView;
import com.github.UniHelper.views.notes.NotesView;
import com.github.UniHelper.views.notes.editNote.DefaultEditNoteView;
import com.github.UniHelper.views.notes.editNote.EditNoteView;
import com.github.UniHelper.views.notes.note.editedNote.DefaultEditedNoteView;
import com.github.UniHelper.views.notes.note.editedNote.EditedNoteView;
import com.github.UniHelper.views.notes.showNotes.DefaultShowNotesView;
import com.github.UniHelper.views.notes.showNotes.ShowNotesView;

import java.util.ArrayList;

public class DefaultNotesPresenter implements NotesPresenter {

    private final NotesView view;

    public DefaultNotesPresenter(NotesView notesView) {
        view = notesView;
        ShowNotesView showNotesView = new DefaultShowNotesView(view.getAccessibleWidth());
        ShowNotesPresenter showNotesPresenter = new DefaultShowNotesPresenter(showNotesView);
        view.addOnNoteEditRequestedCommand(this::showEditView);
        view.addOnNoteEditFinishedCommand(this::showPreviewsView);
        view.setShowNotesView(showNotesView);
    }

    private void showPreviewsView() {
        view.showShowNotesView();
    }

    private void showEditView() {
        EditNoteView editNoteView = new DefaultEditNoteView();
        EditedNoteView editedNoteView = new DefaultEditedNoteView(view.getNoteToEdit());


        //EditedNotePresenter editedNotePresenter = new DefaultEditedNotePresenter(editedNoteView, editedNote);
        editNoteView.setNoteView(editedNoteView);
        view.setEditNoteView(editNoteView);
        view.showEditNoteView();
    }
}
