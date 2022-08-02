package com.github.UniHelper.View.MainWindow;

import com.github.UniHelper.View.FeatureView;

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

    public void chooseView(String featureName) {
        CardLayout cl = (CardLayout) this.getLayout();
        cl.show(this, featureName);
    }
}
