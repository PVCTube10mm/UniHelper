package com.github.UniHelper.GUI;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    public MenuPanel() {
        super();
        this.setLayout(new GridLayout(0, 1));
        this.setPreferredSize(new Dimension(300, 800));
        this.setBackground(Color.GRAY);
    }

    public void addFeatureButton(JButton button) {
        GridLayout gl = (GridLayout) this.getLayout();
        gl.setRows(gl.getRows() + 1);
        this.add(button);
    }
}
