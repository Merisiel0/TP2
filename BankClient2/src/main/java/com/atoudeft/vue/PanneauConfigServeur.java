package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class PanneauConfigServeur extends JPanel {
    private JTextField txtAdrServeur, txtNumPort;
    private JLabel adrServeur, numPort;

    public PanneauConfigServeur(String adr, int port) {
        this.adrServeur = new JLabel("Adresse IP:");
        this.numPort = new JLabel("Port:");
        this.txtAdrServeur = new JTextField(adr);
        this.txtNumPort = new JTextField(String.valueOf(port));

        this.setLayout(new GridLayout(2, 2));

        this.add(adrServeur);
        this.add(txtAdrServeur);
        this.add(numPort);
        this.add(txtNumPort);
    }
    public String getAdresseServeur() {
        return txtAdrServeur.getText();
    }
    public String getPortServeur() { return txtNumPort.getText(); }
}
