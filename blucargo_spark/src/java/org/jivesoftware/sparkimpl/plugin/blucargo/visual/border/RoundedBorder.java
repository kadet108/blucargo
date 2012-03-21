package org.jivesoftware.sparkimpl.plugin.blucargo.visual.border;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.border.AbstractBorder;

public class RoundedBorder extends AbstractBorder {

	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

		int x1 = 0;
		int y1 = 0;
		int w = c.getWidth() - 0;
		int h = c.getHeight() - 0;
		int arc = 30;

		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(Color.BLACK);
		g.drawLine(x1, y1, w, y1);
		g.drawLine(x1, h - 2, w, h - 2);

		g2.dispose();
	}
}
