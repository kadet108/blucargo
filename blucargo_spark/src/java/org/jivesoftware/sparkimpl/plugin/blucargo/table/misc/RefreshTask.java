package org.jivesoftware.sparkimpl.plugin.blucargo.table.misc;

import java.util.TimerTask;

import javax.swing.JTable;

import org.apache.log4j.Logger;
import org.jivesoftware.sparkimpl.plugin.blucargo.table.BlucargoSortableTableModel;

public final class RefreshTask extends TimerTask {

	private Logger logger = Logger.getLogger(RefreshTask.class);

	private BlucargoSortableTableModel model;
	private JTable table;

	public RefreshTask() {
	}

	public RefreshTask(BlucargoSortableTableModel model, JTable table) {
		this.model = model;
		this.setTable(table);
	}

	public void run() {
		try {
			model.refreshQuickly();
			model.fireTableDataChanged();
		} catch (Exception e) {
			logger.error(e.getCause());
		}
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JTable getTable() {
		return table;
	}
}
