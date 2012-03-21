package org.jivesoftware.sparkimpl.plugin.blucargo.systray;

import java.awt.Dimension;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.jivesoftware.sparkimpl.plugin.blucargo.manager.CargoDataManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.manager.CargoHotAmountManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.systray.evtHandler.ICargoOffersHandler;
import org.jivesoftware.sparkimpl.plugin.blucargo.table.BlucargoSortableTableModel;
import org.jivesoftware.sparkimpl.plugin.blucargo.table.BlucargoTable;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.CargoOfferIconPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.CargoOffersBodyPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.CargoOffersLoadPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.CargoOffersOfferValidPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.CargoOffersSubmissionDatePanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.CargoOffersUnloadPanel;


public class HotOfferPanel extends JFrame implements ICargoOffersHandler
{
	private static final int ROW_HIGHT = 80;
	
	private JPanel panel 									= new JPanel();
	private BlucargoSortableTableModel cargoOffersModel 	= cargoOffersModel();
	private final JTable cargoOffersTable 					= createTable(cargoOffersModel);
	private int hotOfferNum 								= CargoHotAmountManager.get().getBufferSize();
	
	public int sizeX;
	public int sizeY;
	
	HotOfferPanel()
	{

//		panel 											= new JPanel();
//		BlucargoSortableTableModel cargoOffersModel 	= cargoOffersModel();
//		JTable cargoOffersTable 						= createTable(cargoOffersModel);
//		hotOfferNum 									= CargoHotAmountManager.get().getBufferSize();
		
		sizeX = 710;
		sizeY = ROW_HIGHT * hotOfferNum + 30;

		
		setPreferredSize(new Dimension(240,180));
		setSize(710, ROW_HIGHT * hotOfferNum + 30);
		setAlwaysOnTop(true);
		setResizable(false);
		add(cargoOffersTable);
		CargoDataManager.getInstance().addOffersListener(this);
	}

	private BlucargoSortableTableModel cargoOffersModel() {
		final BlucargoSortableTableModel sortableTableModel = new BlucargoSortableTableModel() {
			
			@Override
			public List<?> getData() {
				return CargoDataManager.getInstance().getCargoOffers();
			}

			@Override
			public Vector<String> initializeColumnIdentifiers() {
				Vector<String> columnIdentifiers = new Vector<String>();
				columnIdentifiers.add("Info");
				columnIdentifiers.add("Za\u0142adunek");
				columnIdentifiers.add("Roz\u0142adunek");
				columnIdentifiers.add("Pojazd");
				columnIdentifiers.add("Zg\u0142oszono");
				columnIdentifiers.add("Wa\u017cne do");
				return columnIdentifiers;
			}

			public Class<?> getColumnClass(int col) {
				switch (col) {
				case 0:
					return CargoOfferIconPanel.class;
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
				default:
					return String.class;
				}
			}
			
			@Override
			protected int[] getColumnWidths()
			{
				return new int[]{ 10, 10, 10, 10, 10, 10, 100 };
			}
		};

		return sortableTableModel;
	}
	
	private JTable createTable(final BlucargoSortableTableModel sortableTableModel) {
		final JTable table = new BlucargoTable(sortableTableModel);

//
//		sortableTableModel.setTable					(table);
//		table.setColumnSelectionAllowed				(false);
//		table.setRowSelectionAllowed				(false);
//		table.setShowVerticalLines					(true);
		table.setIntercellSpacing					(new Dimension(0,0));
		table.setRowHeight							(ROW_HIGHT);
//		table.setShowHorizontalLines				(false);
//		table.setShowVerticalLines					(false);
//		table.getTableHeader().setReorderingAllowed	(false);
//		table.getTableHeader().setResizingAllowed	(false);
//		table.setBackground							(new Color(0, 145, 255, 255));
//		table.setOpaque								(false);		
//
//		table.setInheritsPopupMenu(true);
//		table.setBorder(null);
//		table.getTableHeader().setBorder(null);
		table.setPreferredSize(new Dimension(700, ROW_HIGHT * hotOfferNum));

		return table;
	}

	@Override
	public void onOffersRefresh() 
	{
		cargoOffersModel.refreshQuickly();
		CargoHotAmountManager.get().appendTopOfferId(CargoDataManager.getInstance().getCargoOffers());
		cargoOffersModel.fireTableDataChanged();
	}

	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}


	
}