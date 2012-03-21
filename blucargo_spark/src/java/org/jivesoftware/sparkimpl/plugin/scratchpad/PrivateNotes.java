/**
 * $Revision: $
 * $Date: $
 *
 * Copyright (C) 2006 Jive Software. All rights reserved.
 *
 * This software is published under the terms of the GNU Lesser Public License (LGPL),
 * a copy of which is included in this distribution.
 */

package org.jivesoftware.sparkimpl.plugin.scratchpad;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.PrivateDataManager;
import org.jivesoftware.smackx.packet.PrivateData;
import org.jivesoftware.smackx.provider.PrivateDataProvider;
import org.jivesoftware.spark.SparkManager;
import org.jivesoftware.spark.util.log.Log;
import org.xmlpull.v1.XmlPullParser;

/**
 * @author Derek DeMoro
 */
public class PrivateNotes implements PrivateData {

    private String notes;

    /**
     * Required Empty Constructor to use Bookmarks.
     */
    public PrivateNotes() {
    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    /**
     * Returns the root element name.
     *
     * @return the element name.
     */
    public String getElementName() {
        return "scratchpad";
    }

    /**
     * Returns the root element XML namespace.
     *
     * @return the namespace.
     */
    public String getNamespace() {
        return "scratchpad:notes";
    }

    /**
     * Returns the XML reppresentation of the PrivateData.
     *
     * @return the private data as XML.
     */
    public String toXML() {
        StringBuffer buf = new StringBuffer();
        buf.append("<scratchpad xmlns=\"scratchpad:notes\">");

        if (getNotes() != null) {
            buf.append("<text>").append(getNotes()).append("</text>");
        }

        buf.append("</scratchpad>");
        return buf.toString();
    }

    /**
     * The IQ Provider for BookmarkStorage.
     *
     * @author Derek DeMoro
     */
    public static class Provider implements PrivateDataProvider {

        PrivateNotes notes = new PrivateNotes();

        /**
         * Empty Constructor for PrivateDataProvider.
         */
        public Provider() {
            super();
        }

        public PrivateData parsePrivateData(XmlPullParser parser) throws Exception {
            boolean done = false;
            while (!done) {
                int eventType = parser.next();
                if (eventType == XmlPullParser.START_TAG && "text".equals(parser.getName())) {
                    notes.setNotes(parser.nextText());
                }
                else if (eventType == XmlPullParser.END_TAG) {
                    if ("scratchpad".equals(parser.getName())) {
                        done = true;
                    }
                }
            }


            return notes;
        }
    }

    public static void savePrivateNotes(PrivateNotes notes) {
        PrivateDataManager manager = new PrivateDataManager(SparkManager.getConnection());

        PrivateDataManager.addPrivateDataProvider("scratchpad", "scratchpad:notes", new PrivateNotes.Provider());
        try {
            manager.setPrivateData(notes);
        }
        catch (XMPPException e) {
            Log.error(e);
        }
    }

    public static PrivateNotes getPrivateNotes() {
        PrivateDataManager manager = new PrivateDataManager(SparkManager.getConnection());

        PrivateDataManager.addPrivateDataProvider("scratchpad", "scratchpad:notes", new PrivateNotes.Provider());

        PrivateNotes notes = null;

        try {
            notes = (PrivateNotes)manager.getPrivateData("scratchpad", "scratchpad:notes");
        }
        catch (XMPPException e) {
            Log.error(e);
        }

        return notes;
    }
}
