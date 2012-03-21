package org.jivesoftware.sparkimpl.plugin.blucargo.visual;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

public class RoundedPanelRight extends RoundedPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -118525973953614085L;

	public RoundedPanelRight() {
		setOpaque(false);
	}

	public RoundedPanelRight(Color color) {
		this.color = color;
		setOpaque(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		
		Insets insets = this.getBorder().getBorderInsets(this);
		
		int x = 0;
		int y = 0;
		int w = getWidth() - 0-insets.right;
		int h = getHeight() - 0;
		int arc = 30;

		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Color color2 = new Color(131, 145, 165, 255);
		//Color color2 = new Color(0, 0, 0, 255);
		g2.setColor(color2);
		g2.fillRect(x, y, w, h);

		g2.setColor(getColor());
		g2.fillRoundRect(w / 2, y, w / 2, h, arc, arc);
		//szara czesc po prawej
		g2.fillRect(x, y, 2 * w / 3, h);
		//szara czesc po lewej
		

		//g2.setStroke(new BasicStroke(3f));
		//g2.setColor(new Color(226, 226, 226, 220));
		

        if(this.borderColor!=null){
        	
        	
        	
        	RoundRectangle2D roundRect = new RoundRectangle2D.Float(w / 2, y, w / 2, h, arc, arc);
            g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
            g2.setColor(this.borderColor);    
            g2.drawLine(x-20, y, w-20, y);
            g2.drawLine(x, h+1, w-20, h+1);

            
            g2.clipRect(w / 2+20, y, w / 2+20, h);   
            
            g2.draw(roundRect);
        	
        	
        }

		
		g2.dispose();
	}

}
