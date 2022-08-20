package com.github.UniHelper.views.utils;

import com.github.UniHelper.presenters.commands.Command;
import lombok.Getter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;

public class RadioButton extends JPanel {

    @Getter
    private boolean active;
    private Color defaultColor;
    private Color hoveredColor;
    private Color clickedColor;
    private final ArrayList<Command> commands;
    private final HashSet<RadioButtonListener> listeners;
    private boolean isBackgroundChangeInternal;

    public RadioButton() {
        super();
        active = false;
        commands = new ArrayList<>();
        listeners = new HashSet<>();
        isBackgroundChangeInternal = false;
        initializeMouseListener();
        initializeLook();
    }

    public void setActive(boolean isActive) {
        this.active = isActive;
        if (isActive) {
            setBackgroundOnMouseEvent(clickedColor);
        } else {
            setBackgroundOnMouseEvent(defaultColor);
        }
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void addListener(RadioButtonListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for(RadioButtonListener rbl : listeners) {
            if(rbl == null) {
                listeners.remove(null);
            }
            else {
                rbl.handleButtonEvent(this);
            }
        }
    }

    @Override
    public void setBackground(Color color) {
        super.setBackground(color);
        if (!isBackgroundChangeInternal) {
            defaultColor = color;
            hoveredColor = color.brighter();
            clickedColor = color.brighter().brighter();
        }
    }

    private void initializeLook() {
        setBorder(new LineBorder(Color.BLACK, 1));
        setBackground(Color.DARK_GRAY.darker().darker());
    }

    private void executeCommands() {
        for (Command c : commands) {
            c.execute();
        }
    }

    private void initializeMouseListener() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(final MouseEvent e) {
                setActive(true);
                executeCommands();
                notifyListeners();
                setBackgroundOnMouseEvent(clickedColor);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!active) {
                    setBackgroundOnMouseEvent(hoveredColor);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!active) {
                    setBackgroundOnMouseEvent(defaultColor);
                }
            }
        });
    }

    private void setBackgroundOnMouseEvent(Color color) {
        isBackgroundChangeInternal = true;
        setBackground(color);
        isBackgroundChangeInternal = false;
    }
}
