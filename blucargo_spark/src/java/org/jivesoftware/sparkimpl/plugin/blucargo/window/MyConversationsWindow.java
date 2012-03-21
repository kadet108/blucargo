package org.jivesoftware.sparkimpl.plugin.blucargo.window;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jivesoftware.resource.SparkRes;
import org.jivesoftware.spark.SparkManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.manager.CargoDataManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.manager.CargoLocationManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.resourcebundle.StrAccessor;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.BackgroundPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.RoundedPanel;
import org.jivesoftware.sparkimpl.plugin.transcripts.ChatTranscriptPlugin;

import com.blucargo.model.CargoOffer;
import com.blucargo.model.City;

public class MyConversationsWindow extends JPanel {

	/**
	 * 
	 */
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
	
	private JTextField price = new JTextField(5);
	private JTextField weight = new JTextField(5);
	private JTextField length = new JTextField(5);
//	private JTextField body = new JTextField(5);
	private JComboBox body = new JComboBox();
	
	private Map<String, String> countriesMap = CargoLocationManager.getInstance().getCountries();
	private Map<String, HashSet<String>> citiesMap = new TreeMap<String, HashSet<String>>();
	private Map<String, String> countriesMap2 = CargoLocationManager.getInstance().getCountries();
	private Map<String, HashSet<String>> citiesMap2 = new TreeMap<String, HashSet<String>>();

	Color color = new Color(242, 242, 242, 255);

	Color color2 = new Color(208, 203, 181);
	JPanel mainArea = new JPanel();
	JPanel windowHeader = new JPanel();
	JPanel panel1a = new JPanel();
	JPanel windowMain = new JPanel();
	JPanel windowFooter = new JPanel();
	JPanel windowMainFillBox = new JPanel();
	
	JPanel windowMainFill = new RoundedPanel(color2);
	JScrollPane windowMainScroll = new JScrollPane(windowMainFillBox);
	
	JPanel panel5 = new JPanel();
	JPanel panel6 = new JPanel();
	JPanel panel7 = new JPanel();
	JPanel panel8 = new JPanel();
	JPanel panel9 = new JPanel();
	JPanel panel10 = new JPanel();
	JPanel panel11 = new JPanel();
	JPanel panel12 = new JPanel();
	MyConversationsWindow window = this;

	public MyConversationsWindow() {
		
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

		ImageIcon mojeRozmowyIcon1 = null;
		mojeRozmowyIcon1 = SparkRes.getImageIcon(SparkRes.CARGO_MOJE_ROZMOWY_HEAD1);
			
		ImageIcon mojeRozmowyIcon2 = null;
		mojeRozmowyIcon2 = SparkRes.getImageIcon(SparkRes.CARGO_MOJE_ROZMOWY_HEAD2);

		createHeader(pasekMenuTlo, mojeRozmowyIcon1, mojeRozmowyIcon2);
		createFooter(pasekStopkaTlo);
		createMain();

		//mainArea.add(windowHeader, 		new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		mainArea.add(windowMain, 		new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		//mainArea.add(windowFooter, 		new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

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
		//windowMain.setBackground(new Color(0, 0, 0));
		windowMain.setBackground(new Color(130, 145, 164));
		windowMainScroll.setBorder(null);
		windowMainScroll.setHorizontalScrollBar(null);
		windowMain.add(windowMainScroll, new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		windowMainFillBox.setLayout(new GridBagLayout());
		Color kolorekBoxa = new Color(131, 145, 165);
		windowMainFillBox.setBackground(kolorekBoxa);
		
		windowMainFillBox.add(windowMainFill, new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0));
		
		windowMainFill.setLayout(new GridBagLayout());

		windowMainFill.setPreferredSize	(new Dimension(900, 270));
		windowMainFill.setMaximumSize	(new Dimension(900, 270));
		windowMainFill.setMinimumSize	(new Dimension(900, 270));
		
		
		
		
		JPanel column1 							= new JPanel();
		column1.setBackground(new Color(208, 203, 181));
		column1.setLayout(new GridBagLayout());
		
		File transcriptFolder = new File(SparkManager.getUserDirectory(), "transcripts/"); //$NON-NLS-1$

		

		Set<String> userList = new TreeSet<String>();
		
		Pattern pattern = Pattern.compile("^.@??"); //$NON-NLS-1$
		Matcher matcher = null;
		
		if ((transcriptFolder != null) && (transcriptFolder.listFiles() != null)) {			
			for (File file : transcriptFolder.listFiles()) {
				matcher = pattern.matcher(file.getName());
				if (matcher.find()) {
					String s = matcher.group();
					userList.add(s);
				}
			}
		}
				
		JLabel lp_header = new JLabel("<html><b>"+StrAccessor.getString("MyConversationsWindow.index")+"</b></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		lp_header.setPreferredSize(new Dimension(50, 29));
		lp_header.setMaximumSize(new Dimension(50, 29));
		lp_header.setMinimumSize(new Dimension(50, 29));
		
		column1.add(lp_header, 			new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));

		Integer i=1;
		
		for (String string : userList) {
			JLabel nr 								= new JLabel(i.toString());
			nr.setPreferredSize(new Dimension(50, 29));
			nr.setMaximumSize(new Dimension(50, 29));
			nr.setMinimumSize(new Dimension(50, 29));
			column1.add(nr, 				new GridBagConstraints(0, i, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
			i++;
		}
		
		JPanel column2 							= new JPanel();
		column2.setBackground(new Color(208, 203, 181));
		column2.setLayout(new GridBagLayout());
		
		JLabel nazwa_uzytkownika 				= new JLabel("<html><b>"+StrAccessor.getString("MyConversationsWindow.userName")+"</b></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		nazwa_uzytkownika.setPreferredSize(new Dimension(200, 29));
		nazwa_uzytkownika.setMaximumSize(new Dimension(200, 29));
		nazwa_uzytkownika.setMinimumSize(new Dimension(200, 29));
		
		column2.add(nazwa_uzytkownika, 			new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));

		i = 1;
		for (String string : userList) {
			JLabel name	 						= new JLabel(string);
			name.setPreferredSize(new Dimension(200, 29));
			name.setMaximumSize(new Dimension(200, 29));
			name.setMinimumSize(new Dimension(200, 29));
			column2.add(name, 						new GridBagConstraints(0, i, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
			i++;
		}
		
		JPanel column3 							= new JPanel();
		column3.setBackground(new Color(208, 203, 181));
		column3.setLayout(new GridBagLayout());
		
		JLabel nazwa_firmy 						= new JLabel("<html><b>"+StrAccessor.getString("MyConversationsWindow.companyName")+"</b></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		nazwa_firmy.setPreferredSize(new Dimension(200, 29));
		nazwa_firmy.setMaximumSize(new Dimension(200, 29));
		nazwa_firmy.setMinimumSize(new Dimension(200, 29));
		
		column3.add(nazwa_firmy, 			new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));

		i = 1;
		for (String string : userList) {
			
			String company = CargoDataManager.getInstance().getUserVCardProperty(string, "ORG:ORGNAME"); //$NON-NLS-1$
			
			JLabel firma 							= new JLabel(company);
			firma.setPreferredSize(new Dimension(200, 29));
			firma.setMaximumSize(new Dimension(200, 29));
			firma.setMinimumSize(new Dimension(200, 29));

			column3.add(firma, 						new GridBagConstraints(0, i, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
			i++;
		}
		
		JPanel column4 							= new JPanel();
		column4.setBackground(new Color(208, 203, 181));
		column4.setLayout(new GridBagLayout());
		
		JLabel pusty_header 					= new JLabel(""); //$NON-NLS-1$
		pusty_header.setPreferredSize(new Dimension(200, 29));
		pusty_header.setMaximumSize(new Dimension(200, 29));
		pusty_header.setMinimumSize(new Dimension(200, 29));

		ImageIcon historiaIcon1 = SparkRes.getImageIcon(SparkRes.CARGO_HISTORIA_ROZMOW1);
		ImageIcon historiaIcon2 = SparkRes.getImageIcon(SparkRes.CARGO_HISTORIA_ROZMOW2);

		final ChatTranscriptPlugin transcriptPlugin = new ChatTranscriptPlugin();
		
		column4.add(pusty_header, 					new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		
		i = 1;
		for (String string : userList) {
			JButton historiaRozmowButton = new JButton("<html><font color='white'>"+StrAccessor.getString("MyConversationsWindow.chatHistory")+"</font></html>",historiaIcon1); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			historiaRozmowButton.setVerticalTextPosition(SwingConstants.CENTER);
			historiaRozmowButton.setHorizontalTextPosition(SwingConstants.CENTER);
			historiaRozmowButton.setRolloverIcon(historiaIcon2);
			historiaRozmowButton.setOpaque(false);
			historiaRozmowButton.setBorder(null);
			historiaRozmowButton.setBorderPainted(false);
			historiaRozmowButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			final String jid = string + "@" + SparkManager.getSessionManager().getServerAddress(); //$NON-NLS-1$
			historiaRozmowButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					transcriptPlugin.showHistory(jid);
				}
			});
			
			column4.add(historiaRozmowButton, 						new GridBagConstraints(0, i, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
			i++;
		}
				
		//mam 4 kolumny
		windowMainFill.add(column1, 		new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(10, 10, 10, 10), 0, 0));
		windowMainFill.add(column2, 		new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(10, 10, 10, 10), 0, 0));
		windowMainFill.add(column3, 		new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(10, 10, 10, 10), 0, 0));
		windowMainFill.add(column4, 		new GridBagConstraints(3, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(10, 10, 10, 10), 0, 0));
	}

	private void createDepartureCountryList() {
		final Map<String, String> countriesMap;
		final Map<String, HashSet<String>> citiesMap;
		countriesMap = CargoLocationManager.getInstance().getCountries();
		citiesMap = new TreeMap<String, HashSet<String>>();

		this.departureCity = new JComboBox();
		this.departureCity.addItem(StrAccessor.getString("MyConversationsWindow.comboBox.item.departureCity")); //$NON-NLS-1$

		Vector<String> departureCountryList = new Vector<String>();
		Vector<String> departureCountryListTemp = new Vector<String>(CargoLocationManager.getInstance().getCountries().keySet());
		departureCountryList.add(StrAccessor.getString("MyConversationsWindow.departureCountryList")); //$NON-NLS-1$
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
		final Map<String, HashSet<String>> citiesMap2;
		countriesMap2 = CargoLocationManager.getInstance().getCountries();
		citiesMap2 = new TreeMap<String, HashSet<String>>();

		this.arrivalCity = new JComboBox();
		this.arrivalCity.addItem(StrAccessor.getString("MyConversationsWindow.comboBox.item.arrivalCity")); //$NON-NLS-1$

		Vector<String> arrivalCountryList = new Vector<String>();
		Vector<String> arrivalCountryListTemp = new Vector<String>(CargoLocationManager.getInstance().getCountries().keySet());
		arrivalCountryList.add(StrAccessor.getString("MyConversationsWindow.arrivalCountryList")); //$NON-NLS-1$
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
		windowFooter.setMaximumSize(new Dimension(900, 43));
		windowFooter.setMinimumSize(new Dimension(900, 43));

	}

	private void createHeader(ImageIcon pasekMenuTlo, ImageIcon mojeRozmowyIcon1, ImageIcon mojeRozmowyIcon2) {
		windowHeader = new BackgroundPanel();
		((BackgroundPanel) windowHeader).setBackgroundImage(pasekMenuTlo);
		windowHeader.setLayout(new GridBagLayout());
		windowHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0)); // TUTAJ
		// TRZEBA
		// POGRZEBAC
		windowHeader.setPreferredSize(new Dimension(10, 71));
		windowHeader.setMaximumSize(new Dimension(900, 71));
		windowHeader.setMinimumSize(new Dimension(900, 71));

		windowHeader.add(new JLabel(mojeRozmowyIcon1), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowHeader.add(new JLabel(mojeRozmowyIcon2), new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

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
				new MyConversationsWindow().setVisible(true);
			}
		});
	}

}
