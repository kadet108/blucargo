package org.jivesoftware.sparkimpl.plugin.blucargo.window.util;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.jivesoftware.resource.SparkRes;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.BackgroundPanel;

public class ComponentCreator {
	
	public static JPanel createBlueTopPanel() {
		ImageIcon pasekMenuTlo = SparkRes.getImageIcon(SparkRes.CARGO_PASEK_MENU_TLO);
		BackgroundPanel bluePanel = new BackgroundPanel();
		((BackgroundPanel) bluePanel).setBackgroundImage(pasekMenuTlo);
		bluePanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,0)); //TUTAJ TRZEBA POGRZEBAC
		bluePanel.setPreferredSize(new Dimension(10, 71));
		bluePanel.setMaximumSize(new Dimension(600, 71));
		bluePanel.setMinimumSize(new Dimension(600, 71));
		
		return bluePanel;
	}

	public static JPanel createBlueBottomPanel() {
		ImageIcon pasekMenuTlo = SparkRes.getImageIcon(SparkRes.CARGO_PASEK_MENU_TLO);
		BackgroundPanel bluePanel = new BackgroundPanel();
		((BackgroundPanel) bluePanel).setBackgroundImage(pasekMenuTlo);
		bluePanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,0)); //TUTAJ TRZEBA POGRZEBAC
		bluePanel.setPreferredSize(new Dimension(10, 30));
		bluePanel.setMaximumSize(new Dimension(300, 71));
		bluePanel.setMinimumSize(new Dimension(300, 71));
		
		return bluePanel;
	}

	
}
