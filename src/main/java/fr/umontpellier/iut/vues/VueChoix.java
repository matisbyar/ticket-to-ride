package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.rails.Destination;
import fr.umontpellier.iut.rails.Destinations;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class VueChoix extends BorderPane {

    private IJeu jeu;

    private Label instruction;
    private ChangeListener<String> instructionListener;
    private ListChangeListener<Destination> destinationListener;
    private ListChangeListener<Destinations> cartesWagonsVisiblesListener;

    private Button passer;

    private HBox cartes;
    private HBox cartesDestination;
    private HBox cartesWagonsVisibles;

    public VueChoix(IJeu jeu) {
        this.jeu = jeu;

        instruction = new Label();
        cartesDestination = new HBox();
        cartesWagonsVisibles = new HBox();
        cartes = new HBox();
        passer = new Button("Passer");

        createBindings();

        cartes.getChildren().add(cartesDestination);
        cartes.getChildren().add(cartesWagonsVisibles);
        this.setTop(instruction);
        this.setCenter(cartes);
        this.setRight(passer);

        this.setPadding(new Insets(20.0));
    }

    public void createBindings() {
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

        instructionListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String ancienneInstruction, String nouvelleInstruction) {
                Platform.runLater(() -> {
                    instruction.setText(nouvelleInstruction);
                });
            }
        };
        jeu.instructionProperty().addListener(instructionListener);

        passer.setOnAction(click -> {
            System.out.println("Passer a été selectionné.");
            jeu.passerAEteChoisi();
        });
    }
}
