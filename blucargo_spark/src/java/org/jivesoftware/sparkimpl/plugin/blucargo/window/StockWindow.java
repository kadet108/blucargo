package org.jivesoftware.sparkimpl.plugin.blucargo.window;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.JTableHeader;

import org.apache.commons.lang.StringUtils;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jivesoftware.resource.SparkRes;
import org.jivesoftware.sparkimpl.plugin.blucargo.HTMLtag;
import org.jivesoftware.sparkimpl.plugin.blucargo.manager.CargoDataManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.manager.CargoLazyLoadingManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.manager.CargoLocationManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.manager.CargoSearchManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.resourcebundle.StrAccessor;
import org.jivesoftware.sparkimpl.plugin.blucargo.table.BlucargoSortableTableModel;
import org.jivesoftware.sparkimpl.plugin.blucargo.table.BlucargoTable;
import org.jivesoftware.sparkimpl.plugin.blucargo.table.HeaderListener;
import org.jivesoftware.sparkimpl.plugin.blucargo.table.misc.RefreshTask;
import org.jivesoftware.sparkimpl.plugin.blucargo.table.misc.SortButtonRenderer;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.BackgroundPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.AcceptedOffersBodyPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.AcceptedOffersContactPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.AcceptedOffersInfoPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.AcceptedOffersLoadPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.AcceptedOffersOfferValidPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.AcceptedOffersSubmissionDatePanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.AcceptedOffersUnloadPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.CargoOffersBodyPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.CargoOffersContactPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.CargoOffersInfoPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.CargoOffersLoadPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.CargoOffersOfferValidPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.CargoOffersSubmissionDatePanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.CargoOffersUnloadPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.EndedOffersBodyPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.EndedOffersContactPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.EndedOffersInfoPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.EndedOffersLoadPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.EndedOffersOfferValidPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.EndedOffersSubmissionDatePanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.EndedOffersUnloadPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.FavouriteOffersBodyPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.FavouriteOffersContactPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.FavouriteOffersInfoPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.FavouriteOffersLoadPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.FavouriteOffersOfferValidPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.FavouriteOffersSubmissionDatePanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.FavouriteOffersUnloadPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.menu.ContactMenuItem;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.util.SearchCriteriaHelper;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.util.StockPaneUI;

import com.blucargo.model.CarBody;
import com.blucargo.model.OfferType;
import com.blucargo.model.SearchCriteria;
import com.google.gson.Gson;


public class StockWindow extends javax.swing.JPanel {

	private static final long serialVersionUID 	= -1505676586330308123L;

	private final int DATA_INITIAL_REFRESH 		= 1000;
	private final int DATA_REFRESH_PERIOD 		= 80000;

	//private BackgroundPanel commandPanel;
	private BackgroundPanel bottomPanel;
	
	private JPanel 		searchPanel;
	private JComboBox 	criteriaSearch;
	private JTextField 	criteriaName;
	
	private JComboBox 	departureCountry;
	private JComboBox 	departureCity;
	private JComboBox 	arrivalCountry;
	private JComboBox 	arrivalCity;
	private JComboBox 	body;
	private JComboBox 	weight;
	
	private JButton 	clear;
	private JButton 	search;
	private JButton 	advanced;
	
	private Map<String, String> countriesMap;

	private JPanel tabbedPanel 			= new JPanel();
	private JTabbedPane innerTabbedPane = null;
	private JPanel panel1 				= new JPanel();
	private JPanel panel2 				= new JPanel();
	private JPanel panel3 				= new JPanel();
	private JPanel panel10 				= new JPanel();
	
	private JButton pageUpButton 		= new JButton();
	private JButton pageDownButton 		= new JButton();
	
	private JTextField pageSizeTextField = new JTextField();

	private JPopupMenu contextMenu;
	
	private final BlucargoSortableTableModel acceptedOffersModel 	= acceptedOffersModel();
	private final BlucargoSortableTableModel cargoOffersModel 		= cargoOffersModel();
	private final BlucargoSortableTableModel favouriteOffersModel 	= favouriteOffersModel();
	private final BlucargoSortableTableModel endedOffersModel 		= endedOffersModel();

	private List<SearchCriteria> searchCriteria;
	
	public StockWindow() {

		searchPanel 	= initializeSearchPanel();
		tabbedPanel 	= initializeTabbedPanel();
		bottomPanel 	= initializeBottomPanel();

		initializeCommandPanelSearchPanelTabbedPanelAndBottomPanel();
		CargoLazyLoadingManager.getInstance().lazyLoadCountries();
		CargoLazyLoadingManager.getInstance().lazyLoadCities();

	}
	
	private JPanel initializeTabbedPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBackground(new Color(131, 145, 165, 255));
		innerTabbedPane = new JTabbedPane();

		ArrayList<ImageIcon> icons = new ArrayList<ImageIcon>();
		icons.add(SparkRes.getImageIcon(SparkRes.CARGO_ZAKLADKI_PUSTE2));
		icons.add(SparkRes.getImageIcon(SparkRes.CARGO_ZAKLADKI_PUSTE1));
		icons.add(SparkRes.getImageIcon(SparkRes.CARGO_ZAKLADKI_PUSTE2));
		icons.add(SparkRes.getImageIcon(SparkRes.CARGO_ZAKLADKI_PUSTE1));
		icons.add(SparkRes.getImageIcon(SparkRes.CARGO_ZAKLADKI_PUSTE2));
		icons.add(SparkRes.getImageIcon(SparkRes.CARGO_ZAKLADKI_PUSTE1));
		icons.add(SparkRes.getImageIcon(SparkRes.CARGO_ZAKLADKI_PUSTE2));
		icons.add(SparkRes.getImageIcon(SparkRes.CARGO_ZAKLADKI_PUSTE1));

		StockPaneUI paneUI = new StockPaneUI();
		paneUI.setBackgroundColor(new Color(131, 145, 165, 255));
		paneUI.setIcons(icons);

		innerTabbedPane.setUI(paneUI);
		innerTabbedPane.setBackground(new Color(131, 145, 165, 255));
		innerTabbedPane.setOpaque(true);
		
		innerTabbedPane.addTab(StrAccessor.getString("StockWindow.tab.gielda"), 				initializeCargoOffersPanel());		//$NON-NLS-1$
		innerTabbedPane.addTab(StrAccessor.getString("StockWindow.tab.ulubioneOferty"), 		initializeFavouriteOffersPanel());	//$NON-NLS-1$
		innerTabbedPane.addTab(StrAccessor.getString("StockWindow.tab.zaakceptowaneOferty"), 	initializeAcceptedOffersPanel());	//$NON-NLS-1$
		innerTabbedPane.addTab(StrAccessor.getString("StockWindow.tab.zakonczoneOferty"), 	initializeEndedOffersPanel());			//$NON-NLS-1$
		
		innerTabbedPane.addMouseListener(new MouseListener() { 
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				innerTabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				innerTabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		innerTabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				pageSizeTextFieldAction();
			}
		});
		
		
		
		panel.add(innerTabbedPane, new GridBagConstraints(0, 0, 1, 1, 2.0, 2.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 0, 0, 0), 0, 0));
		panel.setComponentPopupMenu(contextMenu);

		return panel;
	}

	private BackgroundPanel initializeBottomPanel() {
		BackgroundPanel localBottomPanel = new BackgroundPanel();
		localBottomPanel.setBackgroundImage(SparkRes.getImageIcon(SparkRes.CARGO_PASEK_STOPKA_TLO));
		localBottomPanel.setMinimumSize(new Dimension(100, 43));
		localBottomPanel.setPreferredSize(new Dimension(100, 43));
		localBottomPanel.setLayout(new GridBagLayout());

		ImageIcon blankIcon = SparkRes.getImageIcon(SparkRes.CARGO_BLANK);
		
		ImageIcon poprzedniaIcon1 = SparkRes.getImageIcon(SparkRes.CARGO_POPRZEDNIA_PUSTA1);
		ImageIcon poprzedniaIcon2 = SparkRes.getImageIcon(SparkRes.CARGO_POPRZEDNIA_PUSTA2);
		//pageUpButton = new JButton("<HTMLtag.<font color='white'>Poprzednia strona</font></HTMLtag.",poprzedniaIcon1);
		//pageUpButton.setText("<HTMLtag.<font color='white'>Poprzednia strona</font></HTMLtag.");
		pageUpButton.setVerticalTextPosition(SwingConstants.CENTER);
		pageUpButton.setHorizontalTextPosition(SwingConstants.CENTER);
		pageUpButton.setIcon(poprzedniaIcon1);
		pageUpButton.setRolloverIcon(poprzedniaIcon2);
		pageUpButton.setOpaque(false);
		pageUpButton.setBorder(null);
		pageUpButton.setBorderPainted(false);
		pageUpButton.setDisabledIcon(blankIcon);
		pageUpButton.setBorderPainted(false);
		pageUpButton.setContentAreaFilled(false);
		pageUpButton.setFocusPainted(false);
		pageUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pageUpButton.setMinimumSize(new Dimension(200, 50));
				
		ImageIcon nastepnaIcon1 = SparkRes.getImageIcon(SparkRes.CARGO_NASTEPNA_PUSTA1);
		ImageIcon nastepnaIcon2 = SparkRes.getImageIcon(SparkRes.CARGO_NASTEPNA_PUSTA2);
		//pageDownButton = new JButton("<HTMLtag.<font color='white'>Nastepna strona</font></HTMLtag.",nastepnaIcon1);
		//pageDownButton.setText("<HTMLtag.<font color='white'>Nastepna strona</font></HTMLtag.");
		pageDownButton.setVerticalTextPosition(SwingConstants.CENTER);
		pageDownButton.setHorizontalTextPosition(SwingConstants.CENTER);
		pageDownButton.setIcon(nastepnaIcon1);
		pageDownButton.setRolloverIcon(nastepnaIcon2);
		pageDownButton.setOpaque(false);
		pageDownButton.setBorder(null);
		pageDownButton.setBorderPainted(false);
		pageDownButton.setDisabledIcon(blankIcon);
		pageDownButton.setBorderPainted(false);
		pageDownButton.setContentAreaFilled(false);
		pageDownButton.setFocusPainted(false);
		pageDownButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pageDownButton.setMinimumSize(new Dimension(200, 50));

		pageSizeTextField.setMinimumSize(new Dimension(30, 30));
		localBottomPanel.setLayout(new GridBagLayout());
		
		localBottomPanel.add(pageUpButton,		new GridBagConstraints(0, 0, 1, 0, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
		localBottomPanel.add(new JLabel(StrAccessor.getString("StockWindow.localBottomPanel.label.1")),					//$NON-NLS-1$
												new GridBagConstraints(1, 0, 1, 0, 0.5, 0.5, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0)); 
		localBottomPanel.add(new JLabel(StrAccessor.getString("StockWindow.localBottomPanel.label.numberOfAdverts")),	//$NON-NLS-1$
												new GridBagConstraints(2, 0, 1, 0, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
		localBottomPanel.add(pageSizeTextField,	new GridBagConstraints(3, 0, 1, 0, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
		localBottomPanel.add(new JLabel(StrAccessor.getString("StockWindow.localBottomPanel.label.3")),					//$NON-NLS-1$
												new GridBagConstraints(4, 0, 1, 0, 0.5, 0.5, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		localBottomPanel.add(pageDownButton, 	new GridBagConstraints(5, 0, 1, 0, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));

		return localBottomPanel;
	}
	
//	private BackgroundPanel initializeCommandPanel() {
//		BackgroundPanel localCommandPanel = new BackgroundPanel();
//		localCommandPanel.setBackgroundImage(SparkRes.getImageIcon(SparkRes.CARGO_PASEK_MENU_TLO));
//		localCommandPanel.setMinimumSize(new Dimension(100, 71));
//		localCommandPanel.setPreferredSize(new Dimension(100, 71));
//		localCommandPanel.setOpaque(false);
//
//		ImageIcon startIcon = SparkRes.getImageIcon(SparkRes.CARGO_MENU_START_1);
//		JButton startButton = new JButton(startIcon);
//		startButton.setRolloverIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_START_2));
//		startButton.setBorderPainted(false);
//		startButton.setContentAreaFilled(false);
//		startButton.setFocusPainted(false);
//		startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		startButton.addActionListener( new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				innerTabbedPane.setSelectedIndex(0);
//			}
//		});
//
//		ImageIcon pojazdIcon = SparkRes.getImageIcon(SparkRes.CARGO_MENU_POJAZD_1);
//		JButton pojazdButton = new JButton(pojazdIcon);
//		pojazdButton.setRolloverIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_POJAZD_2));
//		pojazdButton.setBorderPainted(false);
//		pojazdButton.setContentAreaFilled(false);
//		pojazdButton.setFocusPainted(false);
//		pojazdButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		pojazdButton.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				OfferWindow ow = new OfferWindow(OfferType.VEHICLE);
//				ow.setVisible(true);
//			}
//		});
//
//		ImageIcon ladunekIcon = SparkRes.getImageIcon(SparkRes.CARGO_MENU_LADUNEK_1);
//		JButton ladunekButton = new JButton(ladunekIcon);
//		ladunekButton.setRolloverIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_LADUNEK_2));
//		ladunekButton.setBorderPainted(false);
//		ladunekButton.setContentAreaFilled(false);
//		ladunekButton.setFocusPainted(false);
//		ladunekButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		ladunekButton.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				OfferWindow ow = new OfferWindow(OfferType.CARGO);
//				ow.setVisible(true);
//			}
//		});
//
//		ImageIcon rozmowyIcon = SparkRes.getImageIcon(SparkRes.CARGO_MENU_ROZMOWY_1);
//		JButton rozmowyButton = new JButton(rozmowyIcon);
//		rozmowyButton.setRolloverIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_ROZMOWY_2));
//		rozmowyButton.setBorderPainted(false);
//		rozmowyButton.setContentAreaFilled(false);
//		rozmowyButton.setFocusPainted(false);
//		rozmowyButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		rozmowyButton.addActionListener(new ActionListener(){
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				new MyConversationsWindow().setVisible(true);
//			}
//			
//		});
//
//		ImageIcon dluznicyIcon = SparkRes.getImageIcon(SparkRes.CARGO_MENU_DLUZNICY_1);
//		JButton dluznicyButton = new JButton(dluznicyIcon);
//		dluznicyButton.setRolloverIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_DLUZNICY_2));
//		dluznicyButton.setBorderPainted(false);
//		dluznicyButton.setContentAreaFilled(false);
//		dluznicyButton.setFocusPainted(false);
//		dluznicyButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		dluznicyButton.addActionListener(new ActionListener(){
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				new DebtorsWindow().setVisible(true);
//			}
//			
//		});
//
//		ImageIcon rankingIcon = SparkRes.getImageIcon(SparkRes.CARGO_MENU_RANKING_1);
//		JButton rankingButton = new JButton(rankingIcon);
//		rankingButton.setRolloverIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_RANKING_2));
//		rankingButton.setBorderPainted(false);
//		rankingButton.setContentAreaFilled(false);
//		rankingButton.setFocusPainted(false);
//		rankingButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		rankingButton.addActionListener(new ActionListener(){
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				new CompanyRankingWindow().setVisible(true);
//			}
//			
//		});
//
//		ImageIcon ustawieniaIcon = SparkRes.getImageIcon(SparkRes.CARGO_MENU_USTAWIENIA_1);
//		JButton ustawieniaButton = new JButton(ustawieniaIcon);
//		ustawieniaButton.setRolloverIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_USTAWIENIA_2));
//		ustawieniaButton.setBorderPainted(false);
//		ustawieniaButton.setContentAreaFilled(false);
//		ustawieniaButton.setFocusPainted(false);
//		ustawieniaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		ustawieniaButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				new SettingsWindow().setVisible(true);
//			}
//		});
//
//		ImageIcon pomocIcon = SparkRes.getImageIcon(SparkRes.CARGO_MENU_POMOC_1);
//		JButton pomocButton = new JButton(pomocIcon);
//		pomocButton.setRolloverIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_POMOC_2));
//		pomocButton.setBorderPainted(false);
//		pomocButton.setContentAreaFilled(false);
//		pomocButton.setFocusPainted(false);
//		pomocButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		pomocButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				new HelpWindow().setVisible(true);
//			}
//		});
//
//		localCommandPanel.setLayout(new GridBagLayout());
//		localCommandPanel.add(startButton, 		new GridBagConstraints(0, 0, 1, 0, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
//		localCommandPanel.add(pojazdButton, 	new GridBagConstraints(1, 0, 1, 0, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
//		localCommandPanel.add(ladunekButton, 	new GridBagConstraints(2, 0, 1, 0, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
//		localCommandPanel.add(rozmowyButton, 	new GridBagConstraints(3, 0, 1, 0, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
//		localCommandPanel.add(rankingButton, 	new GridBagConstraints(5, 0, 1, 0, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
//		localCommandPanel.add(ustawieniaButton, new GridBagConstraints(6, 0, 1, 0, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
//		localCommandPanel.add(pomocButton, 		new GridBagConstraints(7, 0, 1, 0, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
//		localCommandPanel.add(new JLabel(""), 	new GridBagConstraints(8, 0, 1, 0, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
//
//		return localCommandPanel;
//	}

	private JPanel initializeSearchPanel() {
		countriesMap = CargoLocationManager.getInstance().getCountries();
		panel1.setBackground(null);
		panel2.setBackground(null);
		panel3.setBackground(null);
		panel10.setBackground(null);
		
		BackgroundPanel searchPanel = new BackgroundPanel();
		searchPanel.setBackgroundImage(SparkRes.getImageIcon(SparkRes.CARGO_SZUKAJKA_TLO));
		searchPanel.setBackground(Color.RED);
		searchPanel.setMinimumSize	(new Dimension(1000,98));
		searchPanel.setPreferredSize(new Dimension(1000,98));
		searchPanel.setMaximumSize	(new Dimension(1000,98));
		
		searchPanel.setLayout		(new GridBagLayout());
		searchPanel.setOpaque(true);
		
		criteriaName = new JTextField();

		initializeCriteria();
		initializeArrivalCountry();
		initializeDepartureCountry();
		initializeDepartureCity();
		initializeArrivalCity();
		initializeBody();
		initializeWeight();
		initializeClear();
		initializeSearch();
		initializeAdvanced();

		int top		= 2;
		int down	= 2;
		int left 	= 5;
		int right 	= 5;

		Insets insets = 		new Insets(top,left,down,right);
				
		searchPanel.add(departureCountry, 		new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 			0, 0));
		searchPanel.add(departureCity, 			new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 			0, 0));
		searchPanel.add(arrivalCountry, 		new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 			0, 0));
		searchPanel.add(arrivalCity, 			new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 			0, 0));
		searchPanel.add(body, 					new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 			0, 0));
		searchPanel.add(weight, 				new GridBagConstraints(2, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 			0, 0));
		searchPanel.add(search, 				new GridBagConstraints(3, 0, 1, 2, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, 		 insets, 			0, 0));
		searchPanel.add(criteriaSearch, 		new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 			0, 0));
		searchPanel.add(criteriaName, 			new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 			0, 0));
		searchPanel.add(clear, 					new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, 		 insets,			0, 0));
		searchPanel.add(advanced, 				new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, 		 insets, 			0, 0));

		return searchPanel;
	}

	private void initializeArrivalCity() {
		arrivalCity = new JComboBox();
		arrivalCity.addItem(StrAccessor.getString("StockWindow.comboBox.arrivalCity.item"));	//$NON-NLS-1$
		
		arrivalCity.getEditor().getEditorComponent().addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					searchAction();
				}
			}
		});
	}

	private void initializeDepartureCity() {
		departureCity = new JComboBox();
		departureCity.addItem(StrAccessor.getString("StockWindow.comboBox.departureCity.item"));	//$NON-NLS-1$
		
		departureCity.getEditor().getEditorComponent().addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					searchAction();
				}
			}
		});
	}

	private void initializeWeight() {
		weight = new JComboBox();
		weight.addItem(StrAccessor.getString("StockWindow.comboBox.weight.item.weight"));	//$NON-NLS-1$
		weight.addItem(StrAccessor.getString("StockWindow.comboBox.weight.item.k1"));		//$NON-NLS-1$
		weight.addItem(StrAccessor.getString("StockWindow.comboBox.weight.item.k2"));		//$NON-NLS-1$
		AutoCompleteDecorator.decorate(weight);
		
		weight.getEditor().getEditorComponent().addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					searchAction();
				}
			}
		});
	}

	private void initializeBody() {
		body = new JComboBox();
		body.addItem(StrAccessor.getString("StockWindow.comboBox.body.item")); //$NON-NLS-1$
		
		List<CarBody> bodies = CargoDataManager.getInstance().getBodies();
		for (CarBody b : bodies) {
			body.addItem(b.getName());
		}
		AutoCompleteDecorator.decorate(body);
		
		body.getEditor().getEditorComponent().addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					searchAction();
				}
			}
		});
	}
	
	private void initializeAdvanced() {
		
		advanced = new JButton();
		ImageIcon pustaIcon1 = SparkRes.getImageIcon(SparkRes.CARGO_SZUKAJKA_PUSTA_1);
		ImageIcon pustaIcon2 = SparkRes.getImageIcon(SparkRes.CARGO_SZUKAJKA_PUSTA_2);
		advanced = new JButton(HTMLtag.openingWhite + StrAccessor.getString("StockWindow.button.advanced") +	//$NON-NLS-1$
				HTMLtag.closingFont, pustaIcon1);
		advanced.setVerticalTextPosition(SwingConstants.CENTER);
		advanced.setHorizontalTextPosition(SwingConstants.CENTER);
		advanced.setRolloverIcon(pustaIcon2);
		advanced.setOpaque(false);
		advanced.setBorder(null);
		advanced.setBorderPainted(false);
		advanced.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		advanced.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SearchCriteriaHelper searchCriteriaHelper = new SearchCriteriaHelper();
				
				searchCriteriaHelper.setArrivalCity(arrivalCity.getSelectedItem().toString());
				searchCriteriaHelper.setArrivalCountry(arrivalCountry.getSelectedItem().toString());
				searchCriteriaHelper.setDepartureCity(departureCity.getSelectedItem().toString());
				searchCriteriaHelper.setDepartureCountry(departureCountry.getSelectedItem().toString());
				searchCriteriaHelper.setBody(body.getSelectedItem().toString());
				searchCriteriaHelper.setWeight(weight.getSelectedItem().toString());
				
				Gson gson = new Gson();
				SearchCriteria searchCriteriaObject = new SearchCriteria();
				searchCriteriaObject.setValue(gson.toJson(searchCriteriaHelper));
				searchCriteriaObject.setCriteriaName(criteriaName.getText());
				
				searchCriteria.add(searchCriteriaObject);
				criteriaSearch.addItem(criteriaName.getText());
				
				CargoDataManager.getInstance().addSearchCriteria(searchCriteriaObject);
				
			}
		});
		
	}

	private void initializeSearch() {
		
		search = new JButton();
		ImageIcon pustaIcon1 = SparkRes.getImageIcon(SparkRes.CARGO_SZUKAJKA_PUSTA_3);
		ImageIcon pustaIcon2 = SparkRes.getImageIcon(SparkRes.CARGO_SZUKAJKA_PUSTA_4);
		search = new JButton(HTMLtag.openingWhite + StrAccessor.getString("StockWindow.button.search") + //$NON-NLS-1$ 
				HTMLtag.closingFont, pustaIcon1);
		search.setVerticalTextPosition(SwingConstants.CENTER);
		search.setHorizontalTextPosition(SwingConstants.CENTER);
		search.setRolloverIcon(pustaIcon2);
		search.setOpaque(false);
		search.setBorder(null);
		search.setBorderPainted(false);
		search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		search.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				searchAction();
			}
		});
	}

	private void initializeDepartureCountry() {
		Vector<String> departureCountryList = new Vector<String>();
		Vector<String> departureCountryListTemp = new Vector<String>(CargoLocationManager.getInstance().getCountries().keySet());
		departureCountryList.add(StrAccessor.getString("StockWindow.departureCountryList.departureCountry")); //$NON-NLS-1$
		departureCountryList.addAll(departureCountryListTemp);

		departureCountry = new JComboBox(departureCountryList);

		AutoCompleteDecorator.decorate(departureCountry);

		departureCountry.getEditor().getEditorComponent().addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					searchAction();
				}
			}
		});
		
		departureCountry.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				departureCountryAction();

			}

		});
	}
	
	private void departureCountryAction() {
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


	private void initializeArrivalCountry() {
		Vector<String> arrivalCountryList = new Vector<String>(CargoLocationManager.getInstance().getCountries().keySet());
		arrivalCountryList.add(0, StrAccessor.getString("StockWindow.arrivaleCountryList.arrivalCountry")); //$NON-NLS-1$
		arrivalCountry = new JComboBox(arrivalCountryList);
		
		AutoCompleteDecorator.decorate(arrivalCountry);
		arrivalCountry.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				arrivalCountryAction();
			}

		});
		
		arrivalCountry.getEditor().getEditorComponent().addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					searchAction();
				}
			}
		});
		
	}

	private void arrivalCountryAction() {
		if (arrivalCountry.getSelectedIndex() != 0) {
			String country = (String) arrivalCountry.getSelectedItem();
			String country_iso = countriesMap.get(country);
			
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

	
	private void initializeCriteria() {
		criteriaSearch = new JComboBox();
		criteriaSearch.addItem(StrAccessor.getString("StockWindow.comboBox.item.searchBy")); //$NON-NLS-1$

		this.searchCriteria = CargoDataManager.getInstance().getSearchCriterias();
		
		for (SearchCriteria criteria : this.searchCriteria) {
			criteriaSearch.addItem(criteria.getCriteriaName());
		}
		
		AutoCompleteDecorator.decorate(criteriaSearch);
		
		criteriaSearch.getEditor().getEditorComponent().addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					searchAction();
				}
			}
		});
		
		criteriaSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String selectedCriteria=(String) criteriaSearch.getItemAt(criteriaSearch.getSelectedIndex());
				
				SearchCriteria selectedSearchCriteria = null;
				
				for (SearchCriteria criteria : searchCriteria) {
					if (criteria.getCriteriaName().equals(selectedCriteria)){
						selectedSearchCriteria = criteria;
					}
				}
				
				if (selectedSearchCriteria == null ){
					return;
				}
				
				Gson gson = new Gson();
				SearchCriteriaHelper searchCriteriaHelper = gson.fromJson(selectedSearchCriteria.getValue(), SearchCriteriaHelper.class);
				
				if(searchCriteriaHelper == null){
					arrivalCity.setSelectedIndex(0);
					arrivalCountry.setSelectedIndex(0);
					departureCity.setSelectedIndex(0);
					departureCountry.setSelectedIndex(0);
					body.setSelectedIndex(0);
					weight.setSelectedIndex(0);
					return;
				}
				
				arrivalCountry.setSelectedItem(searchCriteriaHelper.getArrivalCountry());
				arrivalCountryAction();
				arrivalCity.setSelectedItem(searchCriteriaHelper.getArrivalCity());
				departureCountry.setSelectedItem(searchCriteriaHelper.getDepartureCountry());
				departureCountryAction();
				departureCity.setSelectedItem(searchCriteriaHelper.getDepartureCity());
				body.setSelectedItem(searchCriteriaHelper.getBody());
				weight.setSelectedItem(searchCriteriaHelper.getWeight());
			}
		});
		
	}	
	
	private void initializeClear() {
		
		clear = new JButton();
		ImageIcon pustaIcon1 = SparkRes.getImageIcon(SparkRes.CARGO_SZUKAJKA_PUSTA_1);
		ImageIcon pustaIcon2 = SparkRes.getImageIcon(SparkRes.CARGO_SZUKAJKA_PUSTA_2);
		clear = new JButton(HTMLtag.openingWhite + StrAccessor.getString("StockWindow.button.clear") + //$NON-NLS-1$
				HTMLtag.closingFont, pustaIcon1);
		clear.setVerticalTextPosition(SwingConstants.CENTER);
		clear.setHorizontalTextPosition(SwingConstants.CENTER);
		clear.setRolloverIcon(pustaIcon2);
		clear.setOpaque(false);
		clear.setBorder(null);
		clear.setBorderPainted(false);
		clear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				arrivalCountry.setSelectedIndex(0);
				departureCountry.setSelectedIndex(0);
				arrivalCity.setSelectedIndex(-1);
				departureCity.setSelectedIndex(-1);
				body.setSelectedIndex(0);
				weight.setSelectedIndex(0);

				CargoSearchManager.getInstance().setArrivalCountry(null);
				CargoSearchManager.getInstance().setDepartureCountry(null);
				CargoSearchManager.getInstance().setArrivalCity(null);
				CargoSearchManager.getInstance().setDepartureCity(null);
				CargoSearchManager.getInstance().setBody(null);
				CargoSearchManager.getInstance().setWeight(null);

				((BlucargoSortableTableModel) CargoDataManager.getInstance().getAcceptedOffersTable().getModel()).fireTableDataChanged();
				((BlucargoSortableTableModel) CargoDataManager.getInstance().getCargoOffersTable().getModel()).fireTableDataChanged();
				((BlucargoSortableTableModel) CargoDataManager.getInstance().getFavouriteOffersTable().getModel()).fireTableDataChanged();

				searchAction();
			}

		});
	}
	
	private void searchAction() {
		if (arrivalCountry.getSelectedIndex() > 0 && StringUtils.isNotEmpty(arrivalCountry.getSelectedItem().toString())) {
			CargoSearchManager.getInstance().setArrivalCountry(arrivalCountry.getSelectedItem().toString());
		} else {
			CargoSearchManager.getInstance().setArrivalCountry(null);
		}
		if (departureCountry.getSelectedIndex() > 0 && StringUtils.isNotEmpty(departureCountry.getSelectedItem().toString())) {
			CargoSearchManager.getInstance().setDepartureCountry(departureCountry.getSelectedItem().toString());
		} else {
			CargoSearchManager.getInstance().setDepartureCountry(null);
		}
		if (arrivalCity.getSelectedIndex() > 0 && StringUtils.isNotEmpty(arrivalCity.getSelectedItem().toString())) {
			CargoSearchManager.getInstance().setArrivalCity(arrivalCity.getSelectedItem().toString());
		} else {
			CargoSearchManager.getInstance().setArrivalCity(null);
		}
		if (departureCity.getSelectedIndex() > 0 && StringUtils.isNotEmpty(departureCity.getSelectedItem().toString())) {
			CargoSearchManager.getInstance().setDepartureCity(departureCity.getSelectedItem().toString());
		} else {
			CargoSearchManager.getInstance().setDepartureCity(null);
		}
		if (body.getSelectedIndex() > 0 && StringUtils.isNotEmpty(body.getSelectedItem().toString())) {
			CargoSearchManager.getInstance().setBody(body.getSelectedItem().toString());
		} else {
			CargoSearchManager.getInstance().setBody(null);
		}
		if (weight.getSelectedIndex() > 0 && StringUtils.isNotEmpty(weight.getSelectedItem().toString())) {
			CargoSearchManager.getInstance().setWeight(weight.getSelectedItem().toString());
		} else {
			CargoSearchManager.getInstance().setWeight(null);
		}

		((BlucargoSortableTableModel) CargoDataManager.getInstance().getAcceptedOffersTable().getModel()).fireTableDataChanged();
		((BlucargoSortableTableModel) CargoDataManager.getInstance().getCargoOffersTable().getModel()).fireTableDataChanged();
		((BlucargoSortableTableModel) CargoDataManager.getInstance().getFavouriteOffersTable().getModel()).fireTableDataChanged();
		((BlucargoSortableTableModel) CargoDataManager.getInstance().getEndedOffersTable().getModel()).fireTableDataChanged();
	}

	private void pageSizeTextFieldAction() {
		BlucargoSortableTableModel currentTableModel = null;

		int selectedIndex = ((JTabbedPane) tabbedPanel.getComponent(0)).getSelectedIndex();
		switch (selectedIndex) {
		case 0:
			currentTableModel = cargoOffersModel;
			break;
		case 1:
			currentTableModel = favouriteOffersModel;
			break;
		case 2:
			currentTableModel = acceptedOffersModel;
			break;
		case 3:
			currentTableModel = endedOffersModel;
			break;
		}

		if (currentTableModel.getPageOffset() == (currentTableModel.getPageCount() - 1)) {
			if (currentTableModel.getDataVector().size() > currentTableModel.getPageSize()) {
				pageUpButton.setEnabled(true);
				pageUpButton.setText(HTMLtag.openingWhite + 
						StrAccessor.getString("StockWindow.pageUpButton.1") + HTMLtag.closingFont); //$NON-NLS-1$
				
				pageDownButton.setEnabled(false);
				pageDownButton.setText(HTMLtag.openingWhite +
						StrAccessor.getString("StockWindow.pageDownButton.1")+ HTMLtag.closingFont); //$NON-NLS-1$
			} else {
				pageUpButton.setEnabled(false);
				pageUpButton.setText(HTMLtag.openingWhite + 
						StrAccessor.getString("StockWindow.pageUpButton.2") + HTMLtag.closingFont); //$NON-NLS-1$
				
				pageDownButton.setEnabled(false);
				pageDownButton.setText(HTMLtag.openingWhite +
						StrAccessor.getString("StockWindow.pageDownButton.2") + HTMLtag.closingFont); //$NON-NLS-1$
			}
		} else if (currentTableModel.getPageOffset() == 0) {
			if (currentTableModel.getDataVector().size() > currentTableModel.getPageSize()) {
				pageUpButton.setEnabled(false);
				pageUpButton.setText(HTMLtag.openingWhite + 
						StrAccessor.getString("StockWindow.pageUpButton.3") + HTMLtag.closingFont); //$NON-NLS-1$
				
				pageDownButton.setEnabled(true);
				pageDownButton.setText(HTMLtag.openingWhite +
						StrAccessor.getString("StockWindow.pageDownButton.3") + HTMLtag.closingFont); //$NON-NLS-1$
			} else {
				pageUpButton.setEnabled(false);
				pageUpButton.setText(HTMLtag.openingWhite + 
						StrAccessor.getString("StockWindow.pageUpButton.4") + HTMLtag.closingFont); //$NON-NLS-1$
				
				pageDownButton.setEnabled(false);
				pageDownButton.setText(HTMLtag.openingWhite +
						StrAccessor.getString("StockWindow.pageDownButton.4") + HTMLtag.closingFont); //$NON-NLS-1$
			}
		} else {
			pageUpButton.setEnabled(true);
			pageUpButton.setText(HTMLtag.openingWhite + 
					StrAccessor.getString("StockWindow.pageUpButton.5") + HTMLtag.closingFont); //$NON-NLS-1$
			
			pageDownButton.setEnabled(true);
			pageDownButton.setText(HTMLtag.openingWhite +
					StrAccessor.getString("StockWindow.pageDownButton.5") + HTMLtag.closingFont); //$NON-NLS-1$
		}
	}
	
	private Component initializeCargoOffersPanel() {

		JPanel cargoOffersPanel 		= new JPanel();

		cargoOffersPanel.setLayout(new GridBagLayout());
		final JTable cargoOffersTable 	= initializeTable(cargoOffersPanel, cargoOffersModel);
		CargoDataManager.getInstance().setCargoOffersTable(cargoOffersTable);

		pageSizeTextField 				= cargoOffersModel.createPageSizeTextField();
		pageUpButton 					= cargoOffersModel.createPageUpButton();
		pageDownButton 					= cargoOffersModel.createPageDownButton();

		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new RefreshTask(cargoOffersModel, cargoOffersTable), this.DATA_INITIAL_REFRESH, this.DATA_REFRESH_PERIOD);
		
		cargoOffersPanel.setComponentPopupMenu(contextMenu);

		pageSizeTextField.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				Integer newPageSize = Integer.parseInt(pageSizeTextField.getText().trim());
				cargoOffersModel.setPageSize(newPageSize);
				favouriteOffersModel.setPageSize(newPageSize);
				acceptedOffersModel.setPageSize(newPageSize);
				endedOffersModel.setPageSize(newPageSize);
				pageSizeTextFieldAction();
			}
		});

		pageUpButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {

				BlucargoSortableTableModel currentTableModel = null;

				int selectedIndex = ((JTabbedPane) tabbedPanel.getComponent(0)).getSelectedIndex();
				switch (selectedIndex) {
				case 0:
					currentTableModel = cargoOffersModel;
					break;
				case 1:
					currentTableModel = favouriteOffersModel;
					break;
				case 2:
					currentTableModel = acceptedOffersModel;
					break;
				case 3:
					currentTableModel = endedOffersModel;
					break;
				}

				currentTableModel.pageUp();

				// If we hit the top of the data, disable the up button.
				if (currentTableModel.getPageOffset() == 0) {
					pageUpButton.setEnabled(false);
					pageUpButton.setText(HTMLtag.openingWhite + 
							StrAccessor.getString("StockWindow.pageUpButton.6") + HTMLtag.closingFont);	//$NON-NLS-1$
				}
				pageDownButton.setEnabled(true);
				pageDownButton.setText(HTMLtag.openingWhite +
						StrAccessor.getString("StockWindow.pageDownButton.6") + HTMLtag.closingFont);	//$NON-NLS-1$
			}
		});

		pageDownButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {

				BlucargoSortableTableModel currentTableModel = null;

				int selectedIndex = ((JTabbedPane) tabbedPanel.getComponent(0)).getSelectedIndex();
				switch (selectedIndex) {
				case 0:
					currentTableModel = cargoOffersModel;
					break;
				case 1:
					currentTableModel = favouriteOffersModel;
					break;
				case 2:
					currentTableModel = acceptedOffersModel;
					break;
				case 3:
					currentTableModel = endedOffersModel;
					break;
				}

				currentTableModel.pageDown();

				// If we hit the bottom of the data, disable the down button.
				if (currentTableModel.getPageOffset() == (currentTableModel.getPageCount() - 1)) {
					pageDownButton.setEnabled(false);
					pageDownButton.setText(HTMLtag.openingWhite + StrAccessor.getString("StockWindow.pageDownButton.7") +	//$NON-NLS-1$ 
							HTMLtag.closingFont);
				}
				pageUpButton.setEnabled(true);
				pageUpButton.setText(HTMLtag.openingWhite + StrAccessor.getString("StockWindow.pageUpButton.7") +	//$NON-NLS-1$
						HTMLtag.closingFont);
			}
		});


		return cargoOffersPanel;
	}

	private JPanel initializeFavouriteOffersPanel() {
		JPanel favouriteOffersPanel = new JPanel();
		favouriteOffersPanel.setLayout(new GridBagLayout());
		final JTable favouriteOffersTable = initializeTable(favouriteOffersPanel, favouriteOffersModel);
		CargoDataManager.getInstance().setFavouriteOffersTable(favouriteOffersTable);

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new RefreshTask(favouriteOffersModel, favouriteOffersTable), this.DATA_INITIAL_REFRESH, this.DATA_REFRESH_PERIOD);

		return favouriteOffersPanel;
	}

	private JPanel initializeAcceptedOffersPanel() {
		JPanel acceptedOffersPanel = new JPanel();
		acceptedOffersPanel.setLayout(new GridBagLayout());
		final JTable acceptedOffersTable = initializeTable(acceptedOffersPanel, acceptedOffersModel);
		CargoDataManager.getInstance().setAcceptedOffersTable(acceptedOffersTable);

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new RefreshTask(acceptedOffersModel, acceptedOffersTable), this.DATA_INITIAL_REFRESH, this.DATA_REFRESH_PERIOD);

		return acceptedOffersPanel;
	}

	private JPanel initializeEndedOffersPanel() {
		JPanel endedOffersPanel = new JPanel();
		endedOffersPanel.setLayout(new GridBagLayout());
		final JTable endedOffersTable = initializeTable(endedOffersPanel, endedOffersModel);

		CargoDataManager.getInstance().setEndedOffersTable(endedOffersTable);

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new RefreshTask(endedOffersModel, endedOffersTable), this.DATA_INITIAL_REFRESH, this.DATA_REFRESH_PERIOD);

		return endedOffersPanel;
	}

	private JTable initializeTable(JPanel panel, final BlucargoSortableTableModel model2) {

		final JTable table = 					createTableFromSortableTableModel(model2);
		table.setInheritsPopupMenu(true);
		createPagingScrollPaneForTableAndAddToPanel(panel, model2, table);
		createHeaderRendererAndAssignHeaderListener(table);
		return table;
	}

	private BlucargoSortableTableModel cargoOffersModel() {
		final BlucargoSortableTableModel sortableTableModel = new BlucargoSortableTableModel() {

			private static final long serialVersionUID = 6698952565364092846L;

			@Override
			public List<?> getData() {
				CargoDataManager.getInstance().refreshCargoOffers();
				return CargoDataManager.getInstance().getCargoOffers();
			}

			@Override
			public Vector<String> initializeColumnIdentifiers() {
				Vector<String> columnIdentifiers = new Vector<String>();
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.info"));		//$NON-NLS-1$
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.zaladunek"));	//$NON-NLS-1$
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.rozladunek"));	//$NON-NLS-1$
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.pojazd"));		//$NON-NLS-1$
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.zgloszono"));	//$NON-NLS-1$
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.wazneDo"));		//$NON-NLS-1$
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.kontakt"));		//$NON-NLS-1$
				return columnIdentifiers;
			}

			public Class<?> getColumnClass(int col) {
				switch (col) {
				case 0:
					return CargoOffersInfoPanel.class;
				case 1:
					return CargoOffersLoadPanel.class;
				case 2:
					return CargoOffersUnloadPanel.class;
				case 3:
					return CargoOffersBodyPanel.class;
				case 4:
					return CargoOffersSubmissionDatePanel.class;
				case 5:
					return CargoOffersOfferValidPanel.class;
				case 6:
					return CargoOffersContactPanel.class;
				default:
					return String.class;
				}
			}
		};

		return sortableTableModel;
	}

	private BlucargoSortableTableModel acceptedOffersModel() {
		final BlucargoSortableTableModel sortableTableModel = new BlucargoSortableTableModel() {

			private static final long serialVersionUID = 2819757677708088421L;

			@Override
			public List<?> getData() {
				CargoDataManager.getInstance().refreshAcceptedOffers();
				return CargoDataManager.getInstance().getAcceptedOffers();
			}

			@Override
			public Vector<?> initializeColumnIdentifiers() {
				Vector<String> columnIdentifiers = new Vector<String>();
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.info"));		//$NON-NLS-1$
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.zaladunek"));	//$NON-NLS-1$
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.rozladunek"));	//$NON-NLS-1$
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.pojazd"));		//$NON-NLS-1$
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.zgloszono"));	//$NON-NLS-1$
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.wazneDo"));		//$NON-NLS-1$
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.kontakt"));		//$NON-NLS-1$
				return columnIdentifiers;
			}

			public Class<?> getColumnClass(int col) {
				switch (col) {
				case 0:
					return AcceptedOffersInfoPanel.class;
				case 1:
					return AcceptedOffersLoadPanel.class;
				case 2:
					return AcceptedOffersUnloadPanel.class;
				case 3:
					return AcceptedOffersBodyPanel.class;
				case 4:
					return AcceptedOffersSubmissionDatePanel.class;
				case 5:
					return AcceptedOffersOfferValidPanel.class;
				case 6:
					return AcceptedOffersContactPanel.class;
				default:
					return String.class;
				}
			}
		};

		return sortableTableModel;
	}

	private BlucargoSortableTableModel favouriteOffersModel() {
		final BlucargoSortableTableModel sortableTableModel = new BlucargoSortableTableModel() {

			private static final long serialVersionUID = -1534261673230039281L;

			@Override
			public List<?> getData() {
				CargoDataManager.getInstance().refreshFavouriteOffers();
				return CargoDataManager.getInstance().getFavouriteOffers();
			}

			@Override
			public Vector<String> initializeColumnIdentifiers() {
				Vector<String> columnIdentifiers = new Vector<String>();
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.info"));		//$NON-NLS-1$
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.zaladunek"));	//$NON-NLS-1$
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.rozladunek"));	//$NON-NLS-1$
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.pojazd"));		//$NON-NLS-1$
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.zgloszono"));	//$NON-NLS-1$
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.wazneDo"));		//$NON-NLS-1$
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.kontakt"));		//$NON-NLS-1$
				return columnIdentifiers;
			}

			public Class<?> getColumnClass(int col) {
				switch (col) {
				case 0:
					return FavouriteOffersInfoPanel.class;
				case 1:
					return FavouriteOffersLoadPanel.class;
				case 2:
					return FavouriteOffersUnloadPanel.class;
				case 3:
					return FavouriteOffersBodyPanel.class;
				case 4:
					return FavouriteOffersSubmissionDatePanel.class;
				case 5:
					return FavouriteOffersOfferValidPanel.class;
				case 6:
					return FavouriteOffersContactPanel.class;
				default:
					return String.class;
				}
			}
		};

		return sortableTableModel;
	}

	private BlucargoSortableTableModel endedOffersModel() {
		final BlucargoSortableTableModel sortableTableModel = new BlucargoSortableTableModel() {

			private static final long serialVersionUID = -1534261673230039281L;

			@Override
			public List<?> getData() {
				CargoDataManager.getInstance().refreshEndedOffers();
				return CargoDataManager.getInstance().getEndedOffers();
			}

			@Override
			public Vector<String> initializeColumnIdentifiers() {
				Vector<String> columnIdentifiers = new Vector<String>();
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.info"));		//$NON-NLS-1$
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.zaladunek"));	//$NON-NLS-1$
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.rozladunek"));	//$NON-NLS-1$
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.pojazd"));		//$NON-NLS-1$
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.zgloszono"));	//$NON-NLS-1$
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.wazneDo"));		//$NON-NLS-1$
				columnIdentifiers.add(StrAccessor.getString("StockWindow.columnIdentifiers.item.kontakt"));		//$NON-NLS-1$
				return columnIdentifiers;
			}

			public Class<?> getColumnClass(int col) {
				switch (col) {
				case 0:
					return EndedOffersInfoPanel.class;
				case 1:
					return EndedOffersLoadPanel.class;
				case 2:
					return EndedOffersUnloadPanel.class;
				case 3:
					return EndedOffersBodyPanel.class;
				case 4:
					return EndedOffersSubmissionDatePanel.class;
				case 5:
					return EndedOffersOfferValidPanel.class;
				case 6:
					return EndedOffersContactPanel.class;
				default:
					return String.class;
				}
			}
		};
		return sortableTableModel;
	}


	private void initializeCommandPanelSearchPanelTabbedPanelAndBottomPanel() {
		this.setLayout(new GridBagLayout());
		this.add(searchPanel, 		new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, 	GridBagConstraints.HORIZONTAL, 	new Insets(0, 0, 0, 0), 0, 0));
		this.add(tabbedPanel, 		new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, 	GridBagConstraints.BOTH, 		new Insets(0, 0, 0, 0), 0, 0));
		this.add(bottomPanel, 		new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTH, 	GridBagConstraints.HORIZONTAL, 	new Insets(0, 0, 0, 0), 0, 0));
	}

	private void createHeaderRendererAndAssignHeaderListener(final JTable table) {
		SortButtonRenderer renderer 			= new SortButtonRenderer();
		JTableHeader header 					= table.getTableHeader();
		
		HeaderListener headerListener = new HeaderListener(header, renderer);
		header.addMouseListener(headerListener);
		header.addMouseMotionListener(headerListener);
	}

	private void createPagingScrollPaneForTableAndAddToPanel(JPanel localTablePanel, final BlucargoSortableTableModel sortableTableModel, final JTable table) {

		JScrollPane pagingScrollPaneForTable 			= sortableTableModel.createPagingScrollPaneForTable(table);
		pagingScrollPaneForTable.getViewport().setBackground(new Color(131, 145, 165, 255));
		pagingScrollPaneForTable.setBackground(new Color(131, 145, 165, 255));
		localTablePanel.add(pagingScrollPaneForTable, 	new GridBagConstraints(0, 1, 1, 3, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

	}

	private JTable createTableFromSortableTableModel(final BlucargoSortableTableModel sortableTableModel) {
		final JTable table = new BlucargoTable(sortableTableModel);
		return table;
	}

}