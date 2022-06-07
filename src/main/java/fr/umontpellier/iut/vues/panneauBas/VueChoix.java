package fr.umontpellier.iut.vues.panneauBas;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.rails.CouleurWagon;
import fr.umontpellier.iut.rails.Destination;
import fr.umontpellier.iut.vues.VueCarteWagon;
import fr.umontpellier.iut.vues.VueDestination;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VueChoix extends VBox {

    private IJeu jeu;

    private Label instruction;

    private Button piocheDestination;
    private Button piocheWagon;

    private HBox cartesDestination;
    private HBox cartesWagonsVisibles;

    private ChangeListener<String> instructionListener;
    private ListChangeListener<Destination> destinationListener;
    private ListChangeListener<CouleurWagon> cartesWagonsVisiblesListener;

    private HBox cartes;

    public VueChoix(IJeu jeu) {
        this.jeu = jeu;

        instruction = new Label();
        piocheDestination = new Button("Destinations");
        piocheWagon = new Button("Wagons");

        cartes = new HBox();
        cartesDestination = new HBox();
        cartesWagonsVisibles = new HBox();

        creerBindings();

        cartes.getChildren().addAll(cartesDestination, piocheDestination, cartesWagonsVisibles, piocheWagon);

        this.getChildren().addAll(instruction, cartes);

        styliser();
    }

    public void creerBindings() {
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
                        piocheDestination.setManaged(true);
                        piocheWagon.setManaged(true);
                        cartesWagonsVisibles.setManaged(true);
                    }
                    if (!change.getList().isEmpty()) {
                        piocheDestination.setVisible(false);
                        piocheWagon.setVisible(false);
                        cartesWagonsVisibles.setVisible(false);
                        piocheDestination.setManaged(false);
                        piocheWagon.setManaged(false);
                        cartesWagonsVisibles.setManaged(false);
                    }
                });
            }
        };
        jeu.destinationsInitialesProperty().addListener(destinationListener);

        cartesWagonsVisiblesListener = new ListChangeListener<CouleurWagon>() {
            @Override
            public void onChanged(Change<? extends CouleurWagon> change) {
                Platform.runLater(() -> {
                    cartesWagonsVisibles.getChildren().clear();
                    for (int i = 0; i<jeu.cartesWagonVisiblesProperty().size(); i++) {
                        cartesWagonsVisibles.getChildren().add(new VueCarteWagon(jeu.cartesWagonVisiblesProperty().get(i)));
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

        piocheDestination.setOnAction(actionEvent -> {
            jeu.uneDestinationAEtePiochee();
        });

        piocheWagon.setOnAction(actionEvent -> {
            jeu.uneCarteWagonAEtePiochee();
        });
    }

    public void styliser() {
        // Alignement
        this.setAlignment(Pos.CENTER);
        cartes.setAlignment(Pos.CENTER);

        // Spacing
        this.setSpacing(10);
        cartes.setSpacing(20);
        cartesDestination.setSpacing(10);
        cartesWagonsVisibles.setSpacing(10);
    }
}
