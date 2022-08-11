package com.github.UniHelper.model;

import java.util.ArrayList;

public interface NotesModel {
    void addNote(Note note);

    void deleteNote(Note note);

    void setNotes(ArrayList<Note> notes);

    ArrayList<Note> getAllNotes();

    void updateNote(Note note);
}
