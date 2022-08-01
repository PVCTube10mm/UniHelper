package com.github.UniHelper.GUI;

import com.github.UniHelper.GUI.Features.FeatureView;

import javax.swing.*;
import java.awt.*;

public class ActiveFeaturePanel extends JPanel {

    public ActiveFeaturePanel() {
        super();
        this.setLayout(new CardLayout());
        this.setPreferredSize(new Dimension(800, 300));
    }

    public void addFeatureView(FeatureView featureView) {
        this.add(featureView.getFeatureName(), featureView.getPanel());
    }

    public void showFeature(String featureName) {
        CardLayout cl = (CardLayout) this.getLayout();
        cl.show(this, featureName);
    }
}
