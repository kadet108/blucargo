package org.jivesoftware.sparkimpl.plugin.blucargo.window.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import org.jivesoftware.resource.SparkRes;

public class StockPaneUI extends BasicTabbedPaneUI {
	
	private ArrayList<ImageIcon> tabIcons=new ArrayList<ImageIcon>();
	
	private static final Insets TAB_INSETS = new Insets(0, 0, 0, 0);
	
	private static final int tabLeft = 10;
	private static final int tabTop = 20;
	
	private Color backgroundColor;
	
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public void setIcons(ArrayList<ImageIcon> icons){
		this.tabIcons=icons;
	}
	
	public static ComponentUI createUI(JComponent c) {
		return new StockPaneUI();
	}

	protected void installDefaults() {
		super.installDefaults();
		tabAreaInsets = new Insets(tabLeft, tabTop, 0, 0);
		selectedTabPadInsets = new Insets(0, 0, 0, 0);
	} 
	
	public int getTabRunCount(JTabbedPane pane) {
		return 1;
	}

	protected Insets getContentBorderInsets(int tabPlacement) {
		return TAB_INSETS;
	}

	protected int calculateTabHeight(int tabPlacement, int tabIndex,
			int fontHeight) {
		return tabIcons.get(tabIndex*2).getIconHeight()+10;
	}

	protected int calculateTabWidth(int tabPlacement, int tabIndex,
			FontMetrics metrics) {
		return tabIcons.get(tabIndex*2).getIconWidth()+20;
	}

	protected int calculateMaxTabWidth(int tabPlacement) {
		return tabIcons.get(tabPlacement*2).getIconWidth()+0;
	}
	
	protected int calculateTabAreaWidth(int tabPlacement, int vertRunCount, int maxTabWidth){
		return tabIcons.get(tabPlacement*2).getIconWidth()+20;
		
	}
	
	protected void paintTabBackground(Graphics g, int tabPlacement,
			int tabIndex, int x, int y, int w, int h, boolean isSelected) {
		
		g.setColor(this.backgroundColor);
		g.fillRect(x, y, w, h);

	}

	protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex,
			int x, int y, int w, int h, boolean isSelected) {
//		g.setColor(darkShadow);
//		g.drawLine(x - (h / 4), y + h, x + (h / 4), y);
//		g.drawLine(x + (h / 4), y, x + w - (h / 4), y);
//		g.drawLine(x + w - (h / 4), y, x + w + (h / 4), y + h);
	}

	protected void paintContentBorderTopEdge(Graphics g, int tabPlacement,
			int selectedIndex, int x, int y, int w, int h) {
//		Rectangle selectedRect = selectedIndex < 0 ? null : getTabBounds(
//				selectedIndex, calcRect);
//		g.setColor(darkShadow);
//		g.drawLine(x, y, selectedRect.x - (selectedRect.height / 4), y);
//		g.drawLine(selectedRect.x + selectedRect.width
//				+ (selectedRect.height / 4), y, x + w, y);
//		g.setColor(selectedColor);
//		g.drawLine(selectedRect.x - (selectedRect.height / 4) + 1, y,
//				selectedRect.x + selectedRect.width + (selectedRect.height / 4)
//						- 1, y);
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

	protected void paintFocusIndicator(Graphics g, int tabPlacement,
			Rectangle[] rects, int tabIndex, Rectangle iconRect,
			Rectangle textRect, boolean isSelected) {
	}

	protected void paintText(Graphics g, int tabPlacement, Font font,
			FontMetrics metrics, int tabIndex, String title,
			Rectangle textRect, boolean isSelected) {
		
		Rectangle rect= new Rectangle();
			
		rect = this.getTabBounds(tabIndex, rect);
		
		if (isSelected) {
			g.drawImage(this.tabIcons.get(tabIndex*2).getImage(), rect.x, rect.y, null);
		} 
		else {
			g.drawImage(this.tabIcons.get(tabIndex*2+1).getImage(), rect.x, rect.y, null);		
		}		
		
		int x = rect.x;
		int y = rect.y;
		
		int width = rect.width;
		int height = rect.height;
		
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D area = fm.getStringBounds(title, g);
		g.setColor(Color.white);
		g.drawString(title, x + (int)(width - area.getWidth())/2 - tabLeft,(int)(height + area.getHeight())/2 + tabTop/8);
		
	}

	protected int getTabLabelShiftY(int tabPlacement, int tabIndex,
			boolean isSelected) {
		return 0;
	}
	
	public static void main(String [] args) {
		
    	JPanel panel=new JPanel();
    	panel.setLayout(new GridBagLayout());
    	JTabbedPane tabbedPane=new JTabbedPane();

    	ArrayList<ImageIcon> icons=new ArrayList<ImageIcon>();
    	icons.add(SparkRes.getImageIcon(SparkRes.CARGO_GIELDA_ON));
    	icons.add(SparkRes.getImageIcon(SparkRes.CARGO_GIELDA_OFF));
    	icons.add(SparkRes.getImageIcon(SparkRes.CARGO_ULUBIONE_OFERTY_ON));
    	icons.add(SparkRes.getImageIcon(SparkRes.CARGO_ULUBIONE_OFERTY_OFF));
    	icons.add(SparkRes.getImageIcon(SparkRes.CARGO_ZAAKCEPTOWANE_OFERTY_ON));
    	icons.add(SparkRes.getImageIcon(SparkRes.CARGO_ZAAKCEPTOWANE_OFERTY_OFF));
    	
    	StockPaneUI paneUI=new StockPaneUI();
    	paneUI.setBackgroundColor(new Color(131, 145, 165, 255));
    	paneUI.setIcons(icons);
    	
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
