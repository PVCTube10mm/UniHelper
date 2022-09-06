package com.github.UniHelper.views.notes;

import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.FeatureView;
import com.github.UniHelper.views.notes.editNote.EditNoteView;
import com.github.UniHelper.views.notes.note.previewNote.PreviewNoteView;
import com.github.UniHelper.views.notes.showNotes.ShowNotesView;

public interface NotesView extends FeatureView {

    void setEditNoteView(EditNoteView editNoteView);

    void setShowNotesView(ShowNotesView showNotesView);

    void addOnNoteEditRequestedCommand(Command command);

    void addOnNoteEditFinishedCommand(Command command);

    PreviewNoteView getNoteToEdit();

    int getAccessibleWidth();

    void showEditNoteView();

    void showShowNotesView();
}
