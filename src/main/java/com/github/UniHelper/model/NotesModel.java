package com.github.UniHelper.model;

import java.util.ArrayList;

public interface NotesModel {
    void addNote(Note note);

    Note getNoteByTitle(String title);

    void updateNotes(ArrayList<Note> notes);

    ArrayList<Note> getAllNotes();
}
