package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IDestination;
import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.rails.Destination;
import fr.umontpellier.iut.rails.Destinations;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class VueChoix extends HBox {

    private IJeu jeu;

    private Label action;
    private ChangeListener<String> stringChangeListener;
    private ListChangeListener<Destination> destinationListener;
    private ListChangeListener<Destinations> cartesWagonsVisiblesListener;

    private Button passer;

    private HBox cartesDestination;
    private HBox cartesWagonsVisibles;

    public VueChoix(IJeu jeu) {
        action = new Label();
        cartesDestination = new HBox();
        cartesWagonsVisibles = new HBox();
        passer = new Button("Passer");
        this.jeu = jeu;

        destinationListener = new ListChangeListener<Destination>() {
            @Override
            public void onChanged(Change<? extends Destination> change) {
                Platform.runLater(() -> {
                    change.next();
                    if (change.wasAdded()) {
                        cartesDestination.getChildren().add(new VueDestination(change.getAddedSubList().get(0)));
                    }
                    if (change.wasRemoved()) {
                        for (Destination carte : change.getRemoved()) {
                            cartesDestination.getChildren().removeIf(vueDestination -> carte.getNom().equals(vueDestination.getId()));
                        }
                    }
                });
            }
        };
        jeu.destinationsInitialesProperty().addListener(destinationListener);

        cartesWagonsVisiblesListener = new ListChangeListener<Destinations>() {
            @Override
            public void onChanged(Change<? extends Destinations> change) {
                Platform.runLater(() -> {
                    change.next();
                    if (change.wasAdded()) {
                        cartesWagonsVisibles.getChildren().add(new VueCarteWagon(change.getAddedSubList().get(0)));
                    }
                    if (change.wasRemoved()) {
                        for (Destinations carte : change.getRemoved()) {
                            cartesWagonsVisibles.getChildren().removeIf(vueCarteWagon -> carte.toString().equals(vueCarteWagon.getId()));
                        }
                    }
                });
            }
        };
        jeu.cartesWagonVisiblesProperty().addListener(cartesWagonsVisiblesListener);

        passer.setOnAction(click -> {
            System.out.println("Passer a été selectionné.");
            jeu.passerAEteChoisi();
        });

        getChildren().add(cartesDestination);
        getChildren().add(cartesWagonsVisibles);
        getChildren().add(passer);
    }

    public void createBindings() {

    }
}
