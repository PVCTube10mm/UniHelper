package com.github.UniHelper.views.notes;

import javax.swing.*;
import java.awt.*;

public class NotesPanel extends JPanel {
    public NotesPanel() {
        super();
        setBackground(Color.DARK_GRAY.darker());
        setPreferredSize(new Dimension(0, 0));
        setLayout(new FlowLayout(FlowLayout.LEFT));
    }
}
