package org.jivesoftware.sparkimpl.plugin.blucargo.visual.border;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.border.AbstractBorder;

public class RoundedBorderRight extends AbstractBorder {

	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

		int x1 = 0;
		int y1 = 0;
		int w = c.getWidth() - 0;
		int h = c.getHeight() - 0;
		int arc = 30;

		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(Color.BLACK);
		g.drawArc(w - 23, y1 - 2, 20, 25, 360, 90);
		g.drawLine(w - 3, y1 + 10, w - 3, h - 10);
		g.drawArc(w - 21, h - 20, 18, 18, 270, 100);
		g.drawLine(x1, y1, w - 10, y1);
		g.drawLine(x1, h - 2, w - 10, h - 2);

		g2.dispose();
	}
}
