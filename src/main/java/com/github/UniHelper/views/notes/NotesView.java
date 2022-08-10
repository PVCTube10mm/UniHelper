package com.github.UniHelper.views.notes;

import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.FeatureView;
import com.github.UniHelper.views.notes.Note.NoteView;

public interface NotesView extends FeatureView {
    void addNoteView(NoteView noteView);

    void addOnNewNoteCommand(Command command);

    void addOnSearchBarUpdateCommand(Command command);

    void removeNoteView(NoteView noteView);

    String getSearchBarText();

    void clearNotes();
}
