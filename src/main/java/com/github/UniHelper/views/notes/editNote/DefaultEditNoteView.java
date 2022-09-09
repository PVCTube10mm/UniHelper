package com.github.UniHelper.views.notes.editNote;

import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.notes.note.editedNote.EditedNoteView;

import java.awt.*;
import java.util.ArrayList;

public class DefaultEditNoteView implements EditNoteView {

    private final MainPanel mainPanel;
    private final ArrayList<Command> onEditFinishedCommands;

    public DefaultEditNoteView() {
        mainPanel = new MainPanel();
        onEditFinishedCommands = new ArrayList<>();
    }

    @Override
    public void setNoteView(EditedNoteView noteView) {
        mainPanel.add(noteView.getContainer(), BorderLayout.CENTER);
        noteView.addOnNoteSavedCommand(this::executeOnEditFinishedCommands);
        noteView.addOnNoteDeletedCommand(this::executeOnEditFinishedCommands);
    }

    @Override
    public void addOnEditFinishedCommand(Command command) {
        onEditFinishedCommands.add(command);
    }

    @Override
    public String getFeatureName() {
        return "edit note";
    }

    @Override
    public Container getContainer() {
        return mainPanel;
    }

    private void executeOnEditFinishedCommands() {
        for (Command c : onEditFinishedCommands) {
            c.execute();
        }
    }
}
