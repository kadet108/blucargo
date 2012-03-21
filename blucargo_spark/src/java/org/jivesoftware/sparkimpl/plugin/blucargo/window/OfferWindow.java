package org.jivesoftware.sparkimpl.plugin.blucargo.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jivesoftware.resource.SparkRes;
import org.jivesoftware.sparkimpl.plugin.blucargo.HTMLtag;
import org.jivesoftware.sparkimpl.plugin.blucargo.BlucargoPlugin;
import org.jivesoftware.sparkimpl.plugin.blucargo.manager.CargoDataManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.manager.CargoLocationManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.resourcebundle.StrAccessor;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.BackgroundPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.RoundedPanel;

import com.blucargo.model.CarBody;
import com.blucargo.model.CargoOffer;
import com.blucargo.model.OfferType;
import com.toedter.calendar.JDateChooser;

public class OfferWindow extends JPanel {

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
	
	private JTextArea comment;
	
	private JTextField price 							= new JTextField(5);
	private JTextField weight 							= new JTextField(5);
	private JTextField length 							= new JTextField(5);
	private JComboBox body 								= new JComboBox();
	
	private JLabel freeFromLabel;
	private JLabel freeToLabel;
	private JLabel offerValidLabel;
	
	JDateChooser freeFromDatePicker;
	JDateChooser freeToDatePicker;
	JDateChooser offerValidDatePicker;
	
	private Map<String, String> countriesMap 			= CargoLocationManager.getInstance().getCountries();
	private Map<String, HashSet<String>> citiesMap 		= new TreeMap<String, HashSet<String>>();
	private Map<String, String> countriesMap2 			= CargoLocationManager.getInstance().getCountries();
	private Map<String, HashSet<String>> citiesMap2 	= new TreeMap<String, HashSet<String>>();

	Color color = new Color(242, 242, 242, 255);

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
	OfferWindow window 			= this;

	private OfferType offerType;
	public OfferWindow(OfferType offerType) {

		initialize();

		this.setBackground(Color.WHITE);
		this.setLayout(new GridBagLayout());
		this.add(mainArea, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		mainArea.setLayout(new GridBagLayout());
		mainArea.setBackground(Color.WHITE);

		ImageIcon blueTopImage 		= SparkRes.getImageIcon(SparkRes.CARGO_NIEBIESKI_PASEK);
		ImageIcon pasekMenuTlo 		= SparkRes.getImageIcon(SparkRes.CARGO_PASEK_MENU_TLO);
		ImageIcon pasekStopkaTlo 	= SparkRes.getImageIcon(SparkRes.CARGO_PASEK_STOPKA_TLO);
		ImageIcon greyGradientImage = SparkRes.getImageIcon(SparkRes.CARGO_PASEK_GORA);
		ImageIcon dolnyPasek 		= SparkRes.getImageIcon(SparkRes.CARGO_DOLNY_PASEK);

		ImageIcon komentarzIcon = null;
/*		if(vehicleCargo.equals("cargo")){
			komentarzIcon = SparkRes.getImageIcon(SparkRes.CARGO_ZGLOS_LADUNEK_HEADER);
		}
		else if (vehicleCargo.equals("vehicle")){
			komentarzIcon = SparkRes.getImageIcon(SparkRes.CARGO_ZGLOS_POJAZD_HEADER);
		}
		
		ImageIcon obrazekIcon = null;
		if(vehicleCargo.equals("cargo")){
			obrazekIcon = SparkRes.getImageIcon(SparkRes.CARGO_LADUNEK_HEAD);
		}
		else if (vehicleCargo.equals("vehicle")){
			obrazekIcon = SparkRes.getImageIcon(SparkRes.CARGO_POJAZD_HEAD);
		}
*/
		
		if(offerType == OfferType.CARGO){
			komentarzIcon = SparkRes.getImageIcon(SparkRes.CARGO_ZGLOS_LADUNEK_HEADER);
		}
		else if (offerType == OfferType.VEHICLE){
			komentarzIcon = SparkRes.getImageIcon(SparkRes.CARGO_ZGLOS_POJAZD_HEADER);
		}
		
		ImageIcon obrazekIcon = null;
		if(offerType == OfferType.CARGO){
			obrazekIcon = SparkRes.getImageIcon(SparkRes.CARGO_LADUNEK_HEAD);
		}
		else if (offerType == OfferType.VEHICLE){
			obrazekIcon = SparkRes.getImageIcon(SparkRes.CARGO_POJAZD_HEAD);
		}

        this.offerType = offerType;
		
		createHeader(pasekMenuTlo, obrazekIcon, komentarzIcon);
		createFooter(pasekStopkaTlo);
		createMain();

		//mainArea.add(windowHeader, 		new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
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
		
		ImageIcon pasekMenuTlo 		= SparkRes.getImageIcon(SparkRes.CARGO_TLO1PX);
		windowMain = new BackgroundPanel();
		((BackgroundPanel) windowMain).setBackgroundImage(pasekMenuTlo);

		createDepartureCountryList();
		createArrivalCountryList();

//		MigLayout migLayout = new MigLayout(
//				"", // Layout Constraints
//				"[grow][][grow]", // Column constraints
//				"[][shrink 0]"); // Row constraints
		
		windowMain.setLayout(new BorderLayout());
		windowMain.setBackground(new Color(130, 145, 164));
//		windowMain.add(windowMainFill, new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(20, 20, 5, 20), 0, 0));
		windowMain.add(windowMainFill);

		windowMainFill.setPreferredSize	(new Dimension(1100, 400));
		windowMainFill.setMaximumSize	(new Dimension(1100, 400));
		windowMainFill.setMinimumSize	(new Dimension(1100, 400));

		JLabel basicLabel 						= new JLabel(HTMLtag.openingBold + StrAccessor.getString("OfferWindow.basicInformation") + HTMLtag.closingBold); //$NON-NLS-1$

		freeFromLabel 							= new JLabel(StrAccessor.getString("OfferWindow.freeFrom")); //$NON-NLS-1$
		freeFrom 								= new JTextField(5);

		freeFromDatePicker 						= new JDateChooser();
		
		freeToLabel 							= new JLabel(StrAccessor.getString("OfferWindow.freeTo")); //$NON-NLS-1$
		freeTo 									= new JTextField(5);

		freeToDatePicker 						= new JDateChooser();
		
		offerValidLabel 						= new JLabel(StrAccessor.getString("OfferWindow.offerValidTill")); //$NON-NLS-1$
		offerValid 								= new JTextField(5);

		offerValidDatePicker 					= new JDateChooser();
		
		JPanel column1 							= new JPanel();
		
		column1.setBackground(new Color(208, 203, 181));
		column1.setLayout(new GridBagLayout());
		
		JLabel departureHourLabel 				= new JLabel(StrAccessor.getString("OfferWindow.departureHour")); //$NON-NLS-1$
		SpinnerDateModel model 					= new SpinnerDateModel();
		departureHourSpinner 					= new JSpinner(model);
		JSpinner.DateEditor timeEditor 			= new JSpinner.DateEditor(departureHourSpinner, "HH:mm:ss"); //$NON-NLS-1$

		departureHourSpinner.setEditor(timeEditor);
		departureHourSpinner.setValue(new Date());

		JLabel arrivalHourLabel 				= new JLabel(StrAccessor.getString("OfferWindow.arrivalHour")); //$NON-NLS-1$
		SpinnerDateModel arrivalModel 			= new SpinnerDateModel();
		arrivalHourSpinner 						= new JSpinner(arrivalModel);
		JSpinner.DateEditor arrivalTimeEditor 	= new JSpinner.DateEditor(arrivalHourSpinner, "HH:mm:ss"); //$NON-NLS-1$

		arrivalHourSpinner.setEditor(arrivalTimeEditor);
		arrivalHourSpinner.setValue(new Date());

		column1.add(basicLabel, 			new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column1.add(freeFromLabel, 			new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column1.add(freeFromDatePicker, 	new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column1.add(departureHourLabel, 	new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column1.add(departureHourSpinner, 	new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.WEST, new Insets(10, 10, 0, 10), 0, 0));
		column1.add(freeToLabel, 			new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column1.add(freeToDatePicker, 		new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column1.add(arrivalHourLabel, 		new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column1.add(arrivalHourSpinner, 	new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.WEST, new Insets(10, 10, 0, 10), 0, 0));
		column1.add(offerValidLabel, 		new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column1.add(offerValidDatePicker, 	new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column1.add(new JLabel(""), 		new GridBagConstraints(0, 11, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0)); //$NON-NLS-1$

		JLabel fromLabel 					= new JLabel(HTMLtag.openingBold + StrAccessor.getString("OfferWindow.whereFrom")+ HTMLtag.closingBold); //$NON-NLS-1$

		JLabel countryLabel 				= new JLabel(StrAccessor.getString("OfferWindow.country"));				//$NON-NLS-1$
		JLabel cityLabel 					= new JLabel(StrAccessor.getString("OfferWindow.city"));				//$NON-NLS-1$
		JLabel departurePoLabel 			= new JLabel(StrAccessor.getString("OfferWindow.departurePostCode"));	//$NON-NLS-1$
		departurePo 						= new JTextField(5);
		
		JPanel column2 						= new JPanel();
		
		column2.setBackground(new Color(208, 203, 181));
		column2.setLayout(new GridBagLayout());

		column2.add(fromLabel, 				new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column2.add(countryLabel, 			new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column2.add(departureCountry, 		new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column2.add(cityLabel, 				new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column2.add(departureCity, 			new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column2.add(departurePoLabel, 		new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column2.add(departurePo, 			new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column2.add(new JLabel(""), 		new GridBagConstraints(0, 7, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0)); //$NON-NLS-1$

		JLabel toLabel 						= new JLabel(HTMLtag.openingBold + StrAccessor.getString("OfferWindow.whereTo")+ HTMLtag.closingBold); //$NON-NLS-1$

		JLabel country2Label 				= new JLabel(StrAccessor.getString("OfferWindow.country"));			//$NON-NLS-1$
		JLabel city2Label 					= new JLabel(StrAccessor.getString("OfferWindow.city"));			//$NON-NLS-1$
		JLabel arrivalPoLabel 				= new JLabel(StrAccessor.getString("OfferWindow.arrivalPostCode"));	//$NON-NLS-1$
		arrivalPo 							= new JTextField(5);

		JPanel column3 						= new JPanel();
		column3.setBackground(new Color(208, 203, 181));
		column3.setLayout(new GridBagLayout());

		column3.add(toLabel, 				new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column3.add(country2Label, 			new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column3.add(arrivalCountry, 		new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column3.add(city2Label, 			new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column3.add(arrivalCity, 			new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column3.add(arrivalPoLabel, 		new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column3.add(arrivalPo, 				new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column3.add(new JLabel(""), 		new GridBagConstraints(0, 7, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0)); //$NON-NLS-1$

		JLabel additionalLabel 				= new JLabel(HTMLtag.openingBold + StrAccessor.getString("OfferWindow.furtherInfo") + HTMLtag.closingBold); //$NON-NLS-1$
		JLabel priceLabel 					= new JLabel(StrAccessor.getString("OfferWindow.freightPrice")); //$NON-NLS-1$
		price 								= new JTextField(5);
		JLabel weightLabel 					= new JLabel(StrAccessor.getString("OfferWindow.weight"));	//$NON-NLS-1$
		weight 								= new JTextField(5);
		JLabel lengthLabel 					= new JLabel(StrAccessor.getString("OfferWindow.length"));	//$NON-NLS-1$
		length 								= new JTextField(5);	
		
		JLabel bodyLabel 					= new JLabel(StrAccessor.getString("OfferWindow.body"));	//$NON-NLS-1$

		body = new JComboBox();
		List<CarBody> bodies 				= CargoDataManager.getInstance().getBodies();
		for (CarBody b : bodies) {
			body.addItem(b.getName());
		}
		AutoCompleteDecorator.decorate(body);
		
		JLabel descriptionLabel 			= new JLabel(StrAccessor.getString("OfferWindow.remarks")); //$NON-NLS-1$
		comment 							= new JTextArea();
		comment.setRows(6);
		comment.setWrapStyleWord(true);
		comment.setLineWrap(true);

		JPanel column4 						= new JPanel();
		column4.setBackground(new Color(208, 203, 181));
		column4.setLayout(new GridBagLayout());

		column4.add(additionalLabel, 		new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column4.add(bodyLabel, 				new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column4.add(body, 					new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column4.add(priceLabel, 			new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column4.add(price, 					new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column4.add(weightLabel, 			new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column4.add(weight, 				new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column4.add(lengthLabel, 			new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column4.add(length, 				new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column4.add(descriptionLabel, 		new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column4.add(comment, 				new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column4.add(new JLabel(""), 		new GridBagConstraints(0, 11, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0)); //$NON-NLS-1$

		windowMainFill.add(column1, 		new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		windowMainFill.add(column2, 		new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		windowMainFill.add(column3, 		new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		windowMainFill.add(column4, 		new GridBagConstraints(3, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
	}

	private boolean validateWindow(){
		boolean correct = true;
		
		Border redBorder = new LineBorder(Color.red);
		
		if (departureCountry.getSelectedIndex()==0){
			departureCountry.setBorder(new LineBorder(Color.red));
			correct = false;
		}
		else {
			departureCountry.setBorder(null);
		}
		
		if (departureCity.getSelectedIndex()==0) {
			departureCity.setBorder(new LineBorder(Color.red));
			correct = false;
		} else {
			departureCity.setBorder(null);
		}

		if (arrivalCountry.getSelectedIndex()==0) {
			arrivalCountry.setBorder(new LineBorder(Color.red));
			correct = false;
		} else {
			arrivalCountry.setBorder(null);
		}
		
		if (arrivalCity.getSelectedIndex()==0) {
			arrivalCity.setBorder(new LineBorder(Color.red));
			correct = false;
		} else {
			arrivalCity.setBorder(null);
		}
		
		String departurePOString = departurePo.getText();
		
		if (departurePOString ==null || departurePOString.isEmpty()) {
			departurePo.setBorder(redBorder);
			correct = false;
		}
		else {
			departurePo.setBorder(null);
		}

		String arrivalPoString = arrivalPo .getText();
		
		if (arrivalPoString == null || arrivalPoString.isEmpty()) {
			arrivalPo.setBorder(redBorder);
			correct = false;
		} else {
			arrivalPo.setBorder(null);
		}

		String priceString = price.getText();
		
		if (priceString == null || priceString.isEmpty()) {
			price.setBorder(redBorder);
			correct = false;
		} else {
			price.setBorder(null);
		}

		String weightString = weight.getText();
		
		if (weightString == null || weightString.isEmpty()) {
			weight.setBorder(redBorder);
			correct = false;
		} else {
			weight.setBorder(null);
		}

		String lengthString = length.getText();
		
		if (lengthString == null || lengthString.isEmpty()) {
			length.setBorder(redBorder);
			correct = false;
		} else {
			length.setBorder(null);
		}
		
		if (body.getSelectedIndex()==0) {
			body.setBorder(redBorder);
			correct = false;
		} else {
			body.setBorder(null);
		}
		
		if ( freeFromDatePicker == null || freeFromDatePicker.getDate() == null) {
			freeFromDatePicker.setBorder(redBorder);
			correct = false;
		} else {
			freeFromDatePicker.setBorder(null);
		}

		if ( freeToDatePicker == null || freeToDatePicker.getDate() == null ) {
			freeToDatePicker.setBorder(redBorder);
			correct = false;
		} else {
			freeToDatePicker.setBorder(null);
		}
		
		if ( offerValidDatePicker == null || offerValidDatePicker.getDate() == null ) {
			offerValidDatePicker.setBorder(redBorder);
			correct = false;
		} else {
			offerValidDatePicker.setBorder(null);
		}
		
		return correct;
	}
	
	private void createDepartureCountryList() {
		final Map<String, String> countriesMap;
		final Map<String, HashSet<String>> citiesMap;
		
		countriesMap 							= CargoLocationManager.getInstance().getCountries();
		citiesMap 								= new TreeMap<String, HashSet<String>>();

		this.departureCity 						= new JComboBox();
		this.departureCity.addItem(StrAccessor.getString("OfferWindow.departureCity"));		//$NON-NLS-1$

		Vector<String> departureCountryList 	= new Vector<String>();
		Vector<String> departureCountryListTemp = new Vector<String>(CargoLocationManager.getInstance().getCountries().keySet());

		departureCountryList.add(StrAccessor.getString("OfferWindow.departureCountry"));	//$NON-NLS-1$
		departureCountryList.addAll(departureCountryListTemp);

		departureCountry 						= new JComboBox(departureCountryList);

		AutoCompleteDecorator.decorate(departureCountry);
		departureCountry.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (departureCountry.getSelectedIndex() != 0) {
					String country 				= (String) departureCountry.getSelectedItem();
					String country_iso 			= countriesMap.get(country);
					List<String> cities 		= CargoLocationManager.getInstance().getCities(country_iso);
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
		final Map<String, HashSet<String>> citiesMap2;

		countriesMap2 							= CargoLocationManager.getInstance().getCountries();
		citiesMap2 								= new TreeMap<String, HashSet<String>>();

		this.arrivalCity 						= new JComboBox();
		this.arrivalCity.addItem(StrAccessor.getString("OfferWindow.arrivalCity")); //$NON-NLS-1$

		Vector<String> arrivalCountryList 		= new Vector<String>();
		Vector<String> arrivalCountryListTemp 	= new Vector<String>(CargoLocationManager.getInstance().getCountries().keySet());
		arrivalCountryList.add(StrAccessor.getString("OfferWindow.arrivalCountry")); //$NON-NLS-1$
		arrivalCountryList.addAll(arrivalCountryListTemp);

		arrivalCountry 							= new JComboBox(arrivalCountryList);
		AutoCompleteDecorator.decorate(arrivalCountry);
		arrivalCountry.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (arrivalCountry.getSelectedIndex() != 0) {
					String country 				= (String) arrivalCountry.getSelectedItem();
					String country_iso 			= countriesMap2.get(country);
					List<String> cities 			= CargoLocationManager.getInstance().getCities(country_iso);
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
		
		ImageIcon pasekMenuTlo 		= SparkRes.getImageIcon(SparkRes.CARGO_TLO1PX);
		windowFooter = new BackgroundPanel();
		((BackgroundPanel) windowFooter).setBackgroundImage(pasekMenuTlo);
		
		//windowFooter = new BackgroundPanel();
		//((BackgroundPanel) windowFooter).setBackgroundImage(pasekStopkaTlo);
		windowFooter.setLayout(new GridBagLayout());
		windowFooter.setPreferredSize(new Dimension(10, 43));
		windowFooter.setMaximumSize(new Dimension(1100, 43));
		windowFooter.setMinimumSize(new Dimension(1100, 43));

		JButton addOfferButton;
		addOfferButton = new JButton(HTMLtag.openingWhite + StrAccessor.getString("OfferWindow.addAdvert") + HTMLtag.closingFont); //$NON-NLS-1$
		addOfferButton.setName("dodaj_ogloszenieButton");
		addOfferButton.setVerticalTextPosition(SwingConstants.CENTER);
		addOfferButton.setHorizontalTextPosition(SwingConstants.CENTER);
		addOfferButton.setIcon					(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY1));
		addOfferButton.setRolloverIcon			(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY2));
		addOfferButton.setRolloverSelectedIcon	(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY2));
		addOfferButton.setBorderPainted(false);
		addOfferButton.setContentAreaFilled(false);
		addOfferButton.setFocusPainted(false);
		addOfferButton.setOpaque(false);
		addOfferButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		
		addOfferButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (!validateWindow()) {
					JOptionPane.showMessageDialog(null, StrAccessor.getString("OfferWindow.incorrectDetailsMessage")); //$NON-NLS-1$
					return;
				}
				
				CargoOffer cargoOffer = constructCargoOffer();
				OfferConfirmationWindow offerWindowConfirmation = new OfferConfirmationWindow(cargoOffer, window);
				
				//TODO przejscie na okno g\u0142\u00f3wne.
				//offerWindowConfirmation.setVisible(true);
				
				BlucargoPlugin.AddOrSelectTab(StrAccessor.getString("OfferWindow.offerConfirmation"), offerWindowConfirmation); //$NON-NLS-1$

			}
		});

		windowFooter.add(addOfferButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

	}

	private void createHeader(ImageIcon pasekMenuTlo, ImageIcon obrazekIcon, ImageIcon komentarzIcon) {
		windowHeader = new BackgroundPanel();
		((BackgroundPanel) windowHeader).setBackgroundImage(pasekMenuTlo);
		windowHeader.setLayout(new GridBagLayout());
		windowHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0)); // TUTAJ
		// TRZEBA
		// POGRZEBAC
		windowHeader.setPreferredSize(new Dimension(10, 71));
		windowHeader.setMaximumSize(new Dimension(1100, 71));
		windowHeader.setMinimumSize(new Dimension(1100, 71));

		//windowHeader.add(new JLabel(komentarzIcon), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		windowHeader.add(new JLabel(obrazekIcon), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowHeader.add(new JLabel(komentarzIcon), new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		
	}
	
	public CargoOffer constructCargoOffer(){
		CargoOffer cargoOffer = new CargoOffer();
		
	    cargoOffer.setType(this.offerType);			

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
		cargoOffer.setSubmissionDate(new Date());
	
		return cargoOffer;
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new OfferWindow(OfferType.CARGO).setVisible(true);
			}
		});
	}

}
