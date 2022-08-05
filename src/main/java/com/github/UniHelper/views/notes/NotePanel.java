package com.github.UniHelper.views.notes;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Random;

public class NotePanel extends JPanel {
    public NotePanel() {
        super();
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        JTextArea text = new JTextArea("Example text");
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        Random r = new Random();
        text.setBackground(new Color(r.nextInt(100), r.nextInt(100), r.nextInt(100)));

        add(new JLabel("Example title"), BorderLayout.NORTH);
        add(text, BorderLayout.CENTER);

        setBorder(new LineBorder(Color.WHITE, 1));
        revalidate();
    }
}
