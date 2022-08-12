package com.github.UniHelper.views.utils;

import com.github.UniHelper.presenters.commands.Command;

import javax.swing.*;
import java.util.ArrayList;

public class NamedButton extends JButton implements ActionButton {
    private ArrayList<Command> commands;

    public NamedButton(String name) {
        super(name);
        this.setName(name);
        commands = new ArrayList<>();
        addActionListener(e -> executeCommands());
    }

    @Override
    public void setCommand(Command command) {
        commands = new ArrayList<>();
        commands.add(command);
    }

    @Override
    public void addCommand(Command command) {
        commands.add(command);
    }

    private void executeCommands() {
        for (Command c : commands)
            c.execute();
    }
}
