package org.jivesoftware.sparkimpl.plugin.blucargo.window;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jivesoftware.resource.SparkRes;
//import org.jivesoftware.sparkimpl.plugin.blucargo.HTMLtag;
import org.jivesoftware.sparkimpl.plugin.blucargo.manager.CargoDataManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.resourcebundle.StrAccessor;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.BackgroundPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.RoundedPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.enums.UserPresenceWithComments;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.util.OfferCommentsUtils;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.util.OfferCommentsUtils.OfferComments;

import com.blucargo.model.CargoOffer;
import com.blucargo.model.Comment;
import com.blucargo.model.EndedOffer;

public class CompanyXRankingWindow extends JPanel {


	private static final long serialVersionUID = 1L; 
	
	Color color = new Color(242, 242, 242, 255);

	Color color2 = new Color(208, 203, 181);
	JPanel mainArea = new JPanel();
	JPanel windowHeader = new JPanel();
	JPanel panel1a = new JPanel();
	JPanel windowMain = new JPanel();
	JPanel windowFooter = new JPanel();
	
	JPanel windowMainFillBox = new JPanel();
	JPanel windowMainFill = new RoundedPanel(color2);
	JScrollPane windowMainScroll = new JScrollPane(windowMainFillBox);
	
	JLabel pusta = new JLabel(""); //$NON-NLS-1$
	JLabel wskaznikZaufania		= new JLabel();
	JLabel wskaznikZaufania2	= new JLabel();
	JLabel nazwaFirmy			= new JLabel();

	List<JLabel> labelList = new ArrayList<JLabel>();
	
	JPanel windowMainFill2 = new RoundedPanel(color2);
	JPanel panel5 = new JPanel();
	JPanel panel6 = new JPanel();
	JPanel panel7 = new JPanel();
	JPanel panel8 = new JPanel();
	JPanel panel9 = new JPanel();
	JPanel panel10 = new JPanel();
	JPanel panel11 = new JPanel();
	JPanel panel12 = new JPanel();
	CompanyXRankingWindow window = this;
	//final CargoOffer cargoOffer = (CargoOffer) table.getValueAt(row, 0);
	OfferComments cmt;
	CargoOffer cargoOffer;
	
	JPanel[] commentRankingCollumn = new JPanel[] { 
			new JPanel(),  new JPanel(), new JPanel(), new JPanel() 
		};

	public CompanyXRankingWindow() {
			
		initialize();

		this.setBackground(Color.WHITE);
		this.setLayout(new GridBagLayout());
		this.add(mainArea, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		mainArea.setLayout(new GridBagLayout());
		mainArea.setBackground(Color.WHITE);

		ImageIcon pasekMenuTlo 		= SparkRes.getImageIcon(SparkRes.CARGO_PASEK_MENU_TLO);
		ImageIcon pasekStopkaTlo 	= SparkRes.getImageIcon(SparkRes.CARGO_PASEK_STOPKA_TLO);

		ImageIcon rankingIcon1 = null;
		rankingIcon1 = SparkRes.getImageIcon(SparkRes.CARGO_RANKING_HEAD1);
			
		ImageIcon rankingIcon2 = null;
		rankingIcon2 = SparkRes.getImageIcon(SparkRes.CARGO_RANKING_HEAD2);

		createHeader(pasekMenuTlo, rankingIcon1, rankingIcon2);
		createFooter(pasekStopkaTlo);
		createMain();

		mainArea.add(windowHeader, 		new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		mainArea.add(windowMain, 		new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		//mainArea.add(windowFooter, 		new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

		//pack();
	}

	private void initialize() {
		windowHeader.setBackground(null);
		panel1a.setBackground(null);
		windowMain.setBackground(null);
		windowFooter.setBackground(null);
		windowMainFill.setBackground(null);
		windowMainFill2.setBackground(null);
	}

	private void createMain() {
		appendLabelList();
		
		ImageIcon pasekMenuTlo 		= SparkRes.getImageIcon(SparkRes.CARGO_TLO1PX);
		windowMain = new BackgroundPanel();
		((BackgroundPanel) windowMain).setBackgroundImage(pasekMenuTlo);
		
		windowMain.setLayout(new GridBagLayout());
		windowMain.setBackground(new Color(130, 145, 164));
		//
		
		
		windowMainScroll.setBorder(null);
		windowMainScroll.setHorizontalScrollBar(null);
		//windowMain.add(windowMainScroll, new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		
		//windowMain.add(windowMainFill, new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 5, 5), 0, 0));
		windowMain.add(windowMainFill2, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(20, 20, 20, 10), 0, 0));
		windowMain.add(windowMainScroll, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		
		setSizes(windowMainScroll, 560, 200);

		windowMainFillBox.setLayout(new GridBagLayout());
		Color kolorekBoxa = new Color(131, 145, 165);
		windowMainFillBox.setBackground(kolorekBoxa);
		windowMainFillBox.add(windowMainFill, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(20, 10, 20, 20), 0, 0));
		
		windowMainFill.setLayout(new GridBagLayout());
		
		windowMainFill2.setLayout(new GridBagLayout());
		setSizes(windowMainFill2, 250, 200);
		

		
		
		JPanel column4 						= new JPanel();
		column4.setBackground(new Color(208, 203, 181));
		column4.setLayout(new GridBagLayout());
		
		JLabel sprawdzHead = new JLabel("OJEJ"+"<html><b>" + StrAccessor.getString("CompanyXRankingWindow.checkContractorRanking") + //$NON-NLS-1$ //$NON-NLS-2$ 
				"</b><br>" + StrAccessor.getString("CompanyXRankingWindow.provideCargoID") + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
		setSizes(sprawdzHead, 200, 29);
		
		final JTextField cargoID 			= new JTextField(1);
		cargoID.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				appendUserCorrect(refreshWindowMain(cargoID.getText()));				
			}
		});
		
		//pierwszy button
		JButton sprawdzButton1 = new JButton();
		
		ImageIcon sprawdzIcon1 = SparkRes.getImageIcon(SparkRes.CARGO_HISTORIA_ROZMOW1);
		ImageIcon sprawdzIcon2 = SparkRes.getImageIcon(SparkRes.CARGO_HISTORIA_ROZMOW2);
		
		sprawdzButton1 = new JButton("<html><font color='white'>" + StrAccessor.getString("CompanyXRankingWindow.checkRanking") + "</font></html>",sprawdzIcon1); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		sprawdzButton1.setVerticalTextPosition(SwingConstants.CENTER);
		sprawdzButton1.setHorizontalTextPosition(SwingConstants.CENTER);
		sprawdzButton1.setRolloverIcon(sprawdzIcon2);
		sprawdzButton1.setOpaque(false);
		sprawdzButton1.setBorder(null);
		sprawdzButton1.setBorderPainted(false);
		sprawdzButton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sprawdzButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					appendUserCorrect(refreshWindowMain(cargoID.getText()));
			}
		});

		column4.add(sprawdzHead, 					new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column4.add(cargoID, 						new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		column4.add(sprawdzButton1, 				new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		
		windowMainFill2.add(column4, 		new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		
		//String user;
		//user = SparkManager.getUserManager().getNickname();
		refreshWindowMain(CompanyRankingWindow.cargoIdFromTextBox);
	}
	
	public UserPresenceWithComments refreshWindowMain(String user){
		boolean userBool = CargoDataManager.getInstance().isUser(user);
		List<Comment> ls;
		ls = CargoDataManager.getCommentDao().findCommentsByAuthor(user);
		List<EndedOffer> ls2 = CargoDataManager.getEndedOfferDao().findEndedOfferByOwner(user);

		if(userBool && ls.size()>0){
			
			apendContactAndRelibleFaktorForCargoId(ls, ls2, user);
			
			fillRankingColumns(ls);

			windowMainFill.removeAll();
			windowMainFill.add(commentRankingCollumn[0], new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(10, 10, 10, 0), 0, 0));
			windowMainFill.add(commentRankingCollumn[1], new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(10, 0, 10, 0), 0, 0));
			windowMainFill.add(commentRankingCollumn[2], new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(10, 0, 10, 0), 0, 0));
			windowMainFill.add(commentRankingCollumn[3], new GridBagConstraints(3, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(10, 0, 10, 0), 0, 0));
			windowMainFill.add(pusta, 					 new GridBagConstraints(4, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(10, 0, 10, 0), 0, 0));

			windowMainFill.validate();
			windowMainFill.repaint();
			
			return UserPresenceWithComments.YesUserWithComments;
		}
		else if(userBool && ls.size() == 0 ){
			if(CompanyRankingWindow.isUserOwner(user))
				return UserPresenceWithComments.YesUserWithNoCommentOwner;
			else
				return UserPresenceWithComments.YesUserWithNoCommentOther;
		}
		else
			return UserPresenceWithComments.NoUser;
		
	}
	
	public void fillRankingColumns(List<Comment> ls){

		setStyleColumns();
		
		for(int rowNo=0; rowNo<ls.size(); rowNo++){
			appendNumberColumnItem(rowNo);
			appendCommentOwnerColumn(ls.get(rowNo).getAuthor(), rowNo);
			appendPositivityColumn(ls.get(rowNo).getPositivity() , rowNo);				
			appendButtonColumn(ls.get(rowNo).getCargoOfferId(), ls.get(rowNo).getAuthor(), rowNo);
		}
	}
	
	public void setStyleColumns(){
		for(JPanel lst: commentRankingCollumn){
			lst.removeAll();
			lst.setBackground(new Color(208, 203, 181));
			lst.setLayout(new GridBagLayout());
			lst.validate();
			lst.repaint();
		}
	}
	
	public void appendNumberColumnItem(int rowNo){
		
			JLabel numerPoz1 							= new JLabel("<html><b>" + ""+( rowNo +1 )+"</b></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			
			setSizes(numerPoz1, 20, 29);
			
			commentRankingCollumn[0].add(numerPoz1, new GridBagConstraints(0, rowNo, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 0), 0, 0));
	}
	
	public void appendCommentOwnerColumn(String author, int rowNo){;

			JLabel nazwaPoz1 							= new JLabel(author);
			setSizes(nazwaPoz1, 200, 29);
			commentRankingCollumn[1].add(nazwaPoz1, new GridBagConstraints(0, rowNo, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));

	}
	
	public void appendPositivityColumn(int positivity, int rowNo){
			
			String positivityColour;
			String positivityName;
			
			if (positivity > 0){
				positivityColour = "'green'"; //$NON-NLS-1$
				positivityName = StrAccessor.getString("CompanyXRankingWindow.positiveFeedback"); //$NON-NLS-1$
			}
			else{
				positivityColour = "'red'"; //$NON-NLS-1$
				positivityName = StrAccessor.getString("CompanyXRankingWindow.negativeFeedback"); //$NON-NLS-1$
			}
			
			JLabel ocenaPoz1 = new JLabel("<html><b>" + "<font color="+ positivityColour +">"+positivityName+"</font></b></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	
			setSizes(ocenaPoz1, 80, 29);
			
			commentRankingCollumn[2].add(ocenaPoz1, new GridBagConstraints(0, rowNo, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
	}
	
	public void appendButtonColumn(long cargoOfferId, String user, int rowNo){

			JButton komentarzeButton1 = new JButton();
						
			ImageIcon komentarzeIcon1 = SparkRes.getImageIcon(SparkRes.CARGO_HISTORIA_ROZMOW1);
			ImageIcon komentarzeIcon2 = SparkRes.getImageIcon(SparkRes.CARGO_HISTORIA_ROZMOW2);
			
			komentarzeButton1 = new JButton("<html><font color='white'>" +  //$NON-NLS-1$
					StrAccessor.getString("CompanyXRankingWindow.seeCommentButton") + "</font></html>",komentarzeIcon1); //$NON-NLS-1$ //$NON-NLS-2$
			komentarzeButton1.setVerticalTextPosition(SwingConstants.CENTER);
			komentarzeButton1.setHorizontalTextPosition(SwingConstants.CENTER);
			komentarzeButton1.setRolloverIcon(komentarzeIcon2);
			komentarzeButton1.setOpaque(false);
			komentarzeButton1.setBorder(null);
			komentarzeButton1.setBorderPainted(false);
			komentarzeButton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			komentarzeButton1.addActionListener(new PreviewCommentsActionListener(cargoOfferId, user));
			
			
			setSizes(komentarzeButton1, 190, 30);
	
			commentRankingCollumn[3].add(komentarzeButton1, new GridBagConstraints(0, rowNo, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));

	}
	
	public void apendContactAndRelibleFaktorForCargoId(List<Comment> ls1, List<EndedOffer> ls2, String username){
		double rFactor;
		int tab[] = new int[3];
		
		tab = CompanyRankingWindow.getCountNegativePisitiveAndAllComment(ls1, ls2);
		rFactor = CompanyRankingWindow.relibleFactor(tab[0], tab[2]);
		
		wskaznikZaufania.setText("<html><b>" + "<font color='white'>" + StrAccessor.getString("CompanyXRankingWindow.trustIndex") + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
				"<br/>na gie\u0142dzie MyCargo</b>" + "</font></html>"); //$NON-NLS-1$ //$NON-NLS-2$
		wskaznikZaufania2.setText("<html><b>" + "<font color='white' size='20'>"+rFactor+"%</font></b></html>"); //$NON-NLS-1$	//$NON-NLS-2$ //$NON-NLS-3$
		nazwaFirmy.setText("<html><b>" + "<font color='white'>"+username+"<br/>" + "</font></html>");		//$NON-NLS-1$	//$NON-NLS-2$	//$NON-NLS-3$ //$NON-NLS-4$
	}
	
	private void appendUserCorrect(UserPresenceWithComments userPresenceWithComments) {
		for(JPanel lst: commentRankingCollumn){
			
			
			if(userPresenceWithComments == UserPresenceWithComments.YesUserWithComments){
				pusta.setVisible(false);
				lst.setVisible(true);
			}
			else{
				lst.setVisible(false);
				setLabelListNoText();
				if(userPresenceWithComments == UserPresenceWithComments.YesUserWithNoCommentOther){
					pusta.setText("<html><font='red'>" + StrAccessor.getString("CompanyXRankingWindow.pusta.noCommentOther") + "</font></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
				else if(userPresenceWithComments == UserPresenceWithComments.YesUserWithNoCommentOwner){
					pusta.setText("<html><font='red'>" + StrAccessor.getString("CompanyXRankingWindow.pusta.noCommentOwner") + "</font></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
				else{
					pusta.setText("<html><font='red'>" + StrAccessor.getString("CompanyXRankingWindow.pusta.noSuchUser") + "</font></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
				pusta.setVisible(true);
			}
		}	
	}
	private void appendLabelList(){
		labelList.add(nazwaFirmy);
		labelList.add(wskaznikZaufania);
		labelList.add(wskaznikZaufania2);
	}
	
	private void setLabelListNoText(){
		for(JLabel label : labelList){
			label.setText(""); //$NON-NLS-1$
		}
	}

	
	private static class PreviewCommentsActionListener implements ActionListener
	{
		Long ide; 
		String user;

		public PreviewCommentsActionListener(Long ide, String user) {
			this.ide = ide;
			this.user = user;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			OfferComments cmt = OfferCommentsUtils.getOfferComments(ide, user);
			
			CargoOffer coff = CargoDataManager.getOfferDao().findOfferById(ide);
			SubmitACommentWindow submitAComment;
			submitAComment=new SubmitACommentWindow(coff, cmt);
			submitAComment.setVisible(true);
		}
		
	}

	private void createFooter(ImageIcon pasekStopkaTlo) {
		windowFooter = new BackgroundPanel();
		((BackgroundPanel) windowFooter).setBackgroundImage(pasekStopkaTlo);
		windowFooter.setLayout(new GridBagLayout());
		setSizes(windowFooter, 900, 43);
	}

	private void createHeader(ImageIcon pasekMenuTlo, ImageIcon rankingIcon1, ImageIcon rankingIcon2) {
		windowHeader = new BackgroundPanel();
		((BackgroundPanel) windowHeader).setBackgroundImage(pasekMenuTlo);
		windowHeader.setLayout(new GridBagLayout());
		//windowHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0)); // TUTAJ
		// TRZEBA
		// POGRZEBAC
		setSizes(windowHeader, 900, 71);
		
		setSizes(nazwaFirmy, 200, 71);
		
		setSizes(wskaznikZaufania, 150, 71);
		
		setSizes(wskaznikZaufania2, 150, 71);
		
		JLabel pusta 				= new JLabel(""); //$NON-NLS-1$
		pusta.setPreferredSize(new Dimension(1, 71));
		pusta.setMaximumSize(new Dimension(20, 71));
		pusta.setMinimumSize(new Dimension(20, 71));

		//windowHeader.add(new JLabel(rankingIcon1), 	new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.WEST, new Insets(0, 0, 0, 0), 0, 0));
		//windowHeader.add(new JLabel(rankingIcon2), 	new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.WEST, new Insets(0, 0, 0, 0), 0, 0));
		windowHeader.add(nazwaFirmy, 				new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.WEST, new Insets(0, 20, 0, 20), 0, 0));
		windowHeader.add(wskaznikZaufania, 			new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.WEST, new Insets(0, 20, 0, 20), 0, 0));
		windowHeader.add(wskaznikZaufania2, 		new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.WEST, new Insets(0, 20, 0, 20), 0, 0));
		windowHeader.add(pusta, 					new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.WEST, new Insets(0, 0, 0, 0), 0, 0));
		
	}
	

	public void setSizes(JComponent o, int x, int y){
		o.setPreferredSize	(new Dimension(x, y));
		o.setMaximumSize	(new Dimension(x, y));
		o.setMinimumSize	(new Dimension(x, y));
	}


	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new CompanyXRankingWindow().setVisible(true);
			}
		});
	}

}
