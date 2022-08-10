package com.github.UniHelper.views.notes;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class NotesOptionsPanel extends JPanel {
    private final JTextField searchTextField;

    public NotesOptionsPanel(){
        super();
        searchTextField = new JTextField();
        searchTextField.setPreferredSize(new Dimension(400,50));
        setPreferredSize(new Dimension(0,100));
        add(searchTextField);
    }

    public void addSearchBarDocumentListener(DocumentListener documentListener){
        searchTextField.getDocument().addDocumentListener(documentListener);
    }

    public String getSearchBarText(){
        return searchTextField.getText();
    }
}
