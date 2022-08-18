package com.github.UniHelper.views.utils;

import com.github.UniHelper.presenters.commands.Command;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;

public class RadioButtonBundle {

    @Getter
    private ArrayList<RadioButton> buttons;
    @Getter
    private RadioButton activeButton;
    private final ArrayList<Command> onCategoryChangedCommands;


    public RadioButtonBundle() {
        buttons = new ArrayList<>();
        onCategoryChangedCommands = new ArrayList<>();
        initializeButtonsState();
    }

    public RadioButtonBundle(Collection<RadioButton> buttons) {
        onCategoryChangedCommands = new ArrayList<>();
        setButtons(buttons);
    }

    public void setButtons(Collection<RadioButton> buttons) {
        this.buttons = new ArrayList<>(buttons);
        initializeButtonsState();
    }

    public void addOnCategoryChangedCommand(Command command) {
        onCategoryChangedCommands.add(command);
    }

    private void initializeButtonsState() {
        activeButton = null;
        for(RadioButton rb : buttons) {
            rb.setActive(false);
            rb.addCommand(() -> onButtonClicked(rb));
        }
    }

    private void onButtonClicked(RadioButton button) {
        button.setActive(true);
        activeButton = button;
        for(RadioButton rb : buttons) {
            if(rb != button) {
                rb.setActive(false);
            }
        }
        executeOnCategoryChangedCommands();
    }

    private void executeOnCategoryChangedCommands() {
        for(Command c : onCategoryChangedCommands) {
            c.execute();
        }
    }
}
