package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.vues.panneauBas.VueChoix;
import fr.umontpellier.iut.vues.panneauBas.VueReglages;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * Vue utilisée dans la partie basse du BorderPane de la VueDuJeu.
 */
public class VuePanneauDeControles extends GridPane {

    private IJeu jeu;
    private VueChoix choix;
    private VueReglages reglages;
    private Button passer;
    private StackPane conteneurPasser;

    public VuePanneauDeControles(IJeu jeu) {
        this.jeu = jeu;
        this.getStylesheets().add("/css/stylePanneau.css");
        this.getStyleClass().add("controles");

        choix = new VueChoix(jeu);
        reglages = new VueReglages();
        conteneurPasser = new StackPane();
        passer = new Button("Passer");

        creerBindings();

        conteneurPasser.getChildren().add(passer);
        conteneurPasser.setAlignment(Pos.CENTER);

        this.add(reglages, 0, 0);
        this.add(choix, 1, 0);
        this.add(conteneurPasser, 2, 0);

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
        this.setHgap(30);
    }
}
