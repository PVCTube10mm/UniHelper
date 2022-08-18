package com.github.UniHelper.presenters.notes;

import com.github.UniHelper.model.categories.Category;
import com.github.UniHelper.model.notes.Note;
import com.github.UniHelper.model.notes.NotesModel;
import com.github.UniHelper.views.notes.note.DefaultNoteView;
import com.github.UniHelper.views.notes.note.NoteView;
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
        view.addOnCategoryChangedCommand(this::updateSearchedNotes);
    }

    private void updateSearchedNotes() {
        String pattern = view.getSearchBarText();
        Category activeCategory = view.getActiveCategory();
        view.clearNotes();
        ArrayList<Note> notesMatchingPattern = getNotesContainingGivenPattern(pattern);
        ArrayList<Note> notesMatchingCategory = getNotesWithGivenCategory(activeCategory);
        ArrayList<Note> notesMatchingPatternAndCategory = getNotesListsProduct(notesMatchingPattern, notesMatchingCategory);
        for (Note n : notesMatchingPatternAndCategory) {
            addNoteToView(n);
        }
    }

    private void addNewNote() {
        Note newNote = new Note("New note", "", Category.NONE.getName());
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

    private ArrayList<Note> getNotesWithGivenCategory(Category activeCategory) {
        return new ArrayList<>(model.getAllNotes().stream()
                .filter(n -> n.getCategory().equals(activeCategory.getName()))
                .toList());
    }

    private ArrayList<Note> getNotesListsProduct(ArrayList<Note> first, ArrayList<Note> second) {
        ArrayList<Note> firstCopy = new ArrayList<>(first);
        ArrayList<Note> secondCopy = new ArrayList<>(second);
        firstCopy.removeAll(second);
        secondCopy.removeAll(first);
        ArrayList<Note> sum = new ArrayList<>();
        sum.addAll(first);
        sum.addAll(secondCopy);
        ArrayList<Note> product = sum;
        product.removeAll(firstCopy);
        product.removeAll(secondCopy);
        return product;
    }
}
