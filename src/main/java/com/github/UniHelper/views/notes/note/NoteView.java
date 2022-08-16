package com.github.UniHelper.views.notes.note;

import com.github.UniHelper.presenters.commands.Command;

import java.awt.*;

public interface NoteView {

    Container getContainer();

    void addOnNoteModifiedCommand(Command command);

    void addOnNoteDeletedCommand(Command command);

    String getNoteText();

    String getNoteTitle();

    void setNoteTitle(String title);

    void setNoteText(String text);
}
