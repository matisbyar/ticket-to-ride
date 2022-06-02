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
        String defaut = "C:\\Users\\Erwan\\IdeaProjects\\railsihm\\src\\main\\resources\\images\\cartesWagons\\carte-wagon-";
        Image source = new Image(defaut.concat(couleurWagon.toString().toUpperCase(Locale.ROOT).concat(".png")));
        ImageView image = new ImageView(source);
        this.setGraphic(image);
        this.setId(couleurWagon.toString());

        this.setOnAction(actionEvent -> {
            System.out.println(couleurWagon + " a été choisi.");
            ((VueDuJeu) getScene().getRoot()).getJeu().uneCarteWagonAEteChoisie(couleurWagon);

        });
    }

    public ICouleurWagon getCouleurWagon() {
        return couleurWagon;
    }

}
