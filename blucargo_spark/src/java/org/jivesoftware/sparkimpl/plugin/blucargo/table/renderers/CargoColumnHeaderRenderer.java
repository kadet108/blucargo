
package org.jivesoftware.sparkimpl.plugin.blucargo.table.renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import org.jivesoftware.resource.SparkRes;
import org.jivesoftware.sparkimpl.plugin.blucargo.HTMLtag;
import org.jivesoftware.sparkimpl.plugin.blucargo.resourcebundle.StrAccessor;
import org.jivesoftware.sparkimpl.plugin.blucargo.table.HeaderListener;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.RoundedPanelSquare;


public class CargoColumnHeaderRenderer extends Component implements TableCellRenderer{


	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        int red = 202;
        int green = 202;
        int blue = 202;

        if (isSelected)
        {
            red = -10;
            green = -10;
            blue = -10;
        }

        Color color=new Color(red,green,blue,255);
        Color color2=new Color(131,145,165,255);

        JPanel panel1=new RoundedPanelSquare(color);

        panel1 = new RoundedPanelSquare(color2);

        ImageIcon rodzajOgloszeniaIcon 			= SparkRes.getImageIcon(SparkRes.CARGO_RODZAJ_OGLOSZENIA);
        ImageIcon zaladunekIcon 				= SparkRes.getImageIcon(SparkRes.CARGO_ZALADUNEK);
        ImageIcon rozladunekIcon 				= SparkRes.getImageIcon(SparkRes.CARGO_ROZLADUNEK);
        ImageIcon pojazdIcon 					= SparkRes.getImageIcon(SparkRes.CARGO_POJAZD);
        ImageIcon zgloszonoIcon 				= SparkRes.getImageIcon(SparkRes.CARGO_ZGLOSZONO);
        ImageIcon wazneDoIcon 					= SparkRes.getImageIcon(SparkRes.CARGO_WAZNE_DO);
        ImageIcon kontaktIcon 					= SparkRes.getImageIcon(SparkRes.CARGO_KONTAKT);
        ImageIcon dol1Icon 						= SparkRes.getImageIcon(SparkRes.CARGO_DOL1);
        ImageIcon dol2Icon 						= SparkRes.getImageIcon(SparkRes.CARGO_DOL2);
        ImageIcon gora1Icon 					= SparkRes.getImageIcon(SparkRes.CARGO_GORA1);
        ImageIcon gora2Icon 					= SparkRes.getImageIcon(SparkRes.CARGO_GORA2);
        
        JLabel head_rodzajOgloszenia = new JLabel(HTMLtag.openingWhiteBold + 
        		StrAccessor.getString("CargoColumnHeaderRenderer.advertType") + HTMLtag.closingWhiteBold);	//$NON-NLS-1$
        JLabel head_zaladunek = new JLabel(HTMLtag.openingWhiteBold + 
        		StrAccessor.getString("CargoColumnHeaderRenderer.whereFrom") + HTMLtag.closingWhiteBold);	//$NON-NLS-1$
        JLabel head_rozladunek = new JLabel(HTMLtag.openingWhiteBold + 
        		StrAccessor.getString("CargoColumnHeaderRenderer.whereTo") + HTMLtag.closingWhiteBold);		//$NON-NLS-1$
        JLabel head_pojazd = new JLabel(HTMLtag.openingWhiteBold + 
        		StrAccessor.getString("CargoColumnHeaderRenderer.Type") + HTMLtag.closingWhiteBold);		//$NON-NLS-1$
        JLabel head_zgloszono = new JLabel(HTMLtag.openingWhiteBold + 
        		StrAccessor.getString("CargoColumnHeaderRenderer.entered") + HTMLtag.closingWhiteBold);		//$NON-NLS-1$
        JLabel head_wazne = new JLabel(HTMLtag.openingWhiteBold + 
        		StrAccessor.getString("CargoColumnHeaderRenderer.validUntil") + HTMLtag.closingWhiteBold);	//$NON-NLS-1$
        JLabel head_kontakt = new JLabel(HTMLtag.openingWhiteBold + 
        		StrAccessor.getString("CargoColumnHeaderRenderer.contact") + HTMLtag.closingWhiteBold);		//$NON-NLS-1$

        panel1.setBackground(null);
        panel1.setLayout(new FlowLayout());

        ImageIcon dolIcon = dol1Icon;
        ImageIcon goraIcon = gora1Icon;
        
        HeaderListener headerListener = null;
        for (MouseListener mouseListener : table.getTableHeader().getMouseListeners()) {
        	if (mouseListener instanceof HeaderListener){
        		headerListener = (HeaderListener) mouseListener;
        	}
		}
        
        if(headerListener.getSelectedColumn() == column){
        	dolIcon = dol2Icon;
        	goraIcon = gora2Icon;
        	table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        else {
        	table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        
        if (column == 0) {
            panel1.add(head_rodzajOgloszenia,			new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
            panel1.add(new JLabel(dolIcon),				new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
            panel1.add(new JLabel(goraIcon),			new GridBagConstraints(2, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        }
        else if (column == 1) {
            panel1.add(head_zaladunek,					new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
            panel1.add(new JLabel(dolIcon),				new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
            panel1.add(new JLabel(goraIcon),			new GridBagConstraints(2, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        }
        else if (column == 2) {
            panel1.add(head_rozladunek,					new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
            panel1.add(new JLabel(dolIcon),				new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
            panel1.add(new JLabel(goraIcon),			new GridBagConstraints(2, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        }
        else if (column == 3) {
            panel1.add(head_pojazd,						new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
            panel1.add(new JLabel(dolIcon),				new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
            panel1.add(new JLabel(goraIcon),			new GridBagConstraints(2, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        }
        else if (column == 4) {
            panel1.add(head_zgloszono,					new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
            panel1.add(new JLabel(dolIcon),				new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
            panel1.add(new JLabel(goraIcon),			new GridBagConstraints(2, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        }
        else if (column == 5) {
            panel1.add(head_wazne,						new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
            panel1.add(new JLabel(dolIcon),				new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
            panel1.add(new JLabel(goraIcon),			new GridBagConstraints(2, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        }
        else if (column == 6) {
            panel1.add(head_kontakt,					new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
            panel1.add(new JLabel(dolIcon),				new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
            panel1.add(new JLabel(goraIcon),			new GridBagConstraints(2, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        }
        else
            panel1.add(new JLabel(value.toString()),	new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        
        return panel1;
    }
}