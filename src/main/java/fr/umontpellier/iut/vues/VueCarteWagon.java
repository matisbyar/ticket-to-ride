package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.ICouleurWagon;
import fr.umontpellier.iut.rails.CouleurWagon;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Locale;

/**
 * Cette classe représente la vue d'une carte Wagon.
 *
 * On y définit le listener à exécuter lorsque cette carte a été choisie par l'utilisateur
 */
public class VueCarteWagon extends Button {

    private ICouleurWagon couleurWagon;

    public VueCarteWagon(ICouleurWagon couleurWagon) {
        this.getStylesheets().add("/css/styleGeneral.css");

        this.couleurWagon = couleurWagon;
        ImageView image = new ImageView(new Image("images/cartesWagons/carte-wagon-".concat(couleurWagon.toString().toUpperCase(Locale.ROOT).concat(".png"))));
        image.setFitHeight(75);
        image.setPreserveRatio(true);
        this.setGraphic(image);
        this.setId(couleurWagon.toString());

        this.setOnAction(actionEvent -> {
            System.out.println(couleurWagon + " a été choisi.");
            ((VueDuJeu) getScene().getRoot()).getJeu().uneCarteWagonAEteChoisie(couleurWagon);
        });
    }

    public VueCarteWagon(String selec) {
        this.couleurWagon = CouleurWagon.GRIS;
        this.getStylesheets().add("/css/styleGeneral.css");
        ImageView image = new ImageView(new Image("file:ressources/images/images/carte-wagon.png"));
        image.setFitHeight(50);
        image.setPreserveRatio(true);
        this.setGraphic(image);
        this.setId(selec);

        this.setOnAction(actionEvent -> {
            System.out.println("La pioche de cartes wagons a été choisie");
            ((VueDuJeu) getScene().getRoot()).getJeu().uneCarteWagonAEtePiochee();
        });
    }

    public ICouleurWagon getCouleurWagon() {
        return couleurWagon;
    }

}
