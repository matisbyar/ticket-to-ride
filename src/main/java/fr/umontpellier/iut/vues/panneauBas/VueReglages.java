package fr.umontpellier.iut.vues.panneauBas;

import fr.umontpellier.iut.vues.VuesHorsJeu.VueRegles;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class VueReglages extends HBox {

    private Button pause, regles, logs;
    private VueRegles vueRegles;

    public VueReglages() {
        pause = new Button("Pause");
        regles = new Button("Règles");
        logs = new Button("Log");

        vueRegles = new VueRegles();

        creerBindings();

        this.getChildren().addAll(pause, regles, logs);

        styliser();
    }

    public void creerBindings() {
        regles.setOnAction(click -> {
            vueRegles.show();
        });
    }

    private void styliser() {
        // Padding
        this.setPadding(new Insets(20));

        // Spacing
        this.setSpacing(5);

        // Alignement
        this.setAlignment(Pos.CENTER);

        // Couleurs
        this.setStyle("-fx-border-color: gray");
        this.setStyle("-fx-background-color: white");
    }
}
