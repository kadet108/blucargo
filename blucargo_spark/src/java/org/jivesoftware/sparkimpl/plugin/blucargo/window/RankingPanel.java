package org.jivesoftware.sparkimpl.plugin.blucargo.window;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.blucargo.model.CommentAndOffer;


public class RankingPanel extends JPanel{
	

	private static final long serialVersionUID = 1L;
	
	JLabel numberLabel = new JLabel();
	JLabel contactLabel = new JLabel();
	JLabel dateLabel = new JLabel();
	JLabel positivityIndicatorLabel = new JLabel();
	CommentAndOffer commAndOffer;
	
	public RankingPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
				
		add(numberLabel);
		add(contactLabel);
		add(dateLabel);
		add(positivityIndicatorLabel);
	}
	
	
	public void setRankingInfo(CommentAndOffer cmt)
	{
		commAndOffer = cmt;
		
		if( commAndOffer != null )
		{
			
			contactLabel.setText(commAndOffer.getContact());
			dateLabel.setText(String.valueOf( commAndOffer.getDate() ) );
			switch( commAndOffer.getPositivity() ){
				case 1:
					positivityIndicatorLabel.setText("POZYTYWNY");
					positivityIndicatorLabel.setForeground(Color.GREEN);
					break;
				case 0:
					positivityIndicatorLabel.setText("NEUTRALNY");
					positivityIndicatorLabel.setForeground(Color.GRAY);
					break;
				case -1:
					positivityIndicatorLabel.setText("NEGATYWNY");
					positivityIndicatorLabel.setForeground(Color.RED);
					break;
			}
		}
		else
		{
			positivityIndicatorLabel.setVisible(false);	
		}
	}
	
	public void setHeader(String header)
	{
		//commentHeaderLabel.setText(header);
	}

	public String getComment() 
	{
		//return commentArea.getText();
		return "";
	}

}
