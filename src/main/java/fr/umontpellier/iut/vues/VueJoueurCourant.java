package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IDestination;
import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.IJoueur;
import fr.umontpellier.iut.rails.CouleurWagon;
import fr.umontpellier.iut.vues.VuesElementsJoueur.VueCarteWagonJoueur;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

        this.setPadding(new Insets(20.0));
        nom.setStyle("-fx-font-size: 16px");
        mesMissions.setStyle("-fx-font-size: 16px");
        mesCartesWagon.setStyle("-fx-font-size: 16px");

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
                    for (IDestination carte: nouveauJoueur.getDestinations()) {
                        destinations.getChildren().add(new Label(carte.getNom()));
                    }

                    cartesWagons.getChildren().clear();
                    afficheCartes(nouveauJoueur.getCartesWagon());
                });
            }
        };
        jeu.joueurCourantProperty().addListener(changementJoueur);
    }

    public void afficheCartes(List<CouleurWagon> destinations) {
        // On récupère à l'aide de la méthode compteur(), la Collection Map associant une CouleurWagon et son nombre d'exemplaires
        Map<CouleurWagon, Integer> comptage = CouleurWagon.compteur(destinations);
        // La Set va nous permettre de faire une boucle foreach, et de récupérer individuellement la Key et la Value (cf. notes de commit Affichage cartes wagon)
        Set<Map.Entry<CouleurWagon, Integer>> occurrences = comptage.entrySet();

        for (Map.Entry<CouleurWagon, Integer> occurrence: occurrences) {
            CouleurWagon couleur = occurrence.getKey();
            int quantite = occurrence.getValue();

            if (quantite != 0 && couleur != CouleurWagon.GRIS) cartesWagons.getChildren().add(new VueCarteWagonJoueur(couleur, quantite));
        }
    }


}