package com.github.UniHelper.views.calendar.calendar.navigation;

import javax.swing.*;
import java.awt.*;

public class MonthNavigationPanel extends JPanel {

    private final NavigationButton prevButton;
    private final NavigationButton nextButton;
    private final MonthAndYearHeading monthAndYearHeading;
    private final GridBagConstraints gridBagConstraints;

    public MonthNavigationPanel() {
        super();
        prevButton = new NavigationButton("Prev");
        nextButton = new NavigationButton("Next");
        monthAndYearHeading = new MonthAndYearHeading();
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1;

        setLayout(new GridBagLayout());
        setBackground(new Color(0, 51, 102));
        assembleView();
    }

    private void assembleView() {
        assembleNavigationButtons();
        assembleMonthAndYearHeading();
    }

    private void assembleNavigationButtons() {
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.add(prevButton, gridBagConstraints);

        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        this.add(nextButton, gridBagConstraints);
    }

    private void assembleMonthAndYearHeading() {
        gridBagConstraints.weightx = 5;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        this.add(monthAndYearHeading, gridBagConstraints);
    }

}
