package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IDestination;
import fr.umontpellier.iut.IJeu;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Cette classe représente la vue d'une carte Destination.
 *
 * On y définit le listener à exécuter lorsque cette carte a été choisie par l'utilisateur
 */
public class VueDestination extends Button {

    private IDestination destination;
    private Label nom;
    private ImageView image;

    public VueDestination(IDestination destination) {
        this.destination = destination;
        //image = new ImageView(new Image(""));
        nom = new Label(destination.getNom());
        this.setText(nom.getText());
        this.setId(nom.getText());

        this.setOnAction(actionEvent -> {
            System.out.println(nom + " a été choisi.");
            ((VueDuJeu) getScene().getRoot()).getJeu().uneDestinationAEteChoisie(destination.getNom());

        });
    }

    public IDestination getDestination() {
        return destination;
    }

}
