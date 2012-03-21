package org.jivesoftware.sparkimpl.plugin.blucargo.window.util;

import java.awt.Component;
import java.awt.event.MouseEvent;



public class SparkMouseEvent extends MouseEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SparkMouseEvent(MouseEvent e) {
		super((Component) e.getSource(), e.getID(), e.getWhen(), e.getModifiers(), e.getX(), e.getY(), e.getClickCount(),false,e.getButton());
		// TODO Auto-generated constructor stub
	}


	int column;
	int row;
	
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	
	
}
