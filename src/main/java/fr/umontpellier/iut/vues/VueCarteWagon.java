package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.ICouleurWagon;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Cette classe représente la vue d'une carte Wagon.
 *
 * On y définit le listener à exécuter lorsque cette carte a été choisie par l'utilisateur
 */
public class VueCarteWagon extends Button {

    private ICouleurWagon couleurWagon;
    private Label couleur;

    public VueCarteWagon(ICouleurWagon couleurWagon) {
        this.couleurWagon = couleurWagon;
        couleur = new Label(couleurWagon.toString());
        this.setText(couleur.getText());
        this.setId(couleur.getText());

        this.setOnAction(actionEvent -> {
            System.out.println(couleur + " a été choisi.");
            ((VueDuJeu) getScene().getRoot()).getJeu().uneCarteWagonAEteChoisie(couleurWagon);

        });
    }

    public ICouleurWagon getCouleurWagon() {
        return couleurWagon;
    }

}
