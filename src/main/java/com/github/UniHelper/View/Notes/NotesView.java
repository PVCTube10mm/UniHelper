package com.github.UniHelper.View.Notes;

import com.github.UniHelper.Model.Note;
import com.github.UniHelper.View.FeatureView;

import java.util.ArrayList;

public interface NotesView extends FeatureView {
    void updateNotes(ArrayList<Note> notes);

    void addNote(Note note);
}
