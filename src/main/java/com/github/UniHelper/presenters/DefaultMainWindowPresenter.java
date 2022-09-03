package com.github.UniHelper.presenters;

import com.github.UniHelper.model.notes.DefaultNotesModel;
import com.github.UniHelper.model.notes.NotesModel;
import com.github.UniHelper.model.timetable.DefaultTimetableModel;
import com.github.UniHelper.model.timetable.TimetableModel;
import com.github.UniHelper.presenters.notes.DefaultNotesPresenter;
import com.github.UniHelper.presenters.notes.NotesPresenter;
import com.github.UniHelper.presenters.timetable.DefaultTimetablePresenter;
import com.github.UniHelper.presenters.timetable.TimetablePresenter;
import com.github.UniHelper.views.mainWindow.MainWindowView;
import com.github.UniHelper.views.notes.DefaultNotesView;
import com.github.UniHelper.views.notes.NotesView;
import com.github.UniHelper.views.timetable.DefaultTimetableView;
import com.github.UniHelper.views.timetable.TimetableView;
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
        initializeNotes();
        initializeTimetable();
    }

    private void initializeNotes() {
        int accessibleWidth = view.getFeaturePanelSize().width;
        NotesView notesView = new DefaultNotesView(accessibleWidth);
        NotesPresenter notesPresenter = new DefaultNotesPresenter(notesView);
        view.addFeatureView(notesView);
    }

    private void initializeTimetable() {
        TimetableView timetableView = new DefaultTimetableView();
        TimetableModel timetableModel = new DefaultTimetableModel();
        TimetablePresenter timetablePresenter = new DefaultTimetablePresenter(timetableView, timetableModel);
        view.addFeatureView(timetableView);
    }
}
