package com.github.UniHelper.Model;

import java.util.ArrayList;

public interface NotesModel {
    void addNote(Note note);

    Note getNoteByTitle(String title);

    ArrayList<Note> getAllNotes();
}
