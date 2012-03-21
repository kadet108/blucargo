package org.jivesoftware.sparkimpl.plugin.blucargo.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jivesoftware.resource.SparkRes;
import org.jivesoftware.sparkimpl.plugin.blucargo.manager.CargoLocationManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.BackgroundPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.RoundedPanel;

import com.blucargo.model.CargoOffer;

public class DebtorsWindow extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JComboBox departureCountry;
	private JComboBox departureCity;
	private JTextField departurePo;
	private JSpinner departureHourSpinner;
	
	private JComboBox arrivalCountry;
	private JComboBox arrivalCity;
	private JTextField arrivalPo;
	private JSpinner arrivalHourSpinner;
	
	private JTextField freeFrom;
	private JTextField freeTo;
	private JTextField offerValid;
	
	private JXDatePicker freeFromDatePicker; 
	private JXDatePicker freeToDatePicker;
	private JXDatePicker offerValidDatePicker;
	
	private JTextArea comment;
	
	private JTextField price 			= new JTextField(5);
	private JTextField weight 			= new JTextField(5);
	private JTextField length 			= new JTextField(5);
	private JComboBox body 				= new JComboBox();
	
//	private Map<String, String> countriesMap 		= CargoLocationManager.getInstance().getCountries();
//	private Map<String, HashSet<String>> citiesMap 	= new TreeMap<String, HashSet<String>>();
//	private Map<String, String> countriesMap2 		= CargoLocationManager.getInstance().getCountries();
//	private Map<String, HashSet<String>> citiesMap2 = new TreeMap<String, HashSet<String>>();

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
	DebtorsWindow window 		= this;

	public DebtorsWindow() {

		initialize();

		this.setBackground(Color.WHITE);
		this.setLayout(new GridBagLayout());
		this.add(mainArea, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		mainArea.setLayout(new GridBagLayout());
		mainArea.setBackground(Color.WHITE);

		ImageIcon pasekMenuTlo 		= SparkRes.getImageIcon(SparkRes.CARGO_PASEK_MENU_TLO);
		ImageIcon pasekStopkaTlo 	= SparkRes.getImageIcon(SparkRes.CARGO_PASEK_STOPKA_TLO);

		ImageIcon komentarzIcon = null;
			komentarzIcon = SparkRes.getImageIcon(SparkRes.CARGO_ZGLOS_LADUNEK_HEADER);

		createHeader(pasekMenuTlo, komentarzIcon);
		createFooter(pasekStopkaTlo);
		createMain();

		mainArea.add(windowHeader, 		new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		mainArea.add(windowMain, 		new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		mainArea.add(windowFooter, 		new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

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

	}

	private void createDepartureCountryList() {
		final Map<String, String> countriesMap;
		final Map<String, HashSet<String>> citiesMap;
		countriesMap = CargoLocationManager.getInstance().getCountries();
//		citiesMap = new TreeMap<String, HashSet<String>>();

		this.departureCity = new JComboBox();
		this.departureCity.addItem("Miasto wyjazdu");

		Vector<String> departureCountryList = new Vector<String>();
		Vector<String> departureCountryListTemp = new Vector<String>(CargoLocationManager.getInstance().getCountries().keySet());
		departureCountryList.add("Kraj wyjazdu");
		departureCountryList.addAll(departureCountryListTemp);

		departureCountry = new JComboBox(departureCountryList);
		AutoCompleteDecorator.decorate(departureCountry);
		departureCountry.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (departureCountry.getSelectedIndex() != 0) {
					String country = (String) departureCountry.getSelectedItem();
					String country_iso = countriesMap.get(country);
					List<String> cities = CargoLocationManager.getInstance().getCities(country_iso);
					departureCity.removeAllItems();
					for(String city: cities){
						departureCity.addItem(city);						
					}
					AutoCompleteDecorator.decorate(departureCity);
				} else {
					departureCity.removeAllItems();
					AutoCompleteDecorator.decorate(departureCity);
				}

			}
		});
	}

	private void createArrivalCountryList() {
		final Map<String, String> countriesMap2;
//		final Map<String, HashSet<String>> citiesMap2;
		countriesMap2 = CargoLocationManager.getInstance().getCountries();
//		citiesMap2 = new TreeMap<String, HashSet<String>>();

		this.arrivalCity = new JComboBox();
		this.arrivalCity.addItem("Miasto przyjazdu");

		Vector<String> arrivalCountryList = new Vector<String>();
		Vector<String> arrivalCountryListTemp = new Vector<String>(CargoLocationManager.getInstance().getCountries().keySet());
		arrivalCountryList.add("Kraj przyjazdu");
		arrivalCountryList.addAll(arrivalCountryListTemp);

		arrivalCountry = new JComboBox(arrivalCountryList);
		AutoCompleteDecorator.decorate(arrivalCountry);
		arrivalCountry.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (arrivalCountry.getSelectedIndex() != 0) {
					String country = (String) arrivalCountry.getSelectedItem();
					String country_iso = countriesMap2.get(country);
					List<String> cities = CargoLocationManager.getInstance().getCities(country_iso);
					arrivalCity.removeAllItems();
					for(String city: cities){
						arrivalCity.addItem(city);						
					}
					AutoCompleteDecorator.decorate(arrivalCity);
				} else {
					arrivalCity.removeAllItems();
					AutoCompleteDecorator.decorate(arrivalCity);
				}

			}
		});
	}

	private void createFooter(ImageIcon pasekStopkaTlo) {
		windowFooter = new BackgroundPanel();
		((BackgroundPanel) windowFooter).setBackgroundImage(pasekStopkaTlo);
		windowFooter.setLayout(new GridBagLayout());
		windowFooter.setPreferredSize(new Dimension(10, 43));
		windowFooter.setMaximumSize(new Dimension(1100, 43));
		windowFooter.setMinimumSize(new Dimension(1100, 43));

		ImageIcon dodaj_ogloszenie_Icon = SparkRes.getImageIcon(SparkRes.CARGO_DODAJ_OGLOSZENIE_OFF);
		JButton dodaj_ogloszenieButton = new JButton(dodaj_ogloszenie_Icon);
		dodaj_ogloszenieButton.setRolloverIcon(SparkRes.getImageIcon(SparkRes.CARGO_DODAJ_OGLOSZENIE_ON));
		dodaj_ogloszenieButton.setBorderPainted(false);
		dodaj_ogloszenieButton.setContentAreaFilled(false);
		dodaj_ogloszenieButton.setFocusPainted(false);
		
		dodaj_ogloszenieButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//CargoOffer cargoOffer = constructCargoOffer();
				//OfferWindowConfirmation offerWindowConfirmation = new OfferWindowConfirmation(cargoOffer, window);
				//offerWindowConfirmation.setVisible(true);
			}
		});

		windowFooter.add(dodaj_ogloszenieButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

	}

	private void createHeader(ImageIcon pasekMenuTlo, ImageIcon komentarzIcon) {
		windowHeader = new BackgroundPanel();
		((BackgroundPanel) windowHeader).setBackgroundImage(pasekMenuTlo);
		windowHeader.setLayout(new GridBagLayout());
		windowHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0)); // TUTAJ
		// TRZEBA
		// POGRZEBAC
		windowHeader.setPreferredSize(new Dimension(10, 71));
		windowHeader.setMaximumSize(new Dimension(1100, 71));
		windowHeader.setMinimumSize(new Dimension(1100, 71));

		windowHeader.add(new JLabel(komentarzIcon), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

	}
	
	public CargoOffer constructCargoOffer(){
		CargoOffer cargoOffer = new CargoOffer();

		CargoLocationManager locationManager = CargoLocationManager.getInstance();

		if(departureCountry.getSelectedItem() != null){
			cargoOffer.setCountryFrom(locationManager.getCountries().get(departureCountry.getSelectedItem().toString()));
		}

		if(departureCity.getSelectedItem() != null){
			cargoOffer.setCityFrom(departureCity.getSelectedItem().toString());
		}
		
		cargoOffer.setPostOfficeFrom(this.departurePo.getText());

		if(arrivalCountry.getSelectedItem() != null){
			cargoOffer.setCountryTo(locationManager.getCountries().get(arrivalCountry.getSelectedItem().toString()));
		}
		
		if(arrivalCity.getSelectedItem() != null){
			cargoOffer.setCityTo(arrivalCity.getSelectedItem().toString());
			
		}
		cargoOffer.setPostOfficeTo(this.arrivalPo.getText());
		
		cargoOffer.setAvailableFrom(this.freeFromDatePicker.getDate());
		cargoOffer.setAvailableTo(this.freeFromDatePicker.getDate());
		cargoOffer.setOfferValid(offerValidDatePicker.getDate());
		
		cargoOffer.setPrice(this.price.getText());
		cargoOffer.setWeight(this.weight.getText());
		cargoOffer.setCargoLength(this.length.getText());
		cargoOffer.setBody(this.body.getSelectedItem().toString());
		cargoOffer.setComment(this.comment.getText());
	
		return cargoOffer;
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new DebtorsWindow().setVisible(true);
			}
		});
	}

}
