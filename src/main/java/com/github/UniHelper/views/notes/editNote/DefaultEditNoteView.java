package com.github.UniHelper.views.notes.editNote;

import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.notes.note.*;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;

public class DefaultEditNoteView extends DefaultNoteView implements EditNoteView, DocumentListener {

    private final ArrayList<Command> onNoteModifiedCommands;

    public DefaultEditNoteView() {
        super();
        onNoteModifiedCommands = new ArrayList<>();
        this.getTitlePanel().addTitleDocumentListener(this);
        this.getTextPanel().addTextDocumentListener(this);
    }

    @Override
    public void addOnNoteModifiedCommand(Command command) {
        onNoteModifiedCommands.add(command);
    }

    @Override
    public String getNoteText() {
        return this.getTextPanel().getText();
    }

    @Override
    public String getNoteTitle() {
        return this.getTitlePanel().getTitle();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        changedUpdate(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        changedUpdate(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        executeOnNoteModifiedCommands();
    }

    private void executeOnNoteModifiedCommands() {
        for (Command c : onNoteModifiedCommands) {
            c.execute();
        }
    }
}
