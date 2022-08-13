package com.github.UniHelper.views.mainWindow;

import com.github.UniHelper.views.FeatureView;

import javax.swing.*;
import java.awt.*;

public class ActiveFeaturePanel extends JPanel {
    public ActiveFeaturePanel() {
        super();
        this.setLayout(new CardLayout());
    }

    public void addFeaturePanel(FeatureView featureView) {
        this.add(featureView.getContainer(), featureView.getFeatureName());
    }

    public void chooseView(String featureName) {
        CardLayout cl = (CardLayout) this.getLayout();
        cl.show(this, featureName);
    }
}
