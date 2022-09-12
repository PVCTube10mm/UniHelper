package com.github.UniHelper.views.calendar.calendar.navigation;

import javax.swing.*;

public class NavigationButton extends JButton {

    public NavigationButton(String name) {
        super();
        setText(name);
    }

    public NavigationButton(Icon icon) {
        super();
        setIcon(icon);
    }
}