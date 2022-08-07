package com.github.UniHelper.views.notes;

import com.github.UniHelper.model.Note;
import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.FeatureView;

import java.util.ArrayList;

public interface NotesView extends FeatureView {
    void updateNotes(ArrayList<Note> notes);

    void addNote(Note note);

    Note getLastOperationSubjectNote();

    void addOnNoteModifiedCommand(Command command);

    void addOnNoteDeletedCommand(Command command);

    void addOnNewNoteCommand(Command command);
}
