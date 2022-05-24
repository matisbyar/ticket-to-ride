package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.rails.Destinations;
import fr.umontpellier.iut.rails.Destination;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class VueDestinationsJoueur extends Pane {
    public ArrayList<VueDestination> cartesDestination;


    public VueDestinationsJoueur(List<Destination> listeDestinations) {
        cartesDestination = new ArrayList<>();
        for (Destination destinations: listeDestinations) {
            cartesDestination.add(new VueDestination(destinations));
        }
        for (VueDestination vueCarte: cartesDestination) {
            this.getChildren().add(vueCarte);
        }
    }

    public ArrayList<VueDestination> getCartesDestination() {
        return cartesDestination;
    }
}
