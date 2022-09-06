package com.github.UniHelper.views.notes;

import com.github.UniHelper.views.FeatureView;

import javax.swing.*;
import java.awt.*;

public class CurrentViewPanel extends JPanel {

    public CurrentViewPanel() {
        super();
        setLayout(new CardLayout());
    }

    public void addView(FeatureView view) {
        add(view.getContainer(), view.getFeatureName());
    }

    public void chooseView(String featureName) {
        CardLayout cl = (CardLayout) this.getLayout();
        cl.show(this, featureName);
    }
}
