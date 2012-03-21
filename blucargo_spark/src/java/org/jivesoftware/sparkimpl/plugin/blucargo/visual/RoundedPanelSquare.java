package org.jivesoftware.sparkimpl.plugin.blucargo.visual;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

public class RoundedPanelSquare extends RoundedPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5296524427819241591L;

	public RoundedPanelSquare() {
//		setOpaque(false);
	}

	public RoundedPanelSquare(Color color) {
		this.color = color;
//		setOpaque(false);
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

		g2.setColor(getColor());
		g2.fillRect(x, y, w, h);

        if(this.borderColor!=null){
//        	RoundRectangle2D roundRect = new RoundRectangle2D.Float(w / 2, y, w / 2, h, arc, arc);
            g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
            g2.setColor(this.borderColor);            
            g2.drawLine(x, y, w, y);
            g2.drawLine(x, h+1, w, h+1);
        }

		
		g2.dispose();
	}
}
