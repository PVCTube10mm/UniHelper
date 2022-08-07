package com.github.UniHelper.views.mainWindow;

import com.github.UniHelper.views.utils.ActionButton;
import com.github.UniHelper.views.utils.NamedButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SideMenuPanel extends JPanel {
    private final ArrayList<NamedButton> buttons;

    public SideMenuPanel() {
        super();
        this.setLayout(new GridLayout(0, 1));
        this.setPreferredSize(new Dimension(300, 800));
        this.setBackground(Color.GRAY);
        buttons = new ArrayList<>();
    }

    public ActionButton getButtonByName(String name) {
        return buttons.stream()
                .filter(b -> b.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void addButton(NamedButton button) {
        GridLayout gl = (GridLayout) this.getLayout();
        gl.setRows(gl.getRows() + 1);
        buttons.add(button);
        this.add(button);
    }
}
