package org.jivesoftware.sparkimpl.plugin.blucargo.window.models;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;

import org.jivesoftware.smackx.packet.VCard;

public class EmployeeTableCellRenderer implements TableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		if (value == null){
			return null;
		}
		
		VCard vcard = (VCard)value;
		JLabel label = new JLabel((String)vcard.getNickName());
		
		if(isSelected){
			Border border = new LineBorder(Color.black);
			
			label.setBorder(border);
		}
		
		return label;
	}
	
	
	
}

