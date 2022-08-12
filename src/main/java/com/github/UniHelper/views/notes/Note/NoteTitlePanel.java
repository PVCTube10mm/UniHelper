package com.github.UniHelper.views.notes.Note;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class NoteTitlePanel extends JPanel {
    private final JTextPane titleTextPane;

    public NoteTitlePanel() {
        super();
        titleTextPane = new NoteTitleTextPane();
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(0, 50));
        add(titleTextPane);
    }

    public void setTitle(String title) {
        titleTextPane.setText(title);
    }

    public String getTitle() {
        return titleTextPane.getText();
    }

    public void addTitleDocumentListener(DocumentListener documentListener) {
        titleTextPane.getDocument().addDocumentListener(documentListener);
    }
}
