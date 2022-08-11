package com.github.UniHelper.model;

import java.util.ArrayList;
import java.util.UUID;

public interface NotesModel {
    void addNote(Note note);

    void deleteNote(Note note);

    Note getNoteByTitle(String title);

    void updateNotes(ArrayList<Note> notes);

    ArrayList<Note> getAllNotes();

    void updateNoteById(UUID id, Note note);
}
