package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Cette classe correspond à la fenêtre principale de l'application.
 *
 * Elle est initialisée avec une référence sur la partie en cours (Jeu).
 *
 * On y définit les bindings sur les éléments internes qui peuvent changer
 * (le joueur courant, les 5 cartes Wagons visibles, les destinations lors de l'étape d'initialisation de la partie, ...)
 * ainsi que les listeners à exécuter lorsque ces éléments changent
 */
public class VueDuJeu extends Pane {

    private IJeu jeu;
    private BorderPane borderPane;
    private VBox vBoxgauche;
    private VuePlateau plateau;
    private VueAutresJoueurs autresJoueurs;
    private VueRegles regles;

    public VueDuJeu(IJeu jeu) {
        this.jeu = jeu;
        plateau = new VuePlateau();
        autresJoueurs = new VueAutresJoueurs(jeu.getJoueurs());
        regles = new VueRegles();

        borderPane = new BorderPane();
        borderPane.setCenter(plateau);
        borderPane.setRight(autresJoueurs);

        /*
        for (int i = 0; i < jeu.getJoueurs().size(); i++) {

            borderPane.setLeft()
        }
        */

        vBoxgauche = new VBox();
        vBoxgauche.getChildren().add(new VueJoueurCourant(jeu.getJoueurs().get(0)));
        vBoxgauche.getChildren().add(new VueCartesWagonJoueur(jeu.getJoueurs().get(0).getCartesWagon()));


        borderPane.setLeft(vBoxgauche);

        getChildren().add(borderPane);
    }

    public IJeu getJeu() {
        return jeu;
    }

    public void creerBindings() {

    }
}