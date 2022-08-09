package com.github.UniHelper.views.notes;

import com.github.UniHelper.model.Note;
import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.FeatureView;
import com.github.UniHelper.views.notes.Note.NoteView;

import java.util.ArrayList;

public interface NotesView extends FeatureView {
    void updateNotes(ArrayList<Note> notes);

    void addNoteView(NoteView noteView);

    void addOnNewNoteCommand(Command command);

    void removeNoteView(NoteView noteView);
}
