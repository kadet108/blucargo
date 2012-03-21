package org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.blucargo.model.CargoOffer;

public class AcceptedOffersSubmissionDatePanel extends VisualPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2766057273264273625L;

	public void render(CargoOffer cargoOffer, JPanel panel, int row) {

		String submissionDate = "";

		if (cargoOffer.getSubmissionDate() != null) {
			submissionDate = cargoOffer.getSubmissionDate().toString();
		}

		panel.add(new JLabel(submissionDate), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0));
	}

}
