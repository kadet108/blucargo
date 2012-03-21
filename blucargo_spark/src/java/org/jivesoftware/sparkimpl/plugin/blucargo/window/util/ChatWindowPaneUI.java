package org.jivesoftware.sparkimpl.plugin.blucargo.window.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class ChatWindowPaneUI extends BasicTabbedPaneUI {
	
	protected void paintTabBackground(Graphics g, int tabPlacement,
			int tabIndex, int x, int y, int w, int h, boolean isSelected) {
		
//		g.setColor(Color.black);
//		g.fillRect(x, y, w, h);

	}

	protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex,
			int x, int y, int w, int h, boolean isSelected) {
		g.setColor(Color.black);
		
		if(isSelected) {
			g.drawRect(x, y, w-10, h);
		} else {
			g.drawRect(x, y, w-10, h);
		}
		
		
		Rectangle tabRect = null;
	}
	
	protected void paintContentBorderTopEdge(Graphics g, int tabPlacement,
			int selectedIndex, int x, int y, int w, int h) {

	}

	protected void paintContentBorderRightEdge(Graphics g, int tabPlacement,
			int selectedIndex, int x, int y, int w, int h) {

	}

	protected void paintContentBorderLeftEdge(Graphics g, int tabPlacement,
			int selectedIndex, int x, int y, int w, int h) {

	}

	protected void paintContentBorderBottomEdge(Graphics g, int tabPlacement,
			int selectedIndex, int x, int y, int w, int h) {

	}
	
	public static void main(String [] args) {
		
    	JPanel panel=new JPanel();
    	panel.setLayout(new GridBagLayout());
    	JTabbedPane tabbedPane=new JTabbedPane();


    	
    	ChatWindowPaneUI paneUI=new ChatWindowPaneUI();
//    	paneUI.setBackgroundColor(new Color(131, 145, 165, 255));
//    	paneUI.setIcons(icons);
    	
    	tabbedPane.setUI(paneUI);
    	tabbedPane.addMouseMotionListener(null);
    	tabbedPane.addMouseListener(null);
    	
//    	tabbedPane.addTab("Gie\u0142da", initializeCargoOffersPanel());
//    	tabbedPane.addTab("Ulubione Oferty", initializeFavouriteOffersPanel());
//    	tabbedPane.addTab("Zaakceptowane Oferty", initializeAcceptedOffersPanel());
    	tabbedPane.addTab("Gie\u0142da", new JButton("sdsf"));
    	tabbedPane.addTab("Ulubione Oferty", new JButton("sdsfsdf"));
    	tabbedPane.addTab("Zaakceptowane Oferty", new JButton("sds sdf f"));
    	    	
    	panel.add(tabbedPane, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    	panel.setBorder(new LineBorder(Color.red));
    	
		JFrame vFrame = new JFrame();
		vFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vFrame.setSize(300, 200);
		
		vFrame.getContentPane().add(panel);
		vFrame.setTitle("CW Tabs");
		vFrame.show();

	}
	
}
