package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.rails.Destination;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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

        vBoxgauche = new VBox();
        vBoxgauche.getChildren().add(new VueJoueurCourant(jeu));

        borderPane.setLeft(vBoxgauche);
        borderPane.setBottom(new VueChoix(this.jeu));

        getChildren().add(borderPane);
    }

    public IJeu getJeu() {
        return jeu;
    }

    public void creerBindings() {

    }
}