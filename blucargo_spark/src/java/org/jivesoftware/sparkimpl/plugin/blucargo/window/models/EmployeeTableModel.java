package org.jivesoftware.sparkimpl.plugin.blucargo.window.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.jivesoftware.smackx.packet.VCard;

public class EmployeeTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private List<VCard> employees = new ArrayList<VCard>();
	
	private JTable table;
	
	public EmployeeTableModel(JTable table) {
		super();
		this.table=table;
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public int getRowCount() {
		return employees.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(rowIndex < 0 ){
			return null;
		}
		VCard vcard = employees.get(rowIndex);
		return vcard;
	}

	public void removeRow(int row){
		employees.remove(row);
		this.fireTableRowsDeleted(row, row);
	}
	
    public void insertRow(int row, Object rowData) {
    	employees.add(row,(VCard)rowData);
    	this.fireTableRowsInserted(this.getRowCount(), this.getRowCount());
    }

    public void insertRow(Object rowData) {
    	employees.add((VCard)rowData);
    }

	
	@Override
	public void fireTableDataChanged(){
		int row = table.getSelectedRow();
		int rowCount = this.getRowCount();
		super.fireTableDataChanged();

		if(this.getRowCount() == rowCount){
			table.changeSelection(row, 0, true, false);
		}
	}
	
	@Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		VCard vcard = employees.get(rowIndex);
		vcard = (VCard)aValue;
    }

    
	@Override
	public Class<?> getColumnClass(int col) {
		return String.class;
	}

}


class CustomColumn {

	VCard vcard;
	
	public CustomColumn (VCard vcard){
		this.vcard = vcard;
	}
	
	public String render(VCard vcard){
		return (String)vcard.getProperty("userName");
	}
}