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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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

        this.getStylesheets().add("/css/stylePanneau.css");
        this.getStyleClass().add("panneau");

        this.setSpacing(2.0);

        avatar = new ImageView(new Image("images/avatars/avatar-".concat(j.getCouleur().toString().toUpperCase(Locale.ROOT).concat(".png"))));
        avatar.setFitWidth(140);
        avatar.setFitHeight(110);
        avatar.setPreserveRatio(true);

        nom = new Label(j.getNom());
        score = new Label("Score : " + j.getScore());
        wagons = new Label("Wagons : " + j.getNbWagons());
        vousJouez = new Label("Est en train de jouer");

        nom.setFont(Font.font("Georgia", FontWeight.BOLD, 16));
        score.setFont(Font.font("Courier New", 13));
        wagons.setFont(Font.font("Courier New", 13));
        vousJouez.setFont(Font.font("Courier New", 13));
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
        this.setStyle("-fx-background-color: " + getCouleurCourante(joueur));
    }

    public void decolorer() {
        this.setStyle("-fx-background-color: white");
    }

    public String getCouleurCourante(IJoueur joueur) {
        return switch (joueur.getCouleur()) {
            case JAUNE -> "rgb(255,255,139)";
            case ROUGE -> "rgb(255,124,124)";
            case BLEU -> "rgb(143,143,255)";
            case VERT -> "rgb(139,222,139)";
            case ROSE -> "rgb(255,156,236)";
        };
    }
}
