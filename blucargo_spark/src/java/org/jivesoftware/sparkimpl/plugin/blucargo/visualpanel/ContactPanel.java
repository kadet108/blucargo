package org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.blucargo.model.CargoOffer;

public class ContactPanel extends VisualPanel {

	private static final long serialVersionUID = -3949543016005409289L;

	public void render(CargoOffer cargoOffer, JPanel panel, int row) {
		String contact = "";
		if (cargoOffer.getContact() != null) {
			contact = cargoOffer.getContact();
		}
		panel.add(new JLabel(contact), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
	}

}
