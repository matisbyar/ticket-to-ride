package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.IJoueur;
import fr.umontpellier.iut.rails.Joueur;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
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
    private IJoueur joueur;
    private Label nom, score, wagons, gares, statut, mesMissions, mesCartesWagon;
    public ChangeListener<IJoueur> changementJoueur;

    public VueJoueurCourant(Joueur j, IJeu jeu) {
        this.jeu = jeu;
        joueur = jeu.getJoueurs().get(0);

        nom = new Label(j.getNom());
        score = new Label();
        wagons = new Label();
        gares = new Label();
        statut = new Label("À vous de jouer !");
        mesMissions = new Label();
        mesCartesWagon = new Label();

        creerBindings();

        this.getChildren().addAll(nom, score, wagons, gares, statut, mesMissions, new VueDestinationsJoueur(jeu), mesCartesWagon, new VueCartesWagonJoueur(jeu));
    }

    public void creerBindings() {
        changementJoueur = new ChangeListener<IJoueur>() {
            @Override
            public void changed(ObservableValue<? extends IJoueur> observableValue, IJoueur iJoueur, IJoueur t1) {
                Platform.runLater(() -> {
                    joueur = t1;
                    nom.setText(t1.getNom());
                    score.setText("Score : " + t1.getScore());
                    wagons.setText("Wagons restants : " + t1.getNbWagons());
                    gares.setText("Gares : " + t1.getNbGares());
                    System.out.println(t1.getNom() + " aussi nommé " + jeu.joueurCourantProperty().get().getNom() + " joue.");
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

        //labelWagons.bind(Bindings.concat("Mes cartes wagon (" + jeu.joueurCourantProperty().get().getCartesWagon().size() + ")"));
        Platform.runLater(() -> {
            mesCartesWagon.textProperty().bind(Bindings.concat("Mes cartes wagon (" + jeu.joueurCourantProperty().get().getCartesWagon().size() + ")"));
            mesMissions.textProperty().bind(Bindings.concat("Mes destinations (" + jeu.joueurCourantProperty().get().getDestinations().size() + ")"));
        });
    }
}