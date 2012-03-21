/**
 * $Revision: $
 * $Date: $
 *
 * Copyright (C) 2006 Jive Software. All rights reserved.
 *
 * This software is published under the terms of the GNU Lesser Public License (LGPL),
 * a copy of which is included in this distribution.
 */

package org.jivesoftware.spark.ui;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import org.jivesoftware.Spark;
import org.jivesoftware.resource.Default;

/**
 *
 */
public class CommandPanel extends JPanel {

    private Image backgroundImage;

    public CommandPanel() {
        if (Spark.isWindows()) {
            setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        }
        else {
            setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        }


        backgroundImage = Default.getImageIcon(Default.TOP_BOTTOM_BACKGROUND_IMAGE).getImage();

    }

    public Image getBackgroundImage() {
    	return backgroundImage;
    }

	public void setBackgroundImage(Image backgroundImage) {
    	this.backgroundImage = backgroundImage;
    }

	public CommandPanel(int i) {
        this();
        if (i==1)
        {
            backgroundImage = Default.getImageIcon(Default.TOP_BOTTOM_BACKGROUND_IMAGE).getImage();
        }
        else if (i==2)
        {
            backgroundImage = Default.getImageIcon(Default.TOP_BOTTOM_BACKGROUND_IMAGE2).getImage();
        }
   }

    public void paintComponent(Graphics g) {
        double scaleX = getWidth() / (double) backgroundImage.getWidth(null);
        double scaleY = getHeight() / (double) backgroundImage.getHeight(null);
        AffineTransform xform = AffineTransform.getScaleInstance(scaleX, scaleY);
        ((Graphics2D) g).drawImage(backgroundImage, xform, this);
    }
}
