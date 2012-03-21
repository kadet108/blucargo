package org.jivesoftware.sparkimpl.plugin.blucargo.util;
//package org.jivesoftware.sparkimpl.plugin.cargo.util;
//
//import java.awt.Graphics;
//import java.awt.GraphicsConfiguration;
//import java.awt.GraphicsDevice;
//import java.awt.GraphicsEnvironment;
//import java.awt.HeadlessException;
//import java.awt.Image;
//import java.awt.Transparency;
//import java.awt.image.BufferedImage;
//import javax.swing.ImageIcon;
//
//public class Graphic {
//
//	public static BufferedImage toBufferedImage(Image image) {
//		if (image instanceof BufferedImage) {
//			return (BufferedImage) image;
//		}
//
//		// This code ensures that all the pixels in the image are loaded
//		image = new ImageIcon(image).getImage();
//
//		// Determine if the image has transparent pixels; for this method's
//		// implementation, see Determining If an Image Has Transparent Pixels
//		// boolean hasAlpha = hasAlpha(image);
//		boolean hasAlpha = true;
//
//		// Create a buffered image with a format that's compatible with the
//		// screen
//		BufferedImage bimage = null;
//		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		try {
//			// Determine the type of transparency of the new buffered image
//			int transparency = Transparency.OPAQUE;
//			if (hasAlpha) {
//				transparency = Transparency.BITMASK;
//			}
//
//			// Create the buffered image
//			GraphicsDevice gs = ge.getDefaultScreenDevice();
//			GraphicsConfiguration gc = gs.getDefaultConfiguration();
//			bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
//		} catch (HeadlessException e) {
//			// The system does not have a screen
//		}
//
//		if (bimage == null) {
//			// Create a buffered image using the default color model
//			int type = BufferedImage.TYPE_INT_RGB;
//			if (hasAlpha) {
//				type = BufferedImage.TYPE_INT_ARGB;
//			}
//			bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
//		}
//
//		// Copy image to buffered image
//		Graphics g = bimage.createGraphics();
//
//		// Paint the image onto the buffered image
//		g.drawImage(image, 0, 0, null);
//		g.dispose();
//
//		return bimage;
//	}
//
//}
