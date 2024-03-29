/**
 * $Revision: $
 * $Date: $
 *
 * Copyright (C) 2006 Jive Software. All rights reserved.
 *
 * This software is published under the terms of the GNU Lesser Public License (LGPL),
 * a copy of which is included in this distribution.
 */

package org.jivesoftware.spark.search;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.Collection;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;

import org.jivesoftware.Spark;
import org.jivesoftware.resource.Default;
import org.jivesoftware.resource.Res;
import org.jivesoftware.resource.SparkRes;
import org.jivesoftware.spark.SparkManager;
import org.jivesoftware.spark.component.IconTextField;
import org.jivesoftware.spark.util.ResourceUtils;
import org.jivesoftware.spark.util.SwingWorker;

public class SearchService extends JPanel {
    private IconTextField findField;
    private Image backgroundImage;
    private boolean newSearch;

    private Searchable activeSearchable;

    public SearchService() {
        setLayout(new GridBagLayout());
        findField = new IconTextField(SparkRes.getImageIcon(SparkRes.SEARCH_USER_16x16));
        findField.setPreferredSize(new Dimension(160,20));
        
        backgroundImage = Default.getImageIcon(Default.TOP_BOTTOM_BACKGROUND_IMAGE).getImage();

        final JLabel findLabel = new JLabel();

        ResourceUtils.resLabel(findLabel, findField, Res.getString("label.find"));

        // add(findLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

        if (Spark.isMac()) {
            add(findField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        }
        else {
            add(findField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        }

//        // Check for secure connection
//        if (SparkManager.getConnection().isSecureConnection()) {
//            final JLabel lockLabel = new JLabel();
//            lockLabel.setHorizontalTextPosition(JLabel.LEFT);
//            lockLabel.setIcon(SparkRes.getImageIcon(SparkRes.LOCK_16x16));
//            if (Spark.isMac()) {
//                add(lockLabel, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 15), 0, 0));
//
//            }
//            else {
//                add(lockLabel, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
//
//            }
//            lockLabel.setToolTipText(Res.getString("message.spark.secure"));
//        }

        findField.setToolTipText(Res.getString("message.search.for.contacts"));

        findField.getTextComponent().addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {

            }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    final Icon previousIcon = findField.getIcon();

                    findField.setIcon(SparkRes.getImageIcon(SparkRes.BUSY_IMAGE));
                    findField.validate();
                    findField.repaint();

                    SwingWorker worker = new SwingWorker() {
                        public Object construct() {
                            activeSearchable.search(findField.getText());
                            return true;
                        }

                        public void finished() {
                            findField.setIcon(previousIcon);
                            findField.setText("");
                        }
                    };


                    worker.start();
                }
            }

            public void keyReleased(KeyEvent e) {

            }
        });

        findField.getTextComponent().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (newSearch) {
                    findField.setText("");
                    findField.getTextComponent().setForeground((Color) UIManager.get("TextField.foreground"));
                    newSearch = false;
                }
            }
        });


//        Workspace workspace = SparkManager.getWorkspace();
//        workspace.add(this, new GridBagConstraints(0, 10, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
//        workspace.invalidate();
//        workspace.validate();
//        workspace.repaint();


        findField.getImageComponent().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Collection<Searchable> searchables = SparkManager.getSearchManager().getSearchServices();
                if (searchables.size() <= 1) {
                    return;
                }

                // Show popup
                final JPopupMenu popup = new JPopupMenu();
                for (final Searchable searchable : searchables) {
                    Action action = new AbstractAction() {
                        public void actionPerformed(ActionEvent e) {
                            setActiveSearchService(searchable);
                        }
                    };

                    action.putValue(Action.SMALL_ICON, searchable.getIcon());
                    action.putValue(Action.NAME, searchable.getName());
                    popup.add(action);
                }

                popup.show(findField, 0, findField.getHeight());

            }
        });
    }

    public void setActiveSearchService(final Searchable searchable) {
        this.activeSearchable = searchable;

        newSearch = true;
        findField.requestFocus();
        findField.getTextComponent().setForeground((Color) UIManager.get("TextField.lightforeground"));
        findField.setIcon(searchable.getIcon());
        findField.setText(searchable.getDefaultText());
        findField.setToolTipText(searchable.getToolTip());

        findField.getTextComponent().addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                findField.setText("");
            }

            public void focusLost(FocusEvent e) {
                findField.getTextComponent().setForeground((Color) UIManager.get("TextField.lightforeground"));
                findField.setText(searchable.getDefaultText());
            }
        });
    }

    public void paintComponent(Graphics g) {
        double scaleX = getWidth() / (double) backgroundImage.getWidth(null);
        double scaleY = getHeight() / (double) backgroundImage.getHeight(null);
        AffineTransform xform = AffineTransform.getScaleInstance(scaleX, scaleY);
        ((Graphics2D) g).drawImage(backgroundImage, xform, this);
    }

    protected IconTextField getFindField() {
        return findField;
    }

    public void setBackgroundImage(Image image) {
        this.backgroundImage = image;
    }


}
