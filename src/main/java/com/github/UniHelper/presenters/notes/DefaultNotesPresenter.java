package com.github.UniHelper.presenters.notes;

import com.github.UniHelper.model.categories.CategoriesModel;
import com.github.UniHelper.model.categories.Category;
import com.github.UniHelper.model.categories.DefaultCategoriesModel;
import com.github.UniHelper.model.notes.DefaultNotesModel;
import com.github.UniHelper.model.notes.Note;
import com.github.UniHelper.model.notes.NotesModel;
import com.github.UniHelper.views.notes.NotesView;
import com.github.UniHelper.views.notes.note.DefaultNoteView;
import com.github.UniHelper.views.notes.note.NoteView;

import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;

public class DefaultNotesPresenter implements NotesPresenter {

    private final NotesView view;
    private final NotesModel model;

    public DefaultNotesPresenter(NotesView notesView) {
        view = notesView;
        model = DefaultNotesModel.getInstance();
        loadNotes();
        loadCategories();
        addViewCommands();
    }

    public void loadNotes() {
        ArrayList<Note> notes = model.getAllNotes();
        setViewNotes(notes);
    }

    private void loadCategories() {
        CategoriesModel categoriesModel = DefaultCategoriesModel.getInstance();
        ArrayList<Category> categories = categoriesModel.getAllCategories();
        view.setCategories(categories);
    }

    private void addViewCommands() {
        view.addOnNewNoteCommand(this::addNewNote);
        view.addOnSearchBarUpdateCommand(this::updateSearchedNotes);
        view.addOnCategoryChangedCommand(this::updateSearchedNotes);
        view.addOnCategoryModifiedCommand(this::updateCategory);
    }

    private void setViewNotes(ArrayList<Note> notes) {
        view.clearNotes();
        for (Note n : notes) {
            addNoteToView(n);
        }
    }

    private void addNewNote() {
        Note newNote = new Note("New note", "", Category.NONE.getName());
        model.addNote(newNote);
        addNoteToView(newNote);
    }

    private void updateSearchedNotes() {
        String pattern = view.getSearchBarText();
        Category activeCategory = view.getActiveCategory();
        ArrayList<Note> notesMatchingPattern = getNotesContainingGivenPattern(pattern);
        ArrayList<Note> notesMatchingCategory = getNotesWithGivenCategory(activeCategory);
        ArrayList<Note> notesMatchingPatternAndCategory = getNotesListsProduct(notesMatchingPattern, notesMatchingCategory);
        setViewNotes(notesMatchingPatternAndCategory);
    }

    private void updateCategory() {
        Category modifiedCategory = view.getModifiedCategory();
        CategoriesModel categoriesModel = DefaultCategoriesModel.getInstance();
        categoriesModel.addOrModifyCategory(modifiedCategory);
        ArrayList<Category> newCategories = categoriesModel.getAllCategories();
        view.setCategories(newCategories);
        updateSearchedNotes();
    }

    private void addNoteToView(Note note) {
        NoteView noteView = new DefaultNoteView();
        noteView.addOnNoteDeletedCommand(() -> view.removeNoteView(noteView));
        noteView.setColor(getCategoryColor(note.getCategory()));
        NotePresenter notePresenter = new DefaultNotePresenter(noteView, model, note);
        view.addNoteView(noteView);
    }

    private Color getCategoryColor(String category) {
        CategoriesModel cm = DefaultCategoriesModel.getInstance();
        ArrayList<Category> categories = cm.getAllCategories();
        return categories.stream()
                .filter(c -> c.getName().equals(category))
                .map(Category::getColor)
                .findFirst()
                .orElse(null);
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
        ArrayList<Note> notesWithGivenCategory;
        if (isAnyCategoryActive()) {
            notesWithGivenCategory = new ArrayList<>(model.getAllNotes().stream()
                    .filter(n -> n.getCategory().equals(activeCategory.getName()))
                    .toList());
        } else {
            notesWithGivenCategory = model.getAllNotes();
        }
        return notesWithGivenCategory;
    }

    private ArrayList<Note> getNotesListsProduct(ArrayList<Note> first, ArrayList<Note> second) {
        ArrayList<Note> firstCopy = new ArrayList<>(first);
        ArrayList<Note> secondCopy = new ArrayList<>(second);
        firstCopy.retainAll(secondCopy);
        return firstCopy;
    }

    private boolean isAnyCategoryActive() {
        return view.getActiveCategory() != null;
    }
}
