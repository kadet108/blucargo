package org.jivesoftware.sparkimpl.plugin.blucargo;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jivesoftware.resource.SparkRes;
import org.jivesoftware.spark.SparkManager;
import org.jivesoftware.spark.Workspace;
import org.jivesoftware.spark.component.RolloverButton;
import org.jivesoftware.spark.plugin.Plugin;
import org.jivesoftware.spark.ui.CommandPanel;
import org.jivesoftware.spark.ui.ContactGroup;
import org.jivesoftware.spark.ui.ContactList;
import org.jivesoftware.sparkimpl.plugin.blucargo.resourcebundle.StrAccessor;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.CompanyRankingWindow;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.DebtorsWindow;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.HelpWindow;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.MyConversationsWindow;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.OfferWindow;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.SettingsWindow;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.StockWindow;

import com.blucargo.model.OfferType;

public class BlucargoPlugin implements Plugin {

	private StockWindow gielda;
	
	
	ImageIcon gieldaIcon = SparkRes.getImageIcon(SparkRes.CARGO_MENU_START_1);
	final JButton gieldaButton = new JButton(gieldaIcon);
	ImageIcon pojazdIcon = SparkRes.getImageIcon(SparkRes.CARGO_MENU_POJAZD_1);
	final JButton pojazdButton = new JButton(pojazdIcon);
	ImageIcon ladunekIcon = SparkRes.getImageIcon(SparkRes.CARGO_MENU_LADUNEK_1);
	final JButton ladunekButton = new JButton(ladunekIcon);
	ImageIcon rozmowyIcon = SparkRes.getImageIcon(SparkRes.CARGO_MENU_ROZMOWY_1);
	final JButton rozmowyButton = new JButton(rozmowyIcon);
	ImageIcon ustawieniaIcon = SparkRes.getImageIcon(SparkRes.CARGO_MENU_USTAWIENIA_1);
	final JButton ustawieniaButton = new JButton(ustawieniaIcon);
	ImageIcon dluznicyIcon = SparkRes.getImageIcon(SparkRes.CARGO_MENU_DLUZNICY_1);
	final JButton dluznicyButton = new JButton(dluznicyIcon);
	ImageIcon rankingIcon = SparkRes.getImageIcon(SparkRes.CARGO_MENU_RANKING_1);
	final JButton rankingButton = new JButton(rankingIcon);
	ImageIcon pomocIcon = SparkRes.getImageIcon(SparkRes.CARGO_MENU_POMOC_1);
	final JButton pomocButton = new JButton(pomocIcon);
		
	public void initialize() {
	
		JPanel commandPanel = SparkManager.getWorkspace().getCommandPanel1();

		Image pasekMenuTlo = SparkRes.getImageIcon(SparkRes.CARGO_PASEK_MENU_TLO).getImage();
		((CommandPanel) commandPanel).setBackgroundImage(pasekMenuTlo);
		
		SparkManager.getWorkspace().getStatusBar().setBackgroundImage(pasekMenuTlo);
		
		gielda = new StockWindow();
		
		
		gieldaButton.setRolloverIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_START_2));
		gieldaButton.setBorderPainted(false);
		gieldaButton.setContentAreaFilled(false);
		gieldaButton.setFocusPainted(false);
		gieldaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		gieldaButton.setToolTipText(StrAccessor.getString("BlucargoPlugin.toolTip.stock")); //$NON-NLS-1$
		commandPanel.add(gieldaButton);
		gieldaButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
								
				AddOrSelectTab(StrAccessor.getString("BlucargoPlugin.Tab.Stock"), gielda); //$NON-NLS-1$
				
				gieldaButton	.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_START_2));
				pojazdButton	.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_POJAZD_1));
				ladunekButton	.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_LADUNEK_1));
				rozmowyButton	.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_ROZMOWY_1));
				ustawieniaButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_USTAWIENIA_1));
				dluznicyButton	.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_DLUZNICY_1));
				rankingButton	.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_RANKING_1));
				pomocButton		.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_POMOC_1));
			}
		});
		
		pojazdButton.setRolloverIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_POJAZD_2));
		pojazdButton.setBorderPainted(false);
		pojazdButton.setContentAreaFilled(false);
		pojazdButton.setFocusPainted(false);
		pojazdButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pojazdButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				AddOrSelectTab(StrAccessor.getString("BlucargoPlugin.Tab.EnterVehicle"), new OfferWindow(OfferType.VEHICLE)); //$NON-NLS-1$
				
				gieldaButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_START_1));
				pojazdButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_POJAZD_2));
				ladunekButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_LADUNEK_1));
				rozmowyButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_ROZMOWY_1));
				ustawieniaButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_USTAWIENIA_1));
				dluznicyButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_DLUZNICY_1));
				rankingButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_RANKING_1));
				pomocButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_POMOC_1));
			}
		});
		
		commandPanel.add(pojazdButton);
		pojazdButton.setToolTipText(StrAccessor.getString("BlucargoPlugin.toolTip.enterVehicle")); //$NON-NLS-1$
		
		ladunekButton.setRolloverIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_LADUNEK_2));
		ladunekButton.setBorderPainted(false);
		ladunekButton.setContentAreaFilled(false);
		ladunekButton.setFocusPainted(false);
		ladunekButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		ladunekButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				AddOrSelectTab(StrAccessor.getString("BlucargoPlugin.Tab.EnterCargo"), new OfferWindow(OfferType.CARGO)); //$NON-NLS-1$
				
				gieldaButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_START_1));
				pojazdButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_POJAZD_1));
				ladunekButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_LADUNEK_2));
				rozmowyButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_ROZMOWY_1));
				ustawieniaButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_USTAWIENIA_1));
				dluznicyButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_DLUZNICY_1));
				rankingButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_RANKING_1));
				pomocButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_POMOC_1));
			}
		});
		
		
		commandPanel.add(ladunekButton);
		ladunekButton.setToolTipText(StrAccessor.getString("BlucargoPlugin.toolTip.enterCargo")); //$NON-NLS-1$
		
		
		rozmowyButton.setRolloverIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_ROZMOWY_2));
		rozmowyButton.setBorderPainted(false);
		rozmowyButton.setContentAreaFilled(false);
		rozmowyButton.setFocusPainted(false);
		rozmowyButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rozmowyButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				AddOrSelectTab(StrAccessor.getString("BlucargoPlugin.Tab.MyChats"), new MyConversationsWindow()); //$NON-NLS-1$
								
				gieldaButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_START_1));
				pojazdButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_POJAZD_1));
				ladunekButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_LADUNEK_1));
				rozmowyButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_ROZMOWY_2));
				ustawieniaButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_USTAWIENIA_1));
				dluznicyButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_DLUZNICY_1));
				rankingButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_RANKING_1));
				pomocButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_POMOC_1));
			}

			
		});

		commandPanel.add(rozmowyButton);
		rozmowyButton.setToolTipText(StrAccessor.getString("BlucargoPlugin.toolTip.myConversations")); //$NON-NLS-1$
		
		
		ustawieniaButton.setRolloverIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_USTAWIENIA_2));
		ustawieniaButton.setBorderPainted(false);
		ustawieniaButton.setContentAreaFilled(false);
		ustawieniaButton.setFocusPainted(false);
		ustawieniaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ustawieniaButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//new SettingsWindow().setVisible(true);
				
//				if(Workspace.getInstance().getTabbedPane().indexOfTab("Ustawienia")==-1)
//				{
//					Workspace.getInstance().getTabbedPane().addTab("Ustawienia", new SettingsWindow());
//				}
//				if(Workspace.getInstance().getTabbedPane().indexOfTab("Ustawienia")!=-1)
//				{
//					Workspace.getInstance().getTabbedPane().setSelectedIndex(Workspace.getInstance().getTabbedPane().indexOfTab("Ustawienia"));
//				}

				AddOrSelectTab(StrAccessor.getString("BlucargoPlugin.Tab.Settings"), new SettingsWindow()); //$NON-NLS-1$
				
				gieldaButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_START_1));
				pojazdButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_POJAZD_1));
				ladunekButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_LADUNEK_1));
				rozmowyButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_ROZMOWY_1));
				ustawieniaButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_USTAWIENIA_2));
				dluznicyButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_DLUZNICY_1));
				rankingButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_RANKING_1));
				pomocButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_POMOC_1));
			}
		});

		dluznicyButton.setRolloverIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_DLUZNICY_2));
		dluznicyButton.setBorderPainted(false);
		dluznicyButton.setContentAreaFilled(false);
		dluznicyButton.setFocusPainted(false);
		dluznicyButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dluznicyButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				if(Workspace.getInstance().getTabbedPane().indexOfTab(StrAccessor.getString("BlucargoPlugin.tabbedPane.Debtors"))==-1) //$NON-NLS-1$
				{
					Workspace.getInstance().getTabbedPane().addTab(StrAccessor.getString("BlucargoPlugin.tabbedPane.Debtors"), new DebtorsWindow()); //$NON-NLS-1$
				}
				if(Workspace.getInstance().getTabbedPane().indexOfTab(StrAccessor.getString("BlucargoPlugin.tabbedPane.Debtors"))!=-1) //$NON-NLS-1$
				{
					Workspace.getInstance().getTabbedPane().setSelectedIndex(Workspace.getInstance().getTabbedPane().indexOfTab(StrAccessor.getString("BlucargoPlugin.tabbedPane.Debtors"))); //$NON-NLS-1$
				}
				
				gieldaButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_START_1));
				pojazdButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_POJAZD_1));
				ladunekButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_LADUNEK_1));
				rozmowyButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_ROZMOWY_1));
				ustawieniaButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_USTAWIENIA_1));
				dluznicyButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_DLUZNICY_2));
				rankingButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_RANKING_1));
				pomocButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_POMOC_1));
			}
			
		});

		rankingButton.setRolloverIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_RANKING_2));
		rankingButton.setBorderPainted(false);
		rankingButton.setContentAreaFilled(false);
		rankingButton.setFocusPainted(false);
		rankingButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rankingButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				if(Workspace.getInstance().getTabbedPane().indexOfTab(StrAccessor.getString("BlucargoPlugin.tabbedPane.Ranking"))==-1); //$NON-NLS-1$
				{
					Workspace.getInstance().getTabbedPane().addTab(StrAccessor.getString("BlucargoPlugin.tabbedPane.Ranking"), new CompanyRankingWindow()); //$NON-NLS-1$
				}
				if(Workspace.getInstance().getTabbedPane().indexOfTab(StrAccessor.getString("BlucargoPlugin.tabbedPane.Ranking"))!=-1) //$NON-NLS-1$
				{
					Workspace.getInstance().getTabbedPane().setSelectedIndex(Workspace.getInstance().getTabbedPane().indexOfTab(StrAccessor.getString("BlucargoPlugin.tabbedPane.Ranking"))); //$NON-NLS-1$
				}
				
				gieldaButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_START_1));
				pojazdButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_POJAZD_1));
				ladunekButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_LADUNEK_1));
				rozmowyButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_ROZMOWY_1));
				ustawieniaButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_USTAWIENIA_1));
				dluznicyButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_DLUZNICY_1));
				rankingButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_RANKING_2));
				pomocButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_POMOC_1));
				
			}
			
		});
		commandPanel.add(rankingButton);
		rankingButton.setToolTipText(StrAccessor.getString("BlucargoPlugin.toolTip.firmRanking")); //$NON-NLS-1$
		
		pomocButton.setRolloverIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_POMOC_2));
		pomocButton.setBorderPainted(false);
		pomocButton.setContentAreaFilled(false);
		pomocButton.setFocusPainted(false);
		pomocButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pomocButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//new HelpWindow().setVisible(true);
				
				
				if(Workspace.getInstance().getTabbedPane().indexOfTab(StrAccessor.getString("BlucargoPlugin.tabbedPane.Help"))==-1) //$NON-NLS-1$
				{
					Workspace.getInstance().getTabbedPane().addTab(StrAccessor.getString("BlucargoPlugin.tabbedPane.Help"), new HelpWindow()); //$NON-NLS-1$
					//pomocButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_POMOC_2));
					
					
				}
				if(Workspace.getInstance().getTabbedPane().indexOfTab(StrAccessor.getString("BlucargoPlugin.tabbedPane.Help"))!=-1) //$NON-NLS-1$
				{
					Workspace.getInstance().getTabbedPane().setSelectedIndex(Workspace.getInstance().getTabbedPane().indexOfTab(StrAccessor.getString("BlucargoPlugin.tabbedPane.Help"))); //$NON-NLS-1$
				}
				
				gieldaButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_START_1));
				pojazdButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_POJAZD_1));
				ladunekButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_LADUNEK_1));
				rozmowyButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_ROZMOWY_1));
				ustawieniaButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_USTAWIENIA_1));
				dluznicyButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_DLUZNICY_1));
				rankingButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_RANKING_1));
				pomocButton.setIcon(SparkRes.getImageIcon(SparkRes.CARGO_MENU_POMOC_2));
				
				
			}
		});
		commandPanel.add(pomocButton);
		pomocButton.setToolTipText(StrAccessor.getString("BlucargoPlugin.toolTip.help")); //$NON-NLS-1$

		RolloverButton groupButton = new RolloverButton(StrAccessor.getString("BlucargoPlugin.groupRolloverButton.NewGroup")); //$NON-NLS-1$
		groupButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				final ContactList contactList = SparkManager.getWorkspace().getContactList();
				ContactGroup contactGroup = new ContactGroup(JOptionPane.showInputDialog(StrAccessor.getString("BlucargoPlugin.contactGroup.enterGroupName")));  //$NON-NLS-1$
				contactList.addContactGroup(contactGroup);

			}
		});

		SparkManager.getWorkspace().getCommandPanel().validate();
		SparkManager.getWorkspace().getCommandPanel().invalidate();
		SparkManager.getWorkspace().getCommandPanel().repaint();

		SparkManager.getWorkspace().getCommandPanel1().validate();
		SparkManager.getWorkspace().getCommandPanel1().invalidate();
		SparkManager.getWorkspace().getCommandPanel1().repaint();

		SparkManager.getWorkspace().getCommandPanel().add(groupButton);
		SparkManager.getWorkspace().getCommandPanel().setVisible(false);
		groupButton.setBorderPainted(true);
		SparkManager.getWorkspace().getCommandPanel().paintImmediately(0, 0, 600, 500);
		SparkManager.getWorkspace().getCommandPanel1().paintImmediately(0, 0, 600, 500);
		
	}

	
	public static void AddOrSelectTab(String name, JPanel panel) {
		if(Workspace.getInstance().getTabbedPane().indexOfTab(name)==-1)
		{
			Workspace.getInstance().getTabbedPane().addTab(name, panel);
		}
		if(Workspace.getInstance().getTabbedPane().indexOfTab(name)!=-1)
		{
			Workspace.getInstance().getTabbedPane().setSelectedIndex(Workspace.getInstance().getTabbedPane().indexOfTab(name));
		}
	}
	
	public static void killTab(JPanel component){
		Workspace.getInstance().getTabbedPane().remove(component);
	}

	
	/**
	 * Called when Spark is shutting down to allow for persistence of
	 * information or releasing of resources.
	 */
	public void shutdown() {
	}

	/**
	 * Return true if the Spark can shutdown on users request.
	 * 
	 * @return true if Spark can shutdown on users request.
	 */
	public boolean canShutDown() {
		return true;
	}

	/**
	 * Is called when a user explicitly asked to uninstall this plugin. The
	 * plugin owner is responsible to clean up any resources and remove any
	 * components install in Spark.
	 */
	public void uninstall() {
		// Remove all resources belonging to this plugin.
	}
}
