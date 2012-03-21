package org.jivesoftware.sparkimpl.plugin.blucargo.table;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Used for passing Mouseenter events to relevant components.
 *
 */
public class HighlightTableModel {

	private static HighlightTableModel instance = null;

	protected HighlightTableModel() {
	}

	public static HighlightTableModel getInstance() {
		if (instance == null) {
			instance = new HighlightTableModel();
		}
		return instance;
	}

	protected Map<Integer, String> buttonInRowHighlighted = new HashMap<Integer, String>();

	public void clear(){
		buttonInRowHighlighted.clear();
	}
	
	public void setButtonInRowHighlighted(int row, String buttonName) {
		buttonInRowHighlighted.clear();
		buttonInRowHighlighted.put(row, buttonName);
	}

	public String getButtonInRowHighlighted(int row){
		return buttonInRowHighlighted.get(row);
	}
	
}
