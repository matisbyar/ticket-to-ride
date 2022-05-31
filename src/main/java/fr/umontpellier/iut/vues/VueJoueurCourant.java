package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.IJoueur;
import fr.umontpellier.iut.rails.Destination;
import fr.umontpellier.iut.rails.Destinations;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Cette classe présente les éléments appartenant au joueur courant.
 *
 * On y définit les bindings sur le joueur courant, ainsi que le listener à exécuter lorsque ce joueur change
 */
public class VueJoueurCourant extends VBox {

    private IJeu jeu;
    private Label nom, score, wagons, gares, statut, mesMissions, mesCartesWagon;
    private VBox destinations, cartesWagons;
    public ChangeListener<IJoueur> changementJoueur;

    public VueJoueurCourant(IJeu jeu) {
        this.jeu = jeu;

        nom = new Label();
        score = new Label();
        wagons = new Label();
        gares = new Label();
        statut = new Label("À vous de jouer !");
        mesMissions = new Label();
        mesCartesWagon = new Label();
        destinations = new VBox();
        cartesWagons = new VBox();

        creerBindings();

        this.getChildren().addAll(nom, score, wagons, gares, statut, mesMissions, destinations, mesCartesWagon, cartesWagons);
    }

    public void creerBindings() {
        changementJoueur = new ChangeListener<IJoueur>() {
            @Override
            public void changed(ObservableValue<? extends IJoueur> observableValue, IJoueur ancienJoueur, IJoueur nouveauJoueur) {
                Platform.runLater(() -> {
                    nom.setText(nouveauJoueur.getNom());
                    score.setText("Score : " + nouveauJoueur.getScore());
                    wagons.setText("Wagons restants : " + nouveauJoueur.getNbWagons());
                    gares.setText("Gares : " + nouveauJoueur.getNbGares());
                    mesMissions.setText("Mes destinations (" + nouveauJoueur.getDestinations().size() + ")");
                    mesCartesWagon.setText("Mes cartes wagon (" + nouveauJoueur.getCartesWagon().size() + ")");

                    destinations.getChildren().clear();
                    for (Destination carte: nouveauJoueur.getDestinations()) {
                        destinations.getChildren().add(new Label(carte.getNom()));
                    }

                    cartesWagons.getChildren().clear();
                    for (Destinations carteWagon: nouveauJoueur.getCartesWagon()) {
                        cartesWagons.getChildren().add(new Label(carteWagon.toString()));
                    }
                });
            }
        };
        jeu.joueurCourantProperty().addListener(changementJoueur);
    }
}