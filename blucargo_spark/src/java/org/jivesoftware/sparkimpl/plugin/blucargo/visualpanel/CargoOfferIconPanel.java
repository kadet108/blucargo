package org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jivesoftware.resource.SparkRes;

import com.blucargo.model.CargoOffer;
import com.blucargo.model.OfferType;


	public class CargoOfferIconPanel extends VisualPanel {

		private static final long serialVersionUID = 1L;

		JButton addToFavourites;
		JButton details;
		
		public void render(final CargoOffer cargoOffer, JPanel panel, int row) {

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
			
			return;
		}
	}

