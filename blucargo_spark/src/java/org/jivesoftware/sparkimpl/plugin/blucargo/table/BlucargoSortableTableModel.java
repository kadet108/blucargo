package org.jivesoftware.sparkimpl.plugin.blucargo.table;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import org.jivesoftware.sparkimpl.plugin.blucargo.table.misc.SortButtonRenderer;
import org.jivesoftware.sparkimpl.plugin.blucargo.table.renderers.CargoColumnCellRendererThatTriggersChat;
import org.jivesoftware.sparkimpl.plugin.blucargo.table.renderers.CargoColumnHeaderRenderer;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.ArrowIcon;

public abstract class BlucargoSortableTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 4364708719601604454L;

	private int[] indexes;
	private TableSorter sorter;
	private final JButton upButton = new JButton(new ArrowIcon(ArrowIcon.UP));
	private final JButton downButton = new JButton(new ArrowIcon(ArrowIcon.DOWN));
	protected int pageSize;
	protected int pageOffset;

	private JTable table;

	public BlucargoSortableTableModel() {
		this(10000, 100);
	}

	public BlucargoSortableTableModel(int numRows, int size) {
		pageSize = size;
		this.setColumnIdentifiers(initializeColumnIdentifiers());
	}

	public abstract Class<?> getColumnClass(int col);

	public abstract List<?> getData();

	public abstract Vector<?> initializeColumnIdentifiers();

	public boolean isCellEditable(int row, int col) {
		switch (col) {
		case 1:
			return false;
		default:
			return true;
		}
	}

	public void setValueAt(Object obj, int row, int col) {
	}

	public Object getValueAt(int row, int col) {
		int rowIndex = row;
		if (indexes != null) {
			rowIndex = indexes[row];
		}
		int realRow = rowIndex + (pageOffset * pageSize);

		Object value = null;
		try {
			value = super.getValueAt(realRow, col);
/*			Vector<Object> rows= (Vector<Object>)this.getDataVector().get(col);
			value=rows.get(row);
*/		} catch (ArrayIndexOutOfBoundsException e) {
			// This is normal with i.e 3 elements and the last page, do nothing.
		}

		return value;
	}

	public void sortByColumn(int column, boolean isAscent) {
		if (sorter == null) {
			sorter = new TableSorter(this);
		}
		sorter.sort(column, isAscent);
		fireTableDataChanged();
	}

	public int[] getIndexes() {
		int n = getRowCount();
		if (indexes != null) {
			if (indexes.length == n) {
				return indexes;
			}
		}
		indexes = new int[n];
		for (int i = 0; i < n; i++) {
			indexes[i] = i;
		}
		return indexes;
	}

	public void setTable(JTable nTable) {
		this.table = nTable;
		this.table.setRowHeight(60);
	}

	// @TODO: What is the difference
	public void refreshQuickly() {
		Vector<Vector<Object>> mainDataVector = new Vector<Vector<Object>>();

		for (Object c : this.getData()) {
			Vector<Object> columnVector = new Vector<Object>(this.getColumnCount() - 1);
			columnVector.add(0, c);
			for (int i = 0; i < this.getColumnCount() - 1; i++) {
				columnVector.add(null);
			}
			mainDataVector.addElement(columnVector);
		}

		this.dataVector = mainDataVector;

		createHeaders();
	}

	// @TODO: What is the difference
	public void refreshSlowly() {
		if (this.getData() == null)
			return;

		Vector<Vector<Object>> columns = new Vector<Vector<Object>>();

		for (Object c : this.getData()) {
			Vector<Object> rows = new Vector<Object>();
			rows.addElement(c);
			columns.addElement(rows);
		}

		this.setDataVector(columns, super.columnIdentifiers);
	
		createHeaders();
	}

	protected int[] getColumnWidths()
	{
		return new int[]{ 150, 50, 50, 70, 100, 100, 100 };
	}
	
	private void createHeaders() {
		SortButtonRenderer renderer = new SortButtonRenderer();
		JTableHeader header = table.getTableHeader();

		int[] columnWidth = getColumnWidths();
		CargoColumnHeaderRenderer headerRenderer = new CargoColumnHeaderRenderer();
		headerRenderer.addMouseListener(new HeaderListener(header, renderer));
		CargoColumnCellRendererThatTriggersChat cargoColumnCellRenderer = new CargoColumnCellRendererThatTriggersChat();
		int n = this.getColumnCount();
		for (int i = 0; i < n; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(cargoColumnCellRenderer);
			table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
			table.getColumnModel().getColumn(i).setPreferredWidth(columnWidth[i]);
		}
	}

	public int getRowCount() {
		int factor = this.getDataVector().size() - ((this.pageOffset) * (this.pageSize));
		return Math.min(pageSize, factor);
	}

	public int getColumnCount() {
		return super.getColumnCount();
	}

	public synchronized String getColumnName(int col) {
		return super.getColumnName(col);
	}

	public int getPageOffset() {
		return pageOffset;
	}

	public int getPageCount() {
		return (int) Math.ceil((double) super.getRowCount() / pageSize);
	}

	public int getRealRowCount() {
		return super.getDataVector().size();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int s) {
		if (s == pageSize) {
			return;
		}
		int oldPageSize = pageSize;
		pageSize = s;
		pageOffset = (oldPageSize * pageOffset) / pageSize;
		fireTableDataChanged();
	}

	public void pageDown() {
		if (pageOffset < getPageCount() - 1) {
			pageOffset++;
			fireTableDataChanged();
		}
	}

	public void pageUp() {
		if (pageOffset > 0) {
			pageOffset--;
			fireTableDataChanged();
		}
	}

	public JScrollPane createPagingScrollPaneForTable(JTable jt) {
		JScrollPane jsp = new JScrollPane(jt);
		jsp.getViewport().setBorder(null);
		jsp.setBorder(new LineBorder(new Color(131, 145, 165, 255)));
		
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// Add in the corners (page up/down).
		jsp.setCorner(ScrollPaneConstants.UPPER_RIGHT_CORNER, upButton);
		jsp.setCorner(ScrollPaneConstants.LOWER_RIGHT_CORNER, downButton);

		return jsp;
	}

	public JButton createPageUpButton() {

		TableModel tmodel = this;

		final BlucargoSortableTableModel model = (BlucargoSortableTableModel) tmodel;
		upButton.setEnabled(false); // starts off at 0, so can't go up
		if (model.getPageCount() <= 1) {
			downButton.setEnabled(false); // One page...can't scroll down
		}
		return upButton;
	}

	public JButton createPageDownButton() {
		TableModel tmodel = this;
		final BlucargoSortableTableModel model = (BlucargoSortableTableModel) tmodel;

		if (model.getPageCount() <= 1) {
			downButton.setEnabled(false); // One page...can't scroll down
		}
		return downButton;
	}

	public JTextField createPageSizeTextField() {
		final JTextField tf = new JTextField("100", 6);
		tf.setPreferredSize(new Dimension(30, 30));
		tf.setToolTipText("Ilo\u015b\u0107 element\u00f3w na stronie");
		return tf;
	}
}
