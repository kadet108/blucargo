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
import org.jivesoftware.sparkimpl.plugin.blucargo.table.HighlightTableModel;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.ContactClientWindow;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.EndOfferWindow;

import com.blucargo.model.CargoOffer;

public class AcceptedOffersContactPanel extends VisualPanel{

	private static final long serialVersionUID = 2407186038861131442L;
    
    public void render(final CargoOffer cargoOffer, JPanel panel, int row){
        String contact = "";
        if (cargoOffer.getContact() != null) {
            contact = cargoOffer.getContact();
        }
        panel.add(new JLabel(contact), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0));

        String buttonName = HighlightTableModel.getInstance().getButtonInRowHighlighted(row);
        
        JButton finished;
        finished = new JButton("<html><font color='white'>Zako\u0144czone</font></html>");
        finished.setName("finished");
        finished.setVerticalTextPosition(SwingConstants.CENTER);
        finished.setHorizontalTextPosition(SwingConstants.CENTER);
        finished.setIcon					(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY1));
        finished.setRolloverIcon			(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY2));
        finished.setRolloverSelectedIcon	(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY2));
        
        
        
		//RolloverButton finished = new RolloverButton();
		//finished.setName("finished");
		
		
		if(buttonName != null && buttonName.equals("finished")){
			finished.getModel().setRollover(true);
		}
		
		
		//finished.setIcon					(SparkRes.getImageIcon(SparkRes.CARGO_ZAKONCZONO_1));
		//finished.setRolloverIcon			(SparkRes.getImageIcon(SparkRes.CARGO_ZAKONCZONO_2));
		//finished.setRolloverSelectedIcon	(SparkRes.getImageIcon(SparkRes.CARGO_ZAKONCZONO_2));



		finished.setBorderPainted(false);
		finished.setContentAreaFilled(false);
		finished.setFocusPainted(false);
		finished.setOpaque(false);
        finished.addMouseListener(new MouseAdapter(){
        	public void mouseClicked(MouseEvent e){
        		EndOfferWindow endOffer=new EndOfferWindow(cargoOffer);
        		endOffer.setVisible(true);
        	}
        });
        
        JButton contactClient;
        contactClient = new JButton("<html><font color='white'>Kontakt</font></html>");
        contactClient.setName("contactClient");
        contactClient.setVerticalTextPosition(SwingConstants.CENTER);
        contactClient.setHorizontalTextPosition(SwingConstants.CENTER);
        contactClient.setIcon					(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY1));
        contactClient.setRolloverIcon			(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY2));
        contactClient.setRolloverSelectedIcon	(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY2));
        
        
        //JButton contactClient = new JButton();
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
        contactClient.addMouseListener(new MouseAdapter(){
        	public void mouseClicked(MouseEvent e){
        		ContactClientWindow contactClient=new ContactClientWindow();
        		contactClient.setVisible(true);
        	}
        		
        });
        
        panel.add(finished, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(5, 5, 0, 5), 0, 0));
//        panel.add(makeComment, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        panel.add(contactClient, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(0, 5, 0, 5), 0, 0));
    
    }
}
