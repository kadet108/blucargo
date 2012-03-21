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
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jivesoftware.resource.SparkRes;
import org.jivesoftware.spark.SparkManager;
import org.jivesoftware.spark.Workspace;
import org.jivesoftware.sparkimpl.plugin.blucargo.manager.CargoDataManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.resourcebundle.StrAccessor;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.BackgroundPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.RoundedPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.enums.UserPresenceWithComments;

import com.blucargo.model.Comment;
import com.blucargo.model.EndedOffer;

public class CompanyRankingWindow extends JPanel {

	private static final long serialVersionUID = 1L;
	
	Color color = new Color(242, 242, 242, 255);

	Color color2 = new Color(208, 203, 181);
	JPanel mainArea = new JPanel();
	JPanel windowHeader = new JPanel();
	JPanel panel1a = new JPanel();
	JPanel windowMain = new JPanel();
	JPanel windowFooter = new JPanel();
	JPanel windowMainFill = new RoundedPanel(color2);
	JPanel windowMainFill2 = new RoundedPanel(color2);
	JPanel panel5 = new JPanel();
	JPanel panel6 = new JPanel();
	JPanel panel7 = new JPanel();
	JPanel panel8 = new JPanel();
	JPanel panel9 = new JPanel();
	JPanel panel10 = new JPanel();
	JPanel panel11 = new JPanel();
	JPanel panel12 = new JPanel();
	
	String wskaznik1Text = "<html><b>"+StrAccessor.getString("CompanyRankingWindow.trustIndex")+"<br/>"+StrAccessor.getString("CompanyRankingWindow.onMyCargoStock")+"</b></html>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	String ranking_headerText = "<html><b>"+StrAccessor.getString("CompanyRankingWindow.myRanking")+"</b></html>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	String rank1Text = StrAccessor.getString("CompanyRankingWindow.numberOfNegativeComments"); //$NON-NLS-1$
	String rank2Text = StrAccessor.getString("CompanyRankingWindow.numberOfPositiveComments"); //$NON-NLS-1$
	String rank3Text = StrAccessor.getString("CompanyRankingWindow.transactionsNumberOnStock"); //$NON-NLS-1$
	
	JLabel liczba 							= new JLabel();
	JLabel liczba1	 						= new JLabel();
	JLabel liczba2	 						= new JLabel();
	JLabel liczba3	 						= new JLabel();
	JLabel wskaznik2 						= new JLabel();
	JLabel sprawdzHead 						= new JLabel();
	JLabel wskaznik1 						= new JLabel(wskaznik1Text);
	JLabel ranking_header 					= new JLabel(ranking_headerText);
	JLabel rank1 							= new JLabel(rank1Text);
	JLabel rank2 							= new JLabel(rank2Text);
	JLabel rank3 							= new JLabel(rank3Text);
	
	static String cargoIdFromTextBox = SparkManager.getUserManager().getNickname();
	
	List<JLabel> labelList = new ArrayList<JLabel>();
	
	JButton komentarzeButton1 = new JButton();
	
	CompanyRankingWindow window = this;
	
	JPanel[] commentRankingCollumn = new JPanel[] { 
			new JPanel(),  new JPanel(), new JPanel(), new JPanel() 
		};
	
	int countOfNegativePositiveAndAllCommentsTable[] = new int[3];
	
	public CompanyRankingWindow() {

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

		//mainArea.add(windowHeader, 		new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		mainArea.add(windowMain, 		new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		//mainArea.add(windowFooter, 		new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

		//pack();
		
		//appendUserCorrect( refreshWindowMain( SparkManager.getUserManager().getNickname() ) );
	}

	private void initialize() {
		windowHeader.setBackground(null);
		panel1a.setBackground(null);
		windowMain.setBackground(null);
		windowFooter.setBackground(null);
		windowMainFill.setBackground(null);
		windowMainFill2.setBackground(null);
	}
	
	private void appendLabelList(){
		labelList.add(ranking_header);
		labelList.add(liczba);
		labelList.add(liczba1);
		labelList.add(liczba2);
		labelList.add(liczba3);
		labelList.add(rank1);
		labelList.add(rank2);
		labelList.add(rank3);
		labelList.add(wskaznik1);
		labelList.add(wskaznik2);
	}

	private void createMain() {
		//String user = SparkManager.getUserManager().getNickname();
		setStyleColumns();
		appendLabelList();
		
		ImageIcon pasekMenuTlo 		= SparkRes.getImageIcon(SparkRes.CARGO_TLO1PX);
		windowMain = new BackgroundPanel();
		((BackgroundPanel) windowMain).setBackgroundImage(pasekMenuTlo);
		
		windowMain.setLayout(new GridBagLayout());
		windowMain.setBackground(new Color(130, 145, 164));
		//windowMain.add(windowMainFill, new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 5, 5), 0, 0));
		windowMain.add(windowMainFill2, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(20, 20, 20, 10), 0, 0));
		windowMain.add(windowMainFill, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(20, 10, 20, 20), 0, 0));

		windowMainFill.setLayout(new GridBagLayout());
		
		setSizes(windowMainFill, 530, 270, 530, 270, 530, 270);

		windowMainFill2.setLayout(new GridBagLayout());
		
		setSizes(windowMainFill2, 250, 270, 250, 270, 250, 270);
		
		setSizes(ranking_header, 200, 29, 200, 29, 200, 29);
		setSizes(rank1, 200, 29, 200, 29, 200, 29);
		setSizes(rank2, 200, 29, 200, 29, 200, 29);
		setSizes(rank3, 200, 29, 200, 29, 200, 29);
		
		commentRankingCollumn[0].add(ranking_header, 			new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		commentRankingCollumn[0].add(rank1, 						new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		commentRankingCollumn[0].add(rank2, 						new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		commentRankingCollumn[0].add(rank3, 						new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		
		setSizes(liczba, 30, 29, 30, 29, 30, 29);
		setSizes(liczba1, 30, 29, 30, 29, 30, 29);
		setSizes(liczba2, 30, 29, 30, 29, 30, 29);
		setSizes(liczba3, 30, 29, 30, 29, 30, 29);
		
		commentRankingCollumn[1].add(liczba, 						new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		commentRankingCollumn[1].add(liczba1, 						new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		commentRankingCollumn[1].add(liczba2, 						new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		commentRankingCollumn[1].add(liczba3, 						new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		
		JLabel ranking_head2 						= new JLabel(""); //$NON-NLS-1$
		//JLabel wskaznik2 							= new JLabel("<html><b><font size='20'>"+relibleFactor(countP,countAll)+"%</font></b></html>");
		
		setSizes(ranking_head2, 200, 29, 200, 29, 200, 29);
		setSizes(wskaznik1, 200, 29, 200, 29, 200, 29);
		setSizes(wskaznik2, 200, 35, 200, 35, 200, 35);
		
		commentRankingCollumn[2].add(ranking_head2, 			new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		commentRankingCollumn[2].add(wskaznik1, 				new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		commentRankingCollumn[2].add(wskaznik2, 				new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));

		setSizes(sprawdzHead, 200, 29, 200, 29, 200, 29);
		
		final JTextField cargoID 			= new JTextField(1);
		cargoID.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				appendUserCorrect(refreshWindowMain(  ( cargoIdFromTextBox = cargoID.getText() ) ) );	
			}
		});
		
		//pierwszy button
		JButton sprawdzButton1 = new JButton();
		
		
		ImageIcon sprawdzIcon1 = SparkRes.getImageIcon(SparkRes.CARGO_HISTORIA_ROZMOW1);
		ImageIcon sprawdzIcon2 = SparkRes.getImageIcon(SparkRes.CARGO_HISTORIA_ROZMOW2);

		sprawdzButton1 = new JButton("<html><font color='white'>"+StrAccessor.getString("CompanyRankingWindow.checkRanking")+"</font></html>",sprawdzIcon1); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
		
		//pierwszy button
		
		ImageIcon komentarzeIcon1 = SparkRes.getImageIcon(SparkRes.CARGO_HISTORIA_ROZMOW1);
		ImageIcon komentarzeIcon2 = SparkRes.getImageIcon(SparkRes.CARGO_HISTORIA_ROZMOW2);

		komentarzeButton1 = new JButton("<html><font color='white'>"+StrAccessor.getString("CompanyRankingWindow.seeComments")+"</font></html>",komentarzeIcon1); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		komentarzeButton1.setVerticalTextPosition(SwingConstants.CENTER);
		komentarzeButton1.setHorizontalTextPosition(SwingConstants.CENTER);
		komentarzeButton1.setRolloverIcon(komentarzeIcon2);
		komentarzeButton1.setOpaque(false);
		komentarzeButton1.setBorder(null);
		komentarzeButton1.setBorderPainted(false);
		komentarzeButton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		komentarzeButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//new CompanyXRankingWindow().setVisible(true);
				
				if(Workspace.getInstance().getTabbedPane().indexOfTab(StrAccessor.getString("CompanyRankingWindow.firmRanking"))==-1) //$NON-NLS-1$
				{
					Workspace.getInstance().getTabbedPane().addTab(StrAccessor.getString("CompanyRankingWindow.firmRanking"), new CompanyXRankingWindow()); //$NON-NLS-1$
					
//					ImageIcon rankingIcon = SparkRes.getImageIcon(SparkRes.CARGO_MENU_RANKING_1);
//					final JButton rankingButton = new JButton(rankingIcon);
//					rankingButton.setRolloverIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_RANKING_2));
//					rankingButton.setBorderPainted(false);
//					rankingButton.setContentAreaFilled(false);
//					rankingButton.setFocusPainted(false);
//					rankingButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//					rankingButton.setToolTipText("Ranking firm");

					
					//SparkManager.getWorkspace().getCommandPanel1().remove(SparkManager.getWorkspace().getCommandPanel1().getComponent(6));
					//SparkManager.getWorkspace().getCommandPanel1().add(rankingButton, 6);
					
					//rankingButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_RANKING_1));
					
					
					
				}
				if(Workspace.getInstance().getTabbedPane().indexOfTab(StrAccessor.getString("CompanyRankingWindow.firmRanking"))!=-1) //$NON-NLS-1$
				{
					Workspace.getInstance().getTabbedPane().setSelectedIndex(Workspace.getInstance().getTabbedPane().indexOfTab(StrAccessor.getString("CompanyRankingWindow.firmRanking"))); //$NON-NLS-1$
					
					
					//SparkManager.getWorkspace().getCommandPanel1().remove(SparkManager.getWorkspace().getCommandPanel1().getComponent(6));
					//SparkManager.getWorkspace().getCommandPanel1().add(rankingButton, 6);
					
				}
				
				
				
			}
		});
		
		commentRankingCollumn[3].add(sprawdzHead, 			new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		commentRankingCollumn[3].add(cargoID, 				new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		commentRankingCollumn[3].add(sprawdzButton1, 		new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
		
		windowMainFill2.add(commentRankingCollumn[3], 		new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		
		//refreshWindowMain(user);
		appendUserCorrect( refreshWindowMain( SparkManager.getUserManager().getNickname() ) );
	}

	public UserPresenceWithComments refreshWindowMain(String user){
		boolean userBool = CargoDataManager.getInstance().isUser(user);
//		List<Comment> ls = CargoDataManager.getCommentDao().findCommentsByAuthor(user);	
		List<Comment> ls = CargoDataManager.getCommentDao().findAllCommentsForUserSimple(user);
		appendWindowMainFill();
		
			
			if(userBool && ls == null ){
				if(isUserOwner(user))
					return UserPresenceWithComments.YesUserWithNoCommentOwner;
				else
					return UserPresenceWithComments.YesUserWithNoCommentOther;				
			} else 	if(userBool && ls.size() == 0 ){
				if(isUserOwner(user))
					return UserPresenceWithComments.YesUserWithNoCommentOwner;
				else
					return UserPresenceWithComments.YesUserWithNoCommentOther;				
			} else if(userBool && ls.size()>0){
				appendRankingItems(user, ls);
				return UserPresenceWithComments.YesUserWithComments;
			}
			else
				return UserPresenceWithComments.NoUser;
		}
	
	
	public static boolean isUserOwner(String user){
		if(user.equals(SparkManager.getUserManager().getNickname() ))
			return true;
		else
			return false;
	}
	
	public void appendRankingItems(String user, List<Comment> ls){	
		List<EndedOffer> endedOfferList = CargoDataManager.getEndedOfferDao().findEndedOfferByOwner(user);

		int countAllTransaction = endedOfferList.size();
		
		countOfNegativePositiveAndAllCommentsTable = getCountNegativePisitiveAndAllComment(ls, endedOfferList);
		int countP = countOfNegativePositiveAndAllCommentsTable[0];
		int countN = countOfNegativePositiveAndAllCommentsTable[1];
		int countAll=countOfNegativePositiveAndAllCommentsTable[2];
		
		liczba1.setText( String.valueOf(countN) );
		liczba2.setText( String.valueOf(countP) );
		liczba3.setText( String.valueOf(countAllTransaction) );

		wskaznik2.setText( String.valueOf(relibleFactor(countP, countAll)) );
	}
	
	private void appendWindowMainFill(){
		windowMainFill.removeAll();
		windowMainFill.add(commentRankingCollumn[0], 		new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		windowMainFill.add(commentRankingCollumn[1], 		new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		windowMainFill.add(commentRankingCollumn[2], 		new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		windowMainFill.add(komentarzeButton1, 				new GridBagConstraints(0, 1, 0, 0, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		windowMainFill.validate();
		windowMainFill.repaint();
		sprawdzHead.setText("<html><b>"+StrAccessor.getString("CompanyRankingWindow.checkContractorsRanking")+"</b><br>" +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				StrAccessor.getString("CompanyRankingWindow.provideCargoID")+"</html>"); //$NON-NLS-1$ //$NON-NLS-2$
	}
	static public int [] getCountNegativePisitiveAndAllComment(List<Comment> ls, List<EndedOffer> ls2) {
		int tab[] = new int[3];
		int countOfPositiveComment = 0;
		int countOfNegativeComment = 0;
		int countOfAllComment = 0;
			for (Comment cmt : ls){
				if(cmt.getPositivity() == 1)
					countOfPositiveComment++;
				if(cmt.getPositivity() == -1)
					countOfNegativeComment++;
				countOfAllComment++;
			}
			tab[0] = countOfPositiveComment;
			tab[1] = countOfNegativeComment;
			tab[2] = countOfAllComment;
		return tab;
	}
	
	static public double relibleFactor(int pos, int all){
		return round( (( (double)pos/ (double)all ) * 100 ), 2);
	}
	
	static public double round(double d, int ic) {
		  java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
		  nf.setMaximumFractionDigits(ic);
		  nf.setMinimumFractionDigits(ic);
		  return Double.parseDouble((nf.format(d)).replaceAll(",", ".").replaceAll(" ", "") ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
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
	
	private void appendUserCorrect(UserPresenceWithComments userPresenceWithComments) {

			for(JLabel label : labelList){
				if(userPresenceWithComments == UserPresenceWithComments.YesUserWithComments){
					if(label == rank1){
						rank1.setText(rank1Text);
					}
					else{
						label.setVisible(true);
						komentarzeButton1.setVisible(true);
					}
				}
				else{					
					if(label == rank1){
						if(userPresenceWithComments == UserPresenceWithComments.YesUserWithNoCommentOther)
							rank1.setText("<html><font color='red'>"+StrAccessor.getString("CompanyRankingWindow.userHasNoComment")+"</font></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						else if(userPresenceWithComments == UserPresenceWithComments.YesUserWithNoCommentOwner)
							rank1.setText("<html><font color='red'>"+StrAccessor.getString("CompanyRankingWindow.youHaveNoComment")+"</font></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						else
							rank1.setText("<html><font color='red'>"+StrAccessor.getString("CompanyRankingWindow.nuSuchUser")+"</font></html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					}
					else{
						label.setVisible(false);
						komentarzeButton1.setVisible(false);
					}
				}
			}
	}

	private void createFooter(ImageIcon pasekStopkaTlo) {
		windowFooter = new BackgroundPanel();
		((BackgroundPanel) windowFooter).setBackgroundImage(pasekStopkaTlo);
		windowFooter.setLayout(new GridBagLayout());
		setSizes(windowFooter, 10, 43, 900, 43, 900, 43);
	}

	private void createHeader(ImageIcon pasekMenuTlo, ImageIcon rankingIcon1, ImageIcon rankingIcon2) {
		windowHeader = new BackgroundPanel();
		((BackgroundPanel) windowHeader).setBackgroundImage(pasekMenuTlo);
		windowHeader.setLayout(new GridBagLayout());
		windowHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0)); // TUTAJ
		// TRZEBA
		// POGRZEBAC
		setSizes(windowHeader, 10, 71, 900, 71, 900, 71);

		windowHeader.add(new JLabel(rankingIcon1), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowHeader.add(new JLabel(rankingIcon2), new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

	}

	public void setSizes(JComponent o, int x, int y, int maxx, int maxy, int minx, int miny){
		o.setPreferredSize	(new Dimension(x, y));
		o.setMaximumSize	(new Dimension(maxx, maxy));
		o.setMinimumSize	(new Dimension(minx, miny));
	}
	
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new CompanyRankingWindow().setVisible(true);
			}
		});
	}

}
