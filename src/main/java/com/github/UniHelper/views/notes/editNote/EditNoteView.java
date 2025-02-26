package com.github.UniHelper.views.notes.editNote;

import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.FeatureView;
import com.github.UniHelper.views.notes.note.editedNote.EditedNoteView;

public interface EditNoteView extends FeatureView {

    void setNoteView(EditedNoteView noteView);

    void addOnEditFinishedCommand(Command command);
}
