package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.IJoueur;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;

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

        // Attribution des positions
        this.setCenter(plateau);
        this.setRight(autresJoueurs);
        this.setBottom(new VueChoix(jeu));
        this.setLeft(new VueJoueurCourant(jeu));

        debug(0);

        this.setStyle("-fx-background-color: #C8D1D6");
        plateau.setPadding(new Insets(20.0));
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

    /**
     * Outil de debug
     * @param priorite 1 pour la coloration, 2 pour l'invisibilité
     */
    public void debug(int priorite) {
        if (priorite > 0) {
            this.getLeft().setStyle("-fx-background-color: red");
            this.getRight().setStyle("-fx-background-color: green");
            this.getBottom().setStyle("-fx-background-color: blue");
            this.getCenter().setStyle("-fx-background-color: rgba(255,255,0,0.56)");
        }
        if (priorite > 1) {
            this.getLeft().setManaged(false);
            this.getLeft().setVisible(false);
            this.getRight().setManaged(false);
            this.getRight().setVisible(false);
            this.getBottom().setManaged(false);
            this.getBottom().setVisible(false);
        }
    }
}