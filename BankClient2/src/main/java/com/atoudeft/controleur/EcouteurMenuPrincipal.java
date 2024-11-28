package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.vue.PanneauConfigServeur;
import com.programmes.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2024-11-01
 */
public class EcouteurMenuPrincipal implements ActionListener {
    private Client client;
    private JFrame fenetre;

    public EcouteurMenuPrincipal(Client client, JFrame fenetre) {
        this.client = client;
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        String action;
        String alias;
        int res;

        if (source instanceof JMenuItem) {
            action = ((JMenuItem)source).getActionCommand();
            switch (action) {
                case "CONNECTER":
                    if (!client.isConnecte()) {
                        if (!client.connecter()) {
                            JOptionPane.showMessageDialog(fenetre, "Le serveur ne répond pas");
                            break;
                        }
                        else {
                            ((MainFrame)fenetre).toggleBoutonsConnection();
                        }
                    }
                    break;
                case "DECONNECTER":
                    if (!client.isConnecte())
                        break;
                    res = JOptionPane.showConfirmDialog(fenetre, "Vous allez vous déconnecter",
                            "Confirmation Déconnecter",
                            JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                    if (res == JOptionPane.OK_OPTION){
                        client.deconnecter();
                        ((MainFrame)fenetre).toggleBoutonsConnection();
                    }
                    break;
                case "CONFIGURER":
                    String adrServeur = null;
                    int numPort = -1;
                    res = -1;

                    boolean portOk = false;
                    while(!portOk) {
                        try {
                            PanneauConfigServeur configPanel = new PanneauConfigServeur(client.getAdrServeur(), client.getPortServeur());
                            res = JOptionPane.showConfirmDialog(fenetre, configPanel,
                                    "Configuration Serveur",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                            adrServeur = configPanel.getAdresseServeur();
                            numPort = Integer.valueOf(configPanel.getPortServeur());

                            portOk = true;
                        } catch (NumberFormatException e) {
                            portOk = false;
                        }
                    }

                    if (res == JOptionPane.OK_OPTION){
                        client.setAdrServeur(adrServeur);
                        client.setPortServeur(numPort);
                    }

                    break;
                case "QUITTER":
                    if (client.isConnecte()) {
                        res = JOptionPane.showConfirmDialog(fenetre, "Vous allez vous déconnecter",
                                "Confirmation Quitter",
                                JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                        if (res == JOptionPane.OK_OPTION){
                            client.deconnecter();
                            System.exit(0);
                        }
                    }
                    else
                        System.exit(0);
                    break;
            }
        }
    }
}