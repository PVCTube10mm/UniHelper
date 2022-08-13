package com.github.UniHelper.model.notes;

import com.github.UniHelper.model.notes.Note;

import java.util.ArrayList;

public interface NotesModel {
    void addNote(Note note);

    void deleteNote(Note note);

    void updateNote(Note note);

    ArrayList<Note> getAllNotes();

    void setNotes(ArrayList<Note> notes);
}
