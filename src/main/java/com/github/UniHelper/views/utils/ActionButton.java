package com.github.UniHelper.views.utils;

import com.github.UniHelper.presenters.commands.Command;

public interface ActionButton {
    void setCommand(Command command);

    void addCommand(Command command);
}
