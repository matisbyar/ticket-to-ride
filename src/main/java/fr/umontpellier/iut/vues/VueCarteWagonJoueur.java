package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.ICouleurWagon;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Locale;

public class VueCarteWagonJoueur extends Button {

    private ICouleurWagon couleurWagon;
    private Label quantite;

    public VueCarteWagonJoueur(ICouleurWagon couleurWagon, int qte) {
        this.couleurWagon = couleurWagon;
        ImageView image = new ImageView(new Image("images/cartesWagons/carte-wagon-".concat(couleurWagon.toString().toUpperCase(Locale.ROOT).concat(".png"))));
        image.setFitWidth(93);
        image.setFitHeight(60);
        this.setGraphic(image);
        this.setId(couleurWagon.toString());

        this.setText("x" + qte);
        quantite = new Label("x" + qte);
        quantite.setStyle("-fx-background-color: rgba(0,0,0,0.5)");

        this.getChildren().add(quantite);

        this.setOnAction(actionEvent -> {
            System.out.println(couleurWagon + " a été choisi par le joueur.");
            ((VueDuJeu) getScene().getRoot()).getJeu().uneCarteWagonAEteChoisie(couleurWagon);
        });
    }

    public ICouleurWagon getCouleurWagon() {
        return couleurWagon;
    }

}
