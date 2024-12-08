package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.client.GestionnaireEvenementClient2;
import com.atoudeft.commun.evenement.Evenement;
import com.atoudeft.commun.net.Connexion;
import com.programmes.MainFrame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class EcouteurListeComptes extends MouseAdapter {

    private Client client;
    public EcouteurListeComptes(Client client) {
        this.client = client;
    }

    public Client getClient(Client client){
        return client;
    }
    public String toString(){
        return client+"";
    }


    @Override
    public void mouseClicked(MouseEvent evt) {
        //double clicks
        if (evt.getClickCount() == (2)){
            //prend la position de la souris
            int position=evt.getY();

            //on a selectionné le comtpe cheque
            if (position <= 34){
                String action = "SELECT";




                client.envoyer(action+ " "+ "cheque");

                //System.out.println("tab[0]");
            //on a selectionné le compte épargne
            } else if (position <= 53) {
                String action = "SELECT";

                client.envoyer(action+ " "+ "epargne");
                //System.out.println("tab[1]");
            }

            //prendre la source clicker

            //Object source = getClient(client);
            //System.out.println("double clicked");

            //puisqu'on a double clicker dans le JLIST
            //String action = "SELECT";


            //client.envoyer(action);
            //}
        }
        //à compléter
    }
}
