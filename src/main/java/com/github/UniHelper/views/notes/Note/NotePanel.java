package com.github.UniHelper.views.notes.Note;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class NotePanel extends JPanel {
    public NotePanel() {
        super();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300,300));
        setBorder(new LineBorder(Color.WHITE, 1));
    }
}
