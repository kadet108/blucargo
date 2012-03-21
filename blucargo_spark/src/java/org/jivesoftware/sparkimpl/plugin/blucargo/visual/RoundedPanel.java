package org.jivesoftware.sparkimpl.plugin.blucargo.visual;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class RoundedPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	protected Color color;
	protected Color borderColor;
	
	
	public Color getBorderColor() {
    	return borderColor;
    }

	public void setBorderColor(Color borderColor) {
    	this.borderColor = borderColor;
    }

	public RoundedPanel() {
		setOpaque(false);
	}

	public RoundedPanel(Color color) {
		this.color = color;
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		int x = 5;
		int y = 5;
		int w = getWidth() - 10;
		int h = getHeight() - 5;
		int arc = 30;

		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(getColor());
		g2.fillRoundRect(x, y, w, h, arc, arc);

		g2.setStroke(new BasicStroke(3f));
		g2.setColor(getColor());
		g2.drawRoundRect(x, y, w, h, arc, arc);

		g2.dispose();
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
