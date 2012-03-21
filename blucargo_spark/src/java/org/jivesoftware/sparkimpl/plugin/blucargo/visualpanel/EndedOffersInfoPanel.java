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
import org.jivesoftware.sparkimpl.plugin.blucargo.manager.CargoDataManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.resourcebundle.StrAccessor;
import org.jivesoftware.sparkimpl.plugin.blucargo.table.HighlightTableModel;
import org.jivesoftware.sparkimpl.plugin.blucargo.table.BlucargoSortableTableModel;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.OfferDetailsWindow;

import com.blucargo.model.CargoOffer;
import com.blucargo.model.OfferType;

public class EndedOffersInfoPanel extends VisualPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void render(final CargoOffer cargoOffer, JPanel panel, int row) {
		String unloadingDate = ""; //$NON-NLS-1$
		if (cargoOffer.getUnloadingDate() != null) {
			unloadingDate = cargoOffer.getUnloadingDate().toString();
		}

        String buttonName = HighlightTableModel.getInstance().getButtonInRowHighlighted(row);

		
		// Detaljer knappen
        
        JButton details;
        details = new JButton("<html><font color='white'>"+StrAccessor.getString("EndedOffersInfoPanel.buttonDetails")+"</font></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        details.setName("details"); //$NON-NLS-1$
        details.setVerticalTextPosition(SwingConstants.CENTER);
        details.setHorizontalTextPosition(SwingConstants.CENTER);
        details.setIcon					(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY1));
        details.setRolloverIcon			(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY2));
        details.setRolloverSelectedIcon	(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY2));
        
        
		//JButton details = new JButton();
		//details.setName("details");
		//details.setIcon					(SparkRes.getImageIcon(SparkRes.CARGO_SZCZEGOLY_1));
		//details.setRolloverIcon			(SparkRes.getImageIcon(SparkRes.CARGO_SZCZEGOLY_2));
		//details.setRolloverSelectedIcon	(SparkRes.getImageIcon(SparkRes.CARGO_SZCZEGOLY_2));

		if(buttonName != null && buttonName.equals("details")){ //$NON-NLS-1$
			details.getModel().setRollover(true);
		}


		details.setBorderPainted(false);
		details.setContentAreaFilled(false);
		details.setFocusPainted(false);
		details.setOpaque(false);
		details.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				OfferDetailsWindow offerDetails = new OfferDetailsWindow(cargoOffer);
				offerDetails.setVisible(true);
			}
		});

		// Slett knappen
		
		JButton remove;
		remove = new JButton("<html><font color='white'>"+StrAccessor.getString("EndedOffersInfoPanel.buttonRemove")+"</font></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		remove.setName("remove"); //$NON-NLS-1$
		remove.setVerticalTextPosition(SwingConstants.CENTER);
		remove.setHorizontalTextPosition(SwingConstants.CENTER);
		remove.setIcon					(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY1));
		remove.setRolloverIcon			(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY2));
		remove.setRolloverSelectedIcon	(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY2));
		
		
		//JButton remove = new JButton();
		//remove.setName("remove");
		//remove.setIcon					(SparkRes.getImageIcon(SparkRes.CARGO_USUN_1));
		//remove.setRolloverIcon			(SparkRes.getImageIcon(SparkRes.CARGO_USUN_2));
		//remove.setRolloverSelectedIcon	(SparkRes.getImageIcon(SparkRes.CARGO_USUN_2));

		if(buttonName != null && buttonName.equals("remove")){ //$NON-NLS-1$
			remove.getModel().setRollover(true);
		}


		remove.setBorderPainted(false);
		remove.setContentAreaFilled(false);
		remove.setFocusPainted(false);
		remove.setOpaque(false);
		remove.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				CargoDataManager.getInstance().removeEndedOffer(cargoOffer);
				BlucargoSortableTableModel model = (BlucargoSortableTableModel) CargoDataManager.getInstance().getEndedOffersTable().getModel();
				model.refreshSlowly();

			}
		});

		// moje wypociny
		//

		// /
		ImageIcon pojazdIcon = null;
		// /

		if (cargoOffer.getType() == OfferType.CARGO) {
			pojazdIcon = SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_LADUNEK_ICO);
		} else if (cargoOffer.getType() == OfferType.VEHICLE) {
			pojazdIcon = SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_POJAZD_ICO);
		}

		// ClassLoader cl = SparkRes.class.getClassLoader();
		//        
		//        
		// JLabel iconLabel = new JLabel();
		// String offerString="images/cargo/pojazd_ico.png";
		// URL iconURL=cl.getResource(offerString);
		//
		// ImageIcon offerIcon=null;
		//
		// try{
		// offerIcon=new ImageIcon(iconURL);
		// }
		// catch(Exception e){
		// //Exception
		// }
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

		// iconLabel.set
		iconLabel.setIcon(pojazdIcon);
		// iconLabel.setText("sdfsdf");
		// iconLabel.setPreferredSize(new Dimension(29,37));
		// iconLabel.setMinimumSize(new Dimension(29,37));
		// panel.add(iconLabel, new GridBagConstraints(0, 1, 2, 2, 1.0, 0.0,
		// GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0,
		// 0, 0), 0, 0));
		// panel.add(iconLabel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
		// GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(5, 5,
		// 5, 5), 0, 0));

		// koniec moich wypocin
		//
		panel.add(iconLabel, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(0, 0, 0, 0), 0, 0));
		panel.add(details, new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		panel.add(remove, new GridBagConstraints(0, 4, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

		return;
	}
}
