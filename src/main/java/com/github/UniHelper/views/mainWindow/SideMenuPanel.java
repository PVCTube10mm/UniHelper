package com.github.UniHelper.views.mainWindow;

import com.github.UniHelper.views.utils.ActionButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SideMenuPanel extends JPanel {
    private final ArrayList<MenuButton> buttons;

    public SideMenuPanel() {
        super();
        this.setLayout(new GridLayout(0, 1));
        this.setPreferredSize(new Dimension(300, 800));
        this.setBackground(Color.GRAY);
        buttons = new ArrayList<>();
    }

    public ActionButton getButtonByName(String name) {
        return buttons.stream()
                .filter(b -> b.getText().equals(name))
                .findFirst()
                .orElse(null);
    }

    private void addButton(MenuButton button) {
        GridLayout gl = (GridLayout) this.getLayout();
        gl.setRows(gl.getRows() + 1);
        buttons.add(button);
        this.add(button);
    }

    public void addFeatureButton(String featureName) {
        addButton(new MenuButton(featureName));
    }
}
