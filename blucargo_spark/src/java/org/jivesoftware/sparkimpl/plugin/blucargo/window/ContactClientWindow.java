package org.jivesoftware.sparkimpl.plugin.blucargo.window;

import java.awt.Color;
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

import org.jivesoftware.resource.SparkRes;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.BackgroundPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.RoundedPanel;

public class ContactClientWindow extends JFrame {


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
	ContactClientWindow window = this;

	public ContactClientWindow() {

		initialize();

		this.setBackground(Color.WHITE);
		this.setLayout(new GridBagLayout());
		this.add(mainArea, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));

		mainArea.setLayout(new GridBagLayout());
		mainArea.setBackground(Color.WHITE);

		ImageIcon pasekMenuTlo = SparkRes.getImageIcon(SparkRes.CARGO_PASEK_MENU_TLO);
		ImageIcon pasekStopkaTlo = SparkRes.getImageIcon(SparkRes.CARGO_PASEK_STOPKA_TLO);
		ImageIcon logoIcon = SparkRes.getImageIcon(SparkRes.CARGO_LOGO_ICO);

		createHeader(pasekMenuTlo, logoIcon);
		createFooter(pasekStopkaTlo);
		createMain();
		
		// MAIN WINDOW
		mainArea.add(windowHeader, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
		mainArea.add(windowMain, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,
						0, 0, 0), 0, 0));
		mainArea.add(windowFooter, new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0,
				GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));

		pack();
	}

	private void initialize() {
		windowHeader.setBackground(null);
		panel1a.setBackground(null);
		windowMain.setBackground(null);
		windowFooter.setBackground(null);
		windowMainFill.setBackground(null);
	}

	private void createMain() {
		windowMain.setLayout(new GridBagLayout());
		windowMain.setBackground(new Color(130, 145, 164));
		windowMain.add(windowMainFill, new GridBagConstraints(0, 0, 2, 1, 1.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(0, 10, 5, 5), 0, 0));
		
		windowMainFill.setLayout(new GridBagLayout());
		
		JLabel tranzakcja = new JLabel("Tranzakcja:");
		JLabel cosOtranzakcji = new JLabel("sdfsd sdf sdf sdf sdf sdf sd ");
		JLabel komentarzZleceniodawcy = new JLabel("Komentarz zleceniodawcy:");
		JLabel tempKomentarzZleceniodawcy = new JLabel("sdfs sd sdf sdf sdf sdf wef we ");
		JLabel komentarzZleceniobiorcy = new JLabel("Komentarz zleceniobiorcy:");
		JLabel tempKomentarzZleceniobiorcy = new JLabel("sdfs sd sdf sdf sdf sdf wef we ");



		//Det er grunnleggende data
		windowMainFill.add(tranzakcja, new GridBagConstraints(0, 0, 1, 1, 1.0,
				1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(cosOtranzakcji, new GridBagConstraints(0, 1, 1, 1, 1.0,
				1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(komentarzZleceniodawcy, new GridBagConstraints(0, 2, 1, 1, 1.0,
				1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(tempKomentarzZleceniodawcy, new GridBagConstraints(0, 3, 1, 1, 1.0,
				1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(komentarzZleceniobiorcy, new GridBagConstraints(0, 4, 1, 1, 1.0,
				1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(tempKomentarzZleceniobiorcy, new GridBagConstraints(0, 5, 1, 1, 1.0,
				1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		
		JButton wystawKomentarz=new JButton("Wystaw komentarz");
		wystawKomentarz.setBackground(new Color(71,104,147));
		
		windowMainFill.add(wystawKomentarz, new GridBagConstraints(1, 5, 1, 1, 0.0,
				1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));

	
	}

	private void createFooter(ImageIcon pasekStopkaTlo) {
		windowFooter = new BackgroundPanel();
		((BackgroundPanel) windowFooter).setBackgroundImage(pasekStopkaTlo);
		windowFooter.setLayout(new GridBagLayout());
		windowFooter.setPreferredSize(new Dimension(10, 45));
		windowFooter.setMaximumSize(new Dimension(600, 45));
		
		JButton pozytyw=new JButton("Pozytyw");
		pozytyw.setBackground(new Color(0,182,19));
		JButton negatyw=new JButton("Negatyw");
		negatyw.setBackground(new Color(168,2,2));
		
		windowFooter.add(pozytyw, new GridBagConstraints(0, 0, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		windowFooter.add(negatyw, new GridBagConstraints(1, 0, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		
	}

	private void createHeader(ImageIcon pasekMenuTlo, ImageIcon logoIcon) {
		windowHeader = new BackgroundPanel();
		((BackgroundPanel) windowHeader).setBackgroundImage(pasekMenuTlo);
		windowHeader.setLayout(new GridBagLayout());
		windowHeader.setLayout(new FlowLayout(FlowLayout.LEADING));
		windowHeader.setPreferredSize(new Dimension(10, 60));
		windowHeader.setMaximumSize(new Dimension(600, 60));
		windowHeader.add(new JLabel(logoIcon));
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new ContactClientWindow().setVisible(true);
			}
		});
	}

}
