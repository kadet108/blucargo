/**
 * $Revision: $
 * $Date: $
 *
 * Copyright (C) 2006 Jive Software. All rights reserved.
 *
 * This software is published under the terms of the GNU Lesser Public License (LGPL),
 * a copy of which is included in this distribution.
 */

package org.jivesoftware.spark.ui.rooms;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;

import org.jivesoftware.resource.Res;
import org.jivesoftware.resource.SparkRes;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.FromMatchesFilter;
import org.jivesoftware.smack.filter.OrFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.StreamError;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.MessageEventManager;
import org.jivesoftware.smackx.packet.MessageEvent;
import org.jivesoftware.spark.ChatManager;
import org.jivesoftware.spark.PresenceManager;
import org.jivesoftware.spark.SparkManager;
import org.jivesoftware.spark.ui.ChatRoom;
import org.jivesoftware.spark.ui.ChatRoomButton;
import org.jivesoftware.spark.ui.ContactItem;
import org.jivesoftware.spark.ui.ContactList;
import org.jivesoftware.spark.ui.MessageEventListener;
import org.jivesoftware.spark.ui.RosterDialog;
import org.jivesoftware.spark.ui.VCardPanel;
import org.jivesoftware.spark.util.ModelUtil;
import org.jivesoftware.spark.util.TaskEngine;
import org.jivesoftware.spark.util.log.Log;
import org.jivesoftware.sparkimpl.plugin.blucargo.manager.CargoDataManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.CargoOffersLoadPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.CargoOffersSubmissionDatePanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.CargoOffersUnloadPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.util.CargoChatRoomButton;
import org.jivesoftware.sparkimpl.plugin.transcripts.ChatTranscript;
import org.jivesoftware.sparkimpl.plugin.transcripts.ChatTranscripts;
import org.jivesoftware.sparkimpl.plugin.transcripts.HistoryMessage;
import org.jivesoftware.sparkimpl.profile.VCardManager;
import org.jivesoftware.sparkimpl.settings.local.LocalPreferences;
import org.jivesoftware.sparkimpl.settings.local.SettingsManager;

import com.blucargo.model.CargoOffer;
import com.blucargo.model.OfferAcceptance;

/**
 * This is the Person to Person implementation of <code>ChatRoom</code> This
 * room only allows for 1 to 1 conversations.
 */
public class ChatRoomImpl extends ChatRoom {
	private static final long serialVersionUID = 6163762803773980872L;
	private List<MessageEventListener> messageEventListeners = new ArrayList<MessageEventListener>();
	private String roomname;
	private Icon tabIcon;
	private String roomTitle;
	private String tabTitle;
	private String participantJID;
	private String participantNickname;
	private String participantNicknameOnly;

	private Presence presence;

	private boolean offlineSent;

	private Roster roster;

	private long lastTypedCharTime;
	private boolean sendNotification;

	private TimerTask typingTimerTask;
	private boolean sendTypingNotification;
	private String threadID;

	private long lastActivity;

	private boolean active;

	// Information button
	private ChatRoomButton infoButton;

	private ChatRoomButton addToRosterButton;
	
	private final String CARGO_OFFER_PROPERTY="cargoOfferId";
	
	private JButton acceptedOfferButton;
	
	public void renderCargoOffer(){
		
		CargoOffer co = getCargoOffer();
		Object result = null;
		
		try {
			result = CargoDataManager.getInstance().getAcceptedOfferByCargoOfferIdAndUserName(co.getId());			
		} catch (Exception e) {
			//Exception here indicates succes.
		}
		
		//Accepted offer
		if(result!=null){
			getEditorBar().remove(this.acceptedOfferButton);
			return;
		}
		
		if(this.acceptedOfferButton != null){
			getEditorBar().remove(this.acceptedOfferButton);
		}
		
		JButton acceptOfferButton = getAcceptOfferButton(participantNicknameOnly,getCargoOffer());
		this.acceptedOfferButton = acceptOfferButton;
		getEditorBar().add(this.acceptedOfferButton);

	}

	/**
	 * Constructs a 1-to-1 ChatRoom.
	 * 
	 * @param participantJID
	 *            the participants jid to chat with.
	 * @param participantNickname
	 *            the nickname of the participant.
	 * @param title
	 *            the title of the room.
	 */
	public ChatRoomImpl(final String participantJID, final String participantNickname, String title) {
		this(participantJID, participantNickname, title, null);
	}

	// Modified for cargo
	public ChatRoomImpl(final String participantJID, final String participantNickname, String title, CargoOffer cargoOffer) {
		this.active = true;
		this.participantJID = participantJID;
		this.participantNickname = participantNickname;
		
		String []results=this.participantJID.split("@");
		this.participantNicknameOnly = results[0];
		

		// Loads the current history for this user.
		loadHistory();

		// Register PacketListeners
		PacketFilter fromFilter = new FromMatchesFilter(participantJID);
		PacketFilter orFilter = new OrFilter(new PacketTypeFilter(Presence.class), new PacketTypeFilter(Message.class));
		PacketFilter andFilter = new AndFilter(orFilter, fromFilter);

		SparkManager.getConnection().addPacketListener(this, andFilter);

		// The roomname will be the participantJID
		this.roomname = participantJID;

		// Use the agents username as the Tab Title
		this.tabTitle = title;

		// The name of the room will be the node of the user jid + conversation.
		this.roomTitle = participantNickname;

		// Add RoomInfo
		this.getSplitPane().setRightComponent(null);
		getSplitPane().setDividerSize(0);

		presence = PresenceManager.getPresence(participantJID);

		roster = SparkManager.getConnection().getRoster();

		RosterEntry entry = roster.getEntry(participantJID);

		tabIcon = PresenceManager.getIconFromPresence(presence);

		infoButton = new CargoChatRoomButton("", SparkRes.getImageIcon(SparkRes.SPARK_IKONKA_WIZYTOWKA));
		infoButton.setToolTipText(Res.getString("message.view.information.about.this.user"));
		infoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		
		// Create basic toolbar.
		getToolBar().addChatRoomButton(infoButton);

		// If the user is not in the roster, then allow user to add them.
		//addToRosterButton = new ChatRoomButton("", SparkRes.getImageIcon(SparkRes.ADD_IMAGE_24x24));
		
		addToRosterButton = new ChatRoomButton("",SparkRes.getImageIcon(SparkRes.SPARK_IKONKA_DODAJ));
		addToRosterButton.setRolloverIcon(SparkRes.getImageIcon(SparkRes.SPARK_IKONKA_DODAJ));
		addToRosterButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addToRosterButton.setBorderPainted(false);
		addToRosterButton.setRolloverEnabled(false);
		addToRosterButton.setContentAreaFilled(false);
		addToRosterButton.setIgnoreRepaint(true);
		addToRosterButton.setOpaque(false);
		addToRosterButton.setBorder(null);
		addToRosterButton.setBorderPainted(false);
		addToRosterButton.setMargin(new Insets(0, 0, 0, 0));
		
		MouseListener [] mouseListeners2 = addToRosterButton.getMouseListeners();
		for (MouseListener mouseListener : mouseListeners2) {
			if (mouseListener instanceof MouseAdapter){
				addToRosterButton.removeMouseListener(mouseListener);
			}
		}
		
		if (entry == null && !StringUtils.parseResource(participantJID).equals(participantNickname)) {
			addToRosterButton.setToolTipText(Res.getString("message.add.this.user.to.your.roster"));
			getToolBar().addChatRoomButton(addToRosterButton);

			addToRosterButton.addActionListener(this);
		}

		// Show VCard.
		infoButton.addActionListener(this);

		// If this is a private chat from a group chat room, do not show
		// toolbar.
		if (StringUtils.parseResource(participantJID).equals(participantNickname)) {
			getToolBar().setVisible(false);
		}

		typingTimerTask = new TimerTask() {
			public void run() {
				if (!sendTypingNotification) {
					return;
				}
				long now = System.currentTimeMillis();
				if (now - lastTypedCharTime > 2000) {
					if (!sendNotification) {
						// send cancel
						SparkManager.getMessageEventManager().sendCancelledNotification(getParticipantJID(), threadID);
						sendNotification = true;
					}
				}
			}
		};

		TaskEngine.getInstance().scheduleAtFixedRate(typingTimerTask, 2000, 2000);
		lastActivity = System.currentTimeMillis();

		super.setCargoOffer(cargoOffer);
		
		this.addMessageEventListener(new MessageEventListener() {
			
			@Override
			public void sendingMessage(Message message) {
				message.setProperty(CARGO_OFFER_PROPERTY, getCargoOffer().getId());
			}
			
			@Override
			public void receivingMessage(Message message) {
			}
		});
		
		this.addMessageListener(new org.jivesoftware.spark.ui.MessageListener(){

			@Override
			public void messageReceived(ChatRoom room, Message message) {
				Long cargoOfferId = (Long)message.getProperty(CARGO_OFFER_PROPERTY);
				setCargoOffer(CargoDataManager.getInstance().getCargoOfferById(cargoOfferId));
				
				
				if (getCargoOffer() != null) {
					if(acceptedOfferButton != null){
						getEditorBar().remove(acceptedOfferButton);
					}
					
					JButton acceptOfferButtonTemp = getAcceptOfferButton(participantNicknameOnly,getCargoOffer());
					acceptedOfferButton = acceptOfferButtonTemp;
					getEditorBar().add(acceptedOfferButton);
					
				} else{
					Long newCargoOfferId=null;
					if(message.getProperty(CARGO_OFFER_PROPERTY)!=null){
						newCargoOfferId=(Long)message.getProperty(CARGO_OFFER_PROPERTY);
					}
					
					if(newCargoOfferId != null) {
						CargoOffer co=CargoDataManager.getInstance().getCargoOfferById(newCargoOfferId);
						setCargoOffer(co);
						
						if(acceptedOfferButton != null){
							getEditorBar().remove(acceptedOfferButton);
						}
						JButton acceptOfferButton = getAcceptOfferButton(participantNicknameOnly,getCargoOffer());
						acceptedOfferButton=acceptOfferButton;
						getEditorBar().add(acceptedOfferButton);
						
					}
				
				}
			}

			@Override
			public void messageSent(ChatRoom room, Message message) {
				// TODO Auto-generated method stub
				String ooo="oeoeu";
				
			}
			
		});
		
	}

	private JButton getAcceptOfferButton(final String nick, CargoOffer co) {
		JButton acceptOfferButton = new JButton();
		acceptOfferButton.setToolTipText("Zaakceptuj ofert\u0119");
		
		CargoOffersLoadPanel loadPanel=new CargoOffersLoadPanel();
		JPanel renderedPanel=new JPanel();
		loadPanel.render(co, renderedPanel, 0);
		renderedPanel.setOpaque(false);

		CargoOffersUnloadPanel unloadPanel=new CargoOffersUnloadPanel();
		JPanel renderedUnloadPanel=new JPanel();
		unloadPanel.render(co, renderedUnloadPanel, 0);
		renderedUnloadPanel.setOpaque(false);
		
		CargoOffersSubmissionDatePanel submissionPanel=new CargoOffersSubmissionDatePanel();
		JPanel renderedSubmissionDatePanel=new JPanel();
		submissionPanel.render(co, renderedSubmissionDatePanel, 0);
		renderedSubmissionDatePanel.setOpaque(false);
		
		renderedSubmissionDatePanel.setOpaque(false);
		
		acceptOfferButton.setLayout(new FlowLayout());
		
		acceptOfferButton.add(renderedPanel);
		acceptOfferButton.add(renderedUnloadPanel);
		acceptOfferButton.add(renderedSubmissionDatePanel);
		
		//dodatki wygladowe tego buttonu
		acceptOfferButton.setBorderPainted(false);
		acceptOfferButton.setContentAreaFilled(false);
		acceptOfferButton.setFocusPainted(false);
		acceptOfferButton.setOpaque(false);
		acceptOfferButton.setVerticalTextPosition(SwingConstants.CENTER);
		acceptOfferButton.setHorizontalTextPosition(SwingConstants.CENTER);
		acceptOfferButton.setIcon					(SparkRes.getImageIcon(SparkRes.CARGO_AKCEPTACJA_OFERTY1));
		acceptOfferButton.setRolloverIcon			(SparkRes.getImageIcon(SparkRes.CARGO_AKCEPTACJA_OFERTY2));
		acceptOfferButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		//koniec dodatkow

		acceptOfferButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String initiator = null;
				String initiated = null;

				initiator=SparkManager.getSessionManager().getUsername();
				initiated=nick;
				
				CargoOffer co = getCargoOffer();
				OfferAcceptance offerAcceptance = new OfferAcceptance();
				offerAcceptance.setInitiator(initiator);
				offerAcceptance.setInitiated(initiated);
				offerAcceptance.setOfferId(co.getId());
				CargoDataManager.getInstance().initiateOfferAcceptance(initiator, initiated, co);
			}
		});

		return acceptOfferButton;
	}

	public void closeChatRoom() {
		// If already closed, don't bother.
		if (!active) {
			return;
		}

		super.closeChatRoom();

		// Remove info listener
		infoButton.removeActionListener(this);
		addToRosterButton.removeActionListener(this);

		// Send a cancel notification event on closing if listening.
		if (!sendNotification) {
			// send cancel
			SparkManager.getMessageEventManager().sendCancelledNotification(getParticipantJID(), threadID);

			sendNotification = true;
		}

		SparkManager.getChatManager().removeChat(this);

		SparkManager.getConnection().removePacketListener(this);
		if (typingTimerTask != null) {
			TaskEngine.getInstance().cancelScheduledTask(typingTimerTask);
			typingTimerTask = null;
		}
		active = false;
	}

	public void sendMessage() {
		String text = getChatInputEditor().getText();
		sendMessage(text);
	}

	public void sendMessage(String text) {
		final Message message = new Message();

		if (threadID == null) {
			threadID = StringUtils.randomString(6);
		}
		message.setThread(threadID);

		// Set the body of the message using typedMessage
		message.setBody(text);
		
		// IF there is no body, just return and do nothing
		if (!ModelUtil.hasLength(text)) {
			return;
		}

		// Fire Message Filters
		SparkManager.getChatManager().filterOutgoingMessage(this, message);

		// Fire Global Filters
		SparkManager.getChatManager().fireGlobalMessageSentListeners(this, message);

		sendMessage(message);

		sendNotification = true;
	}

	/**
	 * Sends a message to the appropriate jid. The message is automatically
	 * added to the transcript.
	 * 
	 * @param message
	 *            the message to send.
	 */
	public void sendMessage(Message message) {
		lastActivity = System.currentTimeMillis();

		try {
			getTranscriptWindow().insertMessage(getNickname(), message, ChatManager.TO_COLOR, Color.white);
			getChatInputEditor().selectAll();

			getTranscriptWindow().validate();
			getTranscriptWindow().repaint();
			getChatInputEditor().clear();
		} catch (Exception ex) {
			Log.error("Error sending message", ex);
		}

		// Before sending message, let's add our full jid for full verification
		message.setType(Message.Type.chat);
		message.setTo(participantJID);
		message.setFrom(SparkManager.getSessionManager().getJID());

		// Notify users that message has been sent
		fireMessageSent(message);

		addToTranscript(message, false);

		getChatInputEditor().setCaretPosition(0);
		getChatInputEditor().requestFocusInWindow();
		scrollToBottom();

		// No need to request displayed or delivered as we aren't doing anything
		// with this
		// information.
		MessageEventManager.addNotificationsRequests(message, true, false, false, true);

		// Send the message that contains the notifications request
		try {
			fireOutgoingMessageSending(message);
			SparkManager.getConnection().sendPacket(message);
		} catch (Exception ex) {
			Log.error("Error sending message", ex);
		}
	}

	public String getRoomname() {
		return roomname;
	}

	public Icon getTabIcon() {
		return tabIcon;
	}

	public void setTabIcon(Icon icon) {
		this.tabIcon = icon;
	}

	public String getTabTitle() {
		return tabTitle;
	}

	public void setTabTitle(String tabTitle) {
		this.tabTitle = tabTitle;
	}

	public void setRoomTitle(String roomTitle) {
		this.roomTitle = roomTitle;
	}

	public String getRoomTitle() {
		return roomTitle;
	}

	public Message.Type getChatType() {
		return Message.Type.chat;
	}

	public void leaveChatRoom() {
		// There really is no such thing in Agent to Agent
	}

	public boolean isActive() {
		return true;
	}

	public String getParticipantJID() {
		return participantJID;
	}

	/**
	 * Returns the users full jid (ex. macbeth@jivesoftware.com/spark).
	 * 
	 * @return the users Full JID.
	 */
	public String getJID() {
		presence = PresenceManager.getPresence(getParticipantJID());
		return presence.getFrom();
	}

	/**
	 * Process incoming packets.
	 * 
	 * @param packet
	 *            - the packet to process
	 */
	public void processPacket(final Packet packet) {
		final Runnable runnable = new Runnable() {
			public void run() {
				if (packet instanceof Presence) {
					presence = (Presence) packet;

					final Presence presence = (Presence) packet;

					ContactList list = SparkManager.getWorkspace().getContactList();
					ContactItem contactItem = list.getContactItemByJID(getParticipantJID());

					String time = DateFormat.getTimeInstance(DateFormat.SHORT).format(new Date());

					if (presence.getType() == Presence.Type.unavailable && contactItem != null) {
						if (!isOnline()) {
							getTranscriptWindow().insertNotificationMessage("*** " + Res.getString("message.went.offline", participantNickname, time), ChatManager.NOTIFICATION_COLOR);
						}
					} else if (presence.getType() == Presence.Type.available) {
						if (!isOnline()) {
							getTranscriptWindow().insertNotificationMessage("*** " + Res.getString("message.came.online", participantNickname, time), ChatManager.NOTIFICATION_COLOR);
						}
					}
				} else if (packet instanceof Message) {
					lastActivity = System.currentTimeMillis();

					// Do something with the incoming packet here.
					final Message message = (Message) packet;
					if (message.getError() != null) {
						if (message.getError().getCode() == 404) {
							// Check to see if the user is online to recieve
							// this message.
							RosterEntry entry = roster.getEntry(participantJID);
							if (!presence.isAvailable() && !offlineSent && entry != null) {
								getTranscriptWindow().insertNotificationMessage(Res.getString("message.offline.error"), ChatManager.ERROR_COLOR);
								offlineSent = true;
							}
						}
						return;
					}

					// Check to see if the user is online to recieve this
					// message.
					RosterEntry entry = roster.getEntry(participantJID);
					if (!presence.isAvailable() && !offlineSent && entry != null) {
						getTranscriptWindow().insertNotificationMessage(Res.getString("message.offline"), ChatManager.ERROR_COLOR);
						offlineSent = true;
					}

					if (threadID == null) {
						threadID = message.getThread();
						if (threadID == null) {
							threadID = StringUtils.randomString(6);
						}
					}

					boolean broadcast = message.getProperty("broadcast") != null;

					// If this is a group chat message, discard
					if (message.getType() == Message.Type.groupchat || broadcast || message.getType() == Message.Type.normal || message.getType() == Message.Type.headline) {
						return;
					}

					// Do not accept Administrative messages.
					final String host = SparkManager.getSessionManager().getServerAddress();
					if (host.equals(message.getFrom())) {
						return;
					}

					// If the message is not from the current agent. Append to
					// chat.
					if (message.getBody() != null) {
						
//						// Updating cargo offer id
//						if(getCargoOffer()!=null){
//							Long currentCargoOfferId=getCargoOffer().getId();
//							Long newCargoOfferId=null;
//							if(message.getProperty(CARGO_OFFER_PROPERTY)!=null){
//								newCargoOfferId=(Long)message.getProperty(CARGO_OFFER_PROPERTY);
//
//								//Always change the offer to the new one.
//								if(newCargoOfferId.equals(getCargoOffer().getId())){
//									/// Ok
//								}
//								else{
//									CargoOffer co=CargoDataManager.getInstance().getCargoOfferById(newCargoOfferId);
//									setCargoOffer(co);
//									JButton acceptOfferButton = getAcceptOfferButton(participantNicknameOnly,getCargoOffer());
//									insertComponent(acceptOfferButton);
//								}
//							}
//						}
//						else{
//							Long newCargoOfferId=null;
//							if(message.getProperty(CARGO_OFFER_PROPERTY)!=null){
//								newCargoOfferId=(Long)message.getProperty(CARGO_OFFER_PROPERTY);
//							}
//							
//							if(newCargoOfferId != null) {
//								CargoOffer co=CargoDataManager.getInstance().getCargoOfferById(newCargoOfferId);
//								setCargoOffer(co);
//								
//								if(acceptedOfferButton != null){
//									getEditorBar().remove(acceptedOfferButton);
//								}
//								JButton acceptOfferButton = getAcceptOfferButton(participantNicknameOnly,getCargoOffer());
//								acceptedOfferButton=acceptOfferButton;
//								getEditorBar().add(acceptedOfferButton);
//								
//							}
//							
//						}
						
						participantJID = message.getFrom();
						insertMessage(message);

						showTyping(false);
					}
				}
			}
		};
		SwingUtilities.invokeLater(runnable);
	}

	/**
	 * Returns the nickname of the user chatting with.
	 * 
	 * @return the nickname of the chatting user.
	 */
	public String getParticipantNickname() {
		return participantNickname;
	}

	/**
	 * The current SendField has been updated somehow.
	 * 
	 * @param e
	 *            - the DocumentEvent to respond to.
	 */
	public void insertUpdate(DocumentEvent e) {
		checkForText(e);

		if (!sendTypingNotification) {
			return;
		}
		lastTypedCharTime = System.currentTimeMillis();

		// If the user pauses for more than two seconds, send out a new notice.
		if (sendNotification) {
			try {
				SparkManager.getMessageEventManager().sendComposingNotification(getParticipantJID(), threadID);
				sendNotification = false;
			} catch (Exception exception) {
				Log.error("Error updating", exception);
			}
		}
	}

	public void insertMessage(Message message) {
		// Debug info
		super.insertMessage(message);
		MessageEvent messageEvent = (MessageEvent) message.getExtension("x", "jabber:x:event");
		if (messageEvent != null) {
			checkEvents(message.getFrom(), message.getPacketID(), messageEvent);
		}

		getTranscriptWindow().insertMessage(participantNickname, message, ChatManager.FROM_COLOR, Color.white);
		
		// Set the participant jid to their full JID.
		participantJID = message.getFrom();
	}

	private void insertComponent(JComponent component) {
//		// Debug info
//		super.insertMessage(message);
//		MessageEvent messageEvent = (MessageEvent) message.getExtension("x", "jabber:x:event");
//		if (messageEvent != null) {
//			checkEvents(message.getFrom(), message.getPacketID(), messageEvent);
//		}

//		getTranscriptWindow().insertMessage(participantNickname, message, ChatManager.FROM_COLOR, Color.white);
		getTranscriptWindow().addComponent(component);
		
		// Set the participant jid to their full JID.
//		participantJID = message.getFrom();
	}
	
	
	private void checkEvents(String from, String packetID, MessageEvent messageEvent) {
		if (messageEvent.isDelivered() || messageEvent.isDisplayed()) {
			// Create the message to send
			Message msg = new Message(from);
			// Create a MessageEvent Package and add it to the message
			MessageEvent event = new MessageEvent();
			if (messageEvent.isDelivered()) {
				event.setDelivered(true);
			}
			if (messageEvent.isDisplayed()) {
				event.setDisplayed(true);
			}
			event.setPacketID(packetID);
			msg.addExtension(event);
			// Send the packet
			SparkManager.getConnection().sendPacket(msg);
		}
	}

	public void addMessageEventListener(MessageEventListener listener) {
		messageEventListeners.add(listener);
	}

	public void removeMessageEventListener(MessageEventListener listener) {
		messageEventListeners.remove(listener);
	}

	public Collection<MessageEventListener> getMessageEventListeners() {
		return messageEventListeners;
	}

	public void fireOutgoingMessageSending(Message message) {
		for (MessageEventListener messageEventListener : new ArrayList<MessageEventListener>(messageEventListeners)) {
			messageEventListener.sendingMessage(message);
		}
	}

	public void fireReceivingIncomingMessage(Message message) {
		for (MessageEventListener messageEventListener : new ArrayList<MessageEventListener>(messageEventListeners)) {
			messageEventListener.receivingMessage(message);
		}
	}

	/**
	 * Show the typing notification.
	 * 
	 * @param typing
	 *            true if the typing notification should show, otherwise hide
	 *            it.
	 */
	public void showTyping(boolean typing) {
		if (typing) {
			String isTypingText = Res.getString("message.is.typing.a.message", participantNickname);
			getNotificationLabel().setText(isTypingText);
			getNotificationLabel().setIcon(SparkRes.getImageIcon(SparkRes.SMALL_MESSAGE_EDIT_IMAGE));
		} else {
			// Remove is typing text.
			getNotificationLabel().setText("");
			getNotificationLabel().setIcon(SparkRes.getImageIcon(SparkRes.BLANK_IMAGE));
		}

	}

	/**
	 * The last time this chat room sent or received a message.
	 * 
	 * @return the last time this chat room sent or receieved a message.
	 */
	public long getLastActivity() {
		return lastActivity;
	}

	/**
	 * Returns the current presence of the client this room was created for.
	 * 
	 * @return the presence
	 */
	public Presence getPresence() {
		return presence;
	}

	public void setSendTypingNotification(boolean isSendTypingNotification) {
		this.sendTypingNotification = isSendTypingNotification;
	}

	public void connectionClosed() {
		handleDisconnect();

		String message = Res.getString("message.disconnected.error");
		getTranscriptWindow().insertNotificationMessage(message, ChatManager.ERROR_COLOR);
	}

	public void connectionClosedOnError(Exception ex) {
		handleDisconnect();

		String message = Res.getString("message.disconnected.error");

		if (ex instanceof XMPPException) {
			XMPPException xmppEx = (XMPPException) ex;
			StreamError error = xmppEx.getStreamError();
			String reason = error.getCode();
			if ("conflict".equals(reason)) {
				message = Res.getString("message.disconnected.conflict.error");
			}
		}

		getTranscriptWindow().insertNotificationMessage(message, ChatManager.ERROR_COLOR);
	}

	public void reconnectionSuccessful() {
		Presence usersPresence = PresenceManager.getPresence(getParticipantJID());
		if (usersPresence.isAvailable()) {
			presence = usersPresence;
		}

		SparkManager.getChatManager().getChatContainer().fireChatRoomStateUpdated(this);
		getChatInputEditor().setEnabled(true);
		getSendButton().setEnabled(true);
	}

	private void handleDisconnect() {
		presence = new Presence(Presence.Type.unavailable);
		getChatInputEditor().setEnabled(false);
		getSendButton().setEnabled(false);
		SparkManager.getChatManager().getChatContainer().fireChatRoomStateUpdated(this);
	}

	private void loadHistory() {
		// Add VCard Panel
		final VCardPanel vcardPanel = new VCardPanel(participantJID);
		
		vcardPanel.setPreferredSize(new Dimension(10, 71));
		vcardPanel.setMaximumSize(new Dimension(1100, 71));
		vcardPanel.setMinimumSize(new Dimension(1100, 71));
		
		getToolBar().add(vcardPanel, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 2, 0, 2), 0, 0));

		
		
		final LocalPreferences localPreferences = SettingsManager.getLocalPreferences();
		if (!localPreferences.isChatHistoryEnabled()) {
			return;
		}

		if (!localPreferences.isPrevChatHistoryEnabled()) {
			return;
		}

		final String bareJID = StringUtils.parseBareAddress(getParticipantJID());
		final ChatTranscript chatTranscript = ChatTranscripts.getCurrentChatTranscript(bareJID);
		final String personalNickname = SparkManager.getUserManager().getNickname();

		for (HistoryMessage message : chatTranscript.getMessages()) {
			String nickname = SparkManager.getUserManager().getUserNicknameFromJID(message.getFrom());
			String messageBody = message.getBody();
			if (nickname.equals(message.getFrom())) {
				String otherJID = StringUtils.parseBareAddress(message.getFrom());
				String myJID = SparkManager.getSessionManager().getBareAddress();

				if (otherJID.equals(myJID)) {
					nickname = personalNickname;
				} else {
					nickname = StringUtils.parseName(nickname);
				}
			}

			if (ModelUtil.hasLength(messageBody) && messageBody.startsWith("/me ")) {
				messageBody = messageBody.replaceFirst("/me", nickname);
			}

			final Date messageDate = message.getDate();
			getTranscriptWindow().insertHistoryMessage(nickname, messageBody, messageDate);
		}

	}

	private boolean isOnline() {
		Presence presence = roster.getPresence(getParticipantJID());
		return presence.isAvailable();
	}

	// I would normally use the command pattern, but
	// have no real use when dealing with just a couple options.
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == infoButton) {
			VCardManager vcard = SparkManager.getVCardManager();
			vcard.viewProfile(participantJID, SparkManager.getChatManager().getChatContainer());
		} else if (e.getSource() == addToRosterButton) {
			RosterDialog rosterDialog = new RosterDialog();
			rosterDialog.setDefaultJID(participantJID);
			rosterDialog.setDefaultNickname(getParticipantNickname());
			rosterDialog.showRosterDialog(SparkManager.getChatManager().getChatContainer().getChatFrame());
		} else {
			super.actionPerformed(e);
		}
	}
}
