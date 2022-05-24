package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.rails.Joueur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class VueAutresJoueursMini extends VBox {

    private Image image;
    private ImageView avatar;
    private Label nom, score, wagons;

    public VueAutresJoueursMini(Joueur j) {
        this.setSpacing(5.0);

        //avatar = new ImageView("ressources/images/images/avatar-BLEU.png");
        nom = new Label(j.getNom());
        score = new Label("Score : " + j.getScore());
        wagons = new Label("Wagons : " + j.getNbWagons());

        nom.setStyle("-fx-font-size: 16");
        score.setStyle("-fx-font-family: Courier");

        this.getChildren().addAll(nom, score, wagons);
    }
}
