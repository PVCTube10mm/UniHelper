package com.github.UniHelper.View.Utils;

import com.github.UniHelper.Presenter.Commands.Command;

public interface ActionButton {
    void setCommand(Command command);

    void addCommand(Command command);
}
