package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.IJoueur;
import fr.umontpellier.iut.rails.Destinations;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VueCartesWagonJoueur extends VBox {

    private IJeu jeu;
    private IJoueur joueur;

    public VueCartesWagonJoueur(IJeu jeu) {
        this.jeu = jeu;
        joueur = jeu.getJoueurs().get(0);

        creerBindings();
    }

    public void creerBindings() {
        ChangeListener<IJoueur> joueurListener = new ChangeListener<IJoueur>() {
            @Override
            public void changed(ObservableValue<? extends IJoueur> observableValue, IJoueur iJoueur, IJoueur t1) {
                Platform.runLater(() -> {
                    joueur = t1;
                });
            }
        };
        jeu.joueurCourantProperty().addListener(joueurListener);

        ListChangeListener<Destinations> destinationsJoueurListener = new ListChangeListener<Destinations>() {
            @Override
            public void onChanged(Change<? extends Destinations> change) {
                Platform.runLater(() -> {
                    change.next();
                    if (change.wasAdded()) {
                        getChildren().add(new Label(change.getAddedSubList().get(0).toString()));
                    }
                    if (change.wasRemoved()) {
                        for (Destinations carte : change.getRemoved()) {
                            getChildren().removeIf(vueDestination -> carte.toString().equals(vueDestination.toString()));
                        }
                    }
                });
            }
        };
        joueur.cartesWagonProperty().addListener(destinationsJoueurListener);
    }
}
