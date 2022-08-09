package com.github.UniHelper.views.notes.Note;

import com.github.UniHelper.presenters.commands.Command;

import java.awt.*;

public interface NoteView {
    Container getContainer();

    void addOnNoteModifiedCommand(Command command);

    void addOnNoteDeletedCommand(Command command);
}
