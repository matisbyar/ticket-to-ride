package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.IJoueur;
import fr.umontpellier.iut.vues.VuesElementsJoueur.VueAutresJoueurs;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Region;
import javafx.util.Duration;

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
    private VuePanneauDeControles panneauBas;

    private ChangeListener<IJoueur> joueurListener;

    public VueDuJeu(IJeu jeu) {
        this.jeu = jeu;

        plateau = new VuePlateau(this);
        autresJoueurs = new VueAutresJoueurs(jeu, jeu.getJoueurs());
        panneauBas = new VuePanneauDeControles(jeu);

        styliser();

        // Attribution des positions
        this.setCenter(plateau);
        this.setRight(autresJoueurs);
        this.setBottom(panneauBas);
        this.setLeft(new VueJoueurCourant(jeu));

        debug(0);

        this.setStyle("-fx-background-color: #C8D1D6");
        plateau.setPadding(new Insets(30.0));
        plateau.setAlignment(Pos.CENTER);
        //panneauBas.setPadding(new Insets(0, 20, 0, 0));

        this.setMinSize(600, 600);
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

    private void styliser() {
        // Création de la VueChoix
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(25);
        panneauBas.getColumnConstraints().add(column);
        column = new ColumnConstraints();
        column.setPercentWidth(100);
        panneauBas.getColumnConstraints().add(column);
        column = new ColumnConstraints();
        column.setPercentWidth(25);
        panneauBas.getColumnConstraints().add(column);

        panneauBas.setPrefSize(this.getWidth(), this.getHeight()); // Default width and height
        panneauBas.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
    }

    public void masquer() {
        this.getLeft().setVisible(false);
        this.getLeft().setManaged(false);
        this.getRight().setVisible(false);
        this.getRight().setManaged(false);
    }

    public void afficher() {
        this.getLeft().setVisible(true);
        this.getLeft().setManaged(true);
        this.getRight().setVisible(true);
        this.getRight().setManaged(true);
    }

    public String getCouleurCourante(IJoueur joueur) {
        return switch (joueur.getCouleur()) {
            case JAUNE -> "yellow";
            case ROUGE -> "red";
            case BLEU -> "blue";
            case VERT -> "green";
            case ROSE -> "pink";
        };
    }
}