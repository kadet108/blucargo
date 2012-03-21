package org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jivesoftware.sparkimpl.plugin.blucargo.util.DateFormatUtils;

import com.blucargo.model.CargoOffer;

public class CargoOffersSubmissionDatePanel extends VisualPanel {

	public void render(CargoOffer cargoOffer, JPanel panel, int row) {

		String submissionDate = "";

		if (cargoOffer == null){
			return ;
		}
		
		if (cargoOffer.getSubmissionDate() != null) {
			submissionDate = getDate(cargoOffer);
		}

		panel.add(new JLabel(submissionDate), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0));
	}

	protected String getDate(CargoOffer cargoOffer){
		return DateFormatUtils.formatDate(cargoOffer.getSubmissionDate());
	}
}
