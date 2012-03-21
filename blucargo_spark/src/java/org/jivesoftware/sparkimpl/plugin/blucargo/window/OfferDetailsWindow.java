package org.jivesoftware.sparkimpl.plugin.blucargo.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import org.jivesoftware.resource.SparkRes;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.BackgroundPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.RoundedPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.menu.ContactMenuItem;

import com.blucargo.model.CargoOffer;
import com.blucargo.model.OfferType;

public class OfferDetailsWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	Color color 				= new Color(242, 242, 242, 255);

	Color color2 				= new Color(208, 203, 181);
	JPanel mainArea 			= new JPanel();
	JPanel windowHeader 		= new JPanel();
	JPanel panel1a 				= new JPanel();
	JPanel windowMain 			= new JPanel();
	JPanel windowFooter 		= new JPanel();
	JPanel windowMainFill 		= new RoundedPanel(color2);
	JPanel panel5 				= new JPanel();
	JPanel panel6 				= new JPanel();
	JPanel panel7 				= new JPanel();
	JPanel panel8 				= new JPanel();
	JPanel panel9 				= new JPanel();
	JPanel panel10 				= new JPanel();
	JPanel panel11 				= new JPanel();
	JPanel panel12 				= new JPanel();
	OfferDetailsWindow window 	= this;
	CargoOffer cargoOffer;

	public OfferDetailsWindow(final CargoOffer cargoOffer) {
		this.cargoOffer = cargoOffer;
		initialize();

		this.setBackground(Color.WHITE);
		this.setLayout(new GridBagLayout());
		this.add(mainArea, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		mainArea.setLayout(new GridBagLayout());
		mainArea.setBackground(Color.WHITE);

		ImageIcon pasekMenuTlo 			= SparkRes.getImageIcon(SparkRes.CARGO_PASEK_MENU_TLO);
		ImageIcon pasekStopkaTlo 		= SparkRes.getImageIcon(SparkRes.CARGO_PASEK_STOPKA_TLO);
		ImageIcon logoIcon 				= SparkRes.getImageIcon(SparkRes.CARGO_LOGO_ICO);
		ImageIcon szczegolyIcon 		= SparkRes.getImageIcon(SparkRes.CARGO_SZCZEGOLY_OGLOSZENIA_HEADER);

		createHeader(pasekMenuTlo, logoIcon, szczegolyIcon);
		createFooter(pasekStopkaTlo);
		createMain();

		// MAIN WINDOW
		mainArea.add(windowHeader, 	new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		mainArea.add(windowMain, 	new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		mainArea.add(windowFooter, 	new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

		pack();
		
		final JPopupMenu contextMenu = new JPopupMenu("Edit");
        final ContactMenuItem contactMenuItem = new ContactMenuItem();
		contextMenu.add(contactMenuItem);

		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if (e.getButton() == MouseEvent.BUTTON3){
					contactMenuItem.setCargoOffer(cargoOffer);
					contextMenu.show(windowHeader, e.getX(), e.getY());
				}
			}
		});
		
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
		windowMain.add(windowMainFill, new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 5, 5), 0, 0));

		windowMainFill.setLayout(new GridBagLayout());

		ImageIcon pojazdIcon = null;

		if (cargoOffer.getType() == OfferType.CARGO) {
			pojazdIcon = SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_LADUNEK_ICO);
		} else if (cargoOffer.getType() == OfferType.VEHICLE) {
			pojazdIcon = SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_POJAZD_ICO);
		}

		String country = cargoOffer.getCountryFrom();

		ClassLoader cl = SparkRes.class.getClassLoader();

		String countryString = "images/cargo/countries/" + country.toUpperCase() + ".png";
		URL iconURL = cl.getResource(countryString);

		ImageIcon countryIcon = null;

		try {
			countryIcon = new ImageIcon(iconURL);
		} catch (Exception e) {
			// Exception
		}
		JLabel flagfromLabel = new JLabel();
		flagfromLabel.setIcon(countryIcon);
		flagfromLabel.setLayout(new GridBagLayout());

		String country2 = cargoOffer.getCountryTo();

		ClassLoader cl2 = SparkRes.class.getClassLoader();

		String countryString2 = "images/cargo/countries/" + country2.toUpperCase() + ".png";
		URL iconURL2 = cl2.getResource(countryString2);

		ImageIcon countryIcon2 = null;

		try {
			countryIcon2 = new ImageIcon(iconURL2);
		} catch (Exception e) {
			// Exception
		}
		
		JLabel flagtoLabel = new JLabel();
		flagtoLabel.setIcon(countryIcon2);
		flagtoLabel.setLayout(new GridBagLayout());

		JLabel iconLabel = new JLabel();
		iconLabel.setIcon(pojazdIcon);
		iconLabel.setLayout(new GridBagLayout());

		JLabel coJestWolne = new JLabel();
		JLabel propertyOffer = new JLabel("24t plandeka dl.14m");

		String hds = "";
		hds = cargoOffer.getHds();

		JLabel zadzwon = new JLabel("<html><b>Zadzwo\u0144 ></b></html>");
		JLabel napisz = new JLabel("<html><b>Napisz ></b></html>");

		JLabel skad = new JLabel("<html><b>Sk\u0105d:</b></html>");
		JLabel dokad = new JLabel("<html><b>Dok\u0105d:</b></html>");

		String fromCountry = "";
		fromCountry = cargoOffer.getCountryFrom();

		String fromCity = "";
		fromCity = cargoOffer.getCityFrom();

		String fromStreet = "";
		fromStreet = cargoOffer.getAddressFrom();

		String fromZIP = "";
		fromZIP = cargoOffer.getPostOfficeFrom();

		String submissionDate = "";
		submissionDate = cargoOffer.getSubmissionDate().toString();

		String toCountry = "";
		toCountry = cargoOffer.getCountryTo();

		String toCity = "";
		toCity = cargoOffer.getCityTo();

		String toStreet = "";
		toStreet = cargoOffer.getAddressTo();

		String toZIP = "";
		toZIP = cargoOffer.getPostOfficeTo();

		String offerValid = "";
		offerValid = cargoOffer.getOfferValid().toString();

		JLabel kontaktLabel = new JLabel("<html><b>Kontakt:</b></html>");
		JLabel nazwaOsoby = new JLabel("Andrzej Nowak");
		JLabel idOsoby = new JLabel("CargoID: 4345432");
		JLabel telOsoby = new JLabel("Tel.: +48329129320");
		JLabel komOsoby = new JLabel("Kom. +482398923987");

		JLabel firmaLabel = new JLabel("<html><b>Dane firmy:</b></html>");
		JLabel nazwaFirmy = new JLabel("Nowak Logistic");
		JLabel ulicaFirmy = new JLabel("ul. Kosciuszki 72/1");
		JLabel zipFirmy = new JLabel("02-423");
		JLabel statusFirmy = new JLabel("Firma autoryzowana");

		if (cargoOffer.getType() == OfferType.CARGO) {
			coJestWolne.setText("<html><b>Wolny \ufffdadunek</b></html>");

		} else if (cargoOffer.getType() == OfferType.VEHICLE) {
			coJestWolne.setText("<html><b>Wolny pojazd</b></html>");
		}

		JPanel column1 = new JPanel();
		column1.setBackground(new Color(208, 203, 181));
		column1.setLayout(new GridBagLayout());
		column1.add(iconLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 0), 0, 0));
		column1.add(new JLabel(""), new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		JPanel column2 = new JPanel();
		column2.setBackground(new Color(208, 203, 181));
		column2.setLayout(new GridBagLayout());
		column2.add(coJestWolne, 				new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		column2.add(propertyOffer, 				new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0));
		column2.add(new JLabel(hds), 			new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0));
		column2.add(zadzwon, 					new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column2.add(napisz, 					new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0));
		column2.add(new JLabel(""), 			new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0));

		JPanel column3 = new JPanel();
		column3.setBackground(new Color(208, 203, 181));
		column3.setLayout(new GridBagLayout());
		column3.add(flagfromLabel, 				new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 0), 0, 0));
		column3.add(new JLabel(""), 			new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		JPanel column4 = new JPanel();
		column4.setBackground(new Color(208, 203, 181));
		column4.setLayout(new GridBagLayout());
		column4.add(skad, 						new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		column4.add(new JLabel(fromCountry), 	new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0));
		column4.add(new JLabel(fromCity), 		new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0));
		column4.add(new JLabel(fromStreet), 	new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0));
		column4.add(new JLabel(fromZIP), 		new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0));
		column4.add(new JLabel(submissionDate), new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0));
		column4.add(new JLabel(""), 			new GridBagConstraints(0, 6, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		JPanel column5 = new JPanel();
		column5.setBackground(new Color(208, 203, 181));
		column5.setLayout(new GridBagLayout());
		column5.add(flagtoLabel, 				new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 0), 0, 0));
		column5.add(new JLabel(""), 			new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		JPanel column6 = new JPanel();
		column6.setBackground(new Color(208, 203, 181));
		column6.setLayout(new GridBagLayout());
		column6.add(dokad, 						new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		column6.add(new JLabel(toCountry), 		new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0));
		column6.add(new JLabel(toCity), 		new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0));
		column6.add(new JLabel(toStreet), 		new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0));
		column6.add(new JLabel(toZIP), 			new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0));
		column6.add(new JLabel(offerValid), 	new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0));
		column6.add(new JLabel(""), 			new GridBagConstraints(0, 6, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		JPanel column7 = new JPanel();
		column7.setBackground(new Color(208, 203, 181));
		column7.setLayout(new GridBagLayout());
		column7.add(kontaktLabel, 				new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		column7.add(nazwaOsoby, 				new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0));
		column7.add(idOsoby, 					new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0));
		column7.add(telOsoby, 					new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0));
		column7.add(komOsoby, 					new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0));
		column7.add(new JLabel(""), 			new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		JPanel column8 = new JPanel();
		column8.setBackground(new Color(208, 203, 181));
		column8.setLayout(new GridBagLayout());
		column8.add(firmaLabel, 				new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		column8.add(nazwaFirmy, 				new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0));
		column8.add(ulicaFirmy, 				new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0));
		column8.add(zipFirmy, 					new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0));
		column8.add(statusFirmy, 				new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		column8.add(new JLabel(""), 			new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		windowMainFill.add(column1, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		windowMainFill.add(column2, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		windowMainFill.add(column3, new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		windowMainFill.add(column4, new GridBagConstraints(3, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		windowMainFill.add(column5, new GridBagConstraints(4, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		windowMainFill.add(column6, new GridBagConstraints(5, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		windowMainFill.add(column7, new GridBagConstraints(6, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		windowMainFill.add(column8, new GridBagConstraints(7, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));


	}

	private void createFooter(ImageIcon pasekStopkaTlo) {
		
		windowFooter = new BackgroundPanel();
		((BackgroundPanel) windowFooter).setBackgroundImage(pasekStopkaTlo);
		windowFooter.setLayout(new GridBagLayout());
		windowFooter.setPreferredSize(new Dimension(10, 45));
		windowFooter.setMaximumSize(new Dimension(600, 45));
		windowFooter.setMinimumSize(new Dimension(600, 45));
		
	}

	private void createHeader(ImageIcon pasekMenuTlo, ImageIcon logoIcon, ImageIcon szczegolyIcon) {
		windowHeader = new BackgroundPanel();
		((BackgroundPanel) windowHeader).setBackgroundImage(pasekMenuTlo);
		windowHeader.setLayout(new GridBagLayout());
		windowHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0)); // TUTAJ
																		// TRZEBA
																		// POGRZEBAC
		windowHeader.setPreferredSize(new Dimension(10, 71));
		windowHeader.setMaximumSize(new Dimension(600, 71));
		windowHeader.setMinimumSize(new Dimension(600, 71));

		windowHeader.add(new JLabel(szczegolyIcon), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				CargoOffer co = new CargoOffer();
				co.setAddressFrom("ADR");
				co.setBody("Body");
				co.setCargoPrice("100USD");
				co.setCargoOnPalette("Tak");
				co.setCargoOnPalette("Tak");
				co.setSubmissionDate(new Date());
				co.setCityFrom("Opole");
				co.setCityTo("Opole");
				co.setCountryFrom("pl");
				co.setCountryTo("pl");
				co.setDeliveryOnDay(new Date());
				co.setDescription("opis");
				co.setDimensionOfPalettes("dimension of palettes");
				co.setElevator("elevator");
				co.setHds("hds");
				co.setCargoLength("100m");
				co.setCargoLoad("100m");
				co.setLoadingDate(new Date());
				co.setNegotiable("tak");
				co.setNumberOfPalettes(100L);
				co.setOfferValid(new Date());
				co.setOtherDelivery("sdfdsf");
				co.setPartLoad("sdfsd");
				co.setPostOfficeFrom("345");
				co.setPostOfficeTo("467");
				co.setReadyToLoad("sdsdf");
				co.setStackablePalettes("tak");
				co.setUnloadingDate(new Date());
				co.setVolume("100 L");
				co.setWeight("100 T");
				co.setContact("Mir T cos tam cos tam");
				// Wlasciciel oferty zarejestrowany w sparku
				co.setOwner("a");
				co.setType(OfferType.CARGO);

				new OfferDetailsWindow(co).setVisible(true);
			}
		});
	}

}
