/**
 * $Revision: $
 * $Date: $
 *
 * Copyright (C) 2006 Jive Software. All rights reserved.
 *
 * This software is published under the terms of the GNU Lesser Public License (LGPL),
 * a copy of which is included in this distribution.
 */

package org.jivesoftware.spark.ui;

import java.awt.Color;
import java.awt.Component;

import org.jivesoftware.resource.SparkRes;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.spark.PresenceManager;
import org.jivesoftware.spark.component.tabbedPane.SparkTab;
import org.jivesoftware.spark.ui.rooms.ChatRoomImpl;

/**
 * Allows users to control the decoration of a <code>SparkTab</code> component within the <code>ChatContainer</code>.
 */
public abstract class SparkTabHandler {

    public abstract boolean isTabHandled(SparkTab tab, Component component, boolean isSelectedTab, boolean chatFrameFocused);

    /**
     * Updates the SparkTab to show it is in a stale state.
     *
     * @param tab      the SparkTab.
     * @param chatRoom the ChatRoom of the SparkTab.
     */
    protected void decorateStaleTab(SparkTab tab, ChatRoom chatRoom) {
        tab.setTitleColor(Color.gray);
        tab.setTabFont(tab.getDefaultFont());

        String jid = ((ChatRoomImpl)chatRoom).getParticipantJID();
        Presence presence = PresenceManager.getPresence(jid);

        if (!presence.isAvailable()) {
            tab.setIcon(SparkRes.getImageIcon(SparkRes.IM_UNAVAILABLE_STALE_IMAGE));
        }
        else {
            Presence.Mode mode = presence.getMode();
            if (mode == Presence.Mode.available || mode == null) {
                tab.setIcon(SparkRes.getImageIcon(SparkRes.IM_AVAILABLE_STALE_IMAGE));
            }
            else if (mode == Presence.Mode.away) {
                tab.setIcon(SparkRes.getImageIcon(SparkRes.IM_AWAY_STALE_IMAGE));
            }
            else if (mode == Presence.Mode.chat) {
                tab.setIcon(SparkRes.getImageIcon(SparkRes.IM_FREE_CHAT_STALE_IMAGE));
            }
            else if (mode == Presence.Mode.dnd) {
                tab.setIcon(SparkRes.getImageIcon(SparkRes.IM_DND_STALE_IMAGE));
            }
            else if (mode == Presence.Mode.xa) {
                tab.setIcon(SparkRes.getImageIcon(SparkRes.IM_DND_STALE_IMAGE));
            }
        }

        tab.validateTab();
    }
    
}
