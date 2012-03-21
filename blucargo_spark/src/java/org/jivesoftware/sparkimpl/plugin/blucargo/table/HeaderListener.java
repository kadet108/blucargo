package org.jivesoftware.sparkimpl.plugin.blucargo.table;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.JTableHeader;

import org.jivesoftware.sparkimpl.plugin.blucargo.table.misc.SortButtonRenderer;

public class HeaderListener extends MouseAdapter {
	
	JTableHeader header;
	SortButtonRenderer renderer;
	int selectedColumn = -1;

	public HeaderListener(JTableHeader header, SortButtonRenderer renderer) {
		this.header = header;
		this.renderer = renderer;
	}

	public int getSelectedColumn() {
		return selectedColumn;
	}

	public void setSelectedColumn(int selectedColumn) {
		this.selectedColumn = selectedColumn;
	}

	public void mousePressed(MouseEvent e) {
		int col = header.columnAtPoint(e.getPoint());
		int sortCol = header.getTable().convertColumnIndexToModel(col);
		renderer.setPressedColumn(col);
		renderer.setSelectedColumn(col);
		header.repaint();

		if (header.getTable().isEditing()) {
			header.getTable().getCellEditor().stopCellEditing();
		}

		boolean isAscent;
		if (SortButtonRenderer.DOWN == renderer.getState(col)) {
			isAscent = true;
		} else {
			isAscent = false;
		}
		((BlucargoSortableTableModel) header.getTable().getModel()).sortByColumn(sortCol, isAscent);
	}

	public void mouseReleased(MouseEvent e) {
		renderer.setPressedColumn(-1); // clear
		header.repaint();
	}
	
    public void mouseEntered(MouseEvent e) {
		int col = header.columnAtPoint(e.getPoint());
		this.selectedColumn = col;
		header.repaint();
    }
    
    public void mouseMoved(MouseEvent e){
		int col = header.columnAtPoint(e.getPoint());
		this.selectedColumn = col;
		header.repaint();    	
    }

    public void mouseExited(MouseEvent e) {
		this.selectedColumn = -1;
		renderer.setSelectedColumn(-1);
    	header.repaint();
    }
	
}
