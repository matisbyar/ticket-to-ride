package fr.umontpellier.iut.vues.VuesElementsJoueur;

import fr.umontpellier.iut.ICouleurWagon;
import fr.umontpellier.iut.vues.VueDuJeu;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Locale;

public class VueCarteWagonJoueur extends Button {

    private ICouleurWagon couleurWagon;

    public VueCarteWagonJoueur(ICouleurWagon couleurWagon, int qte) {
        this.couleurWagon = couleurWagon;

        this.getStylesheets().add("/css/stylePanneau.css");
        this.getStyleClass().add("carte");

        ImageView image = new ImageView(new Image("images/cartesWagons/carte-wagon-".concat(couleurWagon.toString().toUpperCase(Locale.ROOT).concat(".png"))));
        image.setFitHeight(50);
        image.setPreserveRatio(true);
        this.setGraphic(image);
        this.setId(couleurWagon.toString());

        this.setText("x" + qte);

        this.setOnAction(actionEvent -> {
            System.out.println(couleurWagon + " a été choisi par le joueur.");
            ((VueDuJeu) getScene().getRoot()).getJeu().uneCarteWagonAEteChoisie(couleurWagon);
            //this.setText("x" + (qte - 1));
        });
    }

    public ICouleurWagon getCouleurWagon() {
        return couleurWagon;
    }

}
