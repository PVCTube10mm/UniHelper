package com.github.UniHelper.views.notes;

import javax.swing.*;
import java.awt.*;

public class NotesContentPanel extends JPanel {
    public NotesContentPanel(){
        super();
        setBackground(Color.DARK_GRAY.darker());
        setPreferredSize(new Dimension(800, 0));
        setLayout(new FlowLayout(FlowLayout.LEFT));
    }
}
