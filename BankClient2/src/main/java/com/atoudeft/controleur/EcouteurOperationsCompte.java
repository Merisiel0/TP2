package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.commun.net.Connexion;
import com.atoudeft.vue.PanneauConfigServeur;
import com.programmes.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurOperationsCompte implements ActionListener {
    private Client client;

    public EcouteurOperationsCompte(Client client) {
        this.client = client;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source instanceof JButton) {
            String action = ((JButton)source).getActionCommand();
            client.envoyer(action);
        }
    }
}
