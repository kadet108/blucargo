package org.jivesoftware.sparkimpl.plugin.blucargo.visual;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.UIManager;

public class VerticalGradientPanel extends JPanel{

	private Color startColor;
	private Color endColor;
	

	public VerticalGradientPanel(Color startColor, Color endColor){
		this.startColor = startColor;
		this.endColor = endColor;
	}
	
	
	public void paintComponent(Graphics g) {

        Color stopColor = endColor;
        Color starterColor = startColor;

        Color customStartColor = (Color)UIManager.get("CollapsiblePane.startColor");
        Color customEndColor = (Color)UIManager.get("CollapsiblePane.endColor");

        if (customEndColor != null) {
            stopColor = customEndColor;
        }

        if (customStartColor != null) {
            starterColor = customStartColor;
        }

        Graphics2D g2 = (Graphics2D)g;

        int w = getWidth();
        int h = getHeight();

        GradientPaint gradient = new GradientPaint(0, h, starterColor, 0, 0, stopColor, true);
        g2.setPaint(gradient);
        g2.fillRect(0, 0, w, h);
    }

    public Color getStartColor() {
    	return startColor;
    }

	public void setStartColor(Color startColor) {
    	this.startColor = startColor;
    }

	public Color getEndColor() {
    	return endColor;
    }

	public void setEndColor(Color endColor) {
    	this.endColor = endColor;
    }

	
}
