package org.jivesoftware.sparkimpl.plugin.blucargo.window.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import org.jivesoftware.spark.SparkManager;

import com.blucargo.model.CargoOffer;

public class ContactMenuItem extends JMenuItem implements ActionListener{

	private static final long serialVersionUID = 1L;
    private CargoOffer cargoOffer;
	
	public CargoOffer getCargoOffer() {
		return cargoOffer;
	}

	public void setCargoOffer(CargoOffer cargoOffer) {
		this.cargoOffer = cargoOffer;
	}

	public ContactMenuItem(){
		super("Kontaktuj");
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String jid = cargoOffer.getOwner() + "@" + SparkManager.getSessionManager().getServerAddress();
		SparkManager.getChatManager().activateChat(jid, jid, cargoOffer);
	}

}
