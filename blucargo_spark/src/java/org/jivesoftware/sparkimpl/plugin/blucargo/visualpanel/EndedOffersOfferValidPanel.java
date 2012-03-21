package org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.blucargo.model.CargoOffer;

public class EndedOffersOfferValidPanel extends VisualPanel {

	private static final long serialVersionUID = 1511028242474095705L;

	public void render(CargoOffer cargoOffer, JPanel panel, int row) {
		String offerValid = "";

		if (cargoOffer.getOfferValid() != null) {
			offerValid = cargoOffer.getOfferValid().toString();
		}

		panel.add(new JLabel(offerValid), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0));
	}

}
