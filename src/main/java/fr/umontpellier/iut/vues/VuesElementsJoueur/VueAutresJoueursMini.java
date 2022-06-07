package fr.umontpellier.iut.vues.VuesElementsJoueur;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.IJoueur;
import fr.umontpellier.iut.vues.VueDuJeu;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Locale;

public class VueAutresJoueursMini extends VBox {

    private IJeu jeu;
    private IJoueur joueur;
    private ImageView avatar;
    private Label nom, score, wagons, vousJouez;

    private ChangeListener<IJoueur> estJoueurCourantListener;

    public VueAutresJoueursMini(IJeu jeu, IJoueur j) {
        this.jeu = jeu;
        this.joueur = j;

        this.setSpacing(5.0);

        avatar = new ImageView(new Image("images/avatars/avatar-".concat(j.getCouleur().toString().toUpperCase(Locale.ROOT).concat(".png"))));
        avatar.setFitWidth(140);
        avatar.setFitHeight(110);
        avatar.setPreserveRatio(true);

        nom = new Label(j.getNom());
        score = new Label("Score : " + j.getScore());
        wagons = new Label("Wagons : " + j.getNbWagons());
        vousJouez = new Label("Est en train de jouer");

        nom.setStyle("-fx-font-size: 16");
        score.setStyle("-fx-font-family: Courier");
        vousJouez.setStyle("font-weight: 30px");
        this.setStyle("-fx-background-radius: 20px");
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(5));

        this.getChildren().addAll(avatar, nom, score, wagons, vousJouez);

        creerBindings();
    }

    public void miseAJourInfos() {
        score.setText("Score : " + joueur.getScore());
        wagons.setText("Wagons : " + joueur.getNbWagons());
    }

    public void creerBindings() {
        estJoueurCourantListener = new ChangeListener<IJoueur>() {
            @Override
            public void changed(ObservableValue<? extends IJoueur> observableValue, IJoueur ancienJoueur, IJoueur nouveauJoueur) {
                Platform.runLater(() -> {
                    if (joueur.equals(nouveauJoueur)) {
                        vousJouez.setVisible(true);
                        vousJouez.setManaged(true);
                        colorer();
                    } else {
                        vousJouez.setVisible(false);
                        vousJouez.setManaged(false);
                        decolorer();
                    }
                });
            }
        };
        jeu.joueurCourantProperty().addListener(estJoueurCourantListener);
    }

    public void colorer() {
        this.setStyle(getCouleurString());
    }

    public void decolorer() {
        this.setStyle("-fx-background-color: transparent");
    }

    private String getCouleurString() {
        IJoueur.Couleur c = joueur.getCouleur();

        if (c.toString().equals("JAUNE")) return "-fx-background-color: rgba(255,255,0,0.56)";
        if (c.toString().equals("ROUGE")) return "-fx-background-color: rgba(255,0,0,0.48)";
        if (c.toString().equals("BLEU")) return "-fx-background-color: rgb(104,104,220)";
        if (c.toString().equals("VERT")) return "-fx-background-color: rgba(49,187,49,0.63)";
        if (c.toString().equals("ROSE")) return "-fx-background-color: rgb(223,106,236)";
        return "-fx-background-color: transparent";
    }
}
