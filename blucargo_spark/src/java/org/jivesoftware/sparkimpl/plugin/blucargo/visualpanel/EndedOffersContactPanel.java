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
import org.jivesoftware.sparkimpl.plugin.blucargo.window.SubmitACommentWindow;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.util.OfferCommentsUtils;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.util.OfferCommentsUtils.OfferComments;

import com.blucargo.model.CargoOffer;
import com.blucargo.model.Comment;

public class EndedOffersContactPanel extends VisualPanel{

	private static final long serialVersionUID = 2407186038861131442L;
    
	OfferComments cmt;
	Comment commentFromList;
	String contentOfCommentStr;
	
    public void render(final CargoOffer cargoOffer, JPanel panel, int row){
    	
        String contact = "";
        if (cargoOffer.getContact() != null) {
            contact = cargoOffer.getContact();
        }
        
        String buttonName = HighlightTableModel.getInstance().getButtonInRowHighlighted(row);
        
        JButton makeComment;
        String makeCommentStr = "<html><font color='white'>Dodaj komentarz</font></html>";
        
        cmt = OfferCommentsUtils.getOfferComments(cargoOffer.getId(), SparkManager.getUserManager().getNickname());
        
        if( cmt.isOwnerCommentAvailable() )
        {
        	makeCommentStr = "<html><font color='white'>Komentarze</font></html>";
        }
        
        makeComment = new JButton(makeCommentStr);
        makeComment.setName("makeComment");
        makeComment.setVerticalTextPosition(SwingConstants.CENTER);
        makeComment.setHorizontalTextPosition(SwingConstants.CENTER);
        makeComment.setIcon					(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY1));
        makeComment.setRolloverIcon			(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY2));
        makeComment.setRolloverSelectedIcon	(SparkRes.getImageIcon(SparkRes.CARGO_OGLOSZENIE_PUSTY2));
        
        
		//JButton makeComment = new JButton();
		//makeComment.setName("makeComment");
		//makeComment.setIcon					(SparkRes.getImageIcon(SparkRes.CARGO_KOMENTARZ_1));
		//makeComment.setRolloverIcon			(SparkRes.getImageIcon(SparkRes.CARGO_KOMENTARZ_2));
		//makeComment.setRolloverSelectedIcon	(SparkRes.getImageIcon(SparkRes.CARGO_KOMENTARZ_2));

		if(buttonName != null && buttonName.equals("makeComment")){
			makeComment.getModel().setRollover(true);
		}


		makeComment.setBorderPainted(false);
		makeComment.setContentAreaFilled(false);
		makeComment.setFocusPainted(false);
		makeComment.setOpaque(false);
        
        makeComment.addMouseListener(new MouseAdapter(){
        	public void mouseClicked(MouseEvent e) {
				SubmitACommentWindow submitAComment=new SubmitACommentWindow(cargoOffer, cmt);
				submitAComment.setVisible(true);        		
        	}
        });
        
        panel.add(new JLabel(contact), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0));
        panel.add(makeComment, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0));
    }

}
