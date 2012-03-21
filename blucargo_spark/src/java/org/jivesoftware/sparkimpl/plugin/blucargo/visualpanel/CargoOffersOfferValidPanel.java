package org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jivesoftware.sparkimpl.plugin.blucargo.util.DateFormatUtils;

import com.blucargo.model.CargoOffer;

public class CargoOffersOfferValidPanel extends VisualPanel {

	public void render(CargoOffer cargoOffer, JPanel panel, int row) {
		String offerValid = "";

		if (cargoOffer.getOfferValid() != null) {
			offerValid = DateFormatUtils.formatDate(cargoOffer.getOfferValid());
		}

		panel.add(new JLabel(offerValid), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0));
	}

}
