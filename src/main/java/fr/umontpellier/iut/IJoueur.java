package fr.umontpellier.iut;

import fr.umontpellier.iut.rails.Destinations;
import fr.umontpellier.iut.rails.Destination;
import javafx.collections.ObservableList;

import java.util.List;

public interface IJoueur {

    ObservableList<Destinations> cartesWagonProperty();
    ObservableList<Destination> destinationsProperty();

    public static enum Couleur {
        JAUNE, ROUGE, BLEU, VERT, ROSE;
    }

    List<Destinations> getCartesWagon();
    List<Destination> getDestinations();
    int getNbWagons();
    String getNom();
    Couleur getCouleur();
    int getNbGares();
    int getScore();
}