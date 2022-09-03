package com.github.UniHelper.views.notes.note;

import com.github.UniHelper.presenters.commands.Command;

import java.awt.*;
import java.util.ArrayList;

public class DefaultNoteView implements NoteView {

    private final MainPanel mainPanel;
    private final TitlePanel titlePanel;
    private final TextPanel textPanel;
    private final OptionsPanel optionsPanel;
    private final ArrayList<Command> onNoteDeletedCommands;

    public DefaultNoteView() {
        mainPanel = new MainPanel();
        titlePanel = new TitlePanel();
        textPanel = new TextPanel();
        optionsPanel = new OptionsPanel();
        onNoteDeletedCommands = new ArrayList<>();
        assembleView();
    }

    @Override
    public Container getContainer() {
        return mainPanel;
    }

    @Override
    public void addOnNoteDeletedCommand(Command command) {
        onNoteDeletedCommands.add(command);
    }

    @Override
    public void setNoteTitle(String title) {
        titlePanel.setTitle(title);
    }

    @Override
    public void setNoteText(String text) {
        textPanel.setText(text);
    }

    @Override
    public void setColor(Color color) {
        titlePanel.setTitleBackground(color.darker().darker());
        textPanel.setTextBackground(color);
    }

    public TitlePanel getTitlePanel() {
        return titlePanel;
    }

    public TextPanel getTextPanel() {
        return textPanel;
    }

    private void assembleView() {
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(textPanel, BorderLayout.CENTER);
        mainPanel.add(optionsPanel, BorderLayout.SOUTH);
        optionsPanel.addDeleteButtonCommand(this::executeOnNoteDeletedCommands);
    }

    private void executeOnNoteDeletedCommands() {
        for (Command c : onNoteDeletedCommands) {
            c.execute();
        }
    }
}
