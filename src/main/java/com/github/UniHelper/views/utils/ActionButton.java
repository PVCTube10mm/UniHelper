package com.github.UniHelper.views.utils;

import com.github.UniHelper.views.commands.Command;

public interface ActionButton {
    void setCommand(Command command);

    void addCommand(Command command);
}
