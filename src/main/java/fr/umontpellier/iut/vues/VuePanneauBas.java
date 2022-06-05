package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import javafx.scene.layout.HBox;

public class VuePanneauBas extends HBox {

    private IJeu jeu;
    private VueChoix choix;
    private VueReglages reglages;

    public VuePanneauBas(IJeu jeu) {
        this.jeu = jeu;

        choix = new VueChoix(jeu);
        reglages = new VueReglages();

        this.getChildren().add(0, reglages);
        this.getChildren().add(1, choix);

        this.getChildren().get(0).autosize();
    }
}
