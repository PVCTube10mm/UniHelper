package com.github.UniHelper.views.notes.Note;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class NoteTitleTextPane extends JTextPane {
    private final int maxNumberOfCharackters;

    public NoteTitleTextPane() {
        super();
        maxNumberOfCharackters = 20;
        setPreferredSize(new Dimension(280, 50));
        StyledDocument styledDocument = getStyledDocumentWithLimitedCharacters();
        setStyledDocument(styledDocument);
        setText("New note");
        SimpleAttributeSet align = new SimpleAttributeSet();
        StyleConstants.setAlignment(align, StyleConstants.ALIGN_CENTER);
        styledDocument.setParagraphAttributes(0, styledDocument.getLength(), align, false);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        setBackground(Color.BLACK);
    }

    private StyledDocument getStyledDocumentWithLimitedCharacters() {
        return new DefaultStyledDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if ((getLength() + str.length()) <= maxNumberOfCharackters && (!str.contains("\n")))
                    super.insertString(offs, str, a);
            }
        };
    }
}
