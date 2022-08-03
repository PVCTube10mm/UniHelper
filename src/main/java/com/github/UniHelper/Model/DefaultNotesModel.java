package com.github.UniHelper.Model;

import java.util.ArrayList;

public class DefaultNotesModel implements NotesModel {
    ArrayList<Note> notes;

    public DefaultNotesModel() {
        notes = new ArrayList<>();
        load();
    }

    @Override
    public void addNote(Note note) {
        notes.add(note);
    }

    @Override
    public Note getNoteByTitle(String title) {
        return notes.stream()
                .filter(n -> n.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    @Override
    public ArrayList<Note> getAllNotes() {
        return notes;
    }

    private void update() {
    }

    private void load() {
        addNote(new Note("title", "data"));
        addNote(new Note("title2", "data2"));
        addNote(new Note("title3", "data3"));
    }

    private void save() {
    }
}
