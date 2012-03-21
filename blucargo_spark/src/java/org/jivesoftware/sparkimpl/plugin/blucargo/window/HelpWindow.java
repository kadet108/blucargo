package org.jivesoftware.sparkimpl.plugin.blucargo.window;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.jivesoftware.resource.SparkRes;
import org.jivesoftware.sparkimpl.plugin.blucargo.resourcebundle.StrAccessor;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.BackgroundPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.RoundedPanel;

public class HelpWindow extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	Color color = new Color(242, 242, 242, 255);

	Color color2 = new Color(208, 203, 181);
	JPanel mainArea = new JPanel();
	JPanel windowHeader = new JPanel();
	JPanel panel1a = new JPanel();
	JPanel windowMain = new JPanel();
	JPanel windowFooter = new JPanel();
	JPanel windowMainFill = new RoundedPanel(color2);
	
	JPanel panel5 = new JPanel();
	JPanel panel6 = new JPanel();
	JPanel panel7 = new JPanel();
	JPanel panel8 = new JPanel();
	JPanel panel9 = new JPanel();
	JPanel panel10 = new JPanel();
	JPanel panel11 = new JPanel();
	JPanel panel12 = new JPanel();
	HelpWindow window = this;
	
	

	public HelpWindow() {

		initialize();
		
	    
		this.setBackground(Color.RED);
		this.setLayout(new GridBagLayout());
		this.add(mainArea, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		mainArea.setLayout(new GridBagLayout());
		mainArea.setBackground(Color.RED);

		ImageIcon blueTopImage 		= SparkRes.getImageIcon(SparkRes.CARGO_NIEBIESKI_PASEK);
		ImageIcon pasekMenuTlo 		= SparkRes.getImageIcon(SparkRes.CARGO_PASEK_MENU_TLO);
		ImageIcon pasekStopkaTlo 	= SparkRes.getImageIcon(SparkRes.CARGO_PASEK_STOPKA_TLO);
		ImageIcon greyGradientImage = SparkRes.getImageIcon(SparkRes.CARGO_PASEK_GORA);
		ImageIcon dolnyPasek 		= SparkRes.getImageIcon(SparkRes.CARGO_DOLNY_PASEK);

		ImageIcon pomocIcon1 = null;
		pomocIcon1 = SparkRes.getImageIcon(SparkRes.CARGO_POMOC_HEAD1);
			
		ImageIcon pomocIcon2 = null;
		pomocIcon2 = SparkRes.getImageIcon(SparkRes.CARGO_POMOC_HEAD2);

		createHeader(pasekMenuTlo, pomocIcon1, pomocIcon2);
		createFooter(pasekStopkaTlo);
		createMain();

		//mainArea.add(windowHeader, 		new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		mainArea.add(windowMain, 		new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		//mainArea.add(windowFooter, 		new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

		//pack();
	}

	private void initialize() {
		windowHeader.setBackground(null);
		panel1a.setBackground(null);
		windowMain.setBackground(null);
		windowFooter.setBackground(null);
		windowMainFill.setBackground(null);
	}

	private void createMain() {
		
		ImageIcon pasekMenuTlo 		= SparkRes.getImageIcon(SparkRes.CARGO_TLO1PX);
		windowMain = new BackgroundPanel();
		((BackgroundPanel) windowMain).setBackgroundImage(pasekMenuTlo);
		
		
		
		windowMain.setLayout(new GridBagLayout());
		windowMain.setBackground(new Color(130, 145, 164));
		//windowMain.setBackground(Color.RED);
		//windowMain.setOpaque(false);
		
		//windowMain.add(new JLabel("sdfsf"));
		//windowMain.repaint();
		//this.repaint();
		//windowMain.se
		windowMain.add(windowMainFill, new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0));
        
		
		windowMainFill.setLayout(new GridBagLayout());

		windowMainFill.setPreferredSize	(new Dimension(500, 200));
		windowMainFill.setMaximumSize	(new Dimension(500, 200));
		windowMainFill.setMinimumSize	(new Dimension(500, 200));
		//windowMainFill.setBackground(Color.RED);
		
		JPanel column1 							= new JPanel();
		column1.setBackground(new Color(208, 203, 181));
		column1.setLayout(new GridBagLayout());
		
		JLabel naglowekKontakt 					= new JLabel("<html><b>"+StrAccessor.getString("HelpWindow.contactDetails")+"</b></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		JLabel tekst1Kontakt 					= new JLabel("<html>"+"TAKAMURA Marta \u0141odykowska"+"<br/>"+"04-679 Warszawa, ul. Kombajnist\u00f3w 21A"+"<br/>"+"NIP: 113-234-59-95"+"<br/>"+"REGON: 140604459"+"</html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$
		JLabel naglowekKontakt2 				= new JLabel("<html><b>"+StrAccessor.getString("HelpWindow.technicalHelp")+"</b></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		JLabel tekst2Kontakt 					= new JLabel("<html>"+"serwis@mycargo.pl"+"<br/>"+"tel.: 532-354-232"+"</html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				
		naglowekKontakt.setPreferredSize(new Dimension(250, 29));
		naglowekKontakt.setMaximumSize(new Dimension(250, 29));
		naglowekKontakt.setMinimumSize(new Dimension(250, 29));
		tekst1Kontakt.setPreferredSize(new Dimension(250, 29));
		tekst1Kontakt.setMaximumSize(new Dimension(250, 29));
		tekst1Kontakt.setMinimumSize(new Dimension(250, 29));
		naglowekKontakt2.setPreferredSize(new Dimension(250, 29));
		naglowekKontakt2.setMaximumSize(new Dimension(250, 29));
		naglowekKontakt2.setMinimumSize(new Dimension(250, 29));
		tekst2Kontakt.setPreferredSize(new Dimension(250, 29));
		tekst2Kontakt.setMaximumSize(new Dimension(250, 29));
		tekst2Kontakt.setMinimumSize(new Dimension(250, 29));
		
		column1.add(naglowekKontakt, 			new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column1.add(tekst1Kontakt, 				new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column1.add(naglowekKontakt2, 			new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column1.add(tekst2Kontakt, 				new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));

		JPanel column4 							= new JPanel();
		column4.setBackground(new Color(208, 203, 181));
		column4.setLayout(new GridBagLayout());
		
		//pierwszy button
		JButton videoButton = new JButton();
		JButton faqButton = new JButton();
		JButton dlaprasyButton = new JButton();
		JButton bladButton = new JButton();
		
		ImageIcon historiaIcon1 = SparkRes.getImageIcon(SparkRes.CARGO_HISTORIA_ROZMOW1);
		ImageIcon historiaIcon2 = SparkRes.getImageIcon(SparkRes.CARGO_HISTORIA_ROZMOW2);

		videoButton = new JButton("<html><font color='white'>"+StrAccessor.getString("HelpWindow.videoTutorials")+"</font></html>",historiaIcon1); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		videoButton.setVerticalTextPosition(SwingConstants.CENTER);
		videoButton.setHorizontalTextPosition(SwingConstants.CENTER);
		videoButton.setRolloverIcon(historiaIcon2);
		videoButton.setOpaque(false);
		videoButton.setBorder(null);
		videoButton.setBorderPainted(false);
		videoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		faqButton = new JButton("<html><font color='white'>"+"FAQ"+"</font></html>",historiaIcon1); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		faqButton.setVerticalTextPosition(SwingConstants.CENTER);
		faqButton.setHorizontalTextPosition(SwingConstants.CENTER);
		faqButton.setRolloverIcon(historiaIcon2);
		faqButton.setOpaque(false);
		faqButton.setBorder(null);
		faqButton.setBorderPainted(false);
		faqButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		dlaprasyButton = new JButton("<html><font color='white'>"+StrAccessor.getString("HelpWindow.forPress")+"</font></html>",historiaIcon1); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		dlaprasyButton.setVerticalTextPosition(SwingConstants.CENTER);
		dlaprasyButton.setHorizontalTextPosition(SwingConstants.CENTER);
		dlaprasyButton.setRolloverIcon(historiaIcon2);
		dlaprasyButton.setOpaque(false);
		dlaprasyButton.setBorder(null);
		dlaprasyButton.setBorderPainted(false);
		dlaprasyButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		bladButton = new JButton("<html><font color='white'>"+StrAccessor.getString("HelpWindow.bugNotify")+"</font></html>",historiaIcon1); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		bladButton.setVerticalTextPosition(SwingConstants.CENTER);
		bladButton.setHorizontalTextPosition(SwingConstants.CENTER);
		bladButton.setRolloverIcon(historiaIcon2);
		bladButton.setOpaque(false);
		bladButton.setBorder(null);
		bladButton.setBorderPainted(false);
		bladButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		column4.add(videoButton, 			new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(30, 10, 0, 10), 0, 0));
		column4.add(faqButton, 				new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column4.add(dlaprasyButton, 		new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column4.add(bladButton, 			new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));

		//mam 4 kolumny
		windowMainFill.add(column1, 		new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		//windowMainFill.add(column2, 		new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		//windowMainFill.add(column3, 		new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		windowMainFill.add(column4, 		new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
	}

	private void createFooter(ImageIcon pasekStopkaTlo) {
		windowFooter = new BackgroundPanel();
		((BackgroundPanel) windowFooter).setBackgroundImage(pasekStopkaTlo);
		windowFooter.setLayout(new GridBagLayout());
		windowFooter.setPreferredSize(new Dimension(10, 43));
		windowFooter.setMaximumSize(new Dimension(900, 43));
		windowFooter.setMinimumSize(new Dimension(900, 43));

		
	}

	private void createHeader(ImageIcon pasekMenuTlo, ImageIcon rankingIcon1, ImageIcon rankingIcon2) {
		windowHeader = new BackgroundPanel();
		((BackgroundPanel) windowHeader).setBackgroundImage(pasekMenuTlo);
		windowHeader.setLayout(new GridBagLayout());
		windowHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0)); // TUTAJ
		// TRZEBA
		// POGRZEBAC
		windowHeader.setPreferredSize(new Dimension(10, 71));
		windowHeader.setMaximumSize(new Dimension(900, 71));
		windowHeader.setMinimumSize(new Dimension(900, 71));

		windowHeader.add(new JLabel(rankingIcon1), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowHeader.add(new JLabel(rankingIcon2), new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

	}
	
	

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new HelpWindow().setVisible(true);
			}
		});
	}

}
