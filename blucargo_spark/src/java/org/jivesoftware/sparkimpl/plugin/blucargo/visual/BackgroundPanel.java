/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jivesoftware.sparkimpl.plugin.blucargo.visual;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9207865908894120366L;
	private ImageIcon backgroundImage;
	private String backgroundRepeat = "repeat";

	public void setBackgroundImage(ImageIcon backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public void setBackgroundRepeat(String backgroundRepeat) {
		this.backgroundRepeat = backgroundRepeat;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (backgroundImage != null) {
			Graphics2D g2 = (Graphics2D) g.create();
			Insets inset = this.getInsets();
			ImageIcon image = backgroundImage;
			Rectangle clippingRegion = new Rectangle(this.getWidth() - (inset.left + inset.right), this.getHeight() - (inset.top + inset.bottom));

			Rectangle clip = g.getClipBounds();

			int height = getSize().height - inset.bottom;
			int width = getSize().width - inset.right;

			if (clip.y + clip.height > height)
				clip.height = height - clip.y;

			if (clip.x + clip.width > width)
				clip.width = width - clip.x;

			g2.setClip(clip);
			int xRepeat = 0;
			int yRepeat = 0;

			if (backgroundRepeat == "repeat" || backgroundRepeat == "repeat-y")
				yRepeat = (int) Math.ceil(clippingRegion.getHeight() / image.getIconHeight());

			if (backgroundRepeat == "repeat" || backgroundRepeat == "repeat-x")
				xRepeat = (int) Math.ceil(clippingRegion.getWidth() / image.getIconWidth());

			for (int i = 0; i <= yRepeat; i++) {
				for (int j = 0; j <= xRepeat; j++) {
					image.paintIcon(this, g2, j * image.getIconWidth() + inset.left, i * image.getIconHeight() + inset.top);
				}
			}

			g2.dispose();
		}
	}
}
