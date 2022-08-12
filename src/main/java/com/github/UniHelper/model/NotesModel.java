package com.github.UniHelper.model;

import java.util.ArrayList;

public interface NotesModel {
    void addNote(Note note);

    void deleteNote(Note note);

    void updateNote(Note note);

    ArrayList<Note> getAllNotes();

    void setNotes(ArrayList<Note> notes);
}
