package com.github.UniHelper.views.notes.note;

import com.github.UniHelper.presenters.commands.Command;

import java.awt.*;

public interface NoteView {

    Container getContainer();

    void addOnNoteDeletedCommand(Command command);

    void setNoteTitle(String title);

    String getNoteTitle();

    void setNoteText(String text);

    String getNoteText();

    void setColor(Color color);
}
