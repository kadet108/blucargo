/**
 * $Revision: $
 * $Date: $
 *
 * Copyright (C) 2006 Jive Software. All rights reserved.
 *
 * This software is published under the terms of the GNU Lesser Public License (LGPL),
 * a copy of which is included in this distribution.
 */

package org.jivesoftware.sparkimpl.plugin.jabber;

import org.jivesoftware.resource.Res;
import org.jivesoftware.resource.SparkRes;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.spark.SparkManager;
import org.jivesoftware.spark.component.RolloverButton;
import org.jivesoftware.spark.plugin.Plugin;
import org.jivesoftware.spark.util.GraphicUtils;
import org.jivesoftware.spark.util.ModelUtil;
import org.jivesoftware.spark.util.ResourceUtils;
import org.jivesoftware.spark.util.log.Log;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

public class JabberBrowser implements Plugin {
    private JLabel addressLabel = new JLabel();
    private JComboBox addressField = new JComboBox();
    private XMPPConnection con;
    private JPanel browsePanel;

    public JabberBrowser() {
        this.con = SparkManager.getConnection();

        addressField.setEditable(true);
        addressField.addItem(con.getHost());
    }

    public void display() {
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        // Setup resource
        ResourceUtils.resLabel(addressLabel, addressField, Res.getString("label.jabber.address") + ":");

        RolloverButton backButton = new RolloverButton();
        backButton.setIcon(SparkRes.getImageIcon(SparkRes.LEFT_ARROW_IMAGE));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedItem = addressField.getSelectedIndex();
                if (selectedItem > 0) {
                    Object historyItem = addressField.getItemAt(selectedItem - 1);
                    browse((String)historyItem);
                }
            }
        });

        mainPanel.add(backButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        mainPanel.add(addressLabel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        mainPanel.add(addressField, new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));


        JButton browseButton = new JButton("");
        ResourceUtils.resButton(browseButton, Res.getString("button.browse"));
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String serviceName = (String)addressField.getSelectedItem();
                if (!ModelUtil.hasLength(serviceName)) {
                    return;
                }
                browse(serviceName);
            }
        });
        mainPanel.add(addressField, new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        mainPanel.add(browseButton, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

        browsePanel = new JPanel();
        browsePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        browsePanel.setBackground(Color.white);

        JScrollPane pane = new JScrollPane(browsePanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        browsePanel.setPreferredSize(new Dimension(0, 0));
        mainPanel.add(pane, new GridBagConstraints(0, 1, 4, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

        JFrame frame = new JFrame();
        frame.setIconImage(SparkRes.getImageIcon(SparkRes.FIND_IMAGE).getImage());

        JDialog dialog = new JDialog(frame, Res.getString("title.jabber.browser"));
        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(mainPanel, BorderLayout.CENTER);
        dialog.pack();
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(SparkManager.getMainWindow());
        dialog.setVisible(true);
    }

    private void browse(String serviceName) {
        browsePanel.removeAll();

        ServiceDiscoveryManager discoManager = ServiceDiscoveryManager.getInstanceFor(con);
        DiscoverItems result;
        try {
            result = discoManager.discoverItems(serviceName);
        }
        catch (XMPPException e) {
            Log.error(e);
            return;
        }

        addAddress(serviceName);


        Iterator<DiscoverItems.Item> discoverItems = result.getItems();
        while (discoverItems.hasNext()) {
            DiscoverItems.Item item = discoverItems.next();
            Entity entity = new Entity(item);
            browsePanel.add(entity);
        }

        browsePanel.invalidate();
        browsePanel.validate();
        browsePanel.repaint();
    }

    private void browseItem(DiscoverItems.Item discoveredItem) {
        addAddress(discoveredItem.getEntityID());
        browsePanel.removeAll();
        ServiceDiscoveryManager discoManager = ServiceDiscoveryManager.getInstanceFor(con);
        DiscoverItems result;
        try {
            result = discoManager.discoverItems(discoveredItem.getEntityID());
        }
        catch (XMPPException e) {
            browsePanel.invalidate();
            browsePanel.validate();
            browsePanel.repaint();
            return;
        }

        Iterator<DiscoverItems.Item> discoverItems = result.getItems();
        List<Entity> list = new ArrayList<Entity>();

        while (discoverItems.hasNext()) {
            DiscoverItems.Item item = discoverItems.next();
            Entity entity = new Entity(item);
            browsePanel.add(entity);
            list.add(entity);
        }

        GraphicUtils.makeSameSize((JComponent[])list.toArray(new JComponent[list.size()]));

        browsePanel.invalidate();
        browsePanel.validate();
        browsePanel.repaint();
    }

    public class Entity extends RolloverButton {
		private static final long serialVersionUID = 2084728014635239794L;
		private DiscoverItems.Item item;

        public Entity(final DiscoverItems.Item item) {
            this.item = item;
            setVerticalTextPosition(JLabel.BOTTOM);
            setHorizontalTextPosition(JLabel.CENTER);
            setText(item.getName());
            setIcon(SparkRes.getImageIcon(SparkRes.USER1_MESSAGE_24x24));

            addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    browseItem(item);
                }
            });

        }

        public DiscoverItems.Item getItem() {
            return item;
        }
    }

    private void addAddress(String address) {
        addressField.addItem(address);
        addressField.setSelectedItem(address);
    }

    public void initialize() {
        SparkManager.getWorkspace().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F8"), "showBrowser");
        SparkManager.getWorkspace().getActionMap().put("showBrowser", new AbstractAction("showBrowser") {
			private static final long serialVersionUID = 341826581565007606L;

			public void actionPerformed(ActionEvent evt) {
                display();
            }
        });
    }

    public void shutdown() {

    }

    public boolean canShutDown() {
        return false;
    }

    public void uninstall() {
        // Do nothing.
    }


}
