package com.atoudeft.vue;

import com.atoudeft.client.Client;
import com.atoudeft.controleur.EcouteurConnexion;
import com.atoudeft.controleur.EcouteurListeComptes;
import com.atoudeft.controleur.EcouteurOperationsCompte;

import javax.swing.*;
import java.awt.*;

/**
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2024-11-01
 */
public class PanneauPrincipal extends JPanel {
    private Client client;
    private PanneauConnexion panneauConnexion;
    private PanneauAction panneauAction;
    private JPanel panneauCompteClient;
    private PanneauOperationsCompte panneauOperationsCompte;

    private DefaultListModel<String> numerosComptes;
    private JList<String> jlNumerosComptes;
    private JDesktopPane bureau;
    //les informations pour l'historique
    private JTextArea historique;
    private JLabel  texte;
    private JFrame  fenetre;


    public PanneauPrincipal(Client client) {
        this.client = client;




        panneauConnexion = new PanneauConnexion();
        panneauConnexion.setEcouteur(new EcouteurConnexion(client, panneauConnexion));

        panneauAction = new PanneauAction();
        EcouteurOperationsCompte ecouteurOperationsCompte = new EcouteurOperationsCompte(client, panneauAction);
        panneauAction.setEcouteur(ecouteurOperationsCompte);
        panneauOperationsCompte = new PanneauOperationsCompte();
        panneauOperationsCompte.setEcouteur(ecouteurOperationsCompte);

        panneauCompteClient = new JPanel();

        panneauCompteClient.setLayout(new BorderLayout());
        panneauCompteClient.setBackground(Color.WHITE);
        panneauOperationsCompte.setBackground(Color.WHITE);

        numerosComptes = new DefaultListModel<>();

        jlNumerosComptes = new JList<>(numerosComptes);
        jlNumerosComptes.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        jlNumerosComptes.setBorder(BorderFactory.createTitledBorder("Comptes bancaires"));
        jlNumerosComptes.setPreferredSize(new Dimension(250, 500));


        panneauCompteClient.add(panneauOperationsCompte, BorderLayout.NORTH);
        panneauCompteClient.add(jlNumerosComptes, BorderLayout.WEST);
        //Enregistrement de l'écouteur de souris:
        jlNumerosComptes.addMouseListener(new EcouteurListeComptes(client));

        //mettre un JTextArea dans la panneuConnexion il est vide au depart
        //this.historique = new JTextArea("");
        //panneauCompteClient.add(historique,BorderLayout.CENTER);
        //ne pas permettre d'écrire dans le TextArea
        //this.historique.setEditable(false);


        this.setLayout(new BorderLayout());

        this.add(panneauConnexion, BorderLayout.NORTH);
        this.add(panneauCompteClient, BorderLayout.CENTER);


        panneauCompteClient.setVisible(true);





    }
    //montrer l'historique de l'utilisateur

    public void MontrerHist(String[] t){
        String text = "Historique du compte\n";

        for (int i=0 ; i<t.length;i++){
            text = text + t[i] + "\n";
    }
        //mettre un JTextArea dans la panneuConnexion il est vide au depart
        this.fenetre = new JFrame();
        this.historique = new JTextArea(text);
        this.historique.setText(text);
        fenetre.add(historique,BorderLayout.NORTH);
        fenetre.setSize(250,700);
        //ne pas permettre d'écrire dans le TextArea
        this.historique.setEditable(false);

        fenetre.setVisible(true);



    }

    //effacer la fenetre d'historique

    //public void SuppHist(){fenetre.setVisible(false);}

    //metre a jour l'historique du client
    //public void setTexte(String texte){ this.historique.setText(texte);}


    //metre a jour le solde du client sélectionné
    public void setMontant(String mtn){
        panneauOperationsCompte.setSolde(mtn);
    }

    /**
     * Vide les éléments d'interface du panneauPrincipal.
     */

    public void vider() {
        this.numerosComptes.clear();
        this.bureau.removeAll();
    }

    public void cacherPanneauConnexion() {
        panneauConnexion.effacer();
        panneauConnexion.setVisible(false);
    }

    public void montrerPanneauConnexion() {
        panneauConnexion.setVisible(true);
    }

    public void cacherPanneauCompteClient() {
        panneauCompteClient.setVisible(false);
        this.numerosComptes.clear();
    }

    public void montrerPanneauCompteClient() {
        panneauCompteClient.setVisible(true);
    }

    /**
     * Affiche un numéro de compte dans le JList des comptes.
     *
     * @param str chaine contenant le numéros de compte
     */
    public void ajouterCompte(String str) {
        numerosComptes.addElement(str);
    }
}