package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.ICouleurWagon;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Cette classe représente la vue d'une carte Wagon.
 *
 * On y définit le listener à exécuter lorsque cette carte a été choisie par l'utilisateur
 */
public class VueCarteWagon extends Pane {

    private ICouleurWagon couleurWagon;
    private Label couleur;

    public VueCarteWagon(ICouleurWagon couleurWagon) {
        this.couleurWagon = couleurWagon;
        couleur = new Label(couleurWagon.toString());
        this.getChildren().add(couleur);
    }

    public ICouleurWagon getCouleurWagon() {
        return couleurWagon;
    }

}
