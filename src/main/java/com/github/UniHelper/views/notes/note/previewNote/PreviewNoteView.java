package com.github.UniHelper.views.notes.note.previewNote;

import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.notes.note.NoteView;

import java.awt.*;

public interface PreviewNoteView extends NoteView {

    void addOnEditRequestCommand(Command command);

    Color getColor();
}
