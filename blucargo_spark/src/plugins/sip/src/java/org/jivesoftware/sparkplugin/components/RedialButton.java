/**
 * $Revision: $
 * $Date: $
 *
 * Copyright (C) 2007 Jive Software. All rights reserved.
 *
 * This software is published under the terms of the GNU Lesser Public License (LGPL),
 * a copy of which is included in this distribution.
 */

package org.jivesoftware.sparkplugin.components;

import org.jivesoftware.spark.plugin.phone.resource.PhoneRes;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 */
public class RedialButton extends JButton implements MouseListener {

    private Icon normalIcon;
    private Icon hoverIcon;
    private Icon downIcon;
    private Image backgroundImage;

    public RedialButton() {
        super();

        normalIcon = PhoneRes.getImageIcon("REDIAL_BUTTON");
        hoverIcon = PhoneRes.getImageIcon("REDIAL_BUTTON_HOVER");
        downIcon = PhoneRes.getImageIcon("REDIAL_BUTTON_DOWN");
        backgroundImage = PhoneRes.getImageIcon("REDIAL_IMAGE").getImage();

        setIcon(normalIcon);
        decorate();

        addMouseListener(this);

        setForeground(new Color(158, 32, 10));

        setDisabledIcon(normalIcon);
    }

    /**
     * Decorates the button with the approriate UI configurations.
     */
    private void decorate() {
        setBorderPainted(false);
        setOpaque(true);

        setContentAreaFilled(false);
        setMargin(new Insets(0, 0, 0, 0));
    }


    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        setIcon(downIcon);
    }

    public void mouseReleased(MouseEvent e) {
        setIcon(normalIcon);
    }

    public void mouseEntered(MouseEvent e) {
        setIcon(hoverIcon);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void mouseExited(MouseEvent e) {
        setIcon(normalIcon);
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        int x = (width - backgroundImage.getWidth(null)) / 2;
        int y = (height - backgroundImage.getHeight(null)) / 2;
        g.drawImage(backgroundImage, x, y - 5, null);

        if (isEnabled()) {
            g.setColor(new Color(158, 32, 10));
        }
        else {
            g.setColor(Color.lightGray);
        }


        g.setFont(new Font("Tahoma", Font.BOLD, 11));

        String endCall = "Redial";
        int stringWidth = g.getFontMetrics().stringWidth(endCall);

        x = (width - stringWidth) / 2;
        y = height - 12;
        g.drawString(endCall, x, y);

    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (!enabled) {
            removeMouseListener(this);
        }
        else {
            addMouseListener(this);
        }
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame();
        frame.add(new EndCallButton());
        frame.pack();
        frame.setVisible(true);
    }
}
