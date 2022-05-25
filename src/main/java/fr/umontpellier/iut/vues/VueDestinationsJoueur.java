package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.rails.Destinations;
import fr.umontpellier.iut.rails.Destination;
import javafx.scene.control.ScrollPane;

import java.util.ArrayList;
import java.util.List;

public class VueDestinationsJoueur extends ScrollPane {
    List<VueDestination>


    public VueDestinationsJoueur(List<Destination> listeDestinations) {
        this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    }

    public ArrayList<VueDestination> getCartesDestination() {
        return cartesDestination;
    }
}
