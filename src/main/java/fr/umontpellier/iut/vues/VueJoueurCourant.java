package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.IJoueur;
import fr.umontpellier.iut.rails.Joueur;
import javafx.application.Platform;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * Cette classe présente les éléments appartenant au joueur courant.
 *
 * On y définit les bindings sur le joueur courant, ainsi que le listener à exécuter lorsque ce joueur change
 */
public class VueJoueurCourant extends VBox {

    private IJeu jeu;
    private Label nom, score, wagons, gares, statut, mesMissions, mesCartesWagon;
    private ScrollPane panneauMissions, panneauCartesWagon;
    private ObservableList<VueDestination> destinations;
    public ChangeListener<IJoueur> changementJoueur;

    public VueJoueurCourant(Joueur j, IJeu jeu) {
        destinations = new SimpleListProperty<>();
        this.jeu = jeu;

        nom = new Label(j.getNom());
        score = new Label("Score : " + j.getScore());
        wagons = new Label("Wagons restants : " + j.getNbWagons());
        gares = new Label("Gares restantes : " + j.getNbGares());
        statut = new Label("À vous de jouer !");
        mesMissions = new Label("Mes missions (" + destinations.size() + ")");
        mesCartesWagon = new Label("Mes cartes wagon");
        panneauMissions = new ScrollPane();

        creerBindings();

        this.getChildren().addAll(nom, score, wagons, gares, statut, mesMissions, panneauMissions, mesCartesWagon, new VueCartesWagonJoueur(jeu));
    }

    public void creerBindings() {
        changementJoueur = new ChangeListener<IJoueur>() {
            @Override
            public void changed(ObservableValue<? extends IJoueur> observableValue, IJoueur iJoueur, IJoueur t1) {
                Platform.runLater(() -> {
                    nom.setText(t1.getNom());
                    score.setText("Score : " + t1.getScore());
                    wagons.setText("Wagons restants : " + t1.getNbWagons());
                    gares.setText("Gares : " + t1.getNbGares());
                });
            }
        };
        jeu.joueurCourantProperty().addListener(changementJoueur);

        /*ListChangeListener<Destination> cartesDestinationsListener = new ListChangeListener<Destination>() {
            @Override
            public void onChanged(Change<? extends Destination> change) {
                Platform.runLater(() ->{
                    change.next();
                    if (change.wasAdded()) {
                        getChildren().add(new VueDestination(change.getAddedSubList().get(0)));
                    }
                    if (change.wasRemoved()) {
                        for (Destination carte : change.getRemoved()) {
                            getChildren().removeIf(vueDestination -> carte.getNom().equals(vueDestination.getId()));
                        }
                    }
                });
            }
        };
        jeu.joueurCourantProperty().get().destinationsProperty().addListener(cartesDestinationsListener);*/
    }
}