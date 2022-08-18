package com.github.UniHelper.views.notes;

import com.github.UniHelper.model.categories.Category;
import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.FeatureView;
import com.github.UniHelper.views.notes.note.NoteView;

public interface NotesView extends FeatureView {

    void addNoteView(NoteView noteView);

    void addOnCategoryChangedCommand(Command command);

    void removeNoteView(NoteView noteView);

    void addOnNewNoteCommand(Command command);

    void addOnSearchBarUpdateCommand(Command command);

    String getSearchBarText();

    void clearNotes();

    Category getActiveCategory();
}
