package com.github.UniHelper.views.notes.note;

import com.github.UniHelper.presenters.commands.Command;

import java.awt.*;
import java.util.UUID;

public interface NoteView {

    Container getContainer();

    void addOnNoteDeletedCommand(Command command);

    void setNoteTitle(String title);

    String getNoteTitle();

    void setNoteText(String text);

    String getNoteText();

    void setColor(Color color);

    Color getColor();

    UUID getId();

    void setId(UUID id);

}
