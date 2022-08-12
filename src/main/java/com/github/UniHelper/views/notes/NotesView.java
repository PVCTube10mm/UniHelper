package com.github.UniHelper.views.notes;

import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.FeatureView;
import com.github.UniHelper.views.notes.Note.NoteView;

public interface NotesView extends FeatureView {
    void addNoteView(NoteView noteView);

    void removeNoteView(NoteView noteView);

    void addOnNewNoteCommand(Command command);

    void addOnSearchBarUpdateCommand(Command command);

    String getSearchBarText();

    void clearNotes();
}
