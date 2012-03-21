package org.jivesoftware.sparkimpl.plugin.blucargo.table.renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;

import org.jivesoftware.sparkimpl.plugin.blucargo.table.HighlightTableModel;
import org.jivesoftware.sparkimpl.plugin.blucargo.table.SecondaryTableModel;
import org.jivesoftware.sparkimpl.plugin.blucargo.table.BlucargoSortableTableModel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.RoundedPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.RoundedPanelLeft;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.RoundedPanelRight;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.RoundedPanelSquare;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.VisualPanel;

import com.blucargo.model.CargoOffer;
import com.blucargo.model.OfferType;

public class CargoColumnCellRendererThatTriggersChat implements TableCellRenderer {

	public Component getTableCellRendererComponent(final JTable table, Object value, boolean isSelected, boolean hasFocus, final int row, int column) {

		Color roundedBorderColor=new Color(0x6e7d93);
		
		final RoundedPanel panel;
		if (column == 0) {
			panel = new RoundedPanelLeft();
			panel.setBorder(BorderFactory.createMatteBorder(0, 15, 0, 0, new Color(131, 145, 165, 255)));
		} else if (column == table.getColumnCount() - 1) {
			panel = new RoundedPanelRight();
			panel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 15, new Color(131, 145, 165, 255)));
		} else {
			panel = new RoundedPanelSquare();
		}
		panel.setLayout(new GridBagLayout());
		panel.setBorderColor(roundedBorderColor);

		int red = 208, green = 203, blue = 181;
		final CargoOffer cargoOffer = (CargoOffer) table.getValueAt(row, 0);
		if (cargoOffer.getType() == OfferType.CARGO) {
			red = 232;
			green = 227;
			blue = 208;
		}
		else if (cargoOffer.getType() == OfferType.VEHICLE) {
			red = 208;
			green = 203;
			blue = 181;
		}

		if (isSelected) {
			if (column == 0) {
				panel.setBorder(BorderFactory.createMatteBorder(0, 15, 0, 0, new Color(131, 145, 165, 255)));

			} else if (column == 6) {
				panel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 15, new Color(131, 145, 165, 255)));
			} else {
//				panel.setBorder(new RoundedBorder());
			}
			
			red -= 10;
			green -= 10;
			blue -= 10;
		}

		if (hasFocus) {
//			red -= 10;
//			green -= 10;
//			blue -= 10;
		}

		Color color = new Color(red, green, blue, 255);
		
		panel.setColor(color);

		Class<?> columnClass = table.getColumnClass(column);
		Object visualPanel = null;
		try {
			visualPanel = columnClass.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}

		if (cargoOffer != null) {
			((VisualPanel) visualPanel).render(cargoOffer, panel, row);

			panel.addMouseListener(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {
					dispatchEvent(table, panel, e, row);
//					String jid = cargoOffer.getOwner() + "@" + SparkManager.getSessionManager().getServerAddress();
//					SparkManager.getChatManager().activateChat(jid, jid, cargoOffer);
				}
			
			});
			
			
			panel.addMouseMotionListener(new MouseMotionListener(){

				@Override
                public void mouseDragged(MouseEvent e) {
	                // TODO Auto-generated method stub
	                
                }

				@Override
                public void mouseMoved(MouseEvent e) {
					MouseEvent event = new MouseEvent((Component) e.getSource(), MouseEvent.MOUSE_ENTERED, e.getWhen(), e.getModifiers(),e.getX(), e.getY(), 1, true);
					dispatchEvent(table, panel, event, row);
                }
				
			});
			
			SecondaryTableModel.getInstance().setValueAsJPanel(panel, row, column);
		}

		panel.setBackground(color);
		panel.setOpaque(true);
		return panel;
	}


	private void dispatchEvent(final JTable table, final RoundedPanel panel, MouseEvent e, int row) {
        JPanel tempPanel = panel;

		int selectedColumn = table.getColumnModel().getColumnIndexAtX(e.getX());
		int selectedRow = e.getY() / table.getRowHeight();
		int rowHeight = table.getRowHeight();

		// Count the columns width up to selected row
		int totalColumnsWidth = 0;
		for (int i = 0; i < selectedColumn; i++) {
			totalColumnsWidth += table.getColumnModel().getColumn(i).getWidth();
		}

		SwingUtilities.convertMouseEvent(table, e, tempPanel);
		boolean highlighted=false;
		for (int i = 0; i < tempPanel.getComponentCount(); i++) {

			Component c = tempPanel.getComponent(i);
			Point p = new Point(c.getX() + totalColumnsWidth, c.getY() + selectedRow * rowHeight);
			Rectangle r = new Rectangle(p, new Dimension(c.getWidth(), c.getHeight()));
			
			if((c instanceof JButton) && (isInside(e.getPoint(), r))){
				
				JButton b = (JButton)c;
				HighlightTableModel.getInstance().setButtonInRowHighlighted(row, b.getName());
				BlucargoSortableTableModel model = (BlucargoSortableTableModel)table.getModel();
				model.fireTableRowsUpdated(row, row);
				highlighted = true;
			}
			
			 if (isInside(e.getPoint(), r) && (e.getID() == MouseEvent.MOUSE_CLICKED)) {
				e.setSource(tempPanel.getComponent(i));
				tempPanel.getComponent(i).dispatchEvent(e);
				return;
			}
		}
		
		String button = HighlightTableModel.getInstance().getButtonInRowHighlighted(row);
		
		if(highlighted){
			table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		else if(!highlighted && button!=null){
			HighlightTableModel.getInstance().clear();
			BlucargoSortableTableModel model = (BlucargoSortableTableModel)table.getModel();
			model.fireTableRowsUpdated(row, row);			
			table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}	
    }
	
	private boolean isInside(Point p, Rectangle r) {
		double x = r.getX();
		double y = r.getY();
		double w = r.getWidth();
		double h = r.getHeight();

		double X = p.getX();
		double Y = p.getY();

		if (X < x || Y < y) {
			return false;
		}

		w += x;
		h += y;
		
		// overflow || intersect
		boolean c1 = (w < x || w > X);
		boolean c2 = (h < y || h > Y);
		return (c1 && c2);
	}
}
