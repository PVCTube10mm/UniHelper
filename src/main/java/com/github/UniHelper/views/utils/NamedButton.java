package com.github.UniHelper.views.utils;

import com.github.UniHelper.presenters.commands.Command;

import javax.swing.*;

public class NamedButton extends JButton implements ActionButton {
    private Command command;

    public NamedButton(String name) {
        super(name);
        this.setName(name);
        command = () -> {};
    }

    @Override
    public void setCommand(Command command) {
        removeActionListener(e -> this.command.execute());
        this.command = command;
        addActionListener(e -> command.execute());
    }

    @Override
    public void addCommand(Command command) {
        addActionListener(e -> command.execute());
    }
}
