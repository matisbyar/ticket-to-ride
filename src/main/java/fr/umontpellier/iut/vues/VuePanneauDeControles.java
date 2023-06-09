package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.vues.panneauBas.VueChoix;
import fr.umontpellier.iut.vues.panneauBas.VueReglages;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * Vue utilisée dans la partie basse du BorderPane de la VueDuJeu.
 */
public class VuePanneauDeControles extends HBox {

    private IJeu jeu;
    private VueChoix choix;
    private VueReglages reglages;
    private Button passer;
    private StackPane conteneurPasser;
    private ImageView imagePasser;

    public VuePanneauDeControles(IJeu jeu) {
        this.jeu = jeu;
        this.getStylesheets().add("/css/stylePanneau.css");
        this.getStyleClass().add("controles");

        choix = new VueChoix(jeu);
        reglages = new VueReglages();
        conteneurPasser = new StackPane();
        passer = new Button("Passer");
        imagePasser = new ImageView(new Image("images/ui/passer.png"));
        passer.setGraphic(imagePasser);
        passer.setContentDisplay(ContentDisplay.TOP);

        this.getStylesheets().add("/css/styleGeneral.css");
        passer.getStyleClass().add("boutonFS");

        creerBindings();

        conteneurPasser.getChildren().add(passer);
        conteneurPasser.setAlignment(Pos.CENTER);
        imagePasser.setFitHeight(30);
        imagePasser.setPreserveRatio(true);

        this.getChildren().add(reglages);
        this.getChildren().add(choix);
        this.getChildren().add(conteneurPasser);

        styliser();
    }

    public void creerBindings() {
        passer.setOnAction(click -> {
            System.out.println("Passer a été selectionné.");
            jeu.passerAEteChoisi();
        });
    }

    public void styliser() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(50);
    }
}
