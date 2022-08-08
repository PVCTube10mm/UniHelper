package com.github.UniHelper.views.notes;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.util.Random;

public class NotePanel extends JPanel {
    public NotePanel() {
        super();
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200,300));

        JTextArea text = new JTextArea();
        DefaultCaret caret = (DefaultCaret) text.getCaret();
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        add(new JLabel("Example title"), BorderLayout.NORTH);
        add(text, BorderLayout.CENTER);
        text.setText("Example text");
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        Random r = new Random();
        text.setBackground(new Color(r.nextInt(100), r.nextInt(100), r.nextInt(100)));

        setBorder(new LineBorder(Color.WHITE, 1));
        revalidate();
    }
}
