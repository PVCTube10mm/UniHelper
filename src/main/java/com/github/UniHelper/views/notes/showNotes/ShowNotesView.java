package com.github.UniHelper.views.notes.showNotes;

import com.github.UniHelper.model.categories.Category;
import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.FeatureView;
import com.github.UniHelper.views.notes.note.NoteView;

import java.util.ArrayList;

public interface ShowNotesView extends FeatureView {

    void addNoteView(NoteView noteView);

    void removeNoteView(NoteView noteView);

    void addOnCategoryChangedCommand(Command command);

    void addOnNewNoteCommand(Command command);

    void addOnSearchBarUpdateCommand(Command command);

    void addOnCategoryModifiedCommand(Command command);

    void addOnNoteRequestedEditCommand(Command command);

    NoteView getNoteToEdit();

    String getSearchBarText();

    void clearNotes();

    Category getActiveCategory();

    void setCategories(ArrayList<Category> categories);

    Category getModifiedCategory();
}
