package com.atoudeft.client;

import com.atoudeft.commun.evenement.Evenement;
import com.atoudeft.commun.evenement.GestionnaireEvenement;
import com.atoudeft.commun.net.Connexion;
import com.atoudeft.vue.PanneauOperationsCompte;
import com.atoudeft.vue.PanneauPrincipal;
import com.programmes.MainFrame;

import javax.swing.*;

public class GestionnaireEvenementClient2 implements GestionnaireEvenement {
    private Client client;
    private PanneauPrincipal panneauPrincipal;


    /**
     * Construit un gestionnaire d'événements pour un client.
     *
     * @param client Client Le client pour lequel ce gestionnaire gère des événements
     */
    public GestionnaireEvenementClient2(Client client, PanneauPrincipal panneauPrincipal) {

        this.client = client;
        this.panneauPrincipal = panneauPrincipal;
        this.client.setGestionnaireEvenement(this);

    }

    //mettre a jour le texte dans le JLabel text servant a afficher l'historique



    @Override
    public void traiter(Evenement evenement) {
        Object source = evenement.getSource();
        Connexion cnx;
        String typeEvenement, arg, str;
        int i;
        String[] t;
        MainFrame fenetre;




        if (source instanceof Connexion) {
            cnx = (Connexion) source;
            typeEvenement = evenement.getType();
            switch (typeEvenement) {
                /******************* COMMANDES GÉNÉRALES *******************/
                case "END": //Le serveur demande de fermer la connexion
                    fenetre = (MainFrame)panneauPrincipal.getTopLevelAncestor();
                    fenetre.toggleBoutonsConnection();
                    client.deconnecter(); //On ferme la connexion
                    break;
                /******************* CREATION et CONNEXION *******************/
//                case "HIST": //Le serveur a renvoyé
//                    panneauPrincipal.setVisible(true);
//                    JOptionPane.showMessageDialog(null,"Panneau visible");
//                    cnx.envoyer("LIST");
//                    arg = evenement.getArgument();
//                    break;
                case "OK":
                    panneauPrincipal.setVisible(true);
                    fenetre = (MainFrame)panneauPrincipal.getTopLevelAncestor();
                    fenetre.setTitle(MainFrame.TITRE);//+" - Connecté"
                    break;
                case "NOUVEAU":
                    arg = evenement.getArgument();
                    if (arg.trim().startsWith("NO")) {
                        JOptionPane.showMessageDialog(panneauPrincipal,"Nouveau refusé");
                    }
                    else {
                        panneauPrincipal.cacherPanneauConnexion();
                        panneauPrincipal.montrerPanneauCompteClient();
                        str = arg.substring(arg.indexOf("OK")+2).trim();
                        panneauPrincipal.ajouterCompte(str);
                    }
                    break;
                case "CONNECT":
                    arg = evenement.getArgument();
                    if (arg.trim().startsWith("NO")) {
                        JOptionPane.showMessageDialog(panneauPrincipal,"Connexion refusée");
                    }
                    else {
                        panneauPrincipal.cacherPanneauConnexion();
                        panneauPrincipal.montrerPanneauCompteClient();
                        str = arg.substring(arg.indexOf("OK")+2).trim();
                        t = str.split(":");
                        for (String s:t) {
                            panneauPrincipal.ajouterCompte(s.substring(0,s.indexOf("]")+1));
                        }
                    }
                    break;
                /******************* SÉLECTION DE COMPTES *******************/
                // evenement = Commande OK/NO/argument
                case "EPARGNE" :
                    arg = evenement.getArgument();
                    t = arg.split(" ");
                    if(t.length >= 2 || t[0].equals("OK")) {
                        panneauPrincipal.ajouterCompte(t[1]);
                    }
                    else {
                        JOptionPane.showMessageDialog(panneauPrincipal,"Création d'un compte épargne échouée.",
                                "ERREUR", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "SELECT" :
                    arg = evenement.getArgument();
                    t = arg.split(" ");
                    System.out.println(arg);
                    //System.out.println(t.length);
                    if(t.length >= 2 || t[0].equals("OK")) {
                        String mtnSolde = t[2];

                        panneauPrincipal.setMontant(mtnSolde);


                        JOptionPane.showMessageDialog(panneauPrincipal,"SELECT "+arg);

                    }
                    else{
                        JOptionPane.showMessageDialog(panneauPrincipal,"Clic non valide",
                                "ERREUR", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                /******************* OPÉRATIONS BANCAIRES *******************/
                case "DEPOT" :
                    arg = evenement.getArgument();
                    JOptionPane.showMessageDialog(panneauPrincipal,"DEPOT "+arg);
                    break;
                case "RETRAIT" :
                    arg = evenement.getArgument();
                    JOptionPane.showMessageDialog(panneauPrincipal,"RETRAIT "+arg);
                    break;
                case "FACTURE" :
                    arg = evenement.getArgument();
                    JOptionPane.showMessageDialog(panneauPrincipal,"FACTURE" + arg);
                    break;
                case "TRANSFER" :
                    arg = evenement.getArgument();
                    JOptionPane.showMessageDialog(panneauPrincipal,"TRANSFER " + arg);
                    break;

                case "HIST" :
                    //System.out.println("MN");
                    //arg est l'argument qui me montre tout les dernières opérations fait par le client
                    arg = evenement.getArgument();
                    //System.out.println(arg);
                    //String phrase = "SUN Dec 08 18:21:28 EST 2024   DEPOT 4.0" +
                                    //"SUN Dec 08 18:21:43 EST 2024   DEPOT   3.0" +
                                    //"SUN Dec 03 18:22:14 EST 2024   DEPOT 430.0";
                    t = arg.split(" ");
                    //System.out.println(t[12]);



                    panneauPrincipal.MontrerHist(t);




                /******************* TRAITEMENT PAR DÉFAUT *******************/
                default:

                    System.out.println("RECU : "+evenement.getType()+" "+evenement.getArgument());
            }
        }
    }
}