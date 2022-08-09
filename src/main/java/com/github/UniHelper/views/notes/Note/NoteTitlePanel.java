package com.github.UniHelper.views.notes.Note;

import javax.swing.*;
import java.awt.*;

public class NoteTitlePanel extends JPanel {
    private final JTextPane titleTextPane;

    public NoteTitlePanel(){
        super();
        titleTextPane = new NoteTitleTextPane();
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(0,50));
        add(titleTextPane);
        revalidate();
        SwingUtilities.invokeLater(titleTextPane::requestFocus);
    }
}
