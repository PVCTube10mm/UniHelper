package com.github.UniHelper.views.notes;

import com.github.UniHelper.model.categories.Category;
import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.notes.note.NoteView;
import com.github.UniHelper.views.notes.notesContentPanel.NotesContentPanel;
import com.github.UniHelper.views.notes.notesContentPanel.NotesContentScrollPane;
import com.github.UniHelper.views.notes.notesOptionsPanel.NotesCategorySelectorPanel;
import com.github.UniHelper.views.notes.notesOptionsPanel.NotesOptionsPanel;
import com.github.UniHelper.views.notes.notesOptionsPanel.NotesSearchBarPanel;
import com.github.UniHelper.views.utils.NamedButton;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;

public class DefaultNotesView implements NotesView, DocumentListener {

    private final NotesMainPanel notesMainPanel;
    private final NotesOptionsPanel notesOptionsPanel;
    private final NotesSearchBarPanel searchBarPanel;
    private final NotesCategorySelectorPanel categorySelectorPanel;
    private final NotesContentPanel notesContentPanel;
    private final NotesContentScrollPane notesContentScrollPane;
    private final NamedButton newNoteButton;
    private final ArrayList<Command> onNewNoteCommands;
    private final ArrayList<Command> onSearchBarUpdateCommands;
    private final ArrayList<Command> onCategoryChangedCommands;
    private final ArrayList<Command> onCategoryModifiedCommands;

    public DefaultNotesView(int accessibleWidth) {
        notesMainPanel = new NotesMainPanel();
        notesContentPanel = new NotesContentPanel();
        notesOptionsPanel = new NotesOptionsPanel();
        searchBarPanel = new NotesSearchBarPanel();
        categorySelectorPanel = new NotesCategorySelectorPanel();
        notesContentScrollPane = new NotesContentScrollPane(notesContentPanel);
        onNewNoteCommands = new ArrayList<>();
        onSearchBarUpdateCommands = new ArrayList<>();
        onCategoryChangedCommands = new ArrayList<>();
        onCategoryModifiedCommands = new ArrayList<>();
        newNoteButton = new NewNoteButton();
        assembleView(accessibleWidth);
    }

    @Override
    public void addNoteView(NoteView noteView) {
        notesContentPanel.add(noteView.getContainer(), 0);
        Dimension newNoteSize = noteView.getContainer().getPreferredSize();
        notesContentPanel.setPreferredSize(calculateNewContentPanelSize(newNoteSize));
    }

    @Override
    public void addOnNewNoteCommand(Command command) {
        onNewNoteCommands.add(command);
    }

    @Override
    public void addOnSearchBarUpdateCommand(Command command) {
        onSearchBarUpdateCommands.add(command);
    }

    @Override
    public void addOnCategoryModifiedCommand(Command command) {
        onCategoryModifiedCommands.add(command);
    }

    @Override
    public void addOnCategoryChangedCommand(Command command) {
        onCategoryChangedCommands.add(command);
    }

    @Override
    public void removeNoteView(NoteView noteView) {
        notesContentPanel.remove(noteView.getContainer());
        Dimension newNoteSize = noteView.getContainer().getPreferredSize();
        notesContentPanel.setPreferredSize(calculateNewContentPanelSize(newNoteSize));
        notesContentPanel.revalidate();
        notesContentPanel.repaint();
    }

    @Override
    public String getSearchBarText() {
        return searchBarPanel.getSearchBarText();
    }

    @Override
    public String getFeatureName() {
        return "Notes";
    }

    @Override
    public Container getContainer() {
        return notesMainPanel;
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
        executeOnSearchBarUpdateCommands();
    }

    @Override
    public void clearNotes() {
        notesContentPanel.removeAll();
        notesContentPanel.revalidate();
        notesContentPanel.repaint();
    }

    @Override
    public Category getActiveCategory() {
        return categorySelectorPanel.getActiveCategory();
    }

    @Override
    public void setCategories(ArrayList<Category> categories) {
        categorySelectorPanel.setCategories(categories);
    }

    @Override
    public Category getModifiedCategory() {
        return categorySelectorPanel.getActiveCategory();
    }

    private void assembleView(int accessibleWidth) {
        notesContentPanel.setPreferredSize(new Dimension(accessibleWidth, 0));
        searchBarPanel.add(newNoteButton);
        notesOptionsPanel.add(searchBarPanel, BorderLayout.NORTH);
        notesOptionsPanel.add(categorySelectorPanel, BorderLayout.CENTER);
        notesMainPanel.add(notesOptionsPanel, BorderLayout.NORTH);
        notesMainPanel.add(notesContentScrollPane, BorderLayout.CENTER);
        newNoteButton.addCommand(this::executeOnNewNoteCommands);
        searchBarPanel.addSearchBarDocumentListener(this);
        categorySelectorPanel.addOnCategoryChangedCommand(this::executeOnCategoryChangedCommands);
        categorySelectorPanel.addOnCategoryModifiedCommand(this::executeOnCategoryModifiedCommands);
    }

    private Dimension calculateNewContentPanelSize(Dimension noteViewSize) {
        int numberOfNotes = notesContentPanel.getComponents().length;
        double contentWidth = notesContentPanel.getPreferredSize().getWidth();
        int numberOfColumns = (int) Math.floor(contentWidth / noteViewSize.getWidth());
        int numberOfRows = (int) Math.ceil((double) numberOfNotes / (double) numberOfColumns);
        int verticalPadding = getVerticalContentPadding();
        int newWidth = (int) notesContentPanel.getPreferredSize().getWidth();
        int newHeight = (int) (numberOfRows * (noteViewSize.getHeight() + verticalPadding) + verticalPadding);
        return new Dimension(newWidth, newHeight);
    }

    private int getVerticalContentPadding() {
        FlowLayout fl = (FlowLayout) notesContentPanel.getLayout();
        return fl.getVgap();
    }

    private void executeOnNewNoteCommands() {
        for (Command c : onNewNoteCommands) {
            c.execute();
        }
    }

    private void executeOnSearchBarUpdateCommands() {
        for (Command c : onSearchBarUpdateCommands) {
            c.execute();
        }
    }

    private void executeOnCategoryChangedCommands() {
        for (Command c : onCategoryChangedCommands) {
            c.execute();
        }
    }

    private void executeOnCategoryModifiedCommands() {
        for (Command c : onCategoryModifiedCommands) {
            c.execute();
        }
    }
}
