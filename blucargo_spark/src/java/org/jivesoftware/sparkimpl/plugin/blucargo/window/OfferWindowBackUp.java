package org.jivesoftware.sparkimpl.plugin.blucargo.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jivesoftware.resource.SparkRes;
import org.jivesoftware.spark.SparkManager;
import org.jivesoftware.spark.component.RolloverButton;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.BackgroundPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visual.RoundedPanel;

import com.blucargo.model.CargoOffer;

public class OfferWindowBackUp extends JFrame {

    Color color = new Color(242, 242, 242, 255);
    JPanel panel0 = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel1a = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new RoundedPanel(color);
    JPanel panel5 = new JPanel();
    JPanel panel6 = new JPanel();
    JPanel panel7 = new JPanel();
    JPanel panel8 = new JPanel();
    JPanel panel9 = new JPanel();
    JPanel panel10 = new JPanel();
    JPanel panel11 = new JPanel();
    JPanel panel12 = new JPanel();
    OfferWindowBackUp window = this;

    public OfferWindowBackUp() {

//        final EntityManager em = CargoConnectionManager.getInstance().getEntityManager();

        panel1.setBackground(null);
        panel1a.setBackground(null);
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
        panel0.setBackground(Color.WHITE);

        ImageIcon blueTopImage = SparkRes.getImageIcon(SparkRes.CARGO_NIEBIESKI_PASEK);
        ImageIcon greyGradientImage = SparkRes.getImageIcon(SparkRes.CARGO_PASEK_GORA);
        ImageIcon dolnyPasek = SparkRes.getImageIcon(SparkRes.CARGO_DOLNY_PASEK);
        ImageIcon logoIcon = SparkRes.getImageIcon(SparkRes.CARGO_LOGO_ICO);

        panel1 = new BackgroundPanel();
        ((BackgroundPanel) panel1).setBackgroundImage(blueTopImage);
        panel1.setLayout(new GridBagLayout());
        panel1.setLayout(new FlowLayout(FlowLayout.LEADING));
        panel1.setPreferredSize(new Dimension(10, 60));
        panel1.setMaximumSize(new Dimension(600, 60));
        panel1.add(new JLabel(logoIcon));

        panel1a = new BackgroundPanel();
        ((BackgroundPanel) panel1a).setBackgroundImage(greyGradientImage);
        panel1a.setLayout(new GridBagLayout());
        panel1a.setPreferredSize(new Dimension(10, 28));
        panel1a.setMaximumSize(new Dimension(600, 28));

        panel3 = new BackgroundPanel();
        ((BackgroundPanel) panel3).setBackgroundImage(dolnyPasek);
        panel3.setLayout(new GridBagLayout());
        panel3.setPreferredSize(new Dimension(10, 50));
        panel3.setMaximumSize(new Dimension(600, 50));

        // MAIN WINDOW
        panel0.add(panel1, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        panel0.add(panel1a, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        panel0.add(panel2, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        panel0.add(panel3, new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

        panel2.setLayout(new GridBagLayout());
        panel2.add(panel4, new GridBagConstraints(0, 0, 2, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 10, 5, 5), 0, 0));
        panel2.add(panel5, new GridBagConstraints(3, 0, 1, 3, 0.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        panel2.add(panel6, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5, 10, 5, 5), 0, 0));
        panel2.add(panel7, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        panel2.add(panel8, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5, 10, 5, 5), 0, 0));
        panel2.add(panel9, new GridBagConstraints(1, 2, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

        //stad zaczynamy
        
        
        final JRadioButton vehicleRadio = new JRadioButton("Zg\u0142o\u015b pojazd");
        vehicleRadio.setBackground(null);

        final JRadioButton cargoRadio = new JRadioButton("Zg\u0142o\u015b \u0142adunek");
        cargoRadio.setBackground(null);

        final ButtonGroup group = new ButtonGroup();
        group.add(vehicleRadio);
        group.add(cargoRadio);

        ImageIcon dodajOferte = SparkRes.getImageIcon(SparkRes.CARGO_DODAJ_OFERTE);
        RolloverButton dodajButton = new RolloverButton(SparkRes.getImageIcon(SparkRes.CARGO_DODAJ_OFERTE));

        panel10.setLayout(new BoxLayout(panel10, BoxLayout.Y_AXIS));
        panel10.add(vehicleRadio);
        panel10.add(cargoRadio);

        panel12.add(dodajButton);

        panel5.setLayout(new GridBagLayout());
        panel5.add(panel10, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        panel5.add(panel11, new GridBagConstraints(0, 1, 1, 1, 0.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        panel5.add(panel12, new GridBagConstraints(0, 5, 1, 1, 0.0, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

        JLabel freeFromLabel = new JLabel("Wolny od:");
        JTextField freeFromText = new JTextField(5);
        JLabel freeToLabel = new JLabel("Wolny do:");
        JTextField freeToText = new JTextField(5);
        JLabel offerValidLabel = new JLabel("Oferta wa\u017cna do:");
        JTextField offerValidText = new JTextField(5);

        panel4.setMaximumSize(new Dimension(600, 50));
        panel4.add(freeFromLabel, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        panel4.add(freeFromText, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        panel4.add(freeToLabel, new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        panel4.add(freeToText, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        panel4.add(offerValidLabel, new GridBagConstraints(4, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        panel4.add(offerValidText, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        JLabel fromLabel = new JLabel("Sk\u0105d");
        JPanel panel6a = new RoundedPanel(color);

        JLabel countryLabel = new JLabel("Kraj");
        final JTextField countryText = new JTextField(5);
        JLabel cityLabel = new JLabel("Miejscowo\u015b\u0107");
        final JTextField cityText = new JTextField(5);
        JLabel poLabel = new JLabel("Kod");
        final JTextField poText = new JTextField(5);
        JLabel hourLabel = new JLabel("Godzina");
        JTextField hourText = new JTextField(5);

        panel6a.setLayout(new GridBagLayout());
        panel6a.add(countryLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0));
        panel6a.add(countryText, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0));
        panel6a.add(cityLabel, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 10, 0, 0), 0, 0));
        panel6a.add(cityText, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 10), 0, 0));
        panel6a.add(poLabel, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 10, 0, 0), 0, 0));
        panel6a.add(poText, new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 10), 0, 0));
        panel6a.add(hourLabel, new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 10, 0, 0), 0, 0));
        panel6a.add(hourText, new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 10), 0, 0));

        panel6.setLayout(new GridBagLayout());
        panel6.add(fromLabel, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 10, 5, 0), 0, 0));
        panel6.add(panel6a, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        JLabel toLabel = new JLabel("Dok\u0105d");
        JPanel panel7a = new RoundedPanel(color);

        JLabel country2Label = new JLabel("Kraj");
        final JTextField country2Text = new JTextField(5);
        JLabel city2Label = new JLabel("Miejscowo\u015b\u0107");
        JTextField city2Text = new JTextField(5);
        JLabel po2Label = new JLabel("Kod");
        final JTextField po2Text = new JTextField(5);

        panel7a.setLayout(new GridBagLayout());
        panel7a.add(country2Label, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0));
        panel7a.add(country2Text, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0));
        panel7a.add(city2Label, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 10, 0, 0), 0, 0));
        panel7a.add(city2Text, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 10), 0, 0));
        panel7a.add(po2Label, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 10, 0, 0), 0, 0));
        panel7a.add(po2Text, new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 10), 0, 0));

        panel7.setLayout(new GridBagLayout());
        panel7.add(toLabel, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 10, 5, 0), 0, 0));
        panel7.add(panel7a, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        JLabel additionalLabel = new JLabel("Informacje dodatkowe:");
        JPanel panel8a = new RoundedPanel(color);

        JLabel priceLabel = new JLabel("Cena frachtu");
        final JTextField priceText = new JTextField(5);
        JLabel weightLabel = new JLabel("Ci\u0119\u017car");
        final JTextField weightText = new JTextField(5);
        JLabel lengthLabel = new JLabel("D\u0142ugo\u015b\u0107");
        final JTextField lengthText = new JTextField(5);
        JLabel bodyLabel = new JLabel("Nadwozie");
        final JTextField bodyText = new JTextField(5);

        panel8a.setLayout(new GridBagLayout());
        panel8a.add(priceLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0));
        panel8a.add(priceText, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0));
        panel8a.add(weightLabel, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 10, 0, 0), 0, 0));
        panel8a.add(weightText, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 10), 0, 0));
        panel8a.add(lengthLabel, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 10, 0, 0), 0, 0));
        panel8a.add(lengthText, new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 10), 0, 0));
        panel8a.add(bodyLabel, new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 10, 0, 0), 0, 0));
        panel8a.add(bodyText, new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 10), 0, 0));

        panel8.setLayout(new GridBagLayout());
        panel8.add(additionalLabel, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 10, 5, 0), 0, 0));
        panel8.add(panel8a, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        JLabel descriptionLabel = new JLabel("Opis dodatkowy:");
        JPanel panel9a = new RoundedPanel(color);

        final JTextArea textArea = new JTextArea();
        textArea.setRows(6);

        panel9a.setLayout(new GridBagLayout());
        panel9a.add(textArea, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
        
        panel9.setLayout(new GridBagLayout());
        panel9.add(descriptionLabel, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 10, 5, 0), 0, 0));
        panel9.add(panel9a, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        dodajButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (cargoRadio.isSelected()) {
                    CargoOffer co = new CargoOffer();
                    co.setBody(bodyText.getText());
                    co.setCargoPrice(priceText.getText());
                    //co.setCargo_on_palette(cityText.getText());
                    //co.setCash_on_delivery(cityText.getText());
                    co.setCityFrom(cityText.getText());
                    co.setCountryFrom(countryText.getText());
                    co.setCountryTo(country2Text.getText());
//                    co.setDelivery_on_day(countryText.getText());
                    co.setDescription(textArea.getText());
//                    co.setDimension_of_palettes(countryText.getText());
//                    co.setElevator(countryText.getText());
//                    co.setHds(textArea.getText());
                    co.setCargoLength(lengthText.getText());
//                    co.setLoad(textArea.getText());
//                    co.setLoadingDate(textArea.getText());
//                    co.setNegotiable(textArea.getText());
//                    co.setNumber_of_palettes(textArea.getText());
//                    co.setOfferValid(textArea.getText());
//                    co.setOther_delivery(textArea.getText());
//                    co.setPart_load(textArea.getText());
                    co.setPostOfficeFrom(poText.getText());
                    co.setPostOfficeTo(po2Text.getText());
//                    co.setReady_to_load(textArea.getText());
//                    co.setStackable_pallettes(textArea.getText());
//                    co.getUnloadingDate(po2Text.getText());
//                    co.setUnstackable_pallettes(po2Text.getText());
                    co.setVolume(po2Text.getText());
                    co.setWeight(weightText.getText());
                    co.setOwner(SparkManager.getUserManager().getNickname());

//                    em.getTransaction().begin();
//                    em.persist(co);
//                    em.getTransaction().commit();

                    window.dispose();

                } else if (vehicleRadio.isSelected()) {

                    CargoOffer co = new CargoOffer();
                    co.setBody(bodyText.getText());
                    //co.setCargoPrice(priceText.getText());
                    //co.setCargo_on_palette(cityText.getText());
                    //co.setCash_on_delivery(cityText.getText());
                    co.setCityFrom(cityText.getText());
                    co.setCountryFrom(countryText.getText());
                    co.setCountryTo(country2Text.getText());
//                    co.setDelivery_on_day(countryText.getText());
                    co.setDescription(textArea.getText());
//                    co.setDimension_of_palettes(countryText.getText());
//                    co.setElevator(countryText.getText());
//                    co.setHds(textArea.getText());
                    co.setCargoLength(lengthText.getText());
//                    co.setLoad(textArea.getText());
//                    co.setLoadingDate(textArea.getText());
//                    co.setNegotiable(textArea.getText());
//                    co.setNumber_of_palettes(textArea.getText());
//                    co.setOfferValid(textArea.getText());
//                    co.setOther_delivery(textArea.getText());
//                    co.setPart_load(textArea.getText());
                    co.setPostOfficeFrom(poText.getText());
                    co.setPostOfficeTo(po2Text.getText());
//                    co.setReady_to_load(textArea.getText());
//                    co.setStackable_pallettes(textArea.getText());
//                    co.getUnloadingDate(po2Text.getText());
//                    co.setUnstackable_pallettes(po2Text.getText());
                    co.setVolume(po2Text.getText());
                    co.setWeight(weightText.getText());
                    co.setOwner(SparkManager.getSessionManager().getJID());

//                    em.getTransaction().begin();
//                    em.persist(co);
//                    em.getTransaction().commit();

                    window.dispose();

                }
            }
        });
        pack();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new OfferWindowBackUp().setVisible(true);
            }
        });
    }
}
