package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.IJoueur;
import fr.umontpellier.iut.rails.Destination;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
public class VueDuJeu extends BorderPane {

    private IJeu jeu;
    private VuePlateau plateau;
    private VueAutresJoueurs autresJoueurs;
    private ChangeListener<IJoueur> joueurListener;

    public VueDuJeu(IJeu jeu) {
        this.jeu = jeu;
        plateau = new VuePlateau();
        autresJoueurs = new VueAutresJoueurs(jeu.getJoueurs());

        //this.setPrefSize(1601.0, 1097.0);

        // Attribution des positions
        this.setCenter(plateau);
        this.setLeft(new VueJoueurCourant(jeu));
        this.setRight(autresJoueurs);
        this.setBottom(new VueChoix(this.jeu));

        /*
        this.getLeft().setStyle("-fx-background-color: red");
        this.getRight().setStyle("-fx-background-color: green");
        this.getBottom().setStyle("-fx-background-color: blue");
         */

        this.setStyle("-fx-background-color: #C8D1D6");
    }

    public IJeu getJeu() {
        return jeu;
    }

    public void creerBindings() {
        // Permet d'actualiser les infos des autres joueurs à chaque tour de jeu.
        joueurListener = new ChangeListener<IJoueur>() {
            @Override
            public void changed(ObservableValue<? extends IJoueur> observableValue, IJoueur iJoueur, IJoueur t1) {
                Platform.runLater(() -> {
                    autresJoueurs.miseAJourInfosJoueurs();
                });
            }
        };
        jeu.joueurCourantProperty().addListener(joueurListener);

        plateau.creerBindings();
    }
}