package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.commun.net.Connexion;
import com.atoudeft.vue.PanneauAction;
import com.atoudeft.vue.PanneauConfigServeur;
import com.programmes.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurOperationsCompte implements ActionListener {
    private Client client;
    private PanneauAction panneauAction;

    public EcouteurOperationsCompte(Client client, PanneauAction panneauAction) {
        this.client = client;
        this.panneauAction = panneauAction;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source instanceof JButton) {
            String action = ((JButton)source).getActionCommand();
            switch (action) {
                case "EPARGNE", "HIST" ->{
                    client.envoyer(action);
                }

                default -> {

                    panneauAction.mettreAJour(action);
                    panneauAction.setVisible(true);
                    int res = JOptionPane.showConfirmDialog(null, panneauAction,
                            (action.charAt(0) + action.substring(1).toLowerCase()),
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (res==JOptionPane.OK_OPTION) {

                        switch (action) {
                            case "DEPOT" -> client.envoyer("DEPOT " + panneauAction.getTxtMontant());

                            case "RETRAIT" -> client.envoyer("RETRAIT " + panneauAction.getTxtMontant());

                            case "FACTURE" -> client.envoyer("FACTURE " + panneauAction.getTxtMontant() + " "
                                    + panneauAction.getTxtNumFacture() + " " + panneauAction.getTxtDescription());

                            case "TRANSFER" -> client.envoyer("TRANSFER " + panneauAction.getTxtMontant() + " "
                                    + panneauAction.getTxtCompteDestinataire());
                        }
                    }
                    panneauAction.effacer();
                    panneauAction.setVisible(false);

                }
            }
        }
    }
}
//WJG86B