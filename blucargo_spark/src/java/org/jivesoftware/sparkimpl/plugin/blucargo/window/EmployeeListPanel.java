package org.jivesoftware.sparkimpl.plugin.blucargo.window;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jdesktop.swingx.JXTable;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.packet.VCard;
import org.jivesoftware.spark.SparkManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.manager.CargoDataManager;
import org.jivesoftware.sparkimpl.plugin.blucargo.resourcebundle.StrAccessor;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.models.EmployeeTableCellRenderer;
import org.jivesoftware.sparkimpl.plugin.blucargo.window.models.EmployeeTableModel;

public class EmployeeListPanel extends JPanel {

	private static final long	serialVersionUID	= 1L;

	private JTextField	      login	             = new JTextField();
	private JTextField	      password	         = new JPasswordField();
	private JTextField	      confirmPassword	 = new JPasswordField();
	private JTextField	      firstName	         = new JTextField();
	private JTextField	      lastName	         = new JTextField();
	private JTextField	      email	             = new JTextField();
	private JTextField	      telefon	         = new JTextField();
	private JTextField	      fax	             = new JTextField();

	VCard	                  vcard1;
	VCard	                  vcard2;
	VCard	                  vcard3;
	VCard	                  vcard4;

	private boolean	          allowEditing	     = true;

	final EmployeeTableModel  model;


	public EmployeeListPanel() {
		this.setLayout(new GridBagLayout());

		final JXTable employeeTable = new JXTable();
		model = new EmployeeTableModel(employeeTable);
		employeeTable.setModel(model);

		List<String> employees = CargoDataManager.getInstance().getEmployeesOf(SparkManager.getUserManager().getNickname());
		String serverName = SparkManager.getSessionManager().getServerAddress();

		
		if(employees!=null){
			for (String employee : employees) {
				String jid = employee + "@" + serverName; //$NON-NLS-1$
				VCard vcard = new VCard();
				try {
					vcard.load(SparkManager.getConnection(), jid);
				} catch (XMPPException e1) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();
				}
				model.insertRow(vcard);
			}
			
		}
		

		EmployeeTableCellRenderer renderer = new EmployeeTableCellRenderer();

		employeeTable.setDefaultRenderer(String.class, renderer);
		
//		employeeTable.setPreferredSize(new Dimension(300,20));
//		employeeTable.setMinimumSize(new Dimension(300,20));
		
		employeeTable.setOpaque(false);
		employeeTable.setColumnControlVisible(true);
		LineBorder border = new LineBorder(Color.black);
		employeeTable.setBorder(border);
		JScrollPane employeeScroll = new JScrollPane(employeeTable);

		employeeTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				VCard vcard = (VCard) model.getValueAt(employeeTable.getSelectedRow(), employeeTable.getSelectedColumn());

				if (vcard == null) {
					return;
				}

				login.setText(vcard.getNickName());
				password.setText((String) vcard.getField("password")); //$NON-NLS-1$
				confirmPassword.setText((String) vcard.getField("confirmPassword")); //$NON-NLS-1$
				firstName.setText(vcard.getFirstName());
				lastName.setText(vcard.getLastName());
				telefon.setText(vcard.getPhoneWork("work")); //$NON-NLS-1$
				fax.setText((String) vcard.getField("fax")); //$NON-NLS-1$
				email.setText(vcard.getEmailWork());
			}
		});

		login.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				int row = employeeTable.getSelectedRow();
				int column = employeeTable.getSelectedColumn();

				VCard vcard = (VCard) model.getValueAt(row, column);
				vcard.setNickName(login.getText());
				model.setValueAt(vcard, row, column);
				model.fireTableDataChanged();

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		password.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				int row = employeeTable.getSelectedRow();
				int column = employeeTable.getSelectedColumn();

				VCard vcard = (VCard) model.getValueAt(row, column);
				vcard.setField("password", password.getText()); //$NON-NLS-1$
				model.setValueAt(vcard, row, column);
				model.fireTableDataChanged();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		confirmPassword.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				int row = employeeTable.getSelectedRow();
				int column = employeeTable.getSelectedColumn();

				VCard vcard = (VCard) model.getValueAt(row, column);
				vcard.setField("confirmPassword", confirmPassword.getText()); //$NON-NLS-1$
				model.setValueAt(vcard, row, column);
				model.fireTableDataChanged();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		firstName.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				int row = employeeTable.getSelectedRow();
				int column = employeeTable.getSelectedColumn();

				VCard vcard = (VCard) model.getValueAt(row, column);
				vcard.setFirstName(firstName.getText());
				model.setValueAt(vcard, row, column);
				model.fireTableDataChanged();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		lastName.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				int row = employeeTable.getSelectedRow();
				int column = employeeTable.getSelectedColumn();

				VCard vcard = (VCard) model.getValueAt(row, column);
				vcard.setLastName(lastName.getText());
				model.setValueAt(vcard, row, column);
				model.fireTableDataChanged();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		telefon.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				VCard vcard = (VCard) model.getValueAt(employeeTable.getSelectedRow(), employeeTable.getSelectedColumn());
				vcard.setPhoneWork("work", telefon.getText()); //$NON-NLS-1$

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		fax.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				VCard vcard = (VCard) model.getValueAt(employeeTable.getSelectedRow(), employeeTable.getSelectedColumn());
				vcard.setField("fax", fax.getText()); //$NON-NLS-1$

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		email.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				VCard vcard = (VCard) model.getValueAt(employeeTable.getSelectedRow(), employeeTable.getSelectedColumn());
				String text = email.getText();
				vcard.setEmailWork(email.getText());

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		model.fireTableDataChanged();

		JButton addNewEmployee = new JButton("+"); //$NON-NLS-1$
		addNewEmployee.setToolTipText(StrAccessor.getString("EmployeeListPanel.toolTip.addEmployee")); //$NON-NLS-1$
		addNewEmployee.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VCard vcard = new VCard();
				vcard.setField("employeeOf", SparkManager.getUserManager().getNickname()); //$NON-NLS-1$
				model.insertRow(vcard);
				model.fireTableDataChanged();
			}

		});

		JButton removeEmployee = new JButton("-"); //$NON-NLS-1$
		removeEmployee.setToolTipText(StrAccessor.getString("EmployeeListPanel.toolTip.removeEmployee")); //$NON-NLS-1$
		removeEmployee.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(employeeTable.getSelectedRow()<0){
					return;
				}
				model.removeRow(employeeTable.getSelectedRow());
			}
		});

		JPanel wypelniacz 						= new JPanel();

		JPanel valuesPanel 						= new JPanel();
		valuesPanel.setLayout(new GridBagLayout());

		int y = 0;

		valuesPanel.add(new JLabel(StrAccessor.getString("EmployeeListPanel.label.login")), 	new GridBagConstraints(0, y, 1, 1, 0, 	0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0)); //$NON-NLS-1$
		valuesPanel.add(login, 					new GridBagConstraints(1, y++, 1, 1, 1.0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		valuesPanel.add(new JLabel(StrAccessor.getString("EmployeeListPanel.label.password")), 	new GridBagConstraints(0, y, 1, 1, 0, 	0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0)); //$NON-NLS-1$
		valuesPanel.add(password, 				new GridBagConstraints(1, y++, 1, 1, 1.0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		valuesPanel.add(new JLabel(StrAccessor.getString("EmployeeListPanel.label.confirmPassword")), new GridBagConstraints(0, y, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0)); //$NON-NLS-1$
		valuesPanel.add(confirmPassword, 		new GridBagConstraints(1, y++, 1, 1, 1.0, 0, 	GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		valuesPanel.add(new JLabel(StrAccessor.getString("EmployeeListPanel.label.name")), 	new GridBagConstraints(0, y, 1, 1, 0, 	0, 		GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0)); //$NON-NLS-1$
		valuesPanel.add(firstName, 				new GridBagConstraints(1, y++, 1, 1, 1.0, 0, 	GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		valuesPanel.add(new JLabel(StrAccessor.getString("EmployeeListPanel.label.surname")), new GridBagConstraints(0, y, 1, 1, 0, 	0, 		GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0)); //$NON-NLS-1$
		valuesPanel.add(lastName, 				new GridBagConstraints(1, y++, 1, 1, 1.0, 0, 	GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		valuesPanel.add(new JLabel(StrAccessor.getString("EmployeeListPanel.label.telephone")), 	new GridBagConstraints(0, y, 1, 1, 0, 	0, 		GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0)); //$NON-NLS-1$
		valuesPanel.add(telefon, 				new GridBagConstraints(1, y++, 1, 1, 1.0, 0, 	GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		valuesPanel.add(new JLabel(StrAccessor.getString("EmployeeListPanel.label.fax")), 		new GridBagConstraints(0, y, 1, 1, 0, 	0, 		GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0)); //$NON-NLS-1$
		valuesPanel.add(fax, 					new GridBagConstraints(1, y++, 1, 1, 1.0, 0, 	GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		valuesPanel.add(new JLabel(StrAccessor.getString("EmployeeListPanel.lable.email")),	new GridBagConstraints(0, y, 1, 1, 0, 	0, 		GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0)); //$NON-NLS-1$
		valuesPanel.add(email, 					new GridBagConstraints(1, y++, 1, 1, 1.0, 0, 	GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		valuesPanel.add(wypelniacz, 			new GridBagConstraints(0, y, 1, 1, 0.0, 1.0, 	GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		JPanel tablePanel = new JPanel();

		tablePanel.setLayout(new GridBagLayout());
		tablePanel.add(addNewEmployee, 			new GridBagConstraints(0, 0, 1, 1, 0, 	0, 		GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		tablePanel.add(removeEmployee, 			new GridBagConstraints(1, 0, 1, 1, 0, 	0, 		GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		tablePanel.add(wypelniacz, 				new GridBagConstraints(2, 0, 1, 1, 1.0, 0, 		GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		tablePanel.add(employeeScroll, 			new GridBagConstraints(0, 1, 2, 1, 1.0, 1.0, 	GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		this.add(tablePanel, 					new GridBagConstraints(0, 0, 2, 1, 0.3, 1.0, 	GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		this.add(valuesPanel, 					new GridBagConstraints(3, 0, 1, 1, 0.7, 0, 		GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

	}

	public boolean saveEmployees() {

		List<Map<String, String>> employees = new ArrayList<Map<String, String>>();
		for (int i = 0; i < model.getRowCount(); i++) {
			VCard vcard = (VCard) model.getValueAt(i, 0);

			Map<String, String> values = new HashMap<String, String>();
			values.put("nickName", vcard.getNickName()); //$NON-NLS-1$
			values.put("password", (String) vcard.getField("password")); //$NON-NLS-1$ //$NON-NLS-2$
			values.put("firstName", vcard.getFirstName()); //$NON-NLS-1$
			values.put("lastName", vcard.getLastName()); //$NON-NLS-1$
			values.put("employeeOf", SparkManager.getUserManager().getNickname()); //$NON-NLS-1$
			values.put("email", vcard.getEmailWork()); //$NON-NLS-1$
			values.put("phone", vcard.getPhoneWork("work")); //$NON-NLS-1$ //$NON-NLS-2$
			values.put("fax", (String) vcard.getField("fax")); //$NON-NLS-1$ //$NON-NLS-2$

			employees.add(values);

		}

		String result = CargoDataManager.getInstance().saveOrUpdateUsers(employees);

		if (result == null) {
			return true;
		}

		JOptionPane.showMessageDialog(null, result, result, JOptionPane.ERROR_MESSAGE);

		return false;
	}

	public boolean isAllowEditing() {
		return allowEditing;
	}
	
	public void allowEditing(boolean allowEditing) {
		this.allowEditing = allowEditing;
	}
}
