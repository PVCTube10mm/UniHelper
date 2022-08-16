package com.github.UniHelper.views.notes;

import javax.swing.*;

public class NotesContentScrollPane extends JScrollPane {

    public NotesContentScrollPane(JPanel notesPanel) {
        super(notesPanel);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        getVerticalScrollBar().setUnitIncrement(16);
    }
}
