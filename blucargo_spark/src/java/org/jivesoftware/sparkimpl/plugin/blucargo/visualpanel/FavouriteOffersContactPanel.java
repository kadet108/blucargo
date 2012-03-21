package org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.blucargo.model.CargoOffer;

public class FavouriteOffersContactPanel extends VisualPanel {

	private static final long serialVersionUID = 2407186038861131442L;

	public void render(CargoOffer cargoOffer, JPanel panel, int row) {

		String contact = "";
		if (cargoOffer.getContact() != null) {
			contact = cargoOffer.getContact();
		}
		panel.add(new JLabel(contact), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0));
	}

}
