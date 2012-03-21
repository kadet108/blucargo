package org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jivesoftware.resource.SparkRes;

import com.blucargo.model.CargoOffer;

public class CargoOffersLoadPanel extends VisualPanel {

	private static final long serialVersionUID = 7333937736370334065L;

	public void render(CargoOffer cargoOffer, JPanel panel, int row) {
		String countryFrom = "";
		String cityFrom = "";
		String poFrom = "";

		if(cargoOffer == null){
			return;
		}
		
		if (cargoOffer.getCountryFrom() != null) {
			countryFrom = cargoOffer.getCountryFrom();
		}
		if (cargoOffer.getCityFrom() != null) {
			cityFrom = cargoOffer.getCityFrom();
		}
		if (cargoOffer.getPostOfficeFrom() != null) {
			poFrom = cargoOffer.getPostOfficeFrom();
		}

		String country = cargoOffer.getCountryFrom();

		// obrazek
		// ImageIcon countryIcon = new
		// ImageIcon("images/cargo/countries/PL.png");
		ClassLoader cl = SparkRes.class.getClassLoader();

		JLabel iconLabel = new JLabel();
		String countryString = "images/cargo/countries/" + country.toUpperCase() + ".png";
		URL iconURL = cl.getResource(countryString);

		ImageIcon countryIcon = null;

		try {
			countryIcon = new ImageIcon(iconURL);
		} catch (Exception e) {
			// Exception
		}

		iconLabel.setIcon(countryIcon);
		panel.add(iconLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

		panel.add(new JLabel(countryFrom), new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		panel.add(new JLabel(cityFrom), new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		panel.add(new JLabel(poFrom), new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		return;
	}

}
