package org.jivesoftware.sparkimpl.plugin.blucargo.test;

import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonTest extends JFrame {

    public ButtonTest() {
        Button b1 = new Button("Test");
        Button b2 = new Button("Test");
        Button b3 = new Button("Test");
        Button b4 = new Button("Test");

        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());

        p.add(b1,new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        p.add(b2,new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        p.add(b3,new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        p.add(b4,new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

        this.setLayout(new GridBagLayout());
        this.add(p,new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.setBounds(0, 0, 300, 300);
    }

    @Override
    public void setLayout(LayoutManager manager) {
        super.setLayout(manager);
    }

    public static void main(String[] args) {
        ButtonTest b = new ButtonTest();
        b.setVisible(true);

    }
}
