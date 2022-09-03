package com.github.UniHelper.views.notes.note;

import com.github.UniHelper.presenters.commands.Command;

import java.awt.*;

public interface NoteView {

    Container getContainer();

    void addOnNoteDeletedCommand(Command command);

    void setNoteTitle(String title);

    void setNoteText(String text);

    void setColor(Color color);
}
