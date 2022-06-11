package fr.umontpellier.iut.vues.VuesJoueurCourant;

import fr.umontpellier.iut.IJeu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class VuePanneauJoueurCourant extends VBox {

    private IJeu jeu;
    private VueJoueurCourant vueJoueurCourant;
    private VueCartesJoueur vueCartesJoueur;
    private VueDestinationsJoueur vueDestinationsJoueur;

    public VuePanneauJoueurCourant(IJeu jeu) {
        this.jeu = jeu;

        vueJoueurCourant = new VueJoueurCourant(jeu);
        vueCartesJoueur = new VueCartesJoueur(jeu);
        vueDestinationsJoueur = new VueDestinationsJoueur(jeu);

        this.getChildren().addAll(vueJoueurCourant, vueDestinationsJoueur, vueCartesJoueur);
    }

    private void styliser() {

    }
}
