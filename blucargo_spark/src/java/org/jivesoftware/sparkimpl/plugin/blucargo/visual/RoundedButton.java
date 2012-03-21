package org.jivesoftware.sparkimpl.plugin.blucargo.visual;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;


public class RoundedButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7286974527319401393L;
	RoundedButton db;

	public RoundedButton() {
		super();
		db = this;
		setOpaque(false);
		this.setBorderPainted(false);
		db.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				int x = db.getX();
				int y = db.getY();
				int w = getWidth() - 0;
				int h = getHeight() - 0;
				int arc = 30;

				// java.awt.Toolkit.getDefaultToolkit().beep();
				Graphics g = db.getParent().getGraphics();
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setStroke(new BasicStroke(3f));
				g2.setColor(new Color(106, 106, 106, 255));
				g2.drawRoundRect(x, y, w, h, arc, arc);

				g2.dispose();
			}

			public void mouseExited(MouseEvent e) {
				int x = db.getX();
				int y = db.getY();
				int w = getWidth() - 0;
				int h = getHeight() - 0;
				int arc = 30;

				// java.awt.Toolkit.getDefaultToolkit().beep();
				Graphics g = db.getParent().getGraphics();
				Graphics2D g2 = (Graphics2D) g.create();

				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				g2.setStroke(new BasicStroke(3f));
				g2.setColor(new Color(226, 226, 226, 255));
				g2.drawRoundRect(x, y, w, h, arc, arc);

				g2.dispose();

			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		int x = 0;
		int y = 0;
		int w = getWidth() - 0;
		int h = getHeight() - 0;
		int arc = 30;

		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(new Color(242, 242, 242, 220));
		g2.fillRoundRect(x, y, w, h, arc, arc);

		g2.setStroke(new BasicStroke(3f));
		g2.setColor(new Color(226, 226, 226, 220));
		g2.drawRoundRect(x, y, w, h, arc, arc);

		g2.dispose();
	}

	protected void paintComponent2(Graphics g) {
		int x = 0;
		int y = 0;
		int w = getWidth() - 0;
		int h = getHeight() - 0;
		int arc = 30;

		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(new Color(242, 242, 242, 220));
		g2.fillRoundRect(x, y, w, h, arc, arc);

		g2.setStroke(new BasicStroke(3f));
		g2.setColor(new Color(236, 236, 236, 220));
		g2.drawRoundRect(x, y, w, h, arc, arc);

		g2.dispose();
	}

	public void mouseClicked(MouseEvent e) {
		java.awt.Toolkit.getDefaultToolkit().beep();

	}

	public void mouseEntered(MouseEvent e) {
		java.awt.Toolkit.getDefaultToolkit().beep();
	}
}
