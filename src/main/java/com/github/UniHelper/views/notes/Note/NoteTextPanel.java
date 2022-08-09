package com.github.UniHelper.views.notes.Note;

import com.github.UniHelper.views.utils.ColorPalette;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelListener;

public class NoteTextPanel extends JPanel {
    JTextArea text;
    JScrollPane scrollPane;
    public NoteTextPanel(){
        super();
        setLayout(new GridLayout());
        text = new JTextArea();
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setBackground(ColorPalette.BLUE);
        text.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,  16));
        text.revalidate();
        scrollPane = new JScrollPane(text);
        scrollPane.getVerticalScrollBar().setBackground(ColorPalette.BLUE);
        MouseWheelListener defaultListener = scrollPane.getMouseWheelListeners()[0];
        scrollPane.removeMouseWheelListener(defaultListener);
        add(scrollPane);
    }
}
