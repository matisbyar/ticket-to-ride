package fr.umontpellier.iut.vues;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class VueReglages extends HBox {

    private Button pause, regles, logs;
    private VueRegles vueRegles;

    public VueReglages() {
        pause = new Button("Pause");
        regles = new Button("Règles");
        logs = new Button("Logs");

        vueRegles = new VueRegles();

        this.getChildren().addAll(pause, regles, logs);
    }

    public void creerBindings() {
        regles.setOnAction(event -> {
            vueRegles.show();
        });
    }
}
