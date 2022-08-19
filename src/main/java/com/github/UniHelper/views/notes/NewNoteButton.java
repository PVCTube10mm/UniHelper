package com.github.UniHelper.views.notes;

import com.github.UniHelper.views.utils.NamedButton;

import javax.swing.border.LineBorder;
import java.awt.*;

public class NewNoteButton extends NamedButton {

    public NewNoteButton() {
        super("New note");
        setPreferredSize(new Dimension(150, 65));
        setBackground(new Color(0, 80, 150));
        setBorder(new LineBorder(Color.WHITE, 1));
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
    }
}
