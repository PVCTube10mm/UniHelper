package com.github.UniHelper.views.notes.Note;

import com.github.UniHelper.views.utils.ColorPalette;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelListener;

public class NoteTextPanel extends JPanel {
    private final JTextArea text;
    private final JScrollPane scrollPane;

    public NoteTextPanel() {
        super();
        setLayout(new GridLayout());
        text = new JTextArea();
        scrollPane = new JScrollPane(text);
        add(scrollPane);
    }

    private void setTextLook() {
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setBackground(ColorPalette.BLUE);
        text.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
    }

    private void setScrollPaneLook() {
        scrollPane.getVerticalScrollBar().setBackground(ColorPalette.BLUE);
        MouseWheelListener defaultListener = scrollPane.getMouseWheelListeners()[0];
        scrollPane.removeMouseWheelListener(defaultListener);
    }
}
