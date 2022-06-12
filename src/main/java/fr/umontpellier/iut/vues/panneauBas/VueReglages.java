package fr.umontpellier.iut.vues.panneauBas;

import fr.umontpellier.iut.vues.VueDuJeu;
import fr.umontpellier.iut.vues.VuesHorsJeu.VueRegles;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class VueReglages extends HBox {

    private Button regles;
    private ToggleButton masquer;
    private VueRegles vueRegles;
    private ImageView dezoom, zoom, rules;

    public VueReglages() {
        regles = new Button("Règles");
        masquer = new ToggleButton("Masquer");

        this.getStylesheets().add("css/styleGeneral.css");
        masquer.getStyleClass().add("boutonFS");
        regles.getStyleClass().add("boutonFS");

        zoom = new ImageView(new Image("images/ui/zoom.png"));
        dezoom = new ImageView(new Image("images/ui/dezoom.png"));
        rules = new ImageView(new Image("images/ui/regles.png"));

        masquer.setContentDisplay(ContentDisplay.TOP);
        regles.setContentDisplay(ContentDisplay.TOP);

        zoom.setFitWidth(30);
        zoom.setPreserveRatio(true);
        dezoom.setFitHeight(30);
        dezoom.setPreserveRatio(true);
        rules.setFitHeight(30);
        rules.setPreserveRatio(true);

        masquer.setGraphic(zoom);
        regles.setGraphic(rules);

        vueRegles = new VueRegles();

        creerBindings();

        this.getChildren().addAll(regles, masquer);

        styliser();
    }

    public void creerBindings() {
        regles.setOnAction(click -> {
            vueRegles.show();
        });

        masquer.setOnAction(click -> {
            if (masquer.isSelected()) {
                ((VueDuJeu) getScene().getRoot()).masquer();
                masquer.setText("Afficher");
                masquer.setGraphic(dezoom);
            } else {
                ((VueDuJeu) getScene().getRoot()).afficher();
                masquer.setText("Masquer");
                masquer.setGraphic(zoom);
            }
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
    }
}
