package fr.umontpellier.iut.vues.VuesJoueurCourant;

import fr.umontpellier.iut.IDestination;
import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.IJoueur;
import fr.umontpellier.iut.rails.CouleurWagon;
import fr.umontpellier.iut.vues.VuesElementsJoueur.VueCarteWagonJoueur;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Cette classe présente les éléments appartenant au joueur courant.
 *
 * On y définit les bindings sur le joueur courant, ainsi que le listener à exécuter lorsque ce joueur change
 */
public class VueJoueurCourant extends VBox {

    private IJeu jeu;
    private ImageView avatar;
    private Image image;
    private Label nom, score, wagons, gares, statut;
    public ChangeListener<IJoueur> changementJoueur;

    private VueCartesJoueur vueCartesJoueur;

    public VueJoueurCourant(IJeu jeu) {
        this.jeu = jeu;
        this.getStylesheets().add("/css/stylePanneau.css");
        this.getStyleClass().add("panneau");

        avatar = new ImageView();
        nom = new Label();
        score = new Label();
        wagons = new Label();
        gares = new Label();
        statut = new Label("À vous de jouer !");
        vueCartesJoueur = new VueCartesJoueur(jeu);

        styliser();

        creerBindings();

        this.getChildren().addAll(avatar, nom, score, wagons, gares, statut);
    }

    public void creerBindings() {
        changementJoueur = new ChangeListener<IJoueur>() {
            @Override
            public void changed(ObservableValue<? extends IJoueur> observableValue, IJoueur ancienJoueur, IJoueur nouveauJoueur) {
                Platform.runLater(() -> {
                    nom.setText(nouveauJoueur.getNom());
                    score.setText("Score : " + nouveauJoueur.getScore());
                    wagons.setText("Wagons restants : " + nouveauJoueur.getNbWagons());
                    gares.setText("Gares : " + nouveauJoueur.getNbGares());

                    image = new Image("images/avatars/avatar-".concat(nouveauJoueur.getCouleur().toString().toUpperCase(Locale.ROOT).concat(".png")));
                    avatar.setImage(image);
                });
            }
        };
        jeu.joueurCourantProperty().addListener(changementJoueur);
    }

    private void styliser() {
        // Alignement
        this.setAlignment(Pos.TOP_CENTER);

        // Padding
        this.setPadding(new Insets(20));
        vueCartesJoueur.setPadding(new Insets(20));

        // Relatif à l'avatar
        avatar.setFitWidth(140);
        avatar.setFitHeight(110);
        avatar.setPreserveRatio(true);

        // Font size
        nom.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        score.setFont(Font.font("Courier New", 13));
        wagons.setFont(Font.font("Courier New", 13));
        gares.setFont(Font.font("Courier New", 13));
    }
}