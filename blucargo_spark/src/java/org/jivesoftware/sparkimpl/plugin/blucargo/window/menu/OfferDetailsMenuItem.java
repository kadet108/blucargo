package org.jivesoftware.sparkimpl.plugin.blucargo.window.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import org.jivesoftware.spark.SparkManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.OfferDetailsWindow;

import com.blucargo.model.CargoOffer;

public class OfferDetailsMenuItem extends JMenuItem implements ActionListener{

	private static final long serialVersionUID = 1L;
    private CargoOffer cargoOffer;
	
	public CargoOffer getCargoOffer() {
		return cargoOffer;
	}

	public void setCargoOffer(CargoOffer cargoOffer) {
		this.cargoOffer = cargoOffer;
	}

	public OfferDetailsMenuItem(){
		super("Szczeg\u00f3\u0142y");
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		OfferDetailsWindow offerDetails = new OfferDetailsWindow(cargoOffer);
		offerDetails.setVisible(true);
	}

}
