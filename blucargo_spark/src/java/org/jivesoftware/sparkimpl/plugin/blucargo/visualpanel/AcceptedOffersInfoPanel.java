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
import org.jivesoftware.sparkimpl.plugin.blucargo.resourcebundle.StrAccessor;
import org.jivesoftware.sparkimpl.plugin.blucargo.table.HighlightTableModel;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.OfferDetailsWindow;

import com.blucargo.model.CargoOffer;
import com.blucargo.model.OfferType;

public class AcceptedOffersInfoPanel extends VisualPanel {

	private static final long serialVersionUID = 1L;

	public void render(final CargoOffer cargoOffer, JPanel panel, int row) {
		String unloadingDate = ""; //$NON-NLS-1$
		if (cargoOffer.getUnloadingDate() != null) {
			unloadingDate = cargoOffer.getUnloadingDate().toString();
		}

        String buttonName = HighlightTableModel.getInstance().getButtonInRowHighlighted(row);
        
        
		
		
		
		// Detaljer knappen
        JButton details;
        details = new JButton("<html><font color='white'>"+StrAccessor.getString("AcceptedOffersInfoPanel.buttonDetails")+"</font></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        details.setName("details"); //$NON-NLS-1$
        details.setVerticalTextPosition(SwingConstants.CENTER);
        details.setHorizontalTextPosition(SwingConstants.CENTER);
        details.setIcon					(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY1));
        details.setRolloverIcon			(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY2));
        details.setRolloverSelectedIcon	(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY2));
        
        
        //RolloverButton details = new RolloverButton();
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

		// koniec moich wypocin

		panel.add(iconLabel, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(0, 0, 0, 0), 0, 0));
		panel.add(details, new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));

		return;
	}
}
