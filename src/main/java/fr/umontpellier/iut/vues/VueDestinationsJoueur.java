package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.IJoueur;
import fr.umontpellier.iut.rails.Destination;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.control.ScrollPane;

public class VueDestinationsJoueur extends ScrollPane {

    private IJeu jeu;
    private IJoueur joueur;

    public VueDestinationsJoueur(IJeu jeu) {
        this.jeu = jeu;

        this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

        ChangeListener<IJoueur> joueurListener = new ChangeListener<IJoueur>() {
            @Override
            public void changed(ObservableValue<? extends IJoueur> observableValue, IJoueur iJoueur, IJoueur t1) {
                Platform.runLater(() -> {
                    joueur = t1;
                });
            }
        };
        jeu.joueurCourantProperty().addListener(joueurListener);

        creerBindings();
    }

    public void creerBindings() {
        ListChangeListener<Destination> cartesDestinationsListener = new ListChangeListener<Destination>() {
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
        //joueur.destinationsProperty().addListener(cartesDestinationsListener);
    }
}
