package com.github.UniHelper.views.utils;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;

public class RadioButtonBundle {

    @Getter
    private ArrayList<RadioButton> buttons;
    @Getter
    private RadioButton activeButton;

    public RadioButtonBundle() {
        buttons = new ArrayList<>();
        initializeButtonsState();
    }

    public RadioButtonBundle(Collection<RadioButton> buttons) {
        setButtons(buttons);
    }

    public void setButtons(Collection<RadioButton> buttons) {
        this.buttons = new ArrayList<>(buttons);
        initializeButtonsState();
    }

    private void initializeButtonsState() {
        activeButton = null;
        for(RadioButton rb : buttons) {
            rb.setActive(false);
            rb.addCommand(() -> onButtonClicked(rb));
        }
    }

    private void onButtonClicked(RadioButton button) {
        for(RadioButton rb : buttons) {
            rb.setActive(rb == button);
        }
    }
}
