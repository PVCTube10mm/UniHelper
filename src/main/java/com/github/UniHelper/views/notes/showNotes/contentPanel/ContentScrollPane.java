package com.github.UniHelper.views.notes.showNotes.contentPanel;

import javax.swing.*;

public class ContentScrollPane extends JScrollPane {

    public ContentScrollPane(JPanel notesPanel) {
        super(notesPanel);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        getVerticalScrollBar().setUnitIncrement(16);
    }
}
