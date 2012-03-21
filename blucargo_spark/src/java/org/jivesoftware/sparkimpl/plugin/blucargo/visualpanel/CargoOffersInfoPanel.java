package org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.jivesoftware.resource.SparkRes;
import org.jivesoftware.spark.SparkManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.HTMLtag;
import org.jivesoftware.sparkimpl.plugin.blucargo.manager.CargoDataManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.resourcebundle.StrAccessor;
import org.jivesoftware.sparkimpl.plugin.blucargo.table.HighlightTableModel;
import org.jivesoftware.sparkimpl.plugin.blucargo.table.BlucargoSortableTableModel;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.OfferDetailsWindow;

import com.blucargo.model.CargoOffer;
import com.blucargo.model.FavouriteOffer;
import com.blucargo.model.OfferType;


public class CargoOffersInfoPanel extends VisualPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton addToFavourites;
	JButton details;
	
	public void render(final CargoOffer cargoOffer, JPanel panel, int row) {
		String unloadingDate = ""; //$NON-NLS-1$
		if (cargoOffer.getUnloadingDate() != null) {
			unloadingDate = cargoOffer.getUnloadingDate().toString();
		}

		addToFavourites = new JButton(HTMLtag.openingWhite + 
				StrAccessor.getString("CargoOffersInfoPanel.addToFavourites") + HTMLtag.closingFont); //$NON-NLS-1$
		addToFavourites.setName("addToFavourites"); //$NON-NLS-1$
		addToFavourites.setVerticalTextPosition(SwingConstants.CENTER);
		addToFavourites.setHorizontalTextPosition(SwingConstants.CENTER);

		String buttonName = HighlightTableModel.getInstance().getButtonInRowHighlighted(row);
		if(buttonName != null && buttonName.equals("addToFavourites")){ //$NON-NLS-1$
			addToFavourites.getModel().setRollover(true);
		}
		
		addToFavourites.setIcon					(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY1));
		addToFavourites.setRolloverIcon			(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY2));
		addToFavourites.setRolloverSelectedIcon	(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY2));
		addToFavourites.setRolloverEnabled(true);

		addToFavourites.setBorderPainted(false);
		addToFavourites.setContentAreaFilled(false);
		addToFavourites.setFocusPainted(false);
		addToFavourites.setOpaque(false);
		addToFavourites.setRolloverEnabled(true);
		
		addToFavourites.setEnabled(true);
		addToFavourites.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				addToFavourites.getModel().setRollover(true);
				addToFavourites.getParent().repaint();
				
				int selectedRow = CargoDataManager.getInstance().getCargoOffersTable().getSelectedRow();
				int selectedColumn = CargoDataManager.getInstance().getCargoOffersTable().getSelectedColumn();

				CargoOffer ip = (CargoOffer) CargoDataManager.getInstance().getCargoOffersTable().getModel().getValueAt(selectedRow, 0);
				FavouriteOffer fo = new FavouriteOffer();
				fo.setOfferId(ip.getId());
				String jid = SparkManager.getSessionManager().getJID();
				String userName = SparkManager.getSessionManager().getUsername();

				fo.setUserName(userName);

				CargoDataManager.getInstance().addFavouriteOffer(fo);
				CargoDataManager.getInstance().refreshCargoOffers();
				CargoDataManager.getInstance().refreshFavouriteOffers();

				BlucargoSortableTableModel model = (BlucargoSortableTableModel) CargoDataManager.getInstance().getFavouriteOffersTable().getModel();
				model.refreshSlowly();

			}
		});
		
		details = new JButton(HTMLtag.openingWhite + StrAccessor.getString("CargoOffersInfoPanel.details") + HTMLtag.closingFont); //$NON-NLS-1$
		details.setName("details"); //$NON-NLS-1$
		details.setVerticalTextPosition(SwingConstants.CENTER);
		details.setHorizontalTextPosition(SwingConstants.CENTER);
		details.setIcon					(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY1));
		details.setRolloverIcon			(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY2));
		details.setRolloverSelectedIcon	(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY2));

		if(buttonName != null && buttonName.equals("details")){ //$NON-NLS-1$
			details.getModel().setRollover(true);
		}
		

		details.setBorderPainted(false);
		details.setContentAreaFilled(false);
		details.setFocusPainted(false);
		details.setOpaque(false);
//		details.setPreferredSize(new Dimension(150, 20));

		
		details.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

//		details.set
				
//				addToFavourites.setBorderPainted(true);
//				addToFavourites.repaint();
				
//				JTable table = (JTable)e.getSource();
//				table.repaint();
				
//				details
				
//				SwingUtilities.convertMouseEvent(source, sourceEvent, destination)
				
				OfferDetailsWindow offerDetails = new OfferDetailsWindow(cargoOffer);
				offerDetails.setVisible(true);
			}
		});
		

		ImageIcon pojazdIcon = null;

		if (cargoOffer.getType() == OfferType.CARGO) {
			pojazdIcon = SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_LADUNEK_ICO);
		} else if (cargoOffer.getType() == OfferType.VEHICLE) {
			pojazdIcon = SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_POJAZD_ICO);
		}

		JLabel iconLabel = new JLabel();

		if (cargoOffer.getType() == OfferType.CARGO) {
			iconLabel.setSize(40, 37);
			iconLabel.setPreferredSize(new Dimension(40, 37));
			iconLabel.setMinimumSize(new Dimension(40, 37));
		} else if (cargoOffer.getType() == OfferType.VEHICLE) {
			iconLabel.setSize(29, 37);
			iconLabel.setPreferredSize(new Dimension(29, 37));
			iconLabel.setMinimumSize(new Dimension(29, 37));
		}

		iconLabel.setIcon(pojazdIcon);
		panel.add(iconLabel, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(0, 0, 0, 0), 0, 0));
		panel.add(addToFavourites, new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		panel.add(details, new GridBagConstraints(0, 4, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

		return;
	}
}
