package fr.umontpellier.iut.vues.VuesElementsJoueur;

import fr.umontpellier.iut.IJoueur;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class VueAutresJoueursMini extends VBox {

    private IJoueur joueur;
    private Image image;
    private ImageView avatar;
    private Label nom, score, wagons;
    private ChangeListener<IJoueur> joueurListener;

    public VueAutresJoueursMini(IJoueur j) {
        this.joueur = j;

        this.setSpacing(5.0);

        //avatar = new ImageView("ressources/images/images/avatar-BLEU.png");
        nom = new Label(j.getNom());
        score = new Label("Score : " + j.getScore());
        wagons = new Label("Wagons : " + j.getNbWagons());

        nom.setStyle("-fx-font-size: 16");
        score.setStyle("-fx-font-family: Courier");

        this.getChildren().addAll(nom, score, wagons);
    }

    public void miseAJourInfos() {
        score.setText("Score : " + joueur.getScore());
        wagons.setText("Wagons : " + joueur.getNbWagons());
    }
}