package com.github.UniHelper.views.notes.showNotes;

import com.github.UniHelper.model.categories.Category;
import com.github.UniHelper.presenters.commands.Command;
import com.github.UniHelper.views.notes.note.previewNote.PreviewNoteView;
import com.github.UniHelper.views.notes.showNotes.contentPanel.ContentPanel;
import com.github.UniHelper.views.notes.showNotes.contentPanel.ContentScrollPane;
import com.github.UniHelper.views.notes.showNotes.optionsPanel.OptionsPanel;
import com.github.UniHelper.views.notes.showNotes.optionsPanel.SearchBarPanel;
import com.github.UniHelper.views.utils.NamedButton;
import com.github.UniHelper.views.utils.categorySelectorPanel.CategorySelectorPanel;
import lombok.Getter;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;

public class DefaultShowNotesView implements ShowNotesView, DocumentListener {

    private final MainPanel mainPanel;
    private final OptionsPanel optionsPanel;
    private final SearchBarPanel searchBarPanel;
    private final CategorySelectorPanel categorySelectorPanel;
    private final ContentPanel contentPanel;
    private final ContentScrollPane contentScrollPane;
    private final NamedButton newNoteButton;
    private final ArrayList<Command> onNewNoteCommands;
    private final ArrayList<Command> onSearchBarUpdateCommands;
    private final ArrayList<Command> onCategoryChangedCommands;
    private final ArrayList<Command> onCategoryModifiedCommands;
    private final ArrayList<Command> onNoteRequestedEditCommands;
    private final ArrayList<Command> onUpdateRequestCommands;
    @Getter
    private PreviewNoteView noteToEdit;

    public DefaultShowNotesView(int accessibleWidth) {
        mainPanel = new MainPanel();
        contentPanel = new ContentPanel();
        optionsPanel = new OptionsPanel();
        searchBarPanel = new SearchBarPanel();
        categorySelectorPanel = new CategorySelectorPanel();
        contentScrollPane = new ContentScrollPane(contentPanel);
        onNewNoteCommands = new ArrayList<>();
        onSearchBarUpdateCommands = new ArrayList<>();
        onCategoryChangedCommands = new ArrayList<>();
        onCategoryModifiedCommands = new ArrayList<>();
        onNoteRequestedEditCommands = new ArrayList<>();
        onUpdateRequestCommands = new ArrayList<>();
        newNoteButton = new NewNoteButton();
        assembleView(accessibleWidth);
    }

    @Override
    public void addNoteView(PreviewNoteView noteView) {
        contentPanel.add(noteView.getContainer(), 0);
        Dimension newNoteSize = noteView.getContainer().getPreferredSize();
        contentPanel.setPreferredSize(calculateNewContentPanelSize(newNoteSize));
        noteView.addOnEditRequestCommand(() -> onNoteEditRequested(noteView));
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
    public void addOnNoteRequestedEditCommand(Command command) {
        onNoteRequestedEditCommands.add(command);
    }

    @Override
    public void addOnCategoryChangedCommand(Command command) {
        onCategoryChangedCommands.add(command);
    }

    @Override
    public void removeNoteView(PreviewNoteView noteView) {
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
        return "show notes";
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

    @Override
    public void requestUpdate() {
        executeOnUpdateRequestCommands();
    }

    @Override
    public void addOnUpdateRequestCommand(Command command) {
        onUpdateRequestCommands.add(command);
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

    private void onNoteEditRequested(PreviewNoteView noteView) {
        noteToEdit = noteView;
        executeOnNoteRequestedEditCommands();
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

    private void executeOnNoteRequestedEditCommands() {
        for (Command c : onNoteRequestedEditCommands) {
            c.execute();
        }
    }

    private void executeOnUpdateRequestCommands() {
        for (Command c : onUpdateRequestCommands) {
            c.execute();
        }
    }
}
