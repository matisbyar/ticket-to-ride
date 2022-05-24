package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.rails.Joueur;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Cette classe présente les éléments appartenant au joueur courant.
 *
 * On y définit les bindings sur le joueur courant, ainsi que le listener à exécuter lorsque ce joueur change
 */
public class VueJoueurCourant extends VBox {

    private Label nom, score, wagons, gares, statut, missions;
    private ObservableList<VueDestination> destinations;

    public VueJoueurCourant(Joueur j) {
        destinations = new SimpleListProperty<>();

        nom = new Label(j.getNom());
        score = new Label("Score : " + j.getScore());
        wagons = new Label("Wagons restants : " + j.getNbWagons());
        gares = new Label("Gares restantes : " + j.getNbGares());
        statut = new Label("À vous de jouer !");
        missions = new Label("Mes missions (" + destinations.size() + ")");

        this.getChildren().addAll(nom, score, wagons, gares, statut, missions, new VueDestinationsJoueur(j.getDestinations()));
    }

}
