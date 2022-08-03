package com.github.UniHelper.Presenter;

import com.github.UniHelper.Model.DefaultNotesModel;
import com.github.UniHelper.View.MainWindow.MainWindowView;
import com.github.UniHelper.View.Notes.DefaultNotesView;
import com.github.UniHelper.View.Notes.NotesView;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DefaultMainWindowPresenter implements MainWindowPresenter {
    private final MainWindowView view;

    @Override
    public void onLaunch() {
        initializeFeatureViews();
        view.show();
    }

    private void initializeFeatureViews() {
        NotesView notesView = new DefaultNotesView();
        NotesPresenter notesPresenter = new DefaultNotesPresenter(notesView, new DefaultNotesModel());
        view.addFeatureView(notesView);
        notesPresenter.loadNotes();
    }
}
