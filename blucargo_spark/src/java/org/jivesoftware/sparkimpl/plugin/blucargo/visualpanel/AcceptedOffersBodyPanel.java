package org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.blucargo.model.CargoOffer;

public class AcceptedOffersBodyPanel extends VisualPanel {

	private static final long serialVersionUID = 1028569284188197340L;

	public void render(CargoOffer cargoOffer, JPanel panel, int row) {
		String weight = "";
		String body = "";
		String length = "";

		if (cargoOffer.getWeight() != null) {
			weight = cargoOffer.getWeight();
		}
		if (cargoOffer.getWeight() != null) {
			body = cargoOffer.getBody();
		}
		if (cargoOffer.getWeight() != null) {
			length = cargoOffer.getCargoLength();
		}

		panel.add(new JLabel(weight), 	new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		panel.add(new JLabel(body), 	new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		panel.add(new JLabel(length), 	new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		
	}

}
