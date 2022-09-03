package com.github.UniHelper.views.notes;

import com.github.UniHelper.model.categories.Category;
import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.notes.note.NoteView;
import com.github.UniHelper.views.notes.notesContentPanel.ContentPanel;
import com.github.UniHelper.views.notes.notesContentPanel.ContentScrollPane;
import com.github.UniHelper.views.notes.notesOptionsPanel.NotesCategorySelectorPanel;
import com.github.UniHelper.views.notes.notesOptionsPanel.OptionsPanel;
import com.github.UniHelper.views.notes.notesOptionsPanel.SearchBarPanel;
import com.github.UniHelper.views.utils.NamedButton;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;

public class DefaultNotesView implements NotesView, DocumentListener {

    private final MainPanel mainPanel;
    private final OptionsPanel optionsPanel;
    private final SearchBarPanel searchBarPanel;
    private final NotesCategorySelectorPanel categorySelectorPanel;
    private final ContentPanel contentPanel;
    private final ContentScrollPane contentScrollPane;
    private final NamedButton newNoteButton;
    private final ArrayList<Command> onNewNoteCommands;
    private final ArrayList<Command> onSearchBarUpdateCommands;
    private final ArrayList<Command> onCategoryChangedCommands;
    private final ArrayList<Command> onCategoryModifiedCommands;

    public DefaultNotesView(int accessibleWidth) {
        mainPanel = new MainPanel();
        contentPanel = new ContentPanel();
        optionsPanel = new OptionsPanel();
        searchBarPanel = new SearchBarPanel();
        categorySelectorPanel = new NotesCategorySelectorPanel();
        contentScrollPane = new ContentScrollPane(contentPanel);
        onNewNoteCommands = new ArrayList<>();
        onSearchBarUpdateCommands = new ArrayList<>();
        onCategoryChangedCommands = new ArrayList<>();
        onCategoryModifiedCommands = new ArrayList<>();
        newNoteButton = new NewNoteButton();
        assembleView(accessibleWidth);
    }

    @Override
    public void addNoteView(NoteView noteView) {
        contentPanel.add(noteView.getContainer(), 0);
        Dimension newNoteSize = noteView.getContainer().getPreferredSize();
        contentPanel.setPreferredSize(calculateNewContentPanelSize(newNoteSize));
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
        contentPanel.remove(noteView.getContainer());
        Dimension newNoteSize = noteView.getContainer().getPreferredSize();
        contentPanel.setPreferredSize(calculateNewContentPanelSize(newNoteSize));
        contentPanel.revalidate();
        contentPanel.repaint();
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
        return mainPanel;
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
        contentPanel.removeAll();
        contentPanel.revalidate();
        contentPanel.repaint();
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
        contentPanel.setPreferredSize(new Dimension(accessibleWidth, 0));
        searchBarPanel.add(newNoteButton);
        optionsPanel.add(searchBarPanel, BorderLayout.NORTH);
        optionsPanel.add(categorySelectorPanel, BorderLayout.CENTER);
        mainPanel.add(optionsPanel, BorderLayout.NORTH);
        mainPanel.add(contentScrollPane, BorderLayout.CENTER);
        newNoteButton.addCommand(this::executeOnNewNoteCommands);
        searchBarPanel.addSearchBarDocumentListener(this);
        categorySelectorPanel.addOnCategoryChangedCommand(this::executeOnCategoryChangedCommands);
        categorySelectorPanel.addOnCategoryModifiedCommand(this::executeOnCategoryModifiedCommands);
    }

    private Dimension calculateNewContentPanelSize(Dimension noteViewSize) {
        int numberOfNotes = contentPanel.getComponents().length;
        double contentWidth = contentPanel.getPreferredSize().getWidth();
        int numberOfColumns = (int) Math.floor(contentWidth / noteViewSize.getWidth());
        int numberOfRows = (int) Math.ceil((double) numberOfNotes / (double) numberOfColumns);
        int verticalPadding = getVerticalContentPadding();
        int newWidth = (int) contentPanel.getPreferredSize().getWidth();
        int newHeight = (int) (numberOfRows * (noteViewSize.getHeight() + verticalPadding) + verticalPadding);
        return new Dimension(newWidth, newHeight);
    }

    private int getVerticalContentPadding() {
        FlowLayout fl = (FlowLayout) contentPanel.getLayout();
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
