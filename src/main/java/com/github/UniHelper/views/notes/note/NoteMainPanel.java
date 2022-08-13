package com.github.UniHelper.views.notes.note;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class NoteMainPanel extends JPanel {
    public NoteMainPanel() {
        super();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 300));
        setBorder(new LineBorder(Color.WHITE, 1));
    }
}
