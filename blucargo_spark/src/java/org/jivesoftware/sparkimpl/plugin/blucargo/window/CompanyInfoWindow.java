package org.jivesoftware.sparkimpl.plugin.blucargo.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jivesoftware.resource.SparkRes;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.BackgroundPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.RoundedPanel;

public class CompanyInfoWindow extends JFrame {

	private static final long serialVersionUID = 1350404045656264441L;

	Color color = new Color(242, 242, 242, 255);

	Color color2 = new Color(208, 203, 181);
	JPanel mainArea = new JPanel();
	JPanel windowHeader = new JPanel();
	JPanel panel1a = new JPanel();
	JPanel windowMain = new JPanel();
	JPanel windowFooter = new JPanel();
	JPanel windowMainFill = new RoundedPanel(color2);
	JPanel panel5 = new JPanel();
	JPanel panel6 = new JPanel();
	JPanel panel7 = new JPanel();
	JPanel panel8 = new JPanel();
	JPanel panel9 = new JPanel();
	JPanel panel10 = new JPanel();
	JPanel panel11 = new JPanel();
	JPanel panel12 = new JPanel();
	CompanyInfoWindow window = this;

	public CompanyInfoWindow() {

		initialize();

		this.setBackground(Color.WHITE);
		this.setLayout(new GridBagLayout());
		this.add(mainArea, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		mainArea.setLayout(new GridBagLayout());
		mainArea.setBackground(Color.WHITE);

		ImageIcon pasekMenuTlo = SparkRes.getImageIcon(SparkRes.CARGO_PASEK_MENU_TLO);
		ImageIcon pasekStopkaTlo = SparkRes.getImageIcon(SparkRes.CARGO_PASEK_STOPKA_TLO);
		ImageIcon logoIcon = SparkRes.getImageIcon(SparkRes.CARGO_LOGO_ICO);

		createHeader(pasekMenuTlo, logoIcon);

		createFooter(pasekStopkaTlo);

		createMain();

		// MAIN WINDOW
		mainArea.add(windowHeader, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		mainArea.add(windowMain, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		mainArea.add(windowFooter, new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

		pack();
	}

	private void initialize() {
		windowHeader.setBackground(null);
		panel1a.setBackground(null);
		windowMain.setBackground(null);
		windowFooter.setBackground(null);
		windowMainFill.setBackground(null);
	}

	private void createMain() {
		windowMain.setLayout(new GridBagLayout());
		windowMain.setBackground(new Color(130, 145, 164));
		windowMain.add(windowMainFill, new GridBagConstraints(0, 0, 2, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 10, 5, 5), 0, 0));

		windowMainFill.setLayout(new GridBagLayout());

		JLabel danePodstawowe = new JLabel("Dane podstawowe");
		JLabel pelnaNazwaFirmy = new JLabel("Pelna nazwa firmy");
		JLabel adres = new JLabel("Adres");

		JLabel miejscowosc = new JLabel("Miejscowosc");
		JLabel telFax = new JLabel("Tel,fax");
		JLabel nip = new JLabel("NIP");
		JLabel email1 = new JLabel("E-mail");
		JLabel cargoId = new JLabel("CargoID");
		JLabel naGieldzieOd = new JLabel("Na gieldzie od... ");
		JLabel firmaAutoryzowana = new JLabel("Firma autoryzowana/nieautoryzowana");
		JLabel krotkiOpisWSlowach = new JLabel("Opis w kilku slowach");

		JLabel informacjeOPracowniku = new JLabel("Informacje o pracownikach");
		JLabel imieNazwisko = new JLabel("Imi\u0119 nazwisko");
		JLabel telefon = new JLabel("Tel.");
		JLabel email2 = new JLabel("E-mail");
		JLabel cargoId2 = new JLabel("CargoID");
		JLabel statystyki = new JLabel("Statystyki");
		JLabel liczbaWiadomosci = new JLabel("Liczba wiadomo\u015bci");
		JLabel sredniaLiczbaWiadomosci = new JLabel("\u015arednia liczba wiadomo\u015bci w ci\u0105gu miesi\u0105ca");
		JLabel liczbaTranzakcji = new JLabel("Liczba tranzakcji");
		JLabel dataRejestracji = new JLabel("Data rejestracji");
		JLabel dataAutoryzacji = new JLabel("Data autoryzacji");
		JLabel ostatnieLogowanie = new JLabel("Ostatnie logowanie");

		// Det er grunnleggende data
		windowMainFill.add(danePodstawowe, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(pelnaNazwaFirmy, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(adres, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(miejscowosc, new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(telFax, new GridBagConstraints(0, 4, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(nip, new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(email1, new GridBagConstraints(0, 6, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(cargoId, new GridBagConstraints(0, 7, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(naGieldzieOd, new GridBagConstraints(0, 8, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(naGieldzieOd, new GridBagConstraints(0, 9, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(firmaAutoryzowana, new GridBagConstraints(0, 10, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(krotkiOpisWSlowach, new GridBagConstraints(0, 11, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		// Det er informasjon om ansatt
		windowMainFill.add(informacjeOPracowniku, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(imieNazwisko, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(telefon, new GridBagConstraints(1, 2, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(email2, new GridBagConstraints(1, 3, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(cargoId2, new GridBagConstraints(1, 4, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		windowMainFill.add(statystyki, new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(liczbaWiadomosci, new GridBagConstraints(2, 1, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(sredniaLiczbaWiadomosci, new GridBagConstraints(2, 2, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(liczbaTranzakcji, new GridBagConstraints(2, 3, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(dataRejestracji, new GridBagConstraints(2, 4, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(dataAutoryzacji, new GridBagConstraints(2, 5, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		windowMainFill.add(ostatnieLogowanie, new GridBagConstraints(2, 6, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

	}

	private void createFooter(ImageIcon pasekStopkaTlo) {
		windowFooter = new BackgroundPanel();
		((BackgroundPanel) windowFooter).setBackgroundImage(pasekStopkaTlo);
		windowFooter.setLayout(new GridBagLayout());
		windowFooter.setPreferredSize(new Dimension(10, 45));
		windowFooter.setMaximumSize(new Dimension(600, 45));
	}

	private void createHeader(ImageIcon pasekMenuTlo, ImageIcon logoIcon) {
		windowHeader = new BackgroundPanel();
		((BackgroundPanel) windowHeader).setBackgroundImage(pasekMenuTlo);
		windowHeader.setLayout(new GridBagLayout());
		windowHeader.setLayout(new FlowLayout(FlowLayout.LEADING));
		windowHeader.setPreferredSize(new Dimension(10, 60));
		windowHeader.setMaximumSize(new Dimension(600, 60));
		windowHeader.add(new JLabel(logoIcon));
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new CompanyInfoWindow().setVisible(true);
			}
		});
	}

}
