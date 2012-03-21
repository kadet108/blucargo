package org.jivesoftware.sparkimpl.plugin.blucargo.window;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import org.jivesoftware.resource.SparkRes;
import org.jivesoftware.spark.SparkManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.manager.CargoDataManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.BackgroundPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.RoundedPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.util.OfferCommentsUtils.OfferComments;

import com.blucargo.model.CargoOffer;
import com.blucargo.model.Comment;
import com.blucargo.model.EndedOffer;
import com.blucargo.model.OfferType;

public class SubmitACommentWindow extends JFrame {
	/**
	 * Stringi dotycz\u0105ce potwierdzenia zam\u00f3wienia
	 */
	private static final String commentConfirmation = "Czy chesz zatwierdzi\u0107 komentarz?";
	private static final String confirmationTitle = "Potwierdzenie zam\u00f3wienia";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Color color = new Color(242, 242, 242, 255);
	
	Color color2 = new Color(208, 203, 181);
	JPanel mainArea = new JPanel();
	JPanel windowHeader = new JPanel();
	JPanel panel1a = new JPanel();
	JPanel windowMain = new JPanel();
	JPanel windowFooter = new JPanel();
	JPanel windowMainFillBox = new JPanel();
	JPanel opisTranzakcjiFill = new JPanel();
	JPanel windowMainFill = new RoundedPanel(color2);
	JScrollPane windowMainScroll = new JScrollPane(windowMainFillBox);
	JPanel panel5 = new JPanel();
	JPanel panel6 = new JPanel();
	JPanel panel7 = new JPanel();
	JPanel panel8 = new JPanel();
	JPanel panel9 = new JPanel();
	JPanel panel10 = new JPanel();
	JPanel panel11 = new JPanel();
	JPanel panel12 = new JPanel();
	SubmitACommentWindow window = this;

	CommentPanel principalPanel = new CommentPanel();
	CommentPanel performerPanel = new CommentPanel();
	CommentPanel editedCommentPanel;
	
	JButton pozytywButton = new JButton("<html><font color='white'>Pozytyw</font></html>");
	JButton negatywButton = new JButton("<html><font color='white'>Negatyw</font></html>");
	
	private CargoOffer cargoOffer;
	private EndedOffer endedOffer;
	private OfferComments cmt;
	
	public SubmitACommentWindow(CargoOffer cargoOffer, OfferComments cmt) {

		if(cargoOffer != null)
			this.setCargoOffer(cargoOffer);
		this.cmt = cmt;
		
		//cargoOffer.getType();
//		this.setEndedOffer(endedOffer);
		
		initialize();
		
		this.setBackground(Color.WHITE);
		this.setLayout(new GridBagLayout());
		this.add(mainArea, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));

		mainArea.setLayout(new GridBagLayout());
		mainArea.setBackground(Color.WHITE);

		ImageIcon blueTopImage = SparkRes
				.getImageIcon(SparkRes.CARGO_NIEBIESKI_PASEK);
		ImageIcon pasekMenuTlo = SparkRes.getImageIcon(SparkRes.CARGO_PASEK_MENU_TLO);
		ImageIcon pasekStopkaTlo = SparkRes
				.getImageIcon(SparkRes.CARGO_PASEK_STOPKA_TLO);
		ImageIcon greyGradientImage = SparkRes
				.getImageIcon(SparkRes.CARGO_PASEK_GORA);
		ImageIcon dolnyPasek = SparkRes.getImageIcon(SparkRes.CARGO_DOLNY_PASEK);
		//ImageIcon logoIcon = SparkRes.getImageIcon(SparkRes.CARGO_LOGO_ICO);
		ImageIcon komentarzHeadIcon = SparkRes.getImageIcon(SparkRes.CARGO_KOMENTARZ_HEAD1);
		ImageIcon komentarzIcon = SparkRes.getImageIcon(SparkRes.CARGO_KOMENTARZ_HEADER);
		ImageIcon negatywnyIcon = SparkRes.getImageIcon(SparkRes.CARGO_NEGATYWNY_HEADER);
		ImageIcon pozytywnyIcon = SparkRes.getImageIcon(SparkRes.CARGO_POZYTYWNY_HEADER);

		createHeader(pasekMenuTlo, komentarzHeadIcon, komentarzIcon, negatywnyIcon, pozytywnyIcon);

		createFooter(pasekStopkaTlo);
		createMain();
		
		if( cmt != null &&  cmt.isOwnerCommentAvailable()  )
		{
			updateCommentInfo(cmt.getOwnerComment(), cmt.getOtherComment());
		}
		else
		{
			if( amIPrincipal() )
			{
				principalPanel.setComment(cmt.getOtherComment());
				editedCommentPanel = performerPanel;
			}
			else
			{
				performerPanel.setComment(cmt.getOtherComment());
				editedCommentPanel = principalPanel;
			}
			
			editedCommentPanel.setCommentEditable(true);
			createCommentButtons();
		}
		//updateTekstsFields(cmtOwner, cmtOther);
		
		// MAIN WINDOW
		mainArea.add(windowHeader, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
		mainArea.add(windowMain, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,
						0, 0, 0), 0, 0));
		mainArea.add(windowFooter, new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0,
				GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));

		pack();
	}
	
	private boolean amIPrincipal()
	{
		return (getCargoOffer().getType() == OfferType.CARGO && this.cmt.getOwnerNickname().equals(getCargoOffer().getOwner() ) || 
		( getCargoOffer().getType() == OfferType.VEHICLE && !this.cmt.getOwnerNickname().equals(getCargoOffer().getOwner())) );
	}
	
	private void updateCommentInfo(Comment cmtOwner, Comment cmtOther) {
	
		if( amIPrincipal() )
		{
			performerPanel.setComment(cmtOwner);
			principalPanel.setComment(cmtOther);
		}
		else
		{
			performerPanel.setComment(cmtOther);
			principalPanel.setComment(cmtOwner);
		}
	}

	private void initialize() {
		windowHeader.setBackground(null);
		panel1a.setBackground(null);
		windowMain.setBackground(null);
		windowFooter.setBackground(null);
		windowMainFill.setBackground(null);
	}

	private void createMain() {
		windowMain.setLayout(new GridBagLayout());
		windowMain.setBackground(new Color(130, 145, 164));
		windowMainScroll.setBorder(null);
		windowMainScroll.setHorizontalScrollBar(null);
		windowMain.add(windowMainScroll, new GridBagConstraints(0, 0, 2, 1, 1.0,
				1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));

		windowMainScroll.setPreferredSize	(new Dimension(700, 300));
		windowMainScroll.setMaximumSize	(new Dimension(700, 300));
		windowMainScroll.setMinimumSize	(new Dimension(700, 300));

		windowMainFillBox.setLayout(new GridBagLayout());
		Color kolorekBoxa = new Color(131, 145, 165);
		windowMainFillBox.setBackground(kolorekBoxa);
		windowMainFillBox.add(windowMainFill, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0));
		
		windowMainFill.setLayout(new GridBagLayout());
		
		JLabel head_tranzakcja = new JLabel("<html><b>Opis tranzakcji:</b></html>");
		ImageIcon flagaFromIcon = SparkRes.getImageIcon(SparkRes.CARGO_TEST_FLAGA);
		ImageIcon flagaToIcon = SparkRes.getImageIcon(SparkRes.CARGO_TEST_FLAGA);
		
		opisTranzakcjiFill.setLayout(new GridBagLayout());
		opisTranzakcjiFill.setBackground(color2);
		opisTranzakcjiFill.add(new JLabel("<html><b>Wolny pojazd</b></html>"), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.WEST, new Insets(0, 0, 0, 0), 0, 0));
		opisTranzakcjiFill.add(new JLabel("<html><b>Sk\u0105d</b></html>"), new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(0, 40, 0, 0), 0, 0));
		opisTranzakcjiFill.add(new JLabel("<html><b>Dok\u0105d</b></html>"), new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(0, 40, 0, 0), 0, 0));
		opisTranzakcjiFill.add(new JLabel("<html><b>Zg\u0142oszono</b></html>"), new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(0, 40, 0, 0), 0, 0));
		opisTranzakcjiFill.add(new JLabel("<html><b>Wa\u017cne do</b></html>"), new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(0, 40, 0, 0), 0, 0));
		opisTranzakcjiFill.add(new JLabel("<html><b>Cena</b></html>"), new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(0, 40, 0, 0), 0, 0));
		
		opisTranzakcjiFill.add(new JLabel("24ton plandeka"), new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(10, 0, 0, 0), 0, 0));
		opisTranzakcjiFill.add(new JLabel(flagaFromIcon), new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(0, 40, 0, 0), 0, 0));
		opisTranzakcjiFill.add(new JLabel(flagaToIcon), new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(0, 40, 0, 0), 0, 0));
		opisTranzakcjiFill.add(new JLabel("24.05.2011"), new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(10, 40, 0, 0), 0, 0));
		opisTranzakcjiFill.add(new JLabel("28.05.2011"), new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(10, 40, 0, 0), 0, 0));
		opisTranzakcjiFill.add(new JLabel("15 z\u0142 / km"), new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(10, 40, 0, 0), 0, 0));
		
		
		opisTranzakcjiFill.add(new JLabel(""), new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		opisTranzakcjiFill.add(new JLabel("Polska"), new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(0, 40, 0, 0), 0, 0));
		opisTranzakcjiFill.add(new JLabel("Polska"), new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(0, 40, 0, 0), 0, 0));
		opisTranzakcjiFill.add(new JLabel(""), new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 40, 0, 0), 0, 0));
		opisTranzakcjiFill.add(new JLabel(""), new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 40, 0, 0), 0, 0));
		opisTranzakcjiFill.add(new JLabel(""), new GridBagConstraints(5, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 40, 0, 0), 0, 0));
		
		opisTranzakcjiFill.add(new JLabel(""), new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.CENTER, new Insets(0, 0, 0, 0), 0, 0));
		opisTranzakcjiFill.add(new JLabel("Opole"), new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(0, 40, 0, 0), 0, 0));
		opisTranzakcjiFill.add(new JLabel("Gdynia"), new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(0, 40, 0, 0), 0, 0));
		opisTranzakcjiFill.add(new JLabel(""), new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 40, 0, 0), 0, 0));
		opisTranzakcjiFill.add(new JLabel(""), new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 40, 0, 0), 0, 0));
		opisTranzakcjiFill.add(new JLabel(""), new GridBagConstraints(5, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 40, 0, 0), 0, 0));
		
		performerPanel.setHeader("<html><b>Komentarz zleceniobiorcy:</b></html>");
		principalPanel.setHeader("<html><b>Komentarz zleceniodawcy:</b></html>");
		
		Object[][] cmp = new Object[][] {
				{ 
					head_tranzakcja, 
					new GridBagConstraints(0, 0, 1, 1, 0.0,
						0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
						new Insets(10, 10, 0, 10), 0, 0) 
				},
				{
					opisTranzakcjiFill, 
					new GridBagConstraints(0, 1, 1, 1, 0.0,
						0.0, GridBagConstraints.WEST, GridBagConstraints.WEST,
						new Insets(10, 10, 10, 10), 0, 0)
				},
				{
					principalPanel,
					new GridBagConstraints(0, 2, 1, 1, 0.0,
							0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
							new Insets(10, 10, 0, 10), 0, 0)
				},
				{
					performerPanel,
					new GridBagConstraints(0, 3, 1, 1, 0.0,
							0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
							new Insets(10, 10, 0, 10), 0, 0)
				},
				{
					new JLabel(""),
					new GridBagConstraints(0, 4, 1, 1, 1.0,
							1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
							new Insets(10, 10, 10, 10), 0, 0)
				}
		};
		
		for( Object[] it : cmp )
		{
			windowMainFill.add( (JComponent) it[0], (GridBagConstraints) it[1] );
		}	
		

	}

	private void createFooter(ImageIcon pasekStopkaTlo) {
		windowFooter = new BackgroundPanel();
		((BackgroundPanel) windowFooter).setBackgroundImage(pasekStopkaTlo);
		windowFooter.setLayout(new GridBagLayout());
		windowFooter.setPreferredSize(new Dimension(700, 45));
		windowFooter.setMaximumSize(new Dimension(700, 45));
		windowFooter.setMinimumSize(new Dimension(700, 45));
	}

	private void createCommentButtons() {
		
		pozytywButton.setName("pozytywButton");
		pozytywButton.setVerticalTextPosition(SwingConstants.CENTER);
		pozytywButton.setHorizontalTextPosition(SwingConstants.CENTER);
		pozytywButton.setIcon					(SparkRes.getImageIcon(SparkRes.CARGO_POZYTYW_PUSTY1));
		pozytywButton.setRolloverIcon			(SparkRes.getImageIcon(SparkRes.CARGO_POZYTYW_PUSTY2));
		pozytywButton.setRolloverSelectedIcon	(SparkRes.getImageIcon(SparkRes.CARGO_POZYTYW_PUSTY2));
		pozytywButton.setBorderPainted(false);
		pozytywButton.setContentAreaFilled(false);
		pozytywButton.setFocusPainted(false);
		pozytywButton.setOpaque(false);
		pozytywButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		negatywButton.setName("negatywButton");
		negatywButton.setVerticalTextPosition(SwingConstants.CENTER);
		negatywButton.setHorizontalTextPosition(SwingConstants.CENTER);
		negatywButton.setIcon					(SparkRes.getImageIcon(SparkRes.CARGO_NEGATYW_PUSTY1));
		negatywButton.setRolloverIcon			(SparkRes.getImageIcon(SparkRes.CARGO_NEGATYW_PUSTY2));
		negatywButton.setRolloverSelectedIcon	(SparkRes.getImageIcon(SparkRes.CARGO_NEGATYW_PUSTY2));
		negatywButton.setBorderPainted(false);
		negatywButton.setContentAreaFilled(false);
		negatywButton.setFocusPainted(false);
		negatywButton.setOpaque(false);
		negatywButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		pozytywButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if( applyComment( editedCommentPanel.getComment(), 1) ){
					hideCommentButtons();
				}
			}
		});		
		
		negatywButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if( applyComment(editedCommentPanel.getComment(), -1) ){
					hideCommentButtons();
				}
			}
		});
		
		windowFooter.add(pozytywButton, new GridBagConstraints(0, 0, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		windowFooter.add(negatywButton, new GridBagConstraints(1, 0, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
	}
	
	private boolean applyComment(String commentStr, int positivity) {
		
		if( JOptionPane.showConfirmDialog(
				this, commentConfirmation, 
				confirmationTitle, JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION 
		  ) return false;
		
		Comment comment=new Comment();
		comment.setAuthor(SparkManager.getUserManager().getNickname());
		comment.setCargoOfferId(cargoOffer.getId());
		comment.setContent(commentStr);
		comment.setDate(new Date());
		comment.setPositivity(positivity);
		
		CargoDataManager.getInstance().addComment(comment);
		CargoDataManager.getInstance().refreshEndedOffers();
		
		cmt.setOwnerComment(comment);
		updateCommentInfo(cmt.getOwnerComment(), cmt.getOtherComment());
		
		return true;
	}

	private void createHeader(ImageIcon pasekMenuTlo, ImageIcon komentarzHeadIcon, ImageIcon komentarzIcon, ImageIcon negatywnyIcon, ImageIcon pozytywnyIcon) {
		windowHeader = new BackgroundPanel();
		((BackgroundPanel) windowHeader).setBackgroundImage(pasekMenuTlo);
		windowHeader.setLayout(new GridBagLayout());
		windowHeader.setLayout(new FlowLayout(FlowLayout.LEFT,10,0)); //TUTAJ TRZEBA POGRZEBAC
		windowHeader.setPreferredSize(new Dimension(700, 71));
		windowHeader.setMaximumSize(new Dimension(700, 71));
		windowHeader.setMinimumSize(new Dimension(700, 71));
		
		
		windowHeader.add(new JLabel(komentarzHeadIcon), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
		
		
		windowHeader.add(new JLabel(komentarzIcon), new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
		
		
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
//				new SubmitAComment().setVisible(true);
			}
		});
	}

	public void setCargoOffer(CargoOffer cargoOffer) {
		this.cargoOffer = cargoOffer;
	}

	public CargoOffer getCargoOffer() {
		return cargoOffer;
	}

	public EndedOffer getEndedOffer() {
		return endedOffer;
	}

	public void setEndedOffer(EndedOffer endedOffer) {
		this.endedOffer = endedOffer;
	}

	private void hideCommentButtons() 
	{
		negatywButton.setVisible(false);
		pozytywButton.setVisible(false);
		
	}

}
