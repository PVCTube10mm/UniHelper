package com.github.UniHelper.views.notes;

import com.github.UniHelper.views.notes.editNote.DefaultEditNoteView;
import com.github.UniHelper.views.notes.editNote.EditNoteView;
import com.github.UniHelper.views.notes.showNotes.ShowNotesView;

import java.awt.*;

public class DefaultNotesView implements NotesView {

    private final CurrentViewPanel currentViewPanel;

    public DefaultNotesView(ShowNotesView showNotesView) {
        currentViewPanel = new CurrentViewPanel();
        currentViewPanel.addView(showNotesView);
        EditNoteView editNoteView = new DefaultEditNoteView();
    }

    @Override
    public String getFeatureName() {
        return "Notes";
    }

    @Override
    public Container getContainer() {
        return currentViewPanel;
    }
}
