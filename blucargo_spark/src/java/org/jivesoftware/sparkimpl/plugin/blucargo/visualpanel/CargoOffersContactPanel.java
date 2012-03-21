package org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.jivesoftware.resource.SparkRes;
import org.jivesoftware.spark.SparkManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.table.HighlightTableModel;

import com.blucargo.model.CargoOffer;

public class CargoOffersContactPanel extends VisualPanel {

	private static final long serialVersionUID = -3949543016005409289L;

	public void render(final CargoOffer cargoOffer, JPanel panel, int row) {
		
		String contact = "";
		if (cargoOffer.getContact() != null) {
			contact = cargoOffer.getContact();
		}

        String buttonName = HighlightTableModel.getInstance().getButtonInRowHighlighted(row);
		
        //ImageIcon pustyIcon1 = SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY1);
		//ImageIcon pustyIcon2 = SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY2);
		
		JButton contactClient;
		contactClient = new JButton("<html><font color='white'>Kontakt</font></html>");
		contactClient.setName("contactClient");
		contactClient.setVerticalTextPosition(SwingConstants.CENTER);
		contactClient.setHorizontalTextPosition(SwingConstants.CENTER);
		contactClient.setIcon					(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY1));
		contactClient.setRolloverIcon			(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY2));
		contactClient.setRolloverSelectedIcon	(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY2));
		//contactClient.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
		//RolloverButton contactClient = new RolloverButton();
		//contactClient.setName("contactClient");
		//contactClient.setIcon					(SparkRes.getImageIcon(SparkRes.CARGO_KONTAKT_1));
		//contactClient.setRolloverIcon			(SparkRes.getImageIcon(SparkRes.CARGO_KONTAKT_2));
		//contactClient.setRolloverSelectedIcon	(SparkRes.getImageIcon(SparkRes.CARGO_KONTAKT_2));

		if(buttonName != null && buttonName.equals("contactClient")){
			contactClient.getModel().setRollover(true);
			
		}

		contactClient.setBorderPainted(false);
		contactClient.setContentAreaFilled(false);
		contactClient.setFocusPainted(false);
		contactClient.setOpaque(false);
		
		contactClient.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String jid = cargoOffer.getOwner() + "@" + SparkManager.getSessionManager().getServerAddress();
				SparkManager.getChatManager().activateChat(jid, jid, cargoOffer);
			}

		});

		panel.add(new JLabel(contact), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0));
		panel.add(contactClient, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0));
	}

}
