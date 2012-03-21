package org.jivesoftware.sparkimpl.plugin.blucargo.table;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jivesoftware.sparkimpl.plugin.blucargo.systray.UserCriteriaRowFilter;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.menu.ContactMenuItem;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.menu.OfferDetailsMenuItem;

import com.blucargo.model.CargoOffer;

public class BlucargoTable extends JTable{

	private int TABLE_ROW_HEIGHT = 100;
	private JTable table	=	this;
	
	private JPopupMenu contextMenu;
	private ContactMenuItem contactMenuItem;
	private OfferDetailsMenuItem offerDetailsMenuItem;
	
	public BlucargoTable(BlucargoSortableTableModel model){
		super(model);
		model.setTable(this);

		TableRowSorter<TableModel> sorter 	= new TableRowSorter<TableModel>(model);
		RowFilter<Object, Object> filter 	= new UserCriteriaRowFilter();
		sorter.setRowFilter(filter);
		table.setRowSorter(sorter);
		
		init();
	}
	
	private void init(){
		this.setColumnSelectionAllowed		(false);
		this.setRowSelectionAllowed			(true);
		this.setRowHeight					(this.TABLE_ROW_HEIGHT);
		this.setShowVerticalLines			(true);
		this.setIntercellSpacing			(new Dimension(0,15));
		this.setRowHeight					(150);
		this.setShowHorizontalLines			(false);
		this.setShowVerticalLines			(false);
		this.getTableHeader().setReorderingAllowed(false);
		this.getTableHeader().setResizingAllowed(false);
		this.setBackground					(new Color(131, 145, 165, 255));
		this.setOpaque						(false);

		table.setInheritsPopupMenu(true);
		table.setBorder(null);
		table.getTableHeader().setBorder(null);
		
		contextMenu 			= new JPopupMenu("Edit");
        contactMenuItem 		= new ContactMenuItem();
		offerDetailsMenuItem 	= new OfferDetailsMenuItem();
		
		contextMenu.add(contactMenuItem);
		contextMenu.add(offerDetailsMenuItem);
		
		this.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				table.setBackground(new Color(131, 145, 165, 255));
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		table.addMouseListener(new MouseAdapter() {

			@SuppressWarnings("static-access")
			public void mouseClicked(MouseEvent e) {

				if (e.getButton() == e.BUTTON1) {
					int column = table.getColumnModel().getColumnIndexAtX(e.getX());
					int row = e.getY() / table.getRowHeight();
					JPanel panel = SecondaryTableModel.getInstance().getValueAsJPanel(row, column);
					
					//Tak by by\u0142o lepiej.
					//sortableTableModel.getValueAt(row, col)
					
					panel.dispatchEvent(e);
				}

				if (e.getButton() == e.BUTTON3) {
					int column = table.getColumnModel().getColumnIndexAtX(e.getX());
					int row = e.getY() / table.getRowHeight();

					BlucargoSortableTableModel model = (BlucargoSortableTableModel)table.getModel();					
					CargoOffer cargoOffer = (CargoOffer)model.getValueAt(row, 0);

					contactMenuItem.setCargoOffer(cargoOffer);
					offerDetailsMenuItem.setCargoOffer(cargoOffer);
					contextMenu.show(table, e.getX(), e.getY());
				}
			}
		});
		
		table.addMouseMotionListener(new MouseMotionListener() {

			@Override
            public void mouseDragged(MouseEvent e) {
	            // TODO Auto-generated method stub
	            
            }

			@Override
            public void mouseMoved(MouseEvent e) {
				int column = table.getColumnModel().getColumnIndexAtX(e.getX());
				int row = e.getY() / table.getRowHeight();
				JPanel panel = SecondaryTableModel.getInstance().getValueAsJPanel(row, column);
				panel.dispatchEvent(e);
            }
			
		});
		
	}
	
}
