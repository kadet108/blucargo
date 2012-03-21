package org.jivesoftware.sparkimpl.plugin.blucargo.table;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

/*
 * Used for setting and getting JPanels.
 */
public class SecondaryTableModel {

	private static SecondaryTableModel instance = null;

	protected SecondaryTableModel() {
	}

	public static SecondaryTableModel getInstance() {
		if (instance == null) {
			instance = new SecondaryTableModel();
		}
		return instance;
	}

	protected Map<Integer, Map<Integer, JPanel>> valuesAsJPanel = new HashMap<Integer, Map<Integer, JPanel>>();

	public void setValueAsJPanel(JPanel jpanel, int row, int column) {
		Map<Integer, JPanel> rowMap = this.valuesAsJPanel.get(row);
		if (rowMap == null){
			rowMap = new HashMap<Integer, JPanel>();
			this.valuesAsJPanel.put(row, rowMap);
		}
		rowMap.put(column, jpanel);
	}

	public JPanel getValueAsJPanel(int row, int column) {
		Map<Integer, JPanel> rowMap = this.valuesAsJPanel.get(row);
		return rowMap.get(column);
	}

}
