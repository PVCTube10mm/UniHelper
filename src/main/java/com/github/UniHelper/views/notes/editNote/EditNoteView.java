package com.github.UniHelper.views.notes.editNote;

import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.notes.note.NoteView;

public interface EditNoteView extends NoteView {

    void addOnNoteModifiedCommand(Command command);

    String getNoteText();

    String getNoteTitle();
}
