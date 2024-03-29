/**
 * $Revision: $
 * $Date: $
 *
 * Copyright (C) 2006 Jive Software. All rights reserved.
 *
 * This software is published under the terms of the GNU Lesser Public License (LGPL),
 * a copy of which is included in this distribution.
 */

package org.jivesoftware.sparkimpl.plugin.manager;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.packet.DiscoverInfo;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.spark.SparkManager;
import org.jivesoftware.spark.util.log.Log;

import java.util.Iterator;

/**
 * EnterpriseSparkManager is responsible for the detecting of features on the server. This allows for fine-grain control of
 * feature sets to enable/disable within Spark.
 *
 * @author Derek DeMoro
 */
public class Enterprise {

    public static final String BROADCAST_FEATURE = "broadcast";
    public static final String MUC_FEATURE = "muc";
    public static final String VCARD_FEATURE = "vcard";
    public static final String FILE_TRANSFER_FEATURE = "file-transfer";

    private static DiscoverInfo featureInfo;

    private boolean sparkManagerInstalled;

    public Enterprise() {
        // Retrieve feature list.
        populateFeatureSet();
    }

    /**
     * Returns true if the Enterprise Spark Manager module is installed on the server we are currently connected to.
     *
     * @return true if Enterprise Spark Manager exists.
     */
    public boolean isSparkManagerInstalled() {
        return sparkManagerInstalled;
    }

    /**
     * Returns true if the feature is available.
     *
     * @param feature the name of the feature to detect.
     * @return true if the feature is available on the server, otherwise false.
     */
    public static boolean containsFeature(String feature) {
        if (featureInfo == null) {
            return true;
        }

        return featureInfo.containsFeature(feature);
    }

    private void populateFeatureSet() {
        final ServiceDiscoveryManager disco = ServiceDiscoveryManager.getInstanceFor(SparkManager.getConnection());
        final DiscoverItems items = SparkManager.getSessionManager().getDiscoveredItems();
        Iterator<DiscoverItems.Item> iter = items.getItems();
        while (iter.hasNext()) {
            DiscoverItems.Item item = iter.next();
            String entity = item.getEntityID();
            if (entity != null) {
                if (entity.startsWith("manager.")) {
                    sparkManagerInstalled = true;

                    // Populate with feature sets.
                    try {
                        featureInfo = disco.discoverInfo(item.getEntityID());
                    }
                    catch (XMPPException e) {
                        Log.error("Error while retrieving feature list for SparkManager.", e);
                    }

                }
            }
        }
    }
}
