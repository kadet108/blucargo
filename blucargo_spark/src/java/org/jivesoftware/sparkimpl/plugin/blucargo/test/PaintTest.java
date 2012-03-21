package org.jivesoftware.sparkimpl.plugin.blucargo.test;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Created by IntelliJ IDEA.
 * User: james.robbins
 * Date: Apr 6, 2005
 * Time: 4:29:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class PaintTest extends JFrame {

    public PaintTest()
    {
        setupScreen();
    }

    private void setupScreen()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BackgroundPanel bgPanel = new BackgroundPanel();
        bgPanel.setPreferredSize(new Dimension(1000, 1000));
        bgPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        try
        {
            bgPanel.setBackgroundImage(new URL("http://www.google.com/intl/en/images/logo.gif"));
        }
        catch (MalformedURLException e)
        {
            System.err.println("Could Not Link to Background Picture");
        }

        //bgPanel.setBackgroundRepeat("repeat-x");
        //bgPanel.setBackgroundRepeat("repeat-y");

        JScrollPane contentPane = new JScrollPane(bgPanel);
        contentPane.setPreferredSize(new Dimension(250, 250));
        this.getContentPane().add(contentPane);
        //this.getContentPane().add(bgPanel);
        this.pack();
        this.show();
    }

    private class BackgroundPanel extends JPanel
    {
        private URL backgroundImage;
        private String backgroundRepeat = "repeat";

        public void setBackgroundImage(URL backgroundImage)
        {
             this.backgroundImage = backgroundImage;
        }

        public void setBackgroundRepeat(String backgroundRepeat)
        {
            this.backgroundRepeat = backgroundRepeat;
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            if (backgroundImage != null)
            {
                Graphics2D g2 = (Graphics2D)g.create();
                Insets inset = this.getInsets();
                ImageIcon image = new ImageIcon(backgroundImage);
                Rectangle clippingRegion = new Rectangle(this.getWidth() - (inset.left + inset.right), this.getHeight() - (inset.top + inset.bottom));

//                g2.setClip(inset.left, inset.top, (int)clippingRegion.getWidth(), (int)clippingRegion.getHeight());
                Rectangle clip = g.getClipBounds();

                int height = getSize().height - inset.bottom;
                int width = getSize().width - inset.right;

                if (clip.y + clip.height > height)
                    clip.height = height - clip.y;

                if (clip.x + clip.width > width)
                    clip.width = width - clip.x;

                g2.setClip(clip);
                int xRepeat = 0;
                int yRepeat = 0;

                if (backgroundRepeat == "repeat" || backgroundRepeat == "repeat-y")
                    yRepeat = (int)Math.ceil(clippingRegion.getHeight() / image.getIconHeight());

                if (backgroundRepeat == "repeat" || backgroundRepeat == "repeat-x")
                    xRepeat = (int)Math.ceil(clippingRegion.getWidth() / image.getIconWidth());

                for (int i = 0; i <= yRepeat; i++)
                {
                    for (int j = 0; j <= xRepeat; j++)
                    {
                        image.paintIcon(this, g2, j * image.getIconWidth() + inset.left, i * image.getIconHeight() + inset.top);
                    }
                }

                g2.dispose();
            }
        }
    }

    public static void main(String[] args)
    {
        PaintTest pt = new PaintTest();
    }
}