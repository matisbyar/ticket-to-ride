package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.ICouleurWagon;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.lang.invoke.StringConcatFactory;
import java.util.Locale;

/**
 * Cette classe représente la vue d'une carte Wagon.
 *
 * On y définit le listener à exécuter lorsque cette carte a été choisie par l'utilisateur
 */
public class VueCarteWagon extends Button {

    private ICouleurWagon couleurWagon;

    public VueCarteWagon(ICouleurWagon couleurWagon) {
        this.couleurWagon = couleurWagon;
        ImageView image = new ImageView(new Image("images/cartesWagons/carte-wagon-".concat(couleurWagon.toString().toUpperCase(Locale.ROOT).concat(".png"))));
        image.setFitWidth(124);
        image.setFitHeight(80);
        this.setGraphic(image);
        this.setId(couleurWagon.toString());

        this.setOnAction(actionEvent -> {
            System.out.println(couleurWagon + " a été choisi.");
            ((VueDuJeu) getScene().getRoot()).getJeu().uneCarteWagonAEteChoisie(couleurWagon);

        });
    }

    public VueCarteWagon(String couleur) {
        this.setText(couleur);
        this.setId(couleur);

        this.setOnAction(actionEvent -> {
            System.out.println(couleurWagon + " a été choisi.");
            ((VueDuJeu) getScene().getRoot()).getJeu().uneCarteWagonAEteChoisie(couleurWagon);

        });
    }

    public ICouleurWagon getCouleurWagon() {
        return couleurWagon;
    }

}
