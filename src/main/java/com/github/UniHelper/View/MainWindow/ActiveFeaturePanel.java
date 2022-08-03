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

    public void addFeaturePanel(FeatureView featureView) {
        this.add(featureView.getPanel());
    }

    public void chooseView(String featureName) {
        CardLayout cl = (CardLayout) this.getLayout();
        cl.show(this, featureName);
    }
}
