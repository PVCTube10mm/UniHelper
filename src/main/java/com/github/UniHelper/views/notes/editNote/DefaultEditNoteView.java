package com.github.UniHelper.views.notes.editNote;

import com.github.UniHelper.views.notes.note.NoteView;

import java.awt.*;

public class DefaultEditNoteView implements EditNoteView {

    private final MainPanel mainPanel;

    public DefaultEditNoteView() {
        mainPanel = new MainPanel();
    }

    public void setNoteView(NoteView noteView) {
        mainPanel.add(noteView.getContainer(), BorderLayout.CENTER);
    }

    @Override
    public String getFeatureName() {
        return "edit note";
    }

    @Override
    public Container getContainer() {
        return mainPanel;
    }
}
