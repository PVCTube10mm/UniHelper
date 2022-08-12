package com.github.UniHelper.presenters;

import com.github.UniHelper.model.Note;
import com.github.UniHelper.model.NotesModel;
import com.github.UniHelper.views.notes.Note.DefaultNoteView;
import com.github.UniHelper.views.notes.Note.NoteView;
import com.github.UniHelper.views.notes.NotesView;

import java.util.ArrayList;
import java.util.Locale;

public class DefaultNotesPresenter implements NotesPresenter {
    private final NotesView view;
    private final NotesModel model;

    public DefaultNotesPresenter(NotesView notesView, NotesModel notesModel) {
        view = notesView;
        model = notesModel;
        addViewCommands();
        loadNotes();
    }

    @Override
    public void loadNotes() {
        ArrayList<Note> notes = model.getAllNotes();
        for (Note n : notes) {
            NoteView noteView = new DefaultNoteView();
            noteView.addOnNoteDeletedCommand(() -> view.removeNoteView(noteView));
            NotePresenter notePresenter = new DefaultNotePresenter(noteView, model, n);
            view.addNoteView(noteView);
        }
    }

    private void addViewCommands() {
        view.addOnSearchBarUpdateCommand(this::updateSearchedNotes);
        view.addOnNewNoteCommand(this::addNewNote);
    }

    private void updateSearchedNotes() {
        String pattern = view.getSearchBarText();
        view.clearNotes();
        ArrayList<Note> matchingNotes = getNotesContainingGivenPattern(pattern);
        for (Note n : matchingNotes) {
            addNoteToView(n);
        }
    }

    private void addNewNote() {
        Note newNote = new Note("New note", "");
        model.addNote(newNote);
        addNoteToView(newNote);
    }

    private void addNoteToView(Note note) {
        NoteView noteView = new DefaultNoteView();
        noteView.addOnNoteDeletedCommand(() -> view.removeNoteView(noteView));
        NotePresenter notePresenter = new DefaultNotePresenter(noteView, model, note);
        view.addNoteView(noteView);
    }

    private ArrayList<Note> getNotesContainingGivenPattern(String pattern) {
        ArrayList<Note> matchingByTitle = getNotesWithTitlesContainingGivenPattern(pattern);
        ArrayList<Note> matchingByText = getNotesWithTextsContainingGivenPattern(pattern);
        return mergeInOrderWithoutRepeatingNotes(matchingByTitle, matchingByText);
    }

    private ArrayList<Note> getNotesWithTitlesContainingGivenPattern(String pattern) {
        ArrayList<Note> notes = model.getAllNotes();
        return new ArrayList<>(notes.stream()
                .filter(n -> n.getTitle().toLowerCase(Locale.ROOT).contains(pattern.toLowerCase(Locale.ROOT)))
                .toList());
    }

    private ArrayList<Note> getNotesWithTextsContainingGivenPattern(String pattern) {
        ArrayList<Note> notes = model.getAllNotes();
        return new ArrayList<>(notes.stream()
                .filter(n -> n.getText().toLowerCase(Locale.ROOT).contains(pattern.toLowerCase(Locale.ROOT)))
                .toList());
    }

    private ArrayList<Note> mergeInOrderWithoutRepeatingNotes(ArrayList<Note> first, ArrayList<Note> second) {
        ArrayList<Note> firstCopy = new ArrayList<>(first);
        ArrayList<Note> secondCopy = new ArrayList<>(second);
        secondCopy.removeAll(firstCopy);
        firstCopy.addAll(secondCopy);
        return firstCopy;
    }
}
