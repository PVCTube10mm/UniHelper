package com.github.UniHelper.views.mainWindow;

import com.github.UniHelper.views.utils.NamedButton;

import javax.swing.*;
import java.awt.*;

public class SideMenuPanel extends JPanel {
    public SideMenuPanel() {
        super();
        this.setLayout(new GridLayout(0, 1));
        this.setPreferredSize(new Dimension(300, 800));
        this.setBackground(Color.GRAY);
    }

    public void addButton(NamedButton button) {
        GridLayout gl = (GridLayout) this.getLayout();
        gl.setRows(gl.getRows() + 1);
        this.add(button);
    }
}
