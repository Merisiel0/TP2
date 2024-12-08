package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanneauAction extends JPanel {
    private JTextField txtMontant, txtNumFacture, txtCompteDestinataire, txtDescription;
    JPanel p1 = new JPanel(), p2 = new JPanel(), p3 = new JPanel(), p4 = new JPanel();
    String typeOperation = "";
    JPanel pTout = new JPanel(new GridLayout(3, 0));

    public PanneauAction() {

        txtMontant = new JTextField(30);
        txtNumFacture = new JTextField(30);
        txtDescription = new JTextField(30);
        txtCompteDestinataire = new JTextField(30);

        txtMontant.setBorder(BorderFactory.createTitledBorder("Montant : "));
        txtNumFacture.setBorder(BorderFactory.createTitledBorder("Numéro de facture : "));
        txtDescription.setBorder(BorderFactory.createTitledBorder("Description de la facture : "));
        txtCompteDestinataire.setBorder(BorderFactory.createTitledBorder("Compte Destinataire : "));

        p1.add(txtMontant);
        p2.add(txtNumFacture);
        p3.add(txtDescription);
        p4.add(txtCompteDestinataire);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        pTout.add(p1);
        pTout.add(p2);
        pTout.add(p3);
        pTout.add(p4);
        this.add(pTout, BorderLayout.NORTH);
        this.setBorder(BorderFactory.createLineBorder(new Color(0x00000000, true), 5));
    }

    public void mettreAJour(String type) {
        pTout.removeAll();
        txtDescription.setVisible(false);
        txtNumFacture.setVisible(false);
        txtCompteDestinataire.setVisible(false);
        typeOperation = type;
        pTout.add(p1);
        switch (type) {
            case "FACTURE" -> {
                txtNumFacture.setVisible(true);
                txtDescription.setVisible(true);
                pTout.add(p2);
                pTout.add(p3);
            }
            case "TRANSFER" -> {
                txtCompteDestinataire.setVisible(true);
                pTout.add(p4);
            }
        }
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(420, 200);
    }

    public String getTxtMontant() {
        return txtMontant.getText();
    }

    public String getTxtNumFacture() {
        return txtNumFacture.getText();
    }

    public String getTxtCompteDestinataire() {
        return txtCompteDestinataire.getText();
    }

    public String getTxtDescription() {
        return txtDescription.getText();
    }

    public void setEcouteur(ActionListener ecouteur) {
    }

    public void effacer() {
        this.txtMontant.setText("");
        this.txtDescription.setText("");
        this.txtCompteDestinataire.setText("");
        this.txtNumFacture.setText("");
    }
}