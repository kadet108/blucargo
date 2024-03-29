/**
 * $Revision: $
 * $Date: $
 *
 * Copyright (C) 2006 Jive Software. All rights reserved.
 *
 * This software is published under the terms of the GNU Lesser Public License (LGPL),
 * a copy of which is included in this distribution.
 */

package org.jivesoftware.sparkimpl.preference.notifications;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.spark.SparkManager;
import org.jivesoftware.spark.plugin.Plugin;
import org.jivesoftware.spark.ui.ContactGroup;
import org.jivesoftware.spark.ui.ContactItem;
import org.jivesoftware.spark.ui.ContactList;
import org.jivesoftware.spark.util.SwingTimerTask;
import org.jivesoftware.spark.util.TaskEngine;
import org.jivesoftware.sparkimpl.plugin.alerts.SparkToaster;
import org.jivesoftware.sparkimpl.settings.local.LocalPreferences;
import org.jivesoftware.sparkimpl.settings.local.SettingsManager;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;
import java.util.TimerTask;

/**
 * Adds a simple notification system to alert users to presence changes.
 *
 * @author Derek DeMoro
 */
public class NotificationPlugin implements Plugin, PacketListener {

    private Set<String> onlineUsers = new HashSet<String>();
    private LocalPreferences preferences;


    public void initialize() {
        // Add the preferences
        NotificationsPreference notifications = new NotificationsPreference();
        SparkManager.getPreferenceManager().addPreference(notifications);
        notifications.load();

        final TimerTask registerTask = new SwingTimerTask() {
            public void doRun() {
                registerListener();
            }
        };

        TaskEngine.getInstance().schedule(registerTask, 5000);
    }

    private void registerListener() {
        preferences = SettingsManager.getLocalPreferences();

        // Iterate through all online users and add them to the list.
        ContactList contactList = SparkManager.getWorkspace().getContactList();
        for (ContactGroup contactGroup : contactList.getContactGroups()) {
            for (ContactItem item : contactGroup.getContactItems()) {
                if (item != null && item.getJID() != null && item.getPresence().isAvailable()) {
                    String bareJID = StringUtils.parseBareAddress(item.getJID());
                    onlineUsers.add(bareJID);
                }
            }
        }

        // Add Presence Listener
        SparkManager.getConnection().addPacketListener(this, new PacketTypeFilter(Presence.class));
    }


    public void processPacket(Packet packet) {
        final Presence presence = (Presence)packet;
        String jid = presence.getFrom();
        if (jid == null) {
            return;
        }

        // Make sure the user is in the contact list.
        ContactItem contactItem = SparkManager.getWorkspace().getContactList().getContactItemByJID(jid);
        if (contactItem == null) {
            return;
        }

        jid = StringUtils.parseBareAddress(jid);
        boolean isOnline = onlineUsers.contains(jid);

        if (presence.isAvailable()) {
            if (preferences.isOnlineNotificationsOn()) {
                if (!isOnline) {
                    notifyUserOnline(jid, presence);
                }
            }

            onlineUsers.add(jid);
        }
        else {
            if (preferences.isOfflineNotificationsOn() && isOnline) {
                notifyUserOffline(jid, presence);
            }

            onlineUsers.remove(jid);
        }
    }

    public void shutdown() {
    }

    public boolean canShutDown() {
        return true;
    }

    public void uninstall() {
        SparkManager.getConnection().removePacketListener(this);
    }

    /**
     * Notify client that a user has come online.
     *
     * @param jid the jid of the user that has come online.
     * @param presence Presence of the online user.
     */
    private void notifyUserOnline(String jid, Presence presence) {
        SparkToaster toaster = new SparkToaster();
        toaster.setDisplayTime(5000);
        toaster.setBorder(BorderFactory.createBevelBorder(0));
        toaster.setCustomAction(new ChatAction(jid));
        NotificationAlertUI alertUI = new NotificationAlertUI(jid, true, presence);

        toaster.setToasterHeight((int)alertUI.getPreferredSize().getHeight() + 40);

        int width = (int)alertUI.getPreferredSize().getWidth() + 40;
        if (width < 300) {
            width = 300;
        }

        toaster.setToasterWidth(width);

        toaster.showToaster("", alertUI);
        toaster.hideTitle();
    }

    /**
     * Notify client that a user has gone offline.
     *
     * @param jid the jid of the user who has gone offline.
     * @param presence of the offline user.
     */
    private void notifyUserOffline(String jid, Presence presence) {
        SparkToaster toaster = new SparkToaster();
        toaster.setCustomAction(new ChatAction(jid));
        toaster.setDisplayTime(5000);
        toaster.setBorder(BorderFactory.createBevelBorder(0));

        NotificationAlertUI alertUI = new NotificationAlertUI(jid, false, presence);


        toaster.setToasterHeight((int)alertUI.getPreferredSize().getHeight() + 40);

        int width = (int)alertUI.getPreferredSize().getWidth() + 40;
        if (width < 300) {
            width = 300;
        }

        toaster.setToasterWidth(width);

        toaster.showToaster("", alertUI);
        toaster.hideTitle();
    }

    private class ChatAction extends AbstractAction {

        private String jid;

        public ChatAction(String jid) {
            this.jid = jid;
        }

        public void actionPerformed(ActionEvent e) {
            String nickname = SparkManager.getUserManager().getUserNicknameFromJID(jid);
            SparkManager.getChatManager().activateChat(jid, nickname);
        }
    }


}
