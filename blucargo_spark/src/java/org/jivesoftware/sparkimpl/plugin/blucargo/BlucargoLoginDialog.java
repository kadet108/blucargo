package org.jivesoftware.sparkimpl.plugin.blucargo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jivesoftware.resource.SparkRes;
import org.jivesoftware.sparkimpl.plugin.blucargo.resourcebundle.StrAccessor;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.BackgroundPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.RoundedButton;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.RoundedPanel;

public class BlucargoLoginDialog extends JFrame {

	
	private static final long serialVersionUID = 1L;

	JPanel panel0 = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new RoundedPanel();
	JPanel panel5 = new JPanel();
	JPanel panel6 = new JPanel();
	JPanel panel7 = new JPanel();
	JPanel panel8 = new JPanel();
	JPanel panel9 = new JPanel();
	JPanel panel10 = new JPanel();
	JPanel panel11 = new JPanel();
	JPanel panel12 = new JPanel();

	public BlucargoLoginDialog() {

	}

	public void invoke(JFrame parentFrame) {
		
		panel1.setBackground(null);
		panel2.setBackground(null);
		panel3.setBackground(null);
		panel4.setBackground(null);
		panel5.setBackground(null);
		panel6.setBackground(null);
		panel7.setBackground(null);
		panel8.setBackground(null);
		panel9.setBackground(null);
		panel10.setBackground(null);

		panel11.setBackground(null);
		panel12.setBackground(null);

		this.setBackground(Color.WHITE);
		this.setLayout(new GridBagLayout());
		this.add(panel0, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		panel0.setLayout(new GridBagLayout());

		ImageIcon ii = SparkRes.getImageIcon(SparkRes.CARGO_NIEBIESKI_PASEK);
		ImageIcon dolnyPasek = SparkRes.getImageIcon(SparkRes.CARGO_DOLNY_PASEK);

		panel1 = new BackgroundPanel();
		((BackgroundPanel) panel1).setBackgroundImage(ii);
		panel1.setLayout(new GridBagLayout());

		panel1.setPreferredSize(new Dimension(10, 60));
		panel1.setMaximumSize(new Dimension(600, 60));

		panel3 = new BackgroundPanel();
		((BackgroundPanel) panel3).setBackgroundImage(dolnyPasek);
		panel3.setLayout(new GridBagLayout());

		panel3.setPreferredSize(new Dimension(10, 50));
		panel3.setMaximumSize(new Dimension(600, 50));

		panel0.add(panel1, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		panel0.add(panel2, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		panel0.add(panel3, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

		RoundedButton option1 = new RoundedButton();
		option1.setText("sdfsdf");
		RoundedButton option2 = new RoundedButton();
		option2.setText("sdfsdf");
		option2.setOpaque(false);
		option2.setFocusPainted(true);
		option2.setBorderPainted(false);
		option2.setContentAreaFilled(false);

		panel2.setLayout(new GridBagLayout());
		panel2.add(option1, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		panel2.add(option2, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

		JLabel login = new JLabel(StrAccessor.getString("BlucargoLoginDialog.label.login")); //$NON-NLS-1$
		JLabel loginInfo = new JLabel(StrAccessor.getString("BlucargoLoginDialog.label.loginInfo")); //$NON-NLS-1$
		ImageIcon lIcon = SparkRes.getImageIcon(SparkRes.CARGO_ZALOGUJ_SIE_ICO);
		JLabel loginIcon = new JLabel(lIcon);

		option1.setLayout(new GridBagLayout());
		option1.add(login, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		option1.add(loginInfo, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		option1.add(loginIcon, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5, 5, 10, 5), 0, 0));

		JLabel register = new JLabel(StrAccessor.getString("BlucargoLoginDialog.label.register")); //$NON-NLS-1$
		JLabel registerInfo = new JLabel(StrAccessor.getString("BlucargoLoginDialog.label.registerInfo")); //$NON-NLS-1$
		ImageIcon rIcon = SparkRes.getImageIcon(SparkRes.CARGO_ZAREJESTRUJ_SIE_ICO);
		JLabel registerIcon = new JLabel(rIcon);

		option2.setLayout(new GridBagLayout());
		option2.add(register, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		option2.add(registerInfo, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		option2.add(registerIcon, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5, 5, 10, 5), 0, 0));

		this.pack();
		this.setVisible(true);

	}

	public static void main(String[] args) {
		BlucargoLoginDialog d = new BlucargoLoginDialog();
		d.invoke(new JFrame());
	}

}
