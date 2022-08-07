package com.github.UniHelper.presenters;

import com.github.UniHelper.model.DefaultNotesModel;
import com.github.UniHelper.model.NotesModel;
import com.github.UniHelper.views.mainWindow.MainWindowView;
import com.github.UniHelper.views.notes.DefaultNotesView;
import com.github.UniHelper.views.notes.NotesView;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DefaultMainWindowPresenter implements MainWindowPresenter {
    private final MainWindowView view;

    @Override
    public void onLaunch() {
        initializeFeatureViews();
        addViewCommands();
        view.show();
    }

    private void initializeFeatureViews() {
        NotesView notesView = new DefaultNotesView();
        NotesModel notesModel = new DefaultNotesModel();
        NotesPresenter notesPresenter = new DefaultNotesPresenter(notesView, notesModel);
        view.addFeatureView(notesView);
    }

    private void addViewCommands(){

    }
}
