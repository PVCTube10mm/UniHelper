package com.github.UniHelper.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.UUID;

public class DefaultNotesModel implements NotesModel {
    @Getter
    private final String saveFileName;
    ArrayList<Note> notes;

    public DefaultNotesModel() {
        notes = new ArrayList<>();
        saveFileName = "saved_notes.txt";
        load();
    }

    @Override
    public void addNote(Note note) {
        notes.add(note);
        save();
    }

    @Override
    public void deleteNote(Note note) {
        notes.remove(note);
        save();
    }

    @Override
    public Note getNoteByTitle(String title) {
        return notes.stream()
                .filter(n -> n.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateNotes(ArrayList<Note> notes) {
        this.notes = notes;
        save();
    }

    @Override
    public ArrayList<Note> getAllNotes() {
        return (ArrayList) notes.clone();
    }

    @Override
    public void updateNoteById(UUID id, Note note) {
        if(note == null)
            return;
        Note noteToUpdate = notes.stream()
                .filter(n -> n.getId().equals(id))
                .findFirst()
                .orElse(null);
        if(noteToUpdate == null){
            addNote(note);
            note.setId(id);
            return;
        }
        noteToUpdate.setTitle(note.getTitle());
        noteToUpdate.setData(note.getData());
    }

    private void load() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            notes = mapper.readValue(new File(saveFileName), new TypeReference<>() {});
        } catch (IOException e) {
            createNewSaveFile();
        }
    }

    private void save() {
        try (PrintWriter out = new PrintWriter(saveFileName, StandardCharsets.UTF_8)) {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
            String json = writer.writeValueAsString(notes);
            out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createNewSaveFile() {
        try {
            File f = new File(saveFileName);
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
