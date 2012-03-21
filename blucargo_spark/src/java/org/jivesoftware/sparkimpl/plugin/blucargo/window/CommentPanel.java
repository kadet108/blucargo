package org.jivesoftware.sparkimpl.plugin.blucargo.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.jivesoftware.resource.SparkRes;

import com.blucargo.model.Comment;

public class CommentPanel extends JPanel 
{
	private static final long serialVersionUID = 1L;
	JLabel commentHeaderLabel = new JLabel();
	JTextArea commentArea = new JTextArea();
	JLabel positivityIndicatorLabel = new JLabel();
	ImageIcon buzkaPozytywna = SparkRes.getImageIcon(SparkRes.CARGO_KOMENTARZ_BUZIA_POZYTYW);
	ImageIcon buzkaNegatywna = SparkRes.getImageIcon(SparkRes.CARGO_KOMENTARZ_BUZIA_NEGATYW);
	Comment comment;
	
	JPanel buzkaPanel = new JPanel();
	
	public CommentPanel()
	{
		
		//JPanel komentarzFill = new JPanel();
		
		setLayout(new GridBagLayout());
		Color kolorekBoxa = new Color(198, 193, 172);
		setBackground(kolorekBoxa);
		
		commentArea.setRows(6);
		commentArea.setWrapStyleWord(true);
		commentArea.setLineWrap(true);
		commentArea.setPreferredSize(new Dimension(300, 60));
		commentArea.setMaximumSize(new Dimension(300, 60));
		commentArea.setMinimumSize(new Dimension(300, 60));
		commentArea.setMargin(new Insets(10,10,10,10)); 
		commentArea.setBackground(kolorekBoxa);
		
		buzkaPanel.setLayout(new GridBagLayout());
		buzkaPanel.setBackground(kolorekBoxa);
		
		if( comment != null )
		{
		

		
		}
		
		//if( positivityIndicatorLabel.getText()=="POZYTYWNY" )
        //{
		//	buzkaPanel.add(new JLabel(buzkaPozytywna), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(0, 0, 0, 10), 0, 0));
        //}
		//else if( positivityIndicatorLabel.getText()=="NEGATYWNY" )
		//{
		//	buzkaPanel.add(new JLabel(buzkaNegatywna), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(0, 0, 0, 10), 0, 0));
		//}
		//else
		//{
		//	buzkaPanel.add(new JLabel(""), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(0, 0, 0, 10), 0, 0));
		//}
		
		
		
		//buzkaPanel.add(new JLabel(buzkaPozytywna), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(0, 0, 0, 10), 0, 0));
		
		//Color kolorcommentArea = new Color(194,189,169);
		//Color kolorBordercommentArea = new Color(182,178,159);
		//commentArea.setBackground(kolorcommentArea);
		//commentArea.setBorder(BorderFactory.createLineBorder(kolorBordercommentArea)); 
		//commentArea.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		positivityIndicatorLabel.setVisible(false);
		setCommentEditable(false);
		
		add(commentHeaderLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		add(commentArea, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		add(buzkaPanel, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		
		//add(positivityIndicatorLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		
		/*
		 * 
		 * 
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		commentArea.setRows(6);
		commentArea.setWrapStyleWord(true);
		commentArea.setLineWrap(true);
		commentArea.setPreferredSize(new Dimension(300, 60));
		commentArea.setMaximumSize(new Dimension(300, 60));
		commentArea.setMinimumSize(new Dimension(300, 60));
		commentArea.setMargin(new Insets(10,10,10,10)); 
		
		Color kolorcommentArea = new Color(194,189,169);
		Color kolorBordercommentArea = new Color(182,178,159);
		commentArea.setBackground(kolorcommentArea);
		commentArea.setBorder(BorderFactory.createLineBorder(kolorBordercommentArea)); 
		commentArea.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		positivityIndicatorLabel.setVisible(false);
		setCommentEditable(false);
		
		add(commentHeaderLabel);
		add(positivityIndicatorLabel);
		add(commentArea);
		*/
	}
	
	public void setCommentEditable(boolean e)
	{
		commentArea.setEditable(e);
		commentArea.setText(e ? "" : "Brak komentrza");
		Color kolorekBoxa = new Color(208, 203, 181);
		setBackground(kolorekBoxa);
	}
	
	public void setComment(Comment cmt)
	{
		comment = cmt;
		
		if( comment != null )
		{
			setCommentEditable(false);
			positivityIndicatorLabel.setVisible(true);
			commentArea.setText(comment.getContent());
			switch( comment.getPositivity() ){
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
			
			if (comment.getPositivity() == 1){
				buzkaPanel.add(new JLabel(buzkaPozytywna), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(0, 0, 0, 10), 0, 0));			
			}
			else if (comment.getPositivity() == -1){
				buzkaPanel.add(new JLabel(buzkaNegatywna), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(0, 0, 0, 10), 0, 0));
			}
		}
		else
		{
			positivityIndicatorLabel.setVisible(false);	
		}
	}
	
	public void setHeader(String header)
	{
		commentHeaderLabel.setText(header);
		Color kolorekBoxa = new Color(208, 203, 181);
		commentHeaderLabel.setBackground(kolorekBoxa);
	}

	public String getComment() 
	{
		return commentArea.getText();
	}
}
