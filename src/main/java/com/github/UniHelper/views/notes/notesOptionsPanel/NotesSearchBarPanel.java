package com.github.UniHelper.views.notes.notesOptionsPanel;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class NotesSearchBarPanel extends JPanel {

    private final JTextField searchTextField;

    public NotesSearchBarPanel() {
        super();
        searchTextField = new JTextField();
        searchTextField.setPreferredSize(new Dimension(400, 50));
        setPreferredSize(new Dimension(0, 75));
        add(searchTextField);
    }

    public void addSearchBarDocumentListener(DocumentListener documentListener) {
        searchTextField.getDocument().addDocumentListener(documentListener);
    }

    public String getSearchBarText() {
        return searchTextField.getText();
    }
}
