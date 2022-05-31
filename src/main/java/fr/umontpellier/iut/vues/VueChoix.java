package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.rails.Destination;
import fr.umontpellier.iut.rails.Destinations;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
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
    private Button piocheDestination;
    private Button piocheWagon;

    private HBox cartes;
    private HBox cartesDestination;
    private HBox cartesWagonsVisibles;

    public VueChoix(IJeu jeu) {
        instruction = new Label();
        cartesDestination = new HBox();
        cartesWagonsVisibles = new HBox();
        cartes = new HBox(15);
        passer = new Button("Passer");
        piocheDestination = new Button("Destinations");
        piocheWagon = new Button("Wagons");
        this.jeu = jeu;

        createBindings();

        piocheDestination.setOnAction(actionEvent -> {
            jeu.uneDestinationAEtePiochee();
        });

        piocheWagon.setOnAction(actionEvent -> {
            jeu.uneCarteWagonAEtePiochee();
        });

        cartes.getChildren().add(cartesDestination);
        cartes.getChildren().add(piocheDestination);
        cartes.getChildren().add(cartesWagonsVisibles);
        cartes.getChildren().add(piocheWagon);
        this.setTop(instruction);
        this.setCenter(cartes);
        this.setRight(passer);
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
                    if (change.getList().isEmpty()) {
                        piocheDestination.setVisible(true);
                        piocheWagon.setVisible(true);
                        cartesWagonsVisibles.setVisible(true);
                    }
                    if (!change.getList().isEmpty()) {
                        piocheDestination.setVisible(false);
                        piocheWagon.setVisible(false);
                        cartesWagonsVisibles.setVisible(false);

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
                            for (int i = 0; i < cartesWagonsVisibles.getChildren().size(); i++) {
                                if (carte.toString().equals(cartesWagonsVisibles.getChildren().get(i).getId())) {
                                    cartesWagonsVisibles.getChildren().remove(i);
                                    break;
                                }
                            }
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
