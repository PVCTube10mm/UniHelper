package com.github.UniHelper.views.calendar;

import com.github.UniHelper.views.calendar.calendar.CalendarPanel;

import javax.swing.*;
import java.awt.*;

public class DefaultCalendarView implements CalendarView {

    private final MainPanel mainPanel;
    private final CalendarPanel calendarPanel;
    private final JButton categorySelectionPanel;
    private final JButton dayDetailPanel;
    private final JButton optionsPanel;
    private final GridBagConstraints gridBagConstraints;

    public DefaultCalendarView() {
        mainPanel = new MainPanel();
        categorySelectionPanel = new JButton("Category Selection");
        calendarPanel = new CalendarPanel();
        dayDetailPanel = new JButton("Day Detail");
        optionsPanel = new JButton("Options");

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        assembleView();
    }

    @Override
    public String getFeatureName() {
        return "Calendar";
    }

    @Override
    public Container getContainer() {
        return mainPanel;
    }

    @Override
    public CalendarPanel getCalendarPanel() {
        return calendarPanel;
    }

    private void assembleView() {
        assembleCategoriesPanel();
        assembleMonthPanel();
        assembleDayDetailPanel();
        assembleOptionsPanel();
    }

    private void assembleCategoriesPanel() {
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.weighty = 0.1;
        addGB(categorySelectionPanel, gridBagConstraints);
    }

    private void assembleMonthPanel() {
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 0.9;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 5;
        addGB(calendarPanel, gridBagConstraints);
    }

    private void assembleDayDetailPanel() {
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.7;
        gridBagConstraints.weighty = 0.9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 4;
        addGB(dayDetailPanel, gridBagConstraints);
    }

    private void assembleOptionsPanel() {
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 1;
        addGB(optionsPanel, gridBagConstraints);
    }

    private void addGB(Component component, GridBagConstraints gridBagConstraints) {
        mainPanel.add(component, gridBagConstraints);
    }

}
