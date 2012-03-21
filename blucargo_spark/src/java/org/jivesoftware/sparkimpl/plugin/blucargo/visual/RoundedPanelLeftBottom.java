package org.jivesoftware.sparkimpl.plugin.blucargo.visual;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class RoundedPanelLeftBottom extends RoundedPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8204515427561020176L;

	public RoundedPanelLeftBottom() {
		setOpaque(false);
	}

	public RoundedPanelLeftBottom(Color color) {
		this.color = color;
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		int x = 0;
		int y = 0;
		int w = getWidth() - 0;
		int h = getHeight() - 0;
		int arc = 30;

		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Color color2 = new Color(131, 145, 165, 255);
		g2.setColor(color2);
		g2.fillRect(x, y, w, h);

		g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
		g2.setColor(getColor());
		g2.fillRect(x, y, w, h / 2);
		g2.fillRect(w / 2, y, w / 2, h);
		g2.fillRoundRect(x, y, w, h, arc, arc);

		g2.setStroke(new BasicStroke(3f));
		g2.setColor(new Color(226, 226, 226, 220));

		g2.dispose();
	}

}
