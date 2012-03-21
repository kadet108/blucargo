package org.jivesoftware.sparkimpl.plugin.blucargo.visual;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

public class RoundedPanelLeft extends RoundedPanel {


    /**
	 * 
	 */
	private static final long serialVersionUID = -8258649786207873104L;


	public RoundedPanelLeft() {
        setOpaque(false);
    }

    public RoundedPanelLeft(Color color) {
        this.color=color;
        setOpaque(false);
    }


    @Override
    protected void paintComponent(Graphics g) {

    	Insets insets = this.getBorder().getBorderInsets(this);

        int x = 0 + insets.left;
        int y = 0;
        int w = getWidth() - 0 ;
        int h = getHeight() - 0;
        int arc = 30;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        Color color2 = new Color(131,145,165,255);
        //Color color2 = new Color(0,0,0,255);
        g2.setColor(color2);
        g2.fillRect(x, y, w, h);

        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
        g2.setColor(getColor());
        g2.fillRoundRect(x, y, 2*w/3, h, arc, arc);
        g2.fillRect(w/2, y, w, h);

        if(this.borderColor!=null){
        	RoundRectangle2D roundRect = new RoundRectangle2D.Float(x, y, 2*w/3, h, arc, arc);
            g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
            g2.setColor(this.borderColor);  
            g2.drawLine(w/2, y, w, y);
            g2.drawLine(w/2, h, w, h);

            
            
            g2.clipRect(x, y, w/2, h);            
            g2.draw(roundRect);
        }
        
        g2.dispose();
    }
}
