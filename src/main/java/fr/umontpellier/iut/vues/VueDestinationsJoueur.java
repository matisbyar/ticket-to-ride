package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.IJoueur;
import fr.umontpellier.iut.rails.Destination;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VueDestinationsJoueur extends VBox {

    private IJeu jeu;
    private IJoueur joueur;

    public VueDestinationsJoueur(IJeu jeu) {
        this.jeu = jeu;
        joueur = jeu.getJoueurs().get(0);

        //this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

        creerBindings();
    }

    public void creerBindings() {
        ChangeListener<IJoueur> joueurListener = new ChangeListener<IJoueur>() {
            @Override
            public void changed(ObservableValue<? extends IJoueur> observableValue, IJoueur iJoueur, IJoueur t1) {
                Platform.runLater(() -> {
                    joueur = t1;
                    System.out.println("changement de joueur");
                });
            }
        };
        jeu.joueurCourantProperty().addListener(joueurListener);

        ListChangeListener<Destination> cartesDestinationsListener = new ListChangeListener<Destination>() {
            @Override
            public void onChanged(Change<? extends Destination> change) {
                Platform.runLater(() ->{
                    change.next();
                    if (change.wasAdded()) {
                        getChildren().add(new Label(change.getAddedSubList().get(0).toString()));
                    }
                    if (change.wasRemoved()) {
                        for (Destination carte : change.getRemoved()) {
                            getChildren().removeIf(vueDestination -> carte.getNom().equals(vueDestination.toString()));
                        }
                    }
                });
            }
        };
        joueur.destinationsProperty().addListener(cartesDestinationsListener);
    }
}
