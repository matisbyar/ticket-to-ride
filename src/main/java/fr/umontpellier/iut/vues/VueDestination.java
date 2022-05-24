package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IDestination;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Cette classe représente la vue d'une carte Destination.
 *
 * On y définit le listener à exécuter lorsque cette carte a été choisie par l'utilisateur
 */
public class VueDestination extends Pane {

    private IDestination destination;
    private Label nom;

    public VueDestination(IDestination destination) {
        this.destination = destination;
        nom = new Label(destination.getNom());
        this.getChildren().add(nom);
    }

    public IDestination getDestination() {
        return destination;
    }

}
