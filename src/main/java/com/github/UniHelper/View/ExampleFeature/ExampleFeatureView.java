package com.github.UniHelper.View.ExampleFeature;

import com.github.UniHelper.View.FeatureView;

import javax.swing.*;
import java.awt.*;

public class ExampleFeatureView implements FeatureView {
    @Override
    public String getFeatureName() {
        return "Example feature";
    }

    @Override
    public JPanel getPanel() {
        return new ExampleFeaturePanel();
    }

    private static class ExampleFeaturePanel extends JPanel {
        public ExampleFeaturePanel() {
            super();
            setBackground(Color.DARK_GRAY);
            setPreferredSize(new Dimension(300, 800));
        }
    }
}
