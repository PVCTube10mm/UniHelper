package com.github.UniHelper.views.notes.note.editedNote;

import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.notes.note.NoteView;

public interface EditedNoteView extends NoteView {

    void addOnNoteModifiedCommand(Command command);

    void addOnNoteSavedCommand(Command command);
}
