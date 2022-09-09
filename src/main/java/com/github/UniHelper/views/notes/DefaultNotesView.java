package com.github.UniHelper.views.notes;

import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.notes.editNote.EditNoteView;
import com.github.UniHelper.views.notes.note.previewNote.PreviewNoteView;
import com.github.UniHelper.views.notes.showNotes.ShowNotesView;
import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;

public class DefaultNotesView implements NotesView {

    private final CurrentViewPanel currentViewPanel;
    private final ArrayList<Command> onNoteEditRequestedCommands;
    private final ArrayList<Command> onNoteEditFinishedCommands;
    @Getter
    private final int accessibleWidth;
    private ShowNotesView showNotesView;
    private EditNoteView editNoteView;

    public DefaultNotesView(int accessibleWidth) {
        currentViewPanel = new CurrentViewPanel();
        onNoteEditRequestedCommands = new ArrayList<>();
        onNoteEditFinishedCommands = new ArrayList<>();
        this.accessibleWidth = accessibleWidth;
    }

    @Override
    public String getFeatureName() {
        return "Notes";
    }

    @Override
    public Container getContainer() {
        return currentViewPanel;
    }

    @Override
    public void setEditNoteView(EditNoteView editNoteView) {
        currentViewPanel.remove(editNoteView.getContainer());
        this.editNoteView = editNoteView;
        editNoteView.addOnEditFinishedCommand(this::executeOnNoteEditFinishedCommands);
        currentViewPanel.addView(editNoteView);
    }

    @Override
    public void setShowNotesView(ShowNotesView showNotesView) {
        currentViewPanel.remove(showNotesView.getContainer());
        this.showNotesView = showNotesView;
        currentViewPanel.addView(showNotesView);
        showNotesView.addOnNoteRequestedEditCommand(this::executeOnShowNotesNoteEditRequestedCommands);
    }

    @Override
    public void addOnNoteEditRequestedCommand(Command command) {
        onNoteEditRequestedCommands.add(command);
    }

    @Override
    public void addOnNoteEditFinishedCommand(Command command) {
        onNoteEditFinishedCommands.add(command);
    }

    @Override
    public PreviewNoteView getNoteToEdit() {
        return showNotesView.getNoteToEdit();
    }

    @Override
    public void showEditNoteView() {
        currentViewPanel.chooseView(editNoteView.getFeatureName());
    }

    @Override
    public void showShowNotesView() {
        showNotesView.requestUpdate();
        currentViewPanel.chooseView(showNotesView.getFeatureName());
    }

    private void executeOnShowNotesNoteEditRequestedCommands() {
        for (Command c : onNoteEditRequestedCommands) {
            c.execute();
        }
    }

    private void executeOnNoteEditFinishedCommands() {
        for (Command c : onNoteEditFinishedCommands) {
            c.execute();
        }
    }
}
